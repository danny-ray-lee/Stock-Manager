package io.github.danny.ray.stockmanager.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.github.danny.ray.stockmanager.dto.trade.TradeDto;
import io.github.danny.ray.stockmanager.dto.trade.TradeItem;
import io.github.danny.ray.stockmanager.dto.trade.TradePair;
import io.github.danny.ray.stockmanager.dto.transaction.TransactionDto;
import io.github.danny.ray.stockmanager.exception.FetchException;
import io.github.danny.ray.stockmanager.model.Positions;
import io.github.danny.ray.stockmanager.model.Transactions;
import io.github.danny.ray.stockmanager.model.enums.EnumTradeItemSort;
import io.github.danny.ray.stockmanager.model.enums.EnumTradeType;
import io.github.danny.ray.stockmanager.repository.TransactionRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final ModelMapper modelMapper;

    private final Map<Integer, Transactions> transactionCache = new ConcurrentHashMap<>();

    public TransactionService(TransactionRepository transactionRepository, ModelMapper modelMapper) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        transactionRepository.findAll().forEach(transaction -> transactionCache.put(transaction.getId(), transaction));
    }

    @Transactional
    public Transactions create(TradeDto dto, Positions position) {
        Transactions transaction = new Transactions()
                .setPosition(position)
                .setTradeType(dto.getTradeType())
                .setPrice(dto.getPrice())
                .setQuantity(dto.getQuantity())
                .setComment(dto.getComment());
        return save(transaction);
    }

    @Transactional
    public void delete(int id) {
        if (!transactionCache.containsKey(id)) {
            throw new FetchException("Transaction not found, ID: " + id);
        }
        transactionCache.remove(id);
        transactionRepository.deleteById(id);
    }

    public Optional<Transactions> get(int id) {
        return Optional.ofNullable(transactionCache.get(id));
    }

    public TransactionDto getDto(int id) {
        return get(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new FetchException("Transaction not found, ID: " + id));
    }

    public List<Transactions> getByPositionId(int positionId) {
        return new ArrayList<>(transactionCache.values().stream()
                .filter(transaction -> transaction.getPosition().getId() == positionId)
                .toList());
    }

    public List<TransactionDto> getDtoByPositionId(int positionId) {
        return convertToDto(getByPositionId(positionId));
    }

    public List<TradePair> getTradePair(int positionId, EnumTradeItemSort sortBy) {

        List<Transactions> transactions = getByPositionId(positionId);

        List<TradeItem> buyList = transactions.stream()
                .filter(transaction -> transaction.getTradeType() == EnumTradeType.BUY)
                .flatMap(transaction ->
                        IntStream.range(0, transaction.getQuantity())
                                .mapToObj(ignore ->
                                        new TradeItem(
                                                transaction.getPrice(),
                                                transaction.getCreatedAt()
                                        )
                                )
                )
                .collect(Collectors.toList());

        List<TradeItem> sellList = transactions.stream()
                .filter(transaction -> transaction.getTradeType() == EnumTradeType.SELL)
                .flatMap(transaction ->
                        IntStream.range(0, transaction.getQuantity())
                                .mapToObj(ignore ->
                                        new TradeItem(
                                                transaction.getPrice(),
                                                transaction.getCreatedAt()
                                        )
                                )
                )
                .collect(Collectors.toList());

        if (sortBy == EnumTradeItemSort.TIME) {
            buyList.sort(Comparator.comparing(TradeItem::tradeTime));
            sellList.sort(Comparator.comparing(TradeItem::tradeTime));
        } else if (sortBy == EnumTradeItemSort.PRICE) {
            buyList.sort(Comparator.comparing(TradeItem::price));
            sellList.sort(Comparator.comparing(TradeItem::price));
        }

        return IntStream.range(0, Math.max(buyList.size(), sellList.size()))
                .mapToObj(i -> new TradePair(
                        i < buyList.size() ? buyList.get(i) : null,
                        i < sellList.size() ? sellList.get(i) : null
                ))
                .toList();
    }

    private Transactions save(Transactions transaction) {
        Transactions newTransaction = transactionRepository.save(transaction);
        transactionCache.put(newTransaction.getId(), newTransaction);
        return newTransaction;
    }

    private TransactionDto convertToDto(Transactions transaction) {
        return modelMapper.map(transaction, TransactionDto.class)
                .setPositionId(transaction.getPosition().getId());
    }

    private List<TransactionDto> convertToDto(List<Transactions> transactions) {
        return transactions.stream()
                .map(this::convertToDto)
                .toList();
    }

}

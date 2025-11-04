package io.github.danny.ray.stockmanager.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import io.github.danny.ray.stockmanager.dto.position.PositionDto;
import io.github.danny.ray.stockmanager.dto.trade.TradeDto;
import io.github.danny.ray.stockmanager.dto.trade.TradePair;
import io.github.danny.ray.stockmanager.exception.FetchException;
import io.github.danny.ray.stockmanager.model.Positions;
import io.github.danny.ray.stockmanager.model.Products;
import io.github.danny.ray.stockmanager.model.Users;
import io.github.danny.ray.stockmanager.model.enums.EnumPositionDirection;
import io.github.danny.ray.stockmanager.model.enums.EnumPositionStatus;
import io.github.danny.ray.stockmanager.model.enums.EnumTradeItemSort;
import io.github.danny.ray.stockmanager.model.enums.EnumTradeType;
import io.github.danny.ray.stockmanager.model.fee.FeePlan;
import io.github.danny.ray.stockmanager.repository.PositionRepository;
import io.github.danny.ray.stockmanager.utils.BigDecimalUtils;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    private final ProductService productService;

    private final FeePlanService feePlanService;

    private final TransactionService transactionService;

    private final ModelMapper modelMapper;

    private final Map<Integer, Positions> positionCache = new ConcurrentHashMap<>();

    public PositionService(
            PositionRepository positionRepository,
            ProductService productService,
            FeePlanService feePlanService,
            TransactionService transactionService,
            ModelMapper modelMapper
    ) {
        this.positionRepository = positionRepository;
        this.productService = productService;
        this.feePlanService = feePlanService;
        this.transactionService = transactionService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        positionRepository.findAll().forEach(position -> positionCache.put(position.getId(), position));
    }

    @Transactional
    public Positions create(TradeDto dto, Users user) {

        Products product = productService.get(dto.getProductId())
                .orElseThrow(() -> new FetchException("Product not found, ID: " + dto.getProductId()));

        FeePlan feePlan = feePlanService.get(dto.getFeePlanId())
                .orElseThrow(() -> new FetchException("Fee plan not found, ID: " + dto.getFeePlanId()));

        EnumPositionDirection direction = dto.getTradeType() == EnumTradeType.BUY
                ? EnumPositionDirection.LONG
                : EnumPositionDirection.SHORT;

        Positions position = new Positions()
                .setUser(user)
                .setProduct(product)
                .setFeePlan(feePlan)
                .setStatus(EnumPositionStatus.OPEN)
                .setDirection(direction)
                .setComment(dto.getComment());

        return save(position);
    }

    @Transactional
    public void updateMetrics(int positionId) {

        Positions position = get(positionId)
                .orElseThrow(() -> new FetchException("Position Not Found, Id: " + positionId));

        List<TradePair> tradePair = transactionService.getTradePair(position.getId(), EnumTradeItemSort.PRICE);

        updateFromTradePairs(position, tradePair);

        save(position);
    }

    @Transactional
    public void delete(int id) {
        if (!positionCache.containsKey(id)) {
            throw new FetchException("Position not found, ID: " + id);
        }
        positionRepository.deleteById(id);
        positionCache.remove(id);
    }

    public Optional<Positions> get(int id) {
        return Optional.ofNullable(positionCache.get(id));
    }

    public PositionDto getDto(int id) {
        return get(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new FetchException("Position not found, ID: " + id));
    }

    public List<Positions> getAll() {
        return new ArrayList<>(positionCache.values());
    }

    public List<PositionDto> getAllDto() {
        return convertToDtoList(getAll());
    }

    public Optional<Positions> getByUserAndProductAndStatusOpen(Integer userId, Integer productId) {
        return getAll().stream()
                .filter(position ->
                        position.getUser().getId() == userId &&
                                position.getProduct().getId() == productId &&
                                position.getStatus() == EnumPositionStatus.OPEN
                ).findFirst();
    }

    private Positions save(Positions position) {
        Positions newPosition = positionRepository.save(position);
        positionCache.put(newPosition.getId(), newPosition);
        return newPosition;
    }

    private void updateFromTradePairs(Positions position, List<TradePair> tradePairs) {
        List<TradePair> unpairedTrades = tradePairs.stream()
                .filter(TradePair::hasNoPair)
                .toList();

        if (!unpairedTrades.isEmpty()) {
            BigDecimal averageCost = calculateAverageCost(unpairedTrades);
            BigDecimal balance = calculateBalance(position.getDirection(), tradePairs, unpairedTrades);

            position.setQuantity(unpairedTrades.size())
                    .setAverageCost(averageCost)
                    .setBalanceCost(balance);
        }
    }

    private BigDecimal calculateAverageCost(List<TradePair> unpairedTrades) {

        List<BigDecimal> prices = unpairedTrades.stream()
                .map(TradePair::getNoPairPrice)
                .collect(Collectors.toList());

        return BigDecimalUtils.getAverage(prices);
    }

    private BigDecimal calculateBalance(EnumPositionDirection direction, List<TradePair> tradePairs, List<TradePair> unpairedTrades) {

        List<BigDecimal> prices = tradePairs.stream()
                .map(pair -> pair.getBalance(direction))
                .collect(Collectors.toList());

        return BigDecimalUtils.getAverage(prices, unpairedTrades.size());
    }

    private PositionDto convertToDto(Positions position) {
        return modelMapper.map(position, PositionDto.class)
                .setProductId(position.getProduct().getId())
                .setFeePlanId(position.getFeePlan().getId());
    }

    private List<PositionDto> convertToDtoList(Collection<Positions> feePlans) {
        return feePlans.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}

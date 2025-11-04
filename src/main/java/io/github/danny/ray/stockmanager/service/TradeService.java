package io.github.danny.ray.stockmanager.service;

import java.util.List;

import io.github.danny.ray.stockmanager.dto.trade.TradeDto;
import io.github.danny.ray.stockmanager.dto.trade.TradePair;
import io.github.danny.ray.stockmanager.model.Positions;
import io.github.danny.ray.stockmanager.model.Users;
import io.github.danny.ray.stockmanager.model.enums.EnumTradeItemSort;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    private final PositionService positionService;

    private final TransactionService transactionService;

    public TradeService(
            PositionService positionService,
            TransactionService transactionService
    ) {
        this.positionService = positionService;
        this.transactionService = transactionService;
    }

    public void trade(TradeDto dto, Users currentUser) {

        Positions position = positionService.getByUserAndProductAndStatusOpen(currentUser.getId(), dto.getProductId())
                .orElseGet(() -> positionService.create(dto, currentUser));

        transactionService.create(dto, position);

        positionService.updateMetrics(position.getId());

    }

    public List<TradePair> getTradePairs(int positionId) {
        return transactionService.getTradePair(positionId, EnumTradeItemSort.PRICE);
    }

    public List<TradePair> getTradePairs(int positionId, EnumTradeItemSort sortBy) {
        return transactionService.getTradePair(positionId, sortBy);
    }

}

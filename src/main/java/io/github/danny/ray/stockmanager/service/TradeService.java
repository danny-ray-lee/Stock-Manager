package io.github.danny.ray.stockmanager.service;

import io.github.danny.ray.stockmanager.dto.trade.TradeDto;
import io.github.danny.ray.stockmanager.exception.FetchException;
import io.github.danny.ray.stockmanager.model.Positions;
import io.github.danny.ray.stockmanager.model.Products;
import io.github.danny.ray.stockmanager.model.Transactions;
import io.github.danny.ray.stockmanager.model.Users;
import io.github.danny.ray.stockmanager.model.enums.EnumPositionDirection;
import io.github.danny.ray.stockmanager.model.enums.EnumPositionStatus;
import io.github.danny.ray.stockmanager.model.fee.FeePlan;
import io.github.danny.ray.stockmanager.repository.FeePlanRepository;
import io.github.danny.ray.stockmanager.repository.PositionRepository;
import io.github.danny.ray.stockmanager.repository.ProductRepository;
import io.github.danny.ray.stockmanager.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    private final FeePlanRepository feePlanRepository;

    private final ProductRepository productRepository;

    private final PositionRepository positionRepository;

    private final TransactionRepository transactionRepository;

    public TradeService(FeePlanRepository feePlanRepository, ProductRepository productRepository, PositionRepository positionRepository, TransactionRepository transactionRepository) {
        this.feePlanRepository = feePlanRepository;
        this.productRepository = productRepository;
        this.positionRepository = positionRepository;
        this.transactionRepository = transactionRepository;
    }

    public void trade(TradeDto dto, Users currentUsers) {

        FeePlan feePlan = feePlanRepository.findById(dto.getFeePlanId())
                .orElseThrow(() -> new FetchException("Fee plan not found, ID: " + dto.getFeePlanId()));

        Products products = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new FetchException("Product not found, ID: " + dto.getProductId()));

        Positions position = positionRepository.findByUserAndProductAndStatusOpen(currentUsers, products)
                .orElseGet(() -> {
                    Positions newPositions = new Positions()
                            .setUser(currentUsers)
                            .setProduct(products)
                            .setFeePlan(feePlan)
                            .setStatus(EnumPositionStatus.OPEN)
                            .setComment(dto.getComment());
                    switch (dto.getTradeType()) {
                        case BUY:
                            newPositions.setDirection(EnumPositionDirection.LONG);
                            break;
                        case SELL:
                            newPositions.setDirection(EnumPositionDirection.SHORT);
                            break;
                    }
                    return newPositions;
                });

        Transactions transaction = new Transactions()
                .setPositions(position)
                .setTradeType(dto.getTradeType())
                .setPrice(dto.getPrice())
                .setQuantity(dto.getQuantity())
                .setComment(dto.getComment());

        transactionRepository.save(transaction);


        positionRepository.save(position);
    }


}

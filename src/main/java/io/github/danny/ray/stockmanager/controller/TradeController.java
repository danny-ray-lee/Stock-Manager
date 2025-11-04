package io.github.danny.ray.stockmanager.controller;

import java.util.List;

import io.github.danny.ray.lib.response.dto.BaseResult;
import io.github.danny.ray.stockmanager.dto.trade.TradeDto;
import io.github.danny.ray.stockmanager.dto.trade.TradePair;
import io.github.danny.ray.stockmanager.exception.UnauthorizedException;
import io.github.danny.ray.stockmanager.model.enums.EnumTradeItemSort;
import io.github.danny.ray.stockmanager.security.UserDetailsDto;
import io.github.danny.ray.stockmanager.service.TradeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 交易
 */
@RestController
@RequestMapping("/api/trade")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    /**
     * 根據部位ID查詢
     *
     * @param positionId 部位ID
     * @return 交易紀錄
     */
    @GetMapping("/{id}")
    public BaseResult<List<TradePair>> getRecord(
            @PathVariable("id") int positionId,
            @RequestParam("sort") EnumTradeItemSort sortBy
    ) {
        return BaseResult.ok(tradeService.getTradePairs(positionId, sortBy));
    }

    /**
     * 新增
     *
     * @param dto 交易資訊
     */
    @PostMapping
    public BaseResult<Void> trade(
            @RequestBody TradeDto dto,
            @AuthenticationPrincipal UserDetailsDto currentUser
    ) {

        if (currentUser == null) {
            throw new UnauthorizedException("Has no any authentication");
        }

        tradeService.trade(dto, currentUser.users());

        return BaseResult.ok("Trade Success");
    }

}

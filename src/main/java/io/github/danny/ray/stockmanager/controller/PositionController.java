package io.github.danny.ray.stockmanager.controller;

import java.util.List;

import io.github.danny.ray.lib.response.dto.BaseResult;
import io.github.danny.ray.stockmanager.dto.position.PositionDto;
import io.github.danny.ray.stockmanager.service.PositionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部位
 */
@RestController
@RequestMapping("/api/position")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    /**
     * 根據ID查詢
     *
     * @param id 部位ID
     * @return 部位資訊
     */
    @GetMapping("/{id}")
    public BaseResult<PositionDto> get(@PathVariable int id) {
        return BaseResult.ok(positionService.getDto(id));
    }

    /**
     * 查詢全部
     *
     * @return 部位資訊列表
     */
    @GetMapping
    public BaseResult<List<PositionDto>> getAll() {
        return BaseResult.ok(positionService.getAllDto());
    }

    /**
     * 刪除
     *
     * @param id 部位ID
     */
    @DeleteMapping("/{id}")
    public BaseResult<Void> delete(@PathVariable int id) {
        positionService.delete(id);
        return BaseResult.ok("Deleted Success, ID: " + id);
    }

    /**
     * 刷新Metrics
     *
     * @param id 部位ID
     */
    @GetMapping("/{id}/refresh")
    public BaseResult<Void> refreshMetrics(@PathVariable("id") int id) {
        positionService.updateMetrics(id);
        return BaseResult.ok("Refresh Success, ID: " + id);
    }

}

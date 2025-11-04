package io.github.danny.ray.stockmanager.controller;

import java.util.List;

import io.github.danny.ray.lib.response.dto.BaseResult;
import io.github.danny.ray.stockmanager.dto.fee.FeePlanDto;
import io.github.danny.ray.stockmanager.service.FeePlanService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 手續費
 */
@RestController
@RequestMapping("/api/fee")
public class FeePlanController {

    private final FeePlanService feePlanService;

    public FeePlanController(FeePlanService feePlanService) {
        this.feePlanService = feePlanService;
    }

    /**
     * 根據ID查詢
     *
     * @param id 手續費ID
     * @return 手續費資訊
     */
    @GetMapping("/{id}")
    public BaseResult<FeePlanDto> get(@PathVariable("id") int id) {
        return BaseResult.ok(feePlanService.getDto(id));
    }

    /**
     * 查詢全部
     *
     * @return 手續費資訊列表
     */
    @GetMapping
    public BaseResult<List<FeePlanDto>> getAll() {
        return BaseResult.ok(feePlanService.getAllDto());
    }


    /**
     * 新增或更新
     *
     * @param dto 手續費DTO
     */
    @PostMapping
    public BaseResult<Void> createOrUpdate(@RequestBody FeePlanDto dto) {
        feePlanService.createOrUpdate(dto);
        return BaseResult.ok("Created Success");
    }

    /**
     * 刪除
     *
     * @param id 手續費ID
     */
    @DeleteMapping("/{id}")
    public BaseResult<Void> delete(@PathVariable("id") int id) {
        feePlanService.delete(id);
        return BaseResult.ok("Deleted Success, ID: " + id);
    }

}

package io.github.danny.ray.stockmanager.controller;

import java.util.List;

import io.github.danny.ray.lib.response.dto.BaseResult;
import io.github.danny.ray.stockmanager.dto.fee.FeePlanDto;
import io.github.danny.ray.stockmanager.model.fee.FeePlan;
import io.github.danny.ray.stockmanager.model.fee.FutureFeePlan;
import io.github.danny.ray.stockmanager.model.fee.StockFeePlan;
import io.github.danny.ray.stockmanager.service.FeePlanService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fee")
public class FeeController {

    private final FeePlanService feePlanService;

    private final ModelMapper modelMapper;

    public FeeController(FeePlanService feePlanService, ModelMapper modelMapper) {
        this.feePlanService = feePlanService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public BaseResult<List<FeePlanDto>> fetchFeePlan() {
        List<FeePlanDto> dtoList = feePlanService.fetchAllFeePlans()
                .stream().map(feePlan ->
                        modelMapper.map(feePlan, FeePlanDto.class)
                ).toList();
        return BaseResult.ok(dtoList);
    }

    @PostMapping("/upsert")
    public BaseResult<Void> createOrUpdateFeePlan(@RequestBody FeePlanDto dto) {
        FeePlan feePlan = switch (dto.getType()) {
            case STOCK -> modelMapper.map(dto, StockFeePlan.class);
            case FUTURE -> modelMapper.map(dto, FutureFeePlan.class);
        };
        feePlanService.createOrUpdateFeePlan(feePlan);
        return BaseResult.ok("Created Success");
    }

    @DeleteMapping("/{id}")
    public BaseResult<Void> deleteFeePlan(@PathVariable("id") int id) {
        feePlanService.deleteFeePlan(id);
        return BaseResult.ok("Deleted Success");
    }

}

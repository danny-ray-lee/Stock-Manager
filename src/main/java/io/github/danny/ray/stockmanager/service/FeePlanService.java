package io.github.danny.ray.stockmanager.service;

import java.util.Comparator;
import java.util.List;

import io.github.danny.ray.stockmanager.dto.fee.FeePlanDto;
import io.github.danny.ray.stockmanager.model.enums.EnumProductType;
import io.github.danny.ray.stockmanager.model.fee.FeePlan;
import io.github.danny.ray.stockmanager.model.fee.FutureFeePlan;
import io.github.danny.ray.stockmanager.model.fee.StockFeePlan;
import io.github.danny.ray.stockmanager.repository.FeePlanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FeePlanService {

    private final FeePlanRepository feePlanRepository;

    private final ModelMapper modelMapper;

    public FeePlanService(FeePlanRepository feePlanRepository, ModelMapper modelMapper) {
        this.feePlanRepository = feePlanRepository;
        this.modelMapper = modelMapper;
    }

    public void createOrUpdateFeePlan(FeePlanDto dto) {
        FeePlan feePlan = switch (dto.getType()) {
            case STOCK -> modelMapper.map(dto, StockFeePlan.class);
            case FUTURE -> modelMapper.map(dto, FutureFeePlan.class);
        };
        feePlanRepository.save(feePlan);
    }

    public List<FeePlan> fetchAllFeePlans() {
        return feePlanRepository.findAll().stream()
                .sorted(Comparator.comparingInt(feePlan -> feePlan.getType().ordinal()))
                .toList();
    }

    public void deleteFeePlan(int id) {
        feePlanRepository.deleteById(id);
    }

    public List<FeePlan> fetchFeePlan(EnumProductType type) {
        return feePlanRepository.findByType(type);
    }

}

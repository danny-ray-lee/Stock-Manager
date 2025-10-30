package io.github.danny.ray.stockmanager.service;

import java.util.Comparator;
import java.util.List;

import io.github.danny.ray.stockmanager.model.enums.EnumProductType;
import io.github.danny.ray.stockmanager.model.fee.FeePlan;
import io.github.danny.ray.stockmanager.repository.FeePlanRepository;
import org.springframework.stereotype.Service;

@Service
public class FeePlanService {

    private final FeePlanRepository feePlanRepository;

    public FeePlanService(FeePlanRepository feePlanRepository) {
        this.feePlanRepository = feePlanRepository;
    }

    public void createOrUpdateFeePlan(FeePlan feePlan) {
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

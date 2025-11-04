package io.github.danny.ray.stockmanager.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import io.github.danny.ray.stockmanager.dto.fee.FeePlanDto;
import io.github.danny.ray.stockmanager.exception.FetchException;
import io.github.danny.ray.stockmanager.model.enums.EnumProductType;
import io.github.danny.ray.stockmanager.model.fee.FeePlan;
import io.github.danny.ray.stockmanager.model.fee.FutureFeePlan;
import io.github.danny.ray.stockmanager.model.fee.StockFeePlan;
import io.github.danny.ray.stockmanager.repository.FeePlanRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FeePlanService {

    private final FeePlanRepository feePlanRepository;

    private final ModelMapper modelMapper;

    private final Map<Integer, FeePlan> feePlanCache = new ConcurrentHashMap<>();

    public FeePlanService(FeePlanRepository feePlanRepository, ModelMapper modelMapper) {
        this.feePlanRepository = feePlanRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        feePlanRepository.findAll().forEach(feePlan -> feePlanCache.put(feePlan.getId(), feePlan));
    }

    @Transactional
    public void createOrUpdate(FeePlanDto dto) {

        FeePlan feePlan = dto.getId() == 0
                ? create(dto)
                : updateExists(dto);

        save(feePlan);

    }

    @Transactional
    public void delete(int id) {
        if (!feePlanCache.containsKey(id)) {
            throw new FetchException("FeePlan not found, ID: " + id);
        }
        feePlanCache.remove(id);
        feePlanRepository.deleteById(id);
    }

    public Optional<FeePlan> get(int id) {
        return Optional.ofNullable(feePlanCache.get(id));
    }

    public FeePlanDto getDto(int id) {
        return get(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new FetchException("FeePlan not found, ID: " + id));
    }

    public List<FeePlan> getAll() {
        return new ArrayList<>(feePlanCache.values());
    }

    public List<FeePlanDto> getAllDto() {
        return convertToDtoList(getAll());
    }

    public List<FeePlan> getAllByType(EnumProductType type) {
        return getAll().stream()
                .filter(feePlan -> feePlan.getType() == type)
                .collect(Collectors.toList());
    }

    public List<FeePlanDto> getAllDtoByType(EnumProductType type) {
        return convertToDtoList(getAllByType(type));
    }

    private FeePlan save(FeePlan feePlan) {
        FeePlan newFeePlan = feePlanRepository.save(feePlan);
        feePlanCache.put(newFeePlan.getId(), newFeePlan);
        return newFeePlan;
    }

    private FeePlan create(FeePlanDto dto) {
        return switch (dto.getType()) {
            case STOCK -> modelMapper.map(dto, StockFeePlan.class);
            case FUTURE -> modelMapper.map(dto, FutureFeePlan.class);
        };
    }

    private FeePlan updateExists(FeePlanDto dto) {
        FeePlan existing = get(dto.getId())
                .orElseThrow(() -> new FetchException("FeePlan not found, ID: " + dto.getId()));

        modelMapper.map(dto, existing);
        return existing;
    }

    private FeePlanDto convertToDto(FeePlan feePlan) {
        return modelMapper.map(feePlan, FeePlanDto.class);
    }

    private List<FeePlanDto> convertToDtoList(Collection<FeePlan> feePlans) {
        return feePlans.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}

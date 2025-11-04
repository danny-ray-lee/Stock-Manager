package io.github.danny.ray.stockmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import io.github.danny.ray.stockmanager.dto.product.ProductDto;
import io.github.danny.ray.stockmanager.exception.FetchException;
import io.github.danny.ray.stockmanager.model.Products;
import io.github.danny.ray.stockmanager.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    private final Map<Integer, Products> productCache = new ConcurrentHashMap<>();

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        productRepository.findAll().forEach(product -> productCache.put(product.getId(), product));
    }

    @Transactional
    public void createOrUpdate(ProductDto dto) {
        Products product = dto.getId() == 0
                ? getBySymbol(dto.getSymbol()).map(p -> updateExists(dto)).orElseGet(() -> create(dto))
                : updateExists(dto);

        save(product);
    }

    @Transactional
    public void delete(int id) {
        if (!productCache.containsKey(id)) {
            throw new FetchException("Product not found, ID: " + id);
        }
        productCache.remove(id);
        productRepository.deleteById(id);
    }

    public Optional<Products> get(int id) {
        return Optional.ofNullable(productCache.get(id));
    }

    public ProductDto getDto(int id) {
        return get(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new FetchException("Product not found, ID: " + id));
    }

    public Optional<Products> getBySymbol(String symbol) {
        return productCache.values().stream()
                .filter(product -> product.getSymbol().equals(symbol))
                .findFirst();
    }

    public List<Products> getAll() {
        return new ArrayList<>(productCache.values());
    }

    public List<ProductDto> getAllDto() {
        return convertToDto(getAll());
    }

    private Products save(Products product) {
        Products newProduct = productRepository.save(product);
        productCache.put(newProduct.getId(), newProduct);
        return newProduct;
    }

    private Products create(ProductDto dto) {
        return modelMapper.map(dto, Products.class);
    }

    private Products updateExists(ProductDto dto) {
        Products existing = get(dto.getId())
                .orElseThrow(() -> new FetchException("Products not found, ID: " + dto.getId()));
        modelMapper.map(dto, existing);
        return existing;
    }

    private ProductDto convertToDto(Products product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private List<ProductDto> convertToDto(List<Products> products) {
        return products.stream().map(this::convertToDto).toList();
    }
}

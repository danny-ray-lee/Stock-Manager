package io.github.danny.ray.stockmanager.service;

import io.github.danny.ray.stockmanager.dto.product.ProductDto;
import io.github.danny.ray.stockmanager.model.Product;
import io.github.danny.ray.stockmanager.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public void createOrUpdateProduct(ProductDto dto) {
        Product product = new Product();
        modelMapper.map(dto, product);
        productRepository.save(product);
    }
}

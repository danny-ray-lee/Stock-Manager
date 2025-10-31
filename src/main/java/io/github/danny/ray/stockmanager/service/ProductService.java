package io.github.danny.ray.stockmanager.service;

import java.util.List;
import java.util.Optional;

import io.github.danny.ray.stockmanager.dto.product.ProductDto;
import io.github.danny.ray.stockmanager.exception.NotFoundException;
import io.github.danny.ray.stockmanager.model.Products;
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

    public Products fetchProduct(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found, Id: " + id));
    }

    public List<Products> fetchAllProducts() {
        return productRepository.findAll();
    }

    public ProductDto createOrUpdateProduct(ProductDto dto) {
        Products products = modelMapper.map(dto, Products.class);
        Optional<Products> productOption = productRepository.findBySymbol(dto.getSymbol());
        if (productOption.isPresent()) {
            products.setId(productOption.get().getId());
        }
        products = productRepository.save(products);
        return modelMapper.map(products, ProductDto.class);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}

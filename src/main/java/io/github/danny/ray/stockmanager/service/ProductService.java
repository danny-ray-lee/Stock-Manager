package io.github.danny.ray.stockmanager.service;

import java.util.List;
import java.util.Optional;

import io.github.danny.ray.stockmanager.exception.NotFoundException;
import io.github.danny.ray.stockmanager.model.Product;
import io.github.danny.ray.stockmanager.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product fetchProduct(int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found, Id: " + id));
    }

    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    public Product createOrUpdateProduct(Product product) {
        Optional<Product> existingProduct = productRepository.findBySymbol(product.getSymbol());
        existingProduct.ifPresent(value -> product.setId(value.getId()));
        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}

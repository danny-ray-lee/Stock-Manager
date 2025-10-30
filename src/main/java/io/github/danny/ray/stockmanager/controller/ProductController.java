package io.github.danny.ray.stockmanager.controller;

import java.util.List;

import io.github.danny.ray.lib.response.dto.BaseResult;
import io.github.danny.ray.stockmanager.dto.product.ProductDto;
import io.github.danny.ray.stockmanager.model.Product;
import io.github.danny.ray.stockmanager.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public BaseResult<ProductDto> fetchProduct(@PathVariable int id) {
        Product product = productService.fetchProduct(id);
        return BaseResult.ok(modelMapper.map(product, ProductDto.class));
    }

    @GetMapping("/all")
    public BaseResult<List<ProductDto>> fetchAllProducts() {
        List<ProductDto> dto = productService.fetchAllProducts()
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return BaseResult.ok(dto);
    }

    @PostMapping
    public BaseResult<ProductDto> createOrUpdateProduct(@RequestBody ProductDto dto) {
        Product product = modelMapper.map(dto, Product.class);
        product = productService.createOrUpdateProduct(product);
        return BaseResult.ok(modelMapper.map(product, ProductDto.class));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }
}

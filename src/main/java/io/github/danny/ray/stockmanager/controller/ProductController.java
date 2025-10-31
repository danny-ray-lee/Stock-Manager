package io.github.danny.ray.stockmanager.controller;

import java.util.List;

import io.github.danny.ray.lib.response.dto.BaseResult;
import io.github.danny.ray.stockmanager.dto.product.ProductDto;
import io.github.danny.ray.stockmanager.model.Products;
import io.github.danny.ray.stockmanager.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    /**
     * 查詢商品
     *
     * @param id 商品ID
     * @return 商品資訊
     */
    @GetMapping("/{id}")
    public BaseResult<ProductDto> fetchProduct(@PathVariable int id) {
        Products products = productService.fetchProduct(id);
        return BaseResult.ok(modelMapper.map(products, ProductDto.class));
    }

    /**
     * 查詢所有商品
     *
     * @return 商品資訊列表
     */
    @GetMapping("/all")
    public BaseResult<List<ProductDto>> fetchAllProducts() {
        List<ProductDto> dto = productService.fetchAllProducts()
                .stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return BaseResult.ok(dto);
    }

    /**
     * 新增或更新商品
     *
     * @param dto 商品資訊
     * @return 商品資訊
     */
    @PostMapping
    public BaseResult<ProductDto> createOrUpdateProduct(@RequestBody ProductDto dto) {
        ProductDto newProduct = productService.createOrUpdateProduct(dto);
        return BaseResult.ok(modelMapper.map(newProduct, ProductDto.class));
    }

    /**
     * 刪除商品
     *
     * @param id 商品ID
     */
    @DeleteMapping("/{id}")
    public BaseResult<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return BaseResult.ok("Deleted Success, ID: " + id);
    }
}

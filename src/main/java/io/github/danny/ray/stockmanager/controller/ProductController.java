package io.github.danny.ray.stockmanager.controller;

import java.util.List;

import io.github.danny.ray.lib.response.dto.BaseResult;
import io.github.danny.ray.stockmanager.dto.product.ProductDto;
import io.github.danny.ray.stockmanager.service.ProductService;
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

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 根據ID查詢
     *
     * @param id 商品ID
     * @return 商品資訊
     */
    @GetMapping("/{id}")
    public BaseResult<ProductDto> get(@PathVariable int id) {
        return BaseResult.ok(productService.getDto(id));
    }

    /**
     * 查詢全部
     *
     * @return 商品資訊列表
     */
    @GetMapping
    public BaseResult<List<ProductDto>> getAll() {
        return BaseResult.ok(productService.getAllDto());
    }

    /**
     * 新增或更新
     *
     * @param dto 商品資訊
     */
    @PostMapping
    public BaseResult<Void> createOrUpdate(@RequestBody ProductDto dto) {
        productService.createOrUpdate(dto);
        return BaseResult.ok("Created Success");
    }

    /**
     * 刪除
     *
     * @param id 商品ID
     */
    @DeleteMapping("/{id}")
    public BaseResult<Void> delete(@PathVariable int id) {
        productService.delete(id);
        return BaseResult.ok("Deleted Success, ID: " + id);
    }

}

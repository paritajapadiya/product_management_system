package com.productmangement.api.controller;

import com.productmangement.api.dto.ApiResponse;
import com.productmangement.api.dto.ProductRequestDTO;
import com.productmangement.api.model.EntityProduct;
import com.productmangement.api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@Tag(name = "Product API", description = "Operations related to products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService service) {
        this.productService = service;
    }

    @GetMapping
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<Page<EntityProduct>> getAllProducts(@PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<EntityProduct> allProducts = productService.getAllProducts(pageable);
        return ApiResponse.success(allProducts, "Success");
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<EntityProduct> getProductById(@PathVariable String id) {
        return ApiResponse.success(productService.getProductById(id), "product_create_success");
    }

    @PostMapping
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Add Product", description = "Only ADMIN users can add products.")
    public ApiResponse<EntityProduct> createProduct(@Validated @RequestBody ProductRequestDTO productRequestDTO) {
        return ApiResponse.success(productService.createProduct(productRequestDTO), "product_create_success");
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<EntityProduct> updateProduct(@PathVariable String id, @Validated @RequestBody ProductRequestDTO productRequestDTO) {
        return ApiResponse.success(productService.updateProduct(id, productRequestDTO), "product_update_success");
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "Bearer Authentication")
    public ApiResponse<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ApiResponse.success(null, "product_delete_success");

    }
}

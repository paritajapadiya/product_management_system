package com.productmangement.api.service;

import com.productmangement.api.dto.ProductRequestDTO;
import com.productmangement.api.exception.ProductNotFoundException;
import com.productmangement.api.mapper.ProductMapper;
import com.productmangement.api.model.EntityProduct;
import com.productmangement.api.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository repository, ProductMapper productMapper) {
        this.productRepository = repository;
        this.productMapper = productMapper;
    }

    public Page<EntityProduct> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public EntityProduct getProductById(String id) {
       return productRepository.findById(id)
               .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }

    public EntityProduct createProduct(ProductRequestDTO productRequestDTO) {
        EntityProduct product = productMapper.toEntity(productRequestDTO);
        return productRepository.save(product);
    }

    public EntityProduct updateProduct(String id, ProductRequestDTO productRequestDTO) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        EntityProduct product = productMapper.toEntity(productRequestDTO);
        product.setId(id);
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(id);
    }
}

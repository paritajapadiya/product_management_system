package com.productmangement.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productmangement.api.dto.ProductRequestDTO;
import com.productmangement.api.model.EntityProduct;
import com.productmangement.api.repository.UserRepository;
import com.productmangement.api.security.JwtRequestFilter;
import com.productmangement.api.security.JwtUtil;
import com.productmangement.api.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    // Test case for GET /api/product
    @Test
    public void testGetAllProducts() throws Exception {
        String validToken = jwtUtil.generateToken("testUser");
        mockMvc.perform(get("/api/product")
                        .header("Authorization", "Bearer "+validToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }

    // Test case for GET /api/product/{id}
    @Test
    public void testGetProductById() throws Exception {
        String productId = "123";

        // Generate a valid JWT token
        String validToken = jwtUtil.generateToken("testUser");
        mockMvc.perform(get("/api/product/{id}", productId)
                        .header("Authorization", "Bearer "+ validToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(productId));
    }

    // Test case for POST /api/product
    @Test
    public void testCreateProduct() throws Exception {
        ProductRequestDTO newProduct = new ProductRequestDTO();
        newProduct.setName("New Product");
        newProduct.setPrice(100.0);
        newProduct.setDescription("A new test product");
        String validToken = jwtUtil.generateToken("testUser");
        mockMvc.perform(post("/api/product")
                        .header("Authorization", "Bearer "+validToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("New Product"));
    }

    // Test case for PUT /api/product/{id}
    @Test
    public void testUpdateProduct() throws Exception {
        String productId = "1";
        ProductRequestDTO updatedProduct = new ProductRequestDTO();
        updatedProduct.setName("Updated Product");
        updatedProduct.setPrice(150.0);
        updatedProduct.setDescription("Updated description");
        String validToken = jwtUtil.generateToken("testUser");
        mockMvc.perform(put("/api/product/{id}", productId)
                        .header("Authorization", "Bearer "+validToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Updated Product"));

        String nonExistentProductId = "nonExistentProductId";

        mockMvc.perform(put("/api/product/" + nonExistentProductId)
                        .header("Authorization", "Bearer " + validToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isNotFound());
    }

    // Test case for DELETE /api/product/{id}
    @Test
    public void testDeleteProduct() throws Exception {
        String productId = "123";
        String validToken = jwtUtil.generateToken("testUser");
        mockMvc.perform(delete("/api/product/{id}", productId)
                        .header("Authorization", "Bearer "+validToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").doesNotExist());
    }
}

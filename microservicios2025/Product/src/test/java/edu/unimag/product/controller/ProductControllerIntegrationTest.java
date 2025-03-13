package edu.unimag.product.controller;

import edu.unimag.product.model.Product;
import edu.unimag.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @BeforeEach
//    void setUp() {
//        productRepository.deleteAll();
//    }
//
//    @Test
//    void testCreateProduct() throws Exception {
//        String productJson = "{ \"name\": \"Laptop\", \"description\": \"High-end gaming laptop\", \"price\": 1500, \"stock\": 10 }";
//
//        mockMvc.perform(post("/products")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(productJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Laptop"));
//    }
//
//    @Test
//    void testGetAllProducts() throws Exception {
//        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
//        productRepository.save(product);
//
//        mockMvc.perform(get("/products"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].name").value("Laptop"));
//    }
//
//    @Test
//    void testGetProductById() throws Exception {
//        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
//        product = productRepository.save(product);
//
//        mockMvc.perform(get("/products/" + product.getId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Laptop"));
//    }
//
//    @Test
//    void testGetProductByIdNotFound() throws Exception {
//        UUID id = UUID.randomUUID();
//        mockMvc.perform(get("/products/" + id))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void testUpdateProduct() throws Exception {
//        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
//        product = productRepository.save(product);
//
//        String updatedProductJson = "{ \"name\": \"Updated Laptop\", \"description\": \"Updated description\", \"price\": 1600, \"stock\": 5 }";
//
//        mockMvc.perform(put("/products/" + product.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(updatedProductJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Updated Laptop"));
//    }
//
//    @Test
//    void testDeleteProduct() throws Exception {
//        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
//        product = productRepository.save(product);
//
//        mockMvc.perform(delete("/products/" + product.getId()))
//                .andExpect(status().isNoContent());
//    }
}
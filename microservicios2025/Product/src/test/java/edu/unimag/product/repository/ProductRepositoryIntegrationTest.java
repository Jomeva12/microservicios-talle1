package edu.unimag.product.repository;

import edu.unimag.product.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class ProductRepositoryIntegrationTest {
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
//    void testSaveProduct() {
//        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
//        Product savedProduct = productRepository.save(product);
//
//        assertNotNull(savedProduct.getId());
//        assertEquals("Laptop", savedProduct.getName());
//    }
//
//    @Test
//    void testFindById() {
//        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
//        product = productRepository.save(product);
//
//        Optional<Product> foundProduct = productRepository.findById(product.getId());
//
//        assertTrue(foundProduct.isPresent());
//        assertEquals("Laptop", foundProduct.get().getName());
//    }
}
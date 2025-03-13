package edu.unimag.product.service;

import edu.unimag.product.model.Product;
import edu.unimag.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceIntegrationTest {

//    @Autowired
//    private ProductService productService;
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
//    void testCreateProduct() {
//        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
//        Product createdProduct = productService.createProduct(product);
//
//        assertNotNull(createdProduct.getId());
//        assertEquals("Laptop", createdProduct.getName());
//    }
//
//    @Test
//    void testGetAllProducts() {
//        Product product1 = new Product("Laptop", "High-end gaming laptop", 1500, 10);
//        Product product2 = new Product("Phone", "Smartphone", 800, 20);
//        productRepository.save(product1);
//        productRepository.save(product2);
//
//        List<Product> products = productService.getAllProducts();
//
//        assertEquals(2, products.size());
//    }
//
//    @Test
//    void testGetProductById() {
//        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
//        product = productRepository.save(product);
//
//        Optional<Product> foundProduct = productService.getProductById(product.getId());
//
//        assertTrue(foundProduct.isPresent());
//        assertEquals("Laptop", foundProduct.get().getName());
//    }
//
//    @Test
//    void testUpdateProduct() {
//        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
//        product = productRepository.save(product);
//
//        Product updatedProduct = new Product("Updated Laptop", "Updated description", 1600, 5);
//        Product result = productService.updateProduct(product.getId(), updatedProduct);
//
//        assertEquals("Updated Laptop", result.getName());
//    }
//
//    @Test
//    void testDeleteProduct() {
//        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
//        product = productRepository.save(product);
//
//        productService.deleteProduct(product.getId());
//
//        Optional<Product> deletedProduct = productRepository.findById(product.getId());
//        assertFalse(deletedProduct.isPresent());
//    }
}
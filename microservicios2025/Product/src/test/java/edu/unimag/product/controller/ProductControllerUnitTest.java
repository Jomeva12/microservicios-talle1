package edu.unimag.product.controller;

import edu.unimag.product.model.Product;
import edu.unimag.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductControllerUnitTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product product = Product.builder()
                .name("Laptop")
                .description("High-end gaming laptop")
                .price(1500.0)
                .stockQuantity(10)
                .build();

        when(productService.createProduct(any(Product.class))).thenReturn(product);

        // Act
        ResponseEntity<Product> response = productController.createProduct(product);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).createProduct(any(Product.class));
    }

    @Test
    void testGetProductById() {
        // Arrange
        UUID productId = UUID.randomUUID();
        Product product = Product.builder()
                .id(productId)
                .name("Laptop")
                .description("High-end gaming laptop")
                .price(1500.0)
                .stockQuantity(10)
                .build();

        when(productService.getProductById(productId)).thenReturn(Optional.of(product));

        // Act
        ResponseEntity<Product> response = productController.getProductById(productId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void testGetProductByIdNotFound() {
        // Arrange
        UUID productId = UUID.randomUUID();
        when(productService.getProductById(productId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Product> response = productController.getProductById(productId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void testGetAllProducts() {
        // Arrange
        Product product1 = Product.builder()
                .id(UUID.randomUUID())
                .name("Laptop")
                .description("High-end gaming laptop")
                .price(1500.0)
                .stockQuantity(10)
                .build();

        Product product2 = Product.builder()
                .id(UUID.randomUUID())
                .name("Smartphone")
                .description("Latest model smartphone")
                .price(800.0)
                .stockQuantity(20)
                .build();

        List<Product> products = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(products);

        // Act
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        UUID productId = UUID.randomUUID();
        Product product = Product.builder()
                .id(productId)
                .name("Laptop")
                .description("High-end gaming laptop")
                .price(1500.0)
                .stockQuantity(10)
                .build();

        when(productService.updateProduct(productId, product)).thenReturn(product);

        // Act
        ResponseEntity<Product> response = productController.updateProduct(productId, product);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).updateProduct(productId, product);
    }

    @Test
    void testUpdateProductNotFound() {
        // Arrange
        UUID productId = UUID.randomUUID();
        Product product = Product.builder()
                .id(productId)
                .name("Laptop")
                .description("High-end gaming laptop")
                .price(1500.0)
                .stockQuantity(10)
                .build();

        when(productService.updateProduct(productId, product)).thenThrow(new RuntimeException("Product not found"));

        // Act
        ResponseEntity<Product> response = productController.updateProduct(productId, product);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).updateProduct(productId, product);
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        UUID productId = UUID.randomUUID();
        when(productService.existsProduct(productId)).thenReturn(true);
        doNothing().when(productService).deleteProduct(productId);

        // Act
        ResponseEntity<Void> response = productController.deleteProduct(productId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(productService, times(1)).existsProduct(productId);
        verify(productService, times(1)).deleteProduct(productId);
    }

    @Test
    void testDeleteProductNotFound() {
        // Arrange
        UUID productId = UUID.randomUUID();
        when(productService.existsProduct(productId)).thenReturn(false);

        // Act
        ResponseEntity<Void> response = productController.deleteProduct(productId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(productService, times(1)).existsProduct(productId);
        verify(productService, never()).deleteProduct(productId);
    }

    @Test
    void testGetProductsByName() {
        // Arrange
        String productName = "Laptop";
        Product product1 = Product.builder()
                .id(UUID.randomUUID())
                .name("Laptop")
                .description("High-end gaming laptop")
                .price(1500.0)
                .stockQuantity(10)
                .build();

        Product product2 = Product.builder()
                .id(UUID.randomUUID())
                .name("Laptop")
                .description("Budget laptop")
                .price(500.0)
                .stockQuantity(15)
                .build();

        List<Product> products = Arrays.asList(product1, product2);

        when(productService.getProductsByName(productName)).thenReturn(products);

        // Act
        ResponseEntity<List<Product>> response = productController.getProductsByName(productName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
        verify(productService, times(1)).getProductsByName(productName);
    }
}
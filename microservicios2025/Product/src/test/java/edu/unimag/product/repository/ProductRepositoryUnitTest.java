package edu.unimag.product.repository;

import edu.unimag.product.model.Product;
import edu.unimag.product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductRepositoryUnitTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService; // Si tienes un servicio que usa el repositorio

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        // Arrange
        UUID productId = UUID.randomUUID();
        Product product = Product.builder()
                .id(productId)
                .name("Laptop")
                .description("High-end gaming laptop")
                .price(1500.0)
                .stockQuantity(10)
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> foundProduct = productRepository.findById(productId);

        // Assert
        assertTrue(foundProduct.isPresent());
        assertEquals(productId, foundProduct.get().getId());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testSaveProduct() {
        // Arrange
        Product product = Product.builder()
                .name("Laptop")
                .description("High-end gaming laptop")
                .price(1500.0)
                .stockQuantity(10)
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        Product savedProduct = productRepository.save(product);

        // Assert
        assertEquals(product, savedProduct);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testDeleteProduct() {
        // Arrange
        UUID productId = UUID.randomUUID();
        doNothing().when(productRepository).deleteById(productId);

        // Act
        productRepository.deleteById(productId);

        // Assert
        verify(productRepository, times(1)).deleteById(productId);
    }
}
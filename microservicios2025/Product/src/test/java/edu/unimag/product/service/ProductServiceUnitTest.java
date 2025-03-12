package edu.unimag.product.service;

import edu.unimag.product.model.Product;
import edu.unimag.product.repository.ProductRepository;
import edu.unimag.product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceUnitTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setPrice(100.0);
        product.setStockQuantity(10);

        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testGetProductById() {
        UUID id = UUID.randomUUID();
        Product product = new Product();
        product.setId(id);
        product.setName("Test Product");

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.getProductById(id);

        assertTrue(foundProduct.isPresent());
        assertEquals(id, foundProduct.get().getId());
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void testGetAllProducts() {
        Product product1 = new Product();
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setName("Product 2");

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testUpdateProduct() {
        UUID id = UUID.randomUUID();
        Product existingProduct = new Product();
        existingProduct.setId(id);
        existingProduct.setName("Existing Product");

        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");

        when(productRepository.existsById(id)).thenReturn(true);
        when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        Product result = productService.updateProduct(id, updatedProduct);

        assertNotNull(result);
        assertEquals("Updated Product", result.getName());
        verify(productRepository, times(1)).existsById(id);
        verify(productRepository, times(1)).save(updatedProduct);
    }

    @Test
    void testDeleteProduct() {
        UUID id = UUID.randomUUID();

        when(productRepository.existsById(id)).thenReturn(true);

        productService.deleteProduct(id);

        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void testGetProductsByName() {
        String name = "Test";
        Product product1 = new Product();
        product1.setName("Test Product 1");

        Product product2 = new Product();
        product2.setName("Test Product 2");

        when(productRepository.getProductsByName(name)).thenReturn(Arrays.asList(product1, product2));

        List<Product> products = productService.getProductsByName(name);

        assertEquals(2, products.size());
        verify(productRepository, times(1)).getProductsByName(name);
    }

    @Test
    void testExistsProduct() {
        UUID id = UUID.randomUUID();

        when(productRepository.existsById(id)).thenReturn(true);

        boolean exists = productService.existsProduct(id);

        assertTrue(exists);
        verify(productRepository, times(1)).existsById(id);
    }
}
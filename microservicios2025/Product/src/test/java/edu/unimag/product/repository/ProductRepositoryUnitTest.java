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
import static org.mockito.Mockito.*;

class ProductRepositoryUnitTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService; // O el servicio que estás probando

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        UUID id = UUID.randomUUID();
        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
        product.setId(id); // Asignar el ID al producto

        // Simular el comportamiento de findById
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Llamar al método findById del servicio
        Optional<Product> foundProduct = productService.getProductById(id);

        // Verificar que el producto fue encontrado
        assertTrue(foundProduct.isPresent());
        assertEquals("Laptop", foundProduct.get().getName());
    }
}
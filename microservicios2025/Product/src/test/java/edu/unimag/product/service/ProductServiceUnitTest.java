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

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
        when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertEquals(product, createdProduct);
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = Arrays.asList(
                new Product("Laptop", "High-end gaming laptop", 1500, 10),
                new Product("Phone", "Smartphone", 800, 20)
        );
        when(productRepository.findAll()).thenReturn(products);

        List<Product> allProducts = productService.getAllProducts();

        assertEquals(products, allProducts);
    }

    @Test
    void testGetProductById() {
        // Crear un ID y un objeto Product
        UUID id = UUID.randomUUID();
        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
        product.setId(id); // Asignar el ID al producto

        // Simular el comportamiento de findById
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Llamar al método getProductById del servicio
        Optional<Product> foundProduct = productService.getProductById(id);

        // Verificar que el producto devuelto sea el mismo que el simulado
        assertTrue(foundProduct.isPresent()); // Asegurarse de que el Optional no esté vacío
        assertEquals(product, foundProduct.get()); // Verificar que el producto sea el correcto
    }

    @Test
    void testUpdateProduct() {
        // Crear un ID y un objeto Product
        UUID id = UUID.randomUUID();
        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
        product.setId(id); // Asignar el ID al producto

        // Simular el comportamiento de findById
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Simular el comportamiento de save
        when(productRepository.save(product)).thenReturn(product);

        // Llamar al método updateProduct del servicio
        Product updatedProduct = productService.updateProduct(id, product);

        // Verificar que el producto devuelto sea el mismo que el simulado
        assertEquals(product, updatedProduct);
    }

    @Test
    void testDeleteProduct() {
        UUID id = UUID.randomUUID();
        doNothing().when(productRepository).deleteById(id);
        productService.deleteProduct(id);
        verify(productRepository, times(1)).deleteById(id);
    }
}
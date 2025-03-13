package edu.unimag.product.controller;

import edu.unimag.product.model.Product;
import edu.unimag.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        // Crear un objeto Product
        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);

        // Simular el comportamiento del servicio
        when(productService.createProduct(product)).thenReturn(product);

        // Llamar al método del controlador
        ResponseEntity<Product> response = productController.createProduct(product);

        // Verificar el código de estado y el cuerpo
        assertEquals(200, response.getStatusCodeValue()); // Verifica el código de estado HTTP
        assertEquals(product, response.getBody()); // Verifica el cuerpo de la respuesta
    }

    @Test
    void testGetAllProducts() {
        // Crear una lista de productos
        List<Product> products = Arrays.asList(
                new Product("Laptop", "High-end gaming laptop", 1500, 10),
                new Product("Phone", "Smartphone", 800, 20)
        );

        // Simular el comportamiento del servicio
        when(productService.getAllProducts()).thenReturn(products);

        // Llamar al método del controlador
        ResponseEntity<List<Product>> response = productController.getAllProducts();

        // Verificar el código de estado y el cuerpo
        assertEquals(200, response.getStatusCodeValue()); // Verifica el código de estado HTTP
        assertEquals(products, response.getBody()); // Verifica el cuerpo de la respuesta
    }

    @Test
    void testGetProductById() {
        UUID id = UUID.randomUUID();
        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
        when(productService.getProductById(id)).thenReturn(Optional.of(product));

        ResponseEntity<Product> response = productController.getProductById(id);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, response.getBody());
    }

    @Test
    void testGetProductByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(productService.getProductById(id)).thenReturn(Optional.empty());

        ResponseEntity<Product> response = productController.getProductById(id);

        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    void testUpdateProduct() {
        UUID id = UUID.randomUUID();
        Product product = new Product("Laptop", "High-end gaming laptop", 1500, 10);
        when(productService.updateProduct(id, product)).thenReturn(product);

        ResponseEntity<Product> response = productController.updateProduct(id, product);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(product, response.getBody());
    }

    @Test
    void testDeleteProduct() {
        UUID id = UUID.randomUUID();
        doNothing().when(productService).deleteProduct(id);

        ResponseEntity<Void> response = productController.deleteProduct(id);

        assertEquals(204, response.getStatusCodeValue());
    }
}
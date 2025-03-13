package edu.unimag.product.service.impl;

import edu.unimag.product.model.Product;
import edu.unimag.product.repository.ProductRepository;
import edu.unimag.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        product.setCreatedAt(new Date());
        product.setUpdatedAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return productRepository.findById(id); // Devuelve un Optional<Product>
    }

    @Override
    public Product updateProduct(UUID id, Product productDetails) {
        // Buscar el producto por ID
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")); // Lanza una excepción si no se encuentra

        // Actualizar los campos del producto
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());

        // Guardar el producto actualizado
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id); // Llama al método deleteById del repositorio
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(UUID.fromString(id));
    }

    @Override
    public Product updateProduct(String id, Product productDetails) {
        Product product = productRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());
        product.setUpdatedAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(UUID.fromString(id));
    }
}
package edu.unimag.product.service;



import edu.unimag.product.model.Product;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Product createProduct(Product product);
    List<Product> getAllProducts();
    Optional<Product> getProductById(UUID id);
    Product updateProduct(UUID id, Product productDetails);
    void deleteProduct(UUID id);

    Optional<Product> getProductById(String id);

    Product updateProduct(String id, Product productDetails);

    void deleteProduct(String id);
}
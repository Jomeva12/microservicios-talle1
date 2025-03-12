package edu.unimag.product.service;

import edu.unimag.product.model.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {


    Product createProduct(Product product);

    Optional<Product> getProductById(UUID id);

    List<Product> getAllProducts();

    Product updateProduct(UUID id, Product product);

    void deleteProduct(UUID id);


    List<Product> getProductsByName(String name);


    boolean existsProduct(UUID id);
}
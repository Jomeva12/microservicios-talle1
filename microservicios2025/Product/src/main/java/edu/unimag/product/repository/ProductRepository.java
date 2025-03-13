package edu.unimag.product.repository;

import edu.unimag.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends MongoRepository<Product, UUID> {
    // Puedes agregar métodos personalizados aquí si es necesario
}
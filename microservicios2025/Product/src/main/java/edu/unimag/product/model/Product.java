package edu.unimag.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection = "products") // Anotación de MongoDB
@Data // Lombok: Genera getters, setters, toString, equals, hashCode
@AllArgsConstructor // Lombok: Genera un constructor con todos los campos
public class Product {
    @Id // Anotación de MongoDB para el ID
    private UUID id; // Usamos UUID en lugar de String

    private String name;
    private String description;
    private double price;
    private int stock;
    private Date createdAt;
    private Date updatedAt;

    // Constructor vacío (necesario para Spring Data MongoDB)
    public Product() {
        this.id = UUID.randomUUID(); // Generar un UUID automáticamente
    }

    // Constructor con parámetros
    public Product(String name, String description, double price, int stock) {
        this.id = UUID.randomUUID(); // Generar un UUID automáticamente
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }
}
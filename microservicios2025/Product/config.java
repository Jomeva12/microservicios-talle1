package edu.unimag.product.config; // O en el paquete de pruebas

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class TestcontainersConfiguration {

    @Bean
    public MongoDBContainer mongoDBContainer() {
        // Crea un contenedor de MongoDB con la imagen más reciente
        MongoDBContainer container = new MongoDBContainer(DockerImageName.parse("mongo:latest"));
        container.start(); // Inicia el contenedor
        return container;
    }
}
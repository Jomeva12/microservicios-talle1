package edu.unimag.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.junit.jupiter.Testcontainers;

@Import(TestcontainersConfiguration.class) // Importa la configuración de Testcontainers
@SpringBootTest
@Testcontainers
class ProductApplicationTests {

    @Test
    void contextLoads() {
        // Esta prueba verifica que el contexto de Spring Boot se carga correctamente
    }
}

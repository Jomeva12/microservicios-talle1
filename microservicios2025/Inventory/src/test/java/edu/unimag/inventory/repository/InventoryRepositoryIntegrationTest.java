package edu.unimag.inventory.repository;

import edu.unimag.inventory.model.Inventory;
import edu.unimag.inventory.model.InventoryStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class InventoryRepositoryIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @Autowired
    private InventoryRepository inventoryRepository;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = Inventory.builder()
                .productId(UUID.randomUUID())
                .quantity(10)
                .status(InventoryStatus.IN_STOCK)
                .build();
        inventoryRepository.save(inventory);
    }

    @Test
    public void testFindByProductId() {
        // Act
        Optional<Inventory> found = inventoryRepository.findByProductId(inventory.getProductId());

        // Assert
        assertThat(found).isPresent();
        assertThat(found.get().getQuantity()).isEqualTo(10);
    }

    @Test
    public void testFindByStatus() {
        // Act
        List<Inventory> inStockItems = inventoryRepository.findByStatus(InventoryStatus.IN_STOCK);

        // Assert
        assertThat(inStockItems).hasSize(1);
        assertThat(inStockItems.get(0).getStatus()).isEqualTo(InventoryStatus.IN_STOCK);
    }
}
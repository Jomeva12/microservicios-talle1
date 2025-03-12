package edu.unimag.inventory.controller;

import edu.unimag.inventory.model.Inventory;
import edu.unimag.inventory.model.InventoryStatus;
import edu.unimag.inventory.service.InventoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InventoryControllerUnitTest {

    @Mock
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryController inventoryController;

    private Inventory inventory;

    @BeforeEach
    public void setUp() {
        inventory = Inventory.builder()
                .id(UUID.randomUUID())
                .productId(UUID.randomUUID())
                .quantity(10)
                .status(InventoryStatus.IN_STOCK)
                .build();
    }

    @Test
    public void testCreateInventory() {
        // Arrange
        when(inventoryService.createInventory(any(Inventory.class))).thenReturn(inventory);

        // Act
        ResponseEntity<Inventory> response = inventoryController.createInventory(inventory);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(inventory);
    }

    @Test
    public void testGetInventoryById() {
        // Arrange
        when(inventoryService.getInventoryById(inventory.getId())).thenReturn(Optional.of(inventory));

        // Act
        ResponseEntity<Inventory> response = inventoryController.getInventoryById(inventory.getId());

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(inventory);
    }

    @Test
    public void testGetInventoryById_NotFound() {
        // Arrange
        when(inventoryService.getInventoryById(any())).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Inventory> response = inventoryController.getInventoryById(UUID.randomUUID());

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
package edu.unimag.inventory.service;

import edu.unimag.inventory.model.Inventory;
import edu.unimag.inventory.model.InventoryStatus;
import edu.unimag.inventory.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceUnitTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

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
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        // Act
        Inventory created = inventoryService.createInventory(inventory);

        // Assert
        assertThat(created).isNotNull();
        assertThat(created.getQuantity()).isEqualTo(10);
        verify(inventoryRepository, times(1)).save(any(Inventory.class));
    }

    @Test
    public void testGetInventoryById() {
        // Arrange
        when(inventoryRepository.findById(inventory.getId())).thenReturn(Optional.of(inventory));

        // Act
        Optional<Inventory> found = inventoryService.getInventoryById(inventory.getId());

        // Assert
        assertThat(found).isPresent();
        assertThat(found.get().getQuantity()).isEqualTo(10);
        verify(inventoryRepository, times(1)).findById(inventory.getId());
    }

    @Test
    public void testUpdateInventory() {
        // Arrange
        when(inventoryRepository.existsById(inventory.getId())).thenReturn(true);
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        // Act
        Inventory updated = inventoryService.updateInventory(inventory.getId(), inventory);

        // Assert
        assertThat(updated).isNotNull();
        assertThat(updated.getQuantity()).isEqualTo(10);
        verify(inventoryRepository, times(1)).existsById(inventory.getId());
        verify(inventoryRepository, times(1)).save(any(Inventory.class));
    }

    @Test
    public void testDeleteInventory() {
        // Arrange
        doNothing().when(inventoryRepository).deleteById(inventory.getId());

        // Act
        inventoryService.deleteInventory(inventory.getId());

        // Assert
        verify(inventoryRepository, times(1)).deleteById(inventory.getId());
    }

    @Test
    public void testUpdateQuantity() {
        // Arrange
        when(inventoryRepository.findByProductId(inventory.getProductId())).thenReturn(Optional.of(inventory));
        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);

        // Act
        boolean updated = inventoryService.updateQuantity(inventory.getProductId(), 5);

        // Assert
        assertThat(updated).isTrue();
        assertThat(inventory.getQuantity()).isEqualTo(5);
        verify(inventoryRepository, times(1)).findByProductId(inventory.getProductId());
        verify(inventoryRepository, times(1)).save(any(Inventory.class));
    }
}
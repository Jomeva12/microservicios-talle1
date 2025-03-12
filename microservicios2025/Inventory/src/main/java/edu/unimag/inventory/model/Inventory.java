package edu.unimag.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@Entity
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
@Builder // <-- Agrega esta anotación
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "inventory_id", columnDefinition = "UUID")
    private UUID id;

    @Column(name = "product_id", nullable = false, columnDefinition = "UUID")
    private UUID productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private InventoryStatus status;
}
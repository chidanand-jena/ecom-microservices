package com.cnj.inventorysvc.service;

import com.cnj.inventorysvc.model.entity.InvStock;

import java.util.List;

public interface InventoryService {
    // Get inventory record by product ID
    InvStock getInventoryByProductId(Long productId);

    // Get all inventory records
    List<InvStock> getAllInventory();

    // Add or update inventory record
    InvStock saveInventory(InvStock invStock);

    // Update inventory quantities (e.g. add or subtract stock)
    InvStock updateInventory(Long productId, int quantityAvailableDelta);

    // Reserve stock for order/cart
    boolean reserveStock(Long productId, int quantity);

    // Release previously reserved stock (e.g. order cancelled)
    boolean releaseStock(Long productId, int quantity);

    // Deduct reserved stock after order completion (finalize sale)
    boolean deductStock(Long productId, int quantity);

    // Delete inventory record (if needed)
    void deleteInventory(Long productId);
}

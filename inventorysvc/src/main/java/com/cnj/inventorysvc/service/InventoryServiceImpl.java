package com.cnj.inventorysvc.service;

import com.cnj.inventorysvc.dao.InvReservationRepository;
import com.cnj.inventorysvc.dao.InvStockRepository;
import com.cnj.inventorysvc.model.entity.InvReservation;
import com.cnj.inventorysvc.model.entity.InvStock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.cnj.inventorysvc.exception.ProductNotFoundException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InvStockRepository invStockRepo;
    private final InvReservationRepository invReservationRepo;

    @Override
    public InvStock getInventoryByProductId(Long productId) {
        return invStockRepo.findByProductId(productId);
    }

    @Override
    public List<InvStock> getAllInventory() {
        return invStockRepo.findAll();
    }

    @Override
    public InvStock saveInventory(InvStock invStock) {
        return invStockRepo.save(invStock);
    }

    @Override
    public InvStock updateInventory(

            Long productId, int quantityAvailableDelta) {
        log.info("Updating inventory for productId={} by delta={}", productId, quantityAvailableDelta);

        InvStock invStockInDb = invStockRepo.findByProductId(productId)
                .orElseThrow(() -> {
                            throw new ProductNotFoundException("Product not found for id: " + productId);
                        }
                );
        Integer newStockQuantity= invStockInDb.getQuantityAvailable()+quantityAvailableDelta;
        if(newStockQuantity<0){
            log.warn("Insufficient inventory for productId={}, requested delta={}", productId, quantityAvailableDelta);
            throw new IllegalStateException("Insufficient inventory for product ID " + productId);

        }
        invStockInDb.setQuantityAvailable(newStockQuantity);
        log.debug("New inventory for productId={} is {}", productId, newStockQuantity);
try {
            return invStockRepo.save(invStockInDb);
        } catch (DataIntegrityViolationException e) {
            log.error("Data integrity violation while saving inventory for productId={}", productId, e);
            throw new RuntimeException("Failed to update inventory due to DB constraint violation");
        } catch (Exception e) {
            log.error("Unexpected error while saving inventory for productId={}", productId, e);
            throw new RuntimeException("Internal server error while updating inventory");
        }
    }




    @Override
    public boolean reserveStock(Long productId, int quantity) {
        return false;
    }

    @Override
    public boolean releaseStock(Long productId, int quantity) {
        return false;
    }

    @Override
    public boolean deductStock(Long productId, int quantity) {
        return false;
    }

    @Override
    public void deleteInventory(Long productId) {

    }
}

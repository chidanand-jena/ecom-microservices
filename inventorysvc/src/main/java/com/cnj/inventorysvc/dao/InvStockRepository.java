package com.cnj.inventorysvc.dao;

import com.cnj.inventorysvc.model.entity.InvStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvStockRepository extends JpaRepository<InvStock,Long> {
    Optional<InvStock> findByProductId(Long productId);
}

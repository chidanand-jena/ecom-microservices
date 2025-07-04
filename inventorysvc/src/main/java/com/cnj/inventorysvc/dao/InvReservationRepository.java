package com.cnj.inventorysvc.dao;

import com.cnj.inventorysvc.model.entity.InvReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvReservationRepository extends JpaRepository<InvReservation,Long> {
    InvReservation findByProductId(Long productId);
}

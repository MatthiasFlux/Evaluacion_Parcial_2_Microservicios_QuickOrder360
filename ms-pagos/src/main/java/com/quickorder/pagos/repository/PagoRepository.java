package com.quickorder.pagos.repository;

import com.quickorder.pagos.model.PagoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<PagoModel, Long> {
}

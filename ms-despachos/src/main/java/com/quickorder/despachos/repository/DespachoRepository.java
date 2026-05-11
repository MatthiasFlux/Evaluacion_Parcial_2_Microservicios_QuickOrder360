package com.quickorder.despachos.repository;

import com.quickorder.despachos.model.DespachoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespachoRepository extends JpaRepository<DespachoModel, Long> {
}

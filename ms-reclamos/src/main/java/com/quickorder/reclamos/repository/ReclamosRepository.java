package com.quickorder.reclamos.repository;

import com.quickorder.reclamos.model.ReclamosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReclamosRepository extends JpaRepository<ReclamosModel, Long> {
    List<ReclamosModel> findByClienteId(Long clienteId);
}

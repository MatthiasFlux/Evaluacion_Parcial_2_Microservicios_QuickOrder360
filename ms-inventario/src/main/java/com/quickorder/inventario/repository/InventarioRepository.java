package com.quickorder.inventario.repository;

import com.quickorder.inventario.model.InventarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioModel, Long> {
    Optional<InventarioModel> findByProductoId(Long productoId);
}

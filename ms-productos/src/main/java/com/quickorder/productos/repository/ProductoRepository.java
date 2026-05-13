package com.quickorder.productos.repository;

import com.quickorder.productos.model.ProductoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel, Long> {

}

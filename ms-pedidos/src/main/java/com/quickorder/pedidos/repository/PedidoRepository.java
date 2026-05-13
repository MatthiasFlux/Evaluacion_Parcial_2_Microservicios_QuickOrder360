package com.quickorder.pedidos.repository;

import com.quickorder.pedidos.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {

    List <PedidoModel> findByClienteId(Long clienteId);
}
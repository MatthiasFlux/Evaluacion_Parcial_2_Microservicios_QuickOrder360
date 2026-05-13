package com.quickorder.detalle_pedido.repository;

import com.quickorder.detalle_pedido.model.DetallePedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedidoModel, Long> {
    List<DetallePedidoModel> findByPedidoId(Long pedidoId);
}

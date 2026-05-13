package com.quickorder.pedidos.client;

import com.quickorder.pedidos.dto.ItemPedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "ms-detalle-pedido")
public interface DetallePedidoClient {

    @PostMapping("/api/v1/detalles/{pedidoId}")
    void crearDetalles(@PathVariable("pedidoId") Long pedidoId, @RequestBody List<ItemPedidoDTO> items);

}

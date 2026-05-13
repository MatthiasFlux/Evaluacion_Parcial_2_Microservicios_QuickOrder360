package com.quickorder.pedidos.client;

import com.quickorder.pedidos.dto.InventarioResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-inventario")
public interface InventarioClient {

    @GetMapping("/api/v1/inventario/verificar/{productoId}")
    InventarioResponseDTO verificarStock(
            @PathVariable("productoId") Long productoId,
            @RequestParam("cantidad") Integer cantidad
    );

}

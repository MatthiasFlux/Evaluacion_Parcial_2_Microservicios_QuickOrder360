package com.quickorder.inventario.service;

import com.quickorder.inventario.dto.InventarioResponseDTO;
import com.quickorder.inventario.model.InventarioModel;
import com.quickorder.inventario.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventarioService {

    private final InventarioRepository repository;

    public InventarioResponseDTO verificarStock(Long productoId, Integer cantidadRequerida) {
        log.info("Verificando stock para producto ID: {}", productoId);

        InventarioModel inventario = repository.findByProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no registrado en inventario"));

        InventarioResponseDTO response = new InventarioResponseDTO();
        response.setProductoId(productoId);
        response.setCantidadDisponible(inventario.getCantidadDisponible());
        response.setTieneStock(inventario.getCantidadDisponible() >= cantidadRequerida);

        return response;
    }

    public void reducirStock(Long productoId, Integer cantidad) {
        InventarioModel inventario = repository.findByProductoId(productoId)
                .orElseThrow(() -> new RuntimeException("No se encontró stock para producto: " + productoId));

        if (inventario.getCantidadDisponible() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }

        inventario.setCantidadDisponible(inventario.getCantidadDisponible() - cantidad);
        repository.save(inventario);
        log.info("Stock reducido para producto {}. Nuevo saldo: {}", productoId, inventario.getCantidadDisponible());
    }
}

package com.quickorder.inventario.controller;

import com.quickorder.inventario.dto.InventarioResponseDTO;
import com.quickorder.inventario.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService service;

    @GetMapping("/verificar/{productoId}")
    public InventarioResponseDTO verificar(@PathVariable Long productoId, @RequestParam Integer cantidad) {
        return service.verificarStock(productoId, cantidad);
    }

    @PostMapping("/reducir/{productoId}")
    public void reducir(@RequestParam Long productoId, @RequestParam Integer cantidad) {
        service.reducirStock(productoId, cantidad);
    }
}

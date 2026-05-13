package com.quickorder.productos.controller;

import com.quickorder.productos.dto.ProductoDetalleDTO;
import com.quickorder.productos.dto.ProductoValidacionRequestDTO;
import com.quickorder.productos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productos") // Agregamos v1 por estándar
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping("/{id}")
    public ProductoDetalleDTO obtenerPorId(@PathVariable Long id) {
        return productoService.obtenerUno(id);
    }

    // Endpoint para que Pedidos valide stock de varios productos
    @PostMapping("/validar")
    public List<ProductoDetalleDTO> validarStock(@RequestBody List<ProductoValidacionRequestDTO> request) {
        return productoService.validarYObtenerProductos(request);
    }

    // El endpoint que busca tu PedidoService (ms-inventario)
    @GetMapping("/verificar/{id}")
    public Boolean verificarStock(@PathVariable Long id) {
        return productoService.tieneStock(id);
    }
}



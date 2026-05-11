package com.quickorder.catalogo.controller;

import com.quickorder.catalogo.dto.ProductoDetalleDTO;
import com.quickorder.catalogo.dto.ProductoValidacionRequestDTO;
import com.quickorder.catalogo.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping("/validar")
    public ResponseEntity<List<ProductoDetalleDTO>> validarProductos(@RequestBody List<ProductoValidacionRequestDTO> peticionProductos) {
        List<ProductoDetalleDTO> respuesta = productoService.validarYObtenerProductos(peticionProductos);
        return ResponseEntity.ok(respuesta);
    }
}

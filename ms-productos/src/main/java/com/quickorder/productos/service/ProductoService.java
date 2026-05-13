package com.quickorder.productos.service;

import com.quickorder.productos.dto.ProductoDetalleDTO;
import com.quickorder.productos.dto.ProductoValidacionRequestDTO;
import com.quickorder.productos.model.ProductoModel;
import com.quickorder.productos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoDetalleDTO obtenerUno(Long id) {
        ProductoModel p = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return mapToDTO(p);
    }

    public Boolean tieneStock(Long id) {
        return productoRepository.findById(id)
                .map(p -> p.getStock() > 0)
                .orElse(false);
    }

    public List<ProductoDetalleDTO> validarYObtenerProductos(List<ProductoValidacionRequestDTO> requestList) {
        List<Long> ids = requestList.stream()
                .map(ProductoValidacionRequestDTO::getProductoId)
                .collect(Collectors.toList());

        return productoRepository.findAllById(ids).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ProductoDetalleDTO mapToDTO(ProductoModel p) {
        ProductoDetalleDTO dto = new ProductoDetalleDTO();
        dto.setId(p.getId());
        dto.setSku(p.getSku());
        dto.setPrecio(p.getPrecio());
        dto.setStockDisponible(p.getStock());
        return dto;
    }
}


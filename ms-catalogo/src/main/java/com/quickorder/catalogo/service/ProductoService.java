package com.quickorder.catalogo.service;

import com.quickorder.catalogo.dto.ProductoDetalleDTO;
import com.quickorder.catalogo.dto.ProductoValidacionRequestDTO;
import com.quickorder.catalogo.model.ProductoModel;
import com.quickorder.catalogo.repository.ProductoRepository;
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

    public List<ProductoDetalleDTO> validarYObtenerProductos(List<ProductoValidacionRequestDTO> requestList) {
        log.info("Procesando validación para {} productos", requestList.size());

        // 1. Extraemos solo los IDs de la petición
        List<Long> ids = requestList.stream()
                .map(ProductoValidacionRequestDTO::getProductoId)
                .collect(Collectors.toList());

        // 2. Buscamos los productos en la base de datos
        List<ProductoModel> productosEncontrados = productoRepository.findAllById(ids);

        // 3. Mapeamos de Entidad a DTO para responderle a Pedidos
        return productosEncontrados.stream().map(p -> {
            ProductoDetalleDTO dto = new ProductoDetalleDTO();
            dto.setId(p.getId());
            dto.setSku(p.getSku());
            dto.setPrecio(p.getPrecio());
            dto.setStockDisponible(p.getStock()); // Aquí mapeamos el stock actual
            return dto;
        }).collect(Collectors.toList());
    }
}

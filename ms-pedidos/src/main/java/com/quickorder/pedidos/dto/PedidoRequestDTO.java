package com.quickorder.pedidos.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequestDTO {

    @NotNull(message = "Debe ingresar el ID del cliente")
    private Long clienteId;

    @NotEmpty(message = "El pedido debe tener al menos un producto")
    @Valid
    private List<PedidoProductoRequestDTO> productos;


}


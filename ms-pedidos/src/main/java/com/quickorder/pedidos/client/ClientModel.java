package com.quickorder.pedidos.client;

import com.quickorder.pedidos.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientModel {

    private final WebClient.Builder webClientBuilder;

    private final String MS_USERS_URL = "http://localhost:8081/api";
    private final String MS_CATALOG_URL = "http://localhost:8082/api";
    private final String MS_PAYMENTS_URL = "http://localhost:8084/api";
    private final String MS_SHIPPING_URL = "http://localhost:8085/api";

    public ClienteDTO validarCliente(Long clienteId) {
        log.info("Llamando a ms-users para validar cliente ID: {}", clienteId);
        try {
            return webClientBuilder.build()
                    .get()
                    .uri(MS_USERS_URL + "/clientes/" + clienteId)
                    .retrieve()
                    .bodyToMono(ClienteDTO.class)
                    .block();
        } catch (WebClientResponseException.NotFound e) {
            log.error("Cliente {} no encontrado", clienteId);
            throw new RuntimeException("El cliente especificado no existe.");
        }
    }

    public List<ProductoDetalleDTO> obtenerInfoProductos(List<PedidoProductoRequestDTO> productos) {
        log.info("Llamando a ms-catalog para validar stock y obtener precios");
        return webClientBuilder.build()
                .post()
                .uri(MS_CATALOG_URL + "/productos/validar")
                .bodyValue(productos)
                .retrieve()
                .bodyToFlux(ProductoDetalleDTO.class)
                .collectList()
                .block();
    }

    public PagoResponseDTO procesarPago(Long pedidoId, BigDecimal total) {
        log.info("Llamando a ms-payments para procesar pago del pedido ID: {}", pedidoId);
        Map<String, Object> pagoRequest = Map.of(
                "pedidoId", pedidoId,
                "montoTotal", total,
                "metodoPago", "TARJETA_CREDITO"
        );
        return webClientBuilder.build()
                .post()
                .uri(MS_PAYMENTS_URL + "/pagos")
                .bodyValue(pagoRequest)
                .retrieve()
                .bodyToMono(PagoResponseDTO.class)
                .block();
    }

    public void generarDespacho(Long pedidoId, String direccion) {
        log.info("Llamando a ms-shipping para programar despacho a: {}", direccion);
        Map<String, Object> despachoRequest = Map.of(
                "pedidoId", pedidoId,
                "direccionDestino", direccion
        );
        webClientBuilder.build()
                .post()
                .uri(MS_SHIPPING_URL + "/despachos")
                .bodyValue(despachoRequest)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
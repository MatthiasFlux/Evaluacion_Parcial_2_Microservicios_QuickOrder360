package com.quickorder.pedidos.client;

import com.quickorder.pedidos.dto.PagoRequestDTO;
import com.quickorder.pedidos.dto.PagoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-pagos")
public interface PagoClient {

    @PostMapping("/api/v1/pagos")
    PagoResponseDTO realizarPago(@RequestBody PagoRequestDTO request);

}

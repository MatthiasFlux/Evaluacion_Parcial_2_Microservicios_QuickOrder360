package com.quickorder.pedidos.client;

import com.quickorder.pedidos.dto.DespachoRequestDTO;
import com.quickorder.pedidos.dto.DespachoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-despachos")
public interface DespachoClient {

    @PostMapping("/api/v1/despachos")
    DespachoResponseDTO crearDespacho(@RequestBody DespachoRequestDTO request);

}

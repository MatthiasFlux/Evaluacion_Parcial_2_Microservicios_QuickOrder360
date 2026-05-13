package com.quickorder.pedidos.client;

import com.quickorder.pedidos.dto.ClienteDTO; // El DTO debe existir también en quien llama
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Conecta directamente con el nombre registrado en Eureka
@FeignClient(name = "ms-clientes")
public interface ClienteClient {

    @GetMapping("/api/v1/clientes/{id}")
    ClienteDTO obtenerClientePorId(@PathVariable("id") Long id);

}

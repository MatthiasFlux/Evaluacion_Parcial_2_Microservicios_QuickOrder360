package com.quickorder.clientes.service;

import com.quickorder.clientes.model.ClienteModel;
import com.quickorder.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteModel guardarCliente(ClienteModel cliente) {
        log.info("Guardando nuevo cliente en la base de datos");
        return clienteRepository.save(cliente);
    }

    public ClienteModel obtenerPorId(Long id) {
        log.info("Buscando cliente con ID: {}", id);
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }
}

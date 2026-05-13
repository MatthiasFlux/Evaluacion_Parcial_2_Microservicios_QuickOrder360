package com.quickorder.clientes.service;

import com.quickorder.clientes.model.ClienteModel;
import com.quickorder.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<ClienteModel> listarTodos() {
        return clienteRepository.findAll();
    }

    public ClienteModel buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    public ClienteModel guardar(ClienteModel cliente) {
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }

}

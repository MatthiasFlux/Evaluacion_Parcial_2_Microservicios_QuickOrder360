package com.quickorder.clientes.controller;

import com.quickorder.clientes.dto.ClienteDTO;
import com.quickorder.clientes.model.ClienteModel;
import com.quickorder.clientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public List<ClienteModel> obtenerTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<ClienteModel> crear(@Valid @RequestBody ClienteDTO clienteDTO) {
        ClienteModel cliente = new ClienteModel();
        cliente.setRut(clienteDTO.getRut());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefono(clienteDTO.getTelefono());

        return new ResponseEntity<>(clienteService.guardar(cliente), HttpStatus.CREATED);
    }

}

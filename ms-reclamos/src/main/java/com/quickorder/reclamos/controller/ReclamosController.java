package com.quickorder.reclamos.controller;

import com.quickorder.reclamos.dto.ReclamosRequestDTO;
import com.quickorder.reclamos.dto.ReclamosResponseDTO;
import com.quickorder.reclamos.service.ReclamosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reclamos")
@RequiredArgsConstructor
public class ReclamosController {

    private final ReclamosService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReclamosResponseDTO registrar(@RequestBody ReclamosRequestDTO request) {
        return service.crearReclamo(request);
    }
}
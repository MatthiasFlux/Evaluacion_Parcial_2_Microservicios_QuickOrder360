package com.quickorder.despachos.controller;

import com.quickorder.despachos.dto.DespachoRequestDTO;
import com.quickorder.despachos.dto.DespachoResponseDTO;
import com.quickorder.despachos.service.DespachoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/despachos")
@RequiredArgsConstructor
public class DespachoController {

    private final DespachoService despachoService;

    @PostMapping
    public ResponseEntity<DespachoResponseDTO> crearDespacho(@RequestBody DespachoRequestDTO request) {
        return new ResponseEntity<>(despachoService.programarDespacho(request), HttpStatus.CREATED);
    }
}

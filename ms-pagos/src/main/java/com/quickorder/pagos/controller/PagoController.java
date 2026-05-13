package com.quickorder.pagos.controller;

import com.quickorder.pagos.dto.PagoRequestDTO;
import com.quickorder.pagos.dto.PagoResponseDTO;
import com.quickorder.pagos.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public ResponseEntity<PagoResponseDTO> realizarPago(@RequestBody PagoRequestDTO request) {
        return new ResponseEntity<>(pagoService.procesarPago(request), HttpStatus.CREATED);
    }
}

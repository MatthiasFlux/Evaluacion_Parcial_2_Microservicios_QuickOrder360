package com.quickorder.despachos.controller;

import com.quickorder.despachos.dto.DespachoRequestDTO;
import com.quickorder.despachos.service.DespachoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/despachos")
@RequiredArgsConstructor
public class DespachoController {

    private final DespachoService despachoService;

    @PostMapping
    public ResponseEntity<Void> generarDespacho(@RequestBody DespachoRequestDTO peticionDespacho) {
        despachoService.programarDespacho(peticionDespacho);
        return ResponseEntity.ok().build();
    }
}

package com.votacaoalmoco.controller;

import com.votacaoalmoco.api.Voto;
import com.votacaoalmoco.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/votos")
public class VotoController {

    private final VotoService service;

    @Autowired
    public VotoController(VotoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> votar(@Valid @RequestBody Voto voto) {
        service.votar(voto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

package com.votacaoalmoco.controller;

import com.votacaoalmoco.api.Voto;
import com.votacaoalmoco.service.VotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
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
        log.info("Incluindo novo voto. Restaurante: " + voto.getIdRestaurante() + ", Matrícula: " + voto.getMatricula());
        service.votar(voto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Voto>> buscarTodos() {
        log.info("Listando todos os votos");
        return ResponseEntity.ok(service.buscarTodos());
    }
}

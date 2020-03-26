package com.votacaoalmoco.controller;

import com.votacaoalmoco.api.Restaurante;
import com.votacaoalmoco.service.RestauranteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    private final RestauranteService service;

    @Autowired
    public RestauranteController(RestauranteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Restaurante> incluir(@Valid @RequestBody Restaurante restaurante) {
        log.info("Adicionando restaurante: " + restaurante.getNome());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.incluir(restaurante));
    }

    @GetMapping
    public ResponseEntity<List<Restaurante>> buscarTodos() {
        log.info("Listando todos os restaurantes");
        return ResponseEntity.ok(service.buscarTodos());
    }

}

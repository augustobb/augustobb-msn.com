package com.votacaoalmoco.controller;

import com.votacaoalmoco.api.ResultadoVotacaoSemana;
import com.votacaoalmoco.service.ResultadoVotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resultados-votacao")
public class ResultadoVotacaoController {

    private final ResultadoVotacaoService service;

    @Autowired
    public ResultadoVotacaoController(ResultadoVotacaoService service) {
        this.service = service;
    }

    @GetMapping("/ultima-votacao")
    public ResponseEntity<ResultadoVotacaoSemana> getUltimaVotacao() {
        return ResponseEntity.ok(service.getUltimoResultadoVotacao());
    }
}

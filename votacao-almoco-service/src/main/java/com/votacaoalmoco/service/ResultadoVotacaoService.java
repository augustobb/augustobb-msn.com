package com.votacaoalmoco.service;

import com.votacaoalmoco.api.ResultadoVotacaoSemana;
import com.votacaoalmoco.entity.RestauranteEntity;
import com.votacaoalmoco.entity.ResultadoVotacaoSemanaEntity;
import com.votacaoalmoco.exception.BusinessException;
import com.votacaoalmoco.message.MessageKey;
import com.votacaoalmoco.repository.ResultadoVotacaoSemanaRepository;
import com.votacaoalmoco.service.converter.ResultadoVotacaoConverter;
import com.votacaoalmoco.util.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
public class ResultadoVotacaoService {

    private final ResultadoVotacaoSemanaRepository repository;
    private final ResultadoVotacaoConverter converter;
    private final ContagemVotacaoService contagemVotacaoService;

    @Autowired
    public ResultadoVotacaoService(ResultadoVotacaoSemanaRepository repository, ResultadoVotacaoConverter converter,
                                   ContagemVotacaoService contagemVotacaoService) {
        this.repository = repository;
        this.converter = converter;
        this.contagemVotacaoService = contagemVotacaoService;
    }

    @Transactional
    public void apurarEscolhidoDoDia() {
        LocalDate diaAtual = LocalDate.now();
        limparResultadosDaSemanaAnterior(diaAtual);

        RestauranteEntity escolhido = contagemVotacaoService.getRestauranteMaisVotado(diaAtual);
        ResultadoVotacaoSemanaEntity resultado = ResultadoVotacaoSemanaEntity.builder()
                .dataAlmoco(diaAtual)
                .escolhido(escolhido)
                .build();
        repository.save(resultado);
        log.info("E o almoço de hoje será no restaurante " + escolhido.getNome() + "!");
    }

    private void limparResultadosDaSemanaAnterior(LocalDate diaAtual) {
        if(DataUtils.isSegundaFeira(diaAtual)) {
            repository.deleteAll();
        }
    }

    public ResultadoVotacaoSemana getUltimoResultadoVotacao() {
        return repository.findAllByOrderByDataAlmocoDesc().stream()
                .findFirst()
                .map(converter::toApi)
                .orElseThrow(() -> new BusinessException(MessageKey.SEM_VOTACOES_APURADAS));
    }
}

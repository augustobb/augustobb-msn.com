package com.votacaoalmoco.scheduler;

import com.votacaoalmoco.service.ResultadoVotacaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ConditionalOnProperty(value = "votacao.cronApuracao")
public class ApuracaoScheduler {

    private static final String TIME_ZONE = "America/Sao_Paulo";

    private final ResultadoVotacaoService resultadoVotacaoService;

    @Autowired
    public ApuracaoScheduler(ResultadoVotacaoService resultadoVotacaoService) {
        this.resultadoVotacaoService = resultadoVotacaoService;
    }

    @Scheduled(cron = "${votacao.cronApuracao}", zone = TIME_ZONE)
    public void execute() {
        log.info("Apurando votos para o almoço do dia.");
        resultadoVotacaoService.apurarEscolhidoDoDia();
    }
}

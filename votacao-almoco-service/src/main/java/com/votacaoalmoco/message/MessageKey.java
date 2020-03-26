package com.votacaoalmoco.message;

import lombok.Getter;

@Getter
public enum MessageKey {

    RESTAURANTE_JA_EXISTENTE("erro.restauranteJaExistente"),
    RESTAURANTE_INEXISTENTE("erro.restauranteInexistente"),
    RESTAURANTE_JA_ESCOLHIDO_NA_SEMANA("erro.restauranteJaEscolhidoNaSemana"),
    SEM_VOTACOES_APURADAS("erro.semVotacoesApuradas"),
    NENHUM_VOTO_NO_DIA("erro.nenhumVotoNoDia");

    private final String key;

    private MessageKey(String key) {
        this.key = key;
    }

}

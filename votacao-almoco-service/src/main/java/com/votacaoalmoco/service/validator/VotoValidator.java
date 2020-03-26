package com.votacaoalmoco.service.validator;

import com.votacaoalmoco.api.Voto;
import com.votacaoalmoco.entity.RestauranteEntity;
import com.votacaoalmoco.exception.BusinessException;
import com.votacaoalmoco.message.MessageKey;
import com.votacaoalmoco.repository.ResultadoVotacaoSemanaRepository;
import com.votacaoalmoco.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotoValidator {

    private final ResultadoVotacaoSemanaRepository resultadoVotacaoSemanaRepository;

    @Autowired
    public VotoValidator(ResultadoVotacaoSemanaRepository resultadoVotacaoSemanaRepository) {
        this.resultadoVotacaoSemanaRepository = resultadoVotacaoSemanaRepository;
    }

    public void validarVoto(Voto voto) {
        RestauranteEntity restaurante = RestauranteEntity.builder().id(voto.getIdRestaurante()).build();
        boolean jaVotadoNaSemana = resultadoVotacaoSemanaRepository.findByEscolhido(restaurante).isPresent();
        if(jaVotadoNaSemana && !DataUtils.isSegundaFeira(voto.getDataAlmoco())) {
            throw new BusinessException(MessageKey.RESTAURANTE_JA_ESCOLHIDO_NA_SEMANA);
        }
    }
}

package com.votacaoalmoco.service.validator;

import com.votacaoalmoco.api.Voto;
import com.votacaoalmoco.entity.RestauranteEntity;
import com.votacaoalmoco.exception.BusinessException;
import com.votacaoalmoco.message.MessageKey;
import com.votacaoalmoco.repository.ResultadoVotacaoSemanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;

@Service
public class VotoValidator {

    private final ResultadoVotacaoSemanaRepository resultadoVotacaoSemanaRepository;

    @Autowired
    public VotoValidator(ResultadoVotacaoSemanaRepository resultadoVotacaoSemanaRepository) {
        this.resultadoVotacaoSemanaRepository = resultadoVotacaoSemanaRepository;
    }

    public void validarVoto(Voto voto) {
        RestauranteEntity restaurante = RestauranteEntity.builder().id(voto.getIdRestaurante()).build();
        ofNullable(resultadoVotacaoSemanaRepository.findByEscolhido(restaurante)).ifPresent(jaEscolhido -> {
            throw new BusinessException(MessageKey.RESTAURANTE_JA_ESCOLHIDO_NA_SEMANA);
        });
    }
}

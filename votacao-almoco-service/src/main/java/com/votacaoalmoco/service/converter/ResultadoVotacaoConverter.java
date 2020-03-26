package com.votacaoalmoco.service.converter;

import com.votacaoalmoco.api.ResultadoVotacaoSemana;
import com.votacaoalmoco.entity.ResultadoVotacaoSemanaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultadoVotacaoConverter implements EntityConverter<ResultadoVotacaoSemanaEntity, ResultadoVotacaoSemana> {

    private final RestauranteConverter restauranteConverter;

    @Autowired
    public ResultadoVotacaoConverter(RestauranteConverter restauranteConverter) {
        this.restauranteConverter = restauranteConverter;
    }

    @Override
    public ResultadoVotacaoSemana toApi(ResultadoVotacaoSemanaEntity entity) {
        return ResultadoVotacaoSemana.builder()
                .escolhido(restauranteConverter.toApi(entity.getEscolhido()))
                .dataAlmoco(entity.getDataAlmoco())
                .build();
    }
}

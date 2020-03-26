package com.votacaoalmoco.service.converter

import com.votacaoalmoco.api.Restaurante
import com.votacaoalmoco.api.ResultadoVotacaoSemana
import com.votacaoalmoco.entity.RestauranteEntity
import com.votacaoalmoco.entity.ResultadoVotacaoSemanaEntity
import spock.lang.Specification

import java.time.LocalDate

class ResultadoVotacaoConverterTest extends Specification {

    ResultadoVotacaoConverter converter

    def restauranteConverter = Mock(RestauranteConverter)

    def mockRestauranteEntity = Mock(RestauranteEntity)
    def mockRestaurante = Mock(Restaurante)
    def dataAlmoco = LocalDate.now()

    void setup() {
        converter = new ResultadoVotacaoConverter(restauranteConverter)
        restauranteConverter.toApi(mockRestauranteEntity) >> mockRestaurante
    }

    def "deve converter entidade para objeto de api"() {
        given:
        ResultadoVotacaoSemanaEntity entity = ResultadoVotacaoSemanaEntity.builder()
                .escolhido(mockRestauranteEntity)
                .dataAlmoco(dataAlmoco)
                .build();

        when:
        ResultadoVotacaoSemana result = converter.toApi(entity)

        then: "deve converter com a data de almoço correta"
        result.getDataAlmoco() == dataAlmoco
        and: "e com o restaurante convertido corretamente"
        result.getEscolhido() == mockRestaurante
    }
}

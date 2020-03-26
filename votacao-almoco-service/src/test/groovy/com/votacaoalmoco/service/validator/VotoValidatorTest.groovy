package com.votacaoalmoco.service.validator

import com.votacaoalmoco.api.Voto
import com.votacaoalmoco.entity.RestauranteEntity
import com.votacaoalmoco.entity.ResultadoVotacaoSemanaEntity
import com.votacaoalmoco.exception.BusinessException
import com.votacaoalmoco.repository.ResultadoVotacaoSemanaRepository
import spock.lang.Specification

import static com.votacaoalmoco.message.MessageKey.RESTAURANTE_JA_ESCOLHIDO_NA_SEMANA

class VotoValidatorTest extends Specification {
    private static final Long ID_RESTAURANTE = 1
    private static final VOTO = Voto.builder().idRestaurante(ID_RESTAURANTE).build()
    private static final RESTAURANTE_ENTITY = RestauranteEntity.builder().id(ID_RESTAURANTE).build()

    VotoValidator validator

    def resultadoVotacaoSemanaRepository = Mock(ResultadoVotacaoSemanaRepository)

    void setup() {
        validator = new VotoValidator(resultadoVotacaoSemanaRepository)
    }

    def "quando voto é restaurante presente na lista de restaurantes já escolhidos na semana, deve lançar exceção" () {
        given:
        resultadoVotacaoSemanaRepository.findByEscolhido(RESTAURANTE_ENTITY) >> Mock(ResultadoVotacaoSemanaEntity)

        when:
        validator.validarVoto(VOTO)

        then:"erro de restaurante já escolhido na semana"
        BusinessException ex = thrown()
        ex.getMessageKey() == RESTAURANTE_JA_ESCOLHIDO_NA_SEMANA

    }

    def "quando voto é restaurante ainda não escolhido, não deve lançar exceção" () {
        given:
        resultadoVotacaoSemanaRepository.findByEscolhido(RESTAURANTE_ENTITY) >> null

        when:
        validator.validarVoto(VOTO)

        then:
        noExceptionThrown()
    }
}

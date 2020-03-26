package com.votacaoalmoco.service.converter

import com.votacaoalmoco.api.Restaurante
import com.votacaoalmoco.entity.RestauranteEntity
import spock.lang.Specification

class RestauranteConverterTest extends Specification {

    private static final ID_RESTAURANTE = 1
    private static final NOME_RESTAURANTE = "Panela da Vovó"

    def converter = new RestauranteConverter()

    def "deve converter objeto de api para entidade"() {
        given:
        Restaurante restaurante = Restaurante.builder().id(ID_RESTAURANTE).nome(NOME_RESTAURANTE).build()

        when:
        RestauranteEntity result = converter.toEntity(restaurante)

        then: "deve converter com id correto"
        result.getId() == ID_RESTAURANTE
        and: "e com nome correto"
        result.getNome() == NOME_RESTAURANTE
    }

    def "deve converter entidade para objeto de api"() {
        given:
        RestauranteEntity entity = RestauranteEntity.builder().id(ID_RESTAURANTE).nome(NOME_RESTAURANTE).build()

        when:
        Restaurante result = converter.toApi(entity)

        then: "deve converter com id correto"
        result.getId() == ID_RESTAURANTE
        and: "e com nome correto"
        result.getNome() == NOME_RESTAURANTE
    }

}
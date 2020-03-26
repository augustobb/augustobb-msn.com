package com.votacaoalmoco.service

import com.votacaoalmoco.api.Restaurante
import com.votacaoalmoco.entity.RestauranteEntity
import com.votacaoalmoco.exception.BusinessException
import com.votacaoalmoco.repository.RestauranteRepository
import com.votacaoalmoco.service.converter.RestauranteConverter
import spock.lang.Specification
import static com.votacaoalmoco.message.MessageKey.RESTAURANTE_JA_EXISTENTE

class RestauranteServiceTest extends Specification {
    private static final NOME_RESTAURANTE = "Panela da Vovó"
    private static final RESTAURANTE = Restaurante.builder().nome(NOME_RESTAURANTE).build()
    def restauranteExistente1 = Mock(RestauranteEntity)
    def restauranteExistente2 = Mock(RestauranteEntity)

    RestauranteService service

    def repository = Mock(RestauranteRepository)
    def converter = Mock(RestauranteConverter)

    void setup() {
        service = new RestauranteService(repository, converter)
    }

    def """quando tentar incluir restaurante com nome válido e ainda não registrado,
            deve incluir restaurante no repositório e retornar o objeto criado"""() {

        given: "restaurante ainda não foi registrado"
        repository.findByNome(NOME_RESTAURANTE) >> null
        def restaurateSalvo = Mock(RestauranteEntity)
        def restauranteRetorno = Mock(Restaurante)
        converter.toEntity(RESTAURANTE) >> restaurateSalvo
        converter.toApi(restaurateSalvo) >> restauranteRetorno

        when:
        def result = service.incluir(RESTAURANTE)

        then: "restaurante foi salvo"
        1 * repository.save(restaurateSalvo) >> restaurateSalvo
        and: "e o retorno é o objeto criado"
        result == restauranteRetorno
    }

    def "quando tentar incluir restaurante já registrado, deve lançar erro e não incluir nada no repositório"() {
        given: "restaurante já existe"
        repository.findByNome(NOME_RESTAURANTE) >> restauranteExistente1

        when:
        service.incluir(RESTAURANTE)

        then: "erro de restaurante já registrado"
        BusinessException ex = thrown()
        ex.getMessageKey() == RESTAURANTE_JA_EXISTENTE
    }

    def "quando buscar todos os restaurantes, retorná-los convertidos para objetos de api"() {
        given:
        repository.findAll() >> [restauranteExistente1, restauranteExistente2]
        def restauranteApi1 = Mock(Restaurante)
        def restauranteApi2 = Mock(Restaurante)
        converter.toApi(restauranteExistente1) >> restauranteApi1
        converter.toApi(restauranteExistente2) >> restauranteApi2

        expect:
        service.buscarTodos() == [restauranteApi1, restauranteApi2]
    }
}
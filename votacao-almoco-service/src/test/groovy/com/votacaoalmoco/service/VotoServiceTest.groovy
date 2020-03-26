package com.votacaoalmoco.service

import com.votacaoalmoco.api.Voto
import com.votacaoalmoco.entity.RestauranteEntity
import com.votacaoalmoco.entity.VotoEntity
import com.votacaoalmoco.exception.BusinessException
import com.votacaoalmoco.repository.RestauranteRepository
import com.votacaoalmoco.repository.VotoRepository
import com.votacaoalmoco.service.converter.VotoConverter
import com.votacaoalmoco.service.validator.VotoValidator
import spock.lang.Specification

import java.time.LocalDate

import static com.votacaoalmoco.message.MessageKey.RESTAURANTE_INEXISTENTE

class VotoServiceTest extends Specification {
    private static final MATRICULA = "9999gold"
    private static final VOTO = Voto.builder().idRestaurante(1).matricula(MATRICULA).build()
    private static final DIA = LocalDate.now()

    VotoService service

    def repository = Mock(VotoRepository)
    def validator = Mock(VotoValidator)
    def dataAlmocoService = Mock(DataAlmocoService)
    def restauranteRepository = Mock(RestauranteRepository)
    def converter = Mock(VotoConverter)

    def mockRestaurante = Mock(RestauranteEntity)

    void setup() {
        service = new VotoService(repository, validator, dataAlmocoService, restauranteRepository, converter)
        restauranteRepository.findById(1) >> Optional.of(mockRestaurante)
        dataAlmocoService.getDataProximoAlmoco() >> DIA
        repository.findByDataAlmocoAndMatricula(DIA, MATRICULA) >> Optional.empty()
    }

    def "quando for realizado um novo voto, o mesmo deve ser validado dentro das regras de negócio" () {
        when:
        service.votar(VOTO)

        then:
        1 * validator.validarVoto(VOTO)
    }

    def "quando for realizado um novo voto com restaurante existente, deve salvar novo voto com o restaurante"() {
        when:
        service.votar(VOTO)

        then:
        1 * repository.save(VotoEntity.builder().restaurante(mockRestaurante).matricula(MATRICULA).dataAlmoco(DIA).build())
    }

    def "quando for realizado um novo voto com restaurante inexistente, deve lançar exceção e não salvar nada"() {
        given:
        restauranteRepository.findById(2) >> Optional.empty()

        when:
        service.votar(Voto.builder().idRestaurante(2).build())

        then: "erro de restaurante inexistente"
        BusinessException ex = thrown()
        ex.getMessageKey() == RESTAURANTE_INEXISTENTE
        0 * repository.save(_)
    }

    def "quando buscar todos os votos, deve retorná-los convertidos" () {
        given:
        def votoEntity1 = Mock(VotoEntity)
        def votoEntity2 = Mock(VotoEntity)
        repository.findAll() >> [votoEntity1, votoEntity2]
        def voto1 = Mock(Voto)
        def voto2 = Mock(Voto)
        converter.toApi(votoEntity1) >> voto1
        converter.toApi(votoEntity2) >> voto2

        expect:
        service.buscarTodos() == [voto1, voto2]
    }
}

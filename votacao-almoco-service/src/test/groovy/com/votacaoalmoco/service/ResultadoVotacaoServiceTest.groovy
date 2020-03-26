package com.votacaoalmoco.service

import com.votacaoalmoco.api.ResultadoVotacaoSemana
import com.votacaoalmoco.entity.ResultadoVotacaoSemanaEntity
import com.votacaoalmoco.exception.BusinessException
import com.votacaoalmoco.repository.ResultadoVotacaoSemanaRepository
import com.votacaoalmoco.service.converter.ResultadoVotacaoConverter
import spock.lang.Specification

import java.time.LocalDate

import static com.votacaoalmoco.message.MessageKey.SEM_VOTACOES_APURADAS

class ResultadoVotacaoServiceTest extends Specification {

    def repository = Mock(ResultadoVotacaoSemanaRepository)
    def converter = Mock(ResultadoVotacaoConverter)
    def contagemVotacaoService = Mock(ContagemVotacaoService)

    ResultadoVotacaoService service

    void setup() {
        service = new ResultadoVotacaoService(repository, converter, contagemVotacaoService)
    }

    def "quando buscar último resultado de votação, deve retorná-lo convertido"() {
        given:
        def primeiroResultadoEntity = ResultadoVotacaoSemanaEntity.builder()
                .dataAlmoco(LocalDate.now().minusDays(1))
                .build()
        def ultimoResultadoEntity = ResultadoVotacaoSemanaEntity.builder()
                .dataAlmoco(LocalDate.now())
                .build()
        def ultimoResultado = Mock(ResultadoVotacaoSemana)
        repository.findAllByOrderByDataAlmocoDesc() >> [ultimoResultadoEntity, primeiroResultadoEntity]
        converter.toApi(ultimoResultadoEntity) >> ultimoResultado

        expect:
        service.getUltimoResultadoVotacao() == ultimoResultado
    }

    def "quando tentar buscar último resultado de votação mas não houver votações apuradas, deve lançar exceção"() {
        given:
        repository.findAllByOrderByDataAlmocoDesc() >> []

        when:
        service.getUltimoResultadoVotacao()

        then: "erro de nenhuma votação apurada"
        BusinessException ex = thrown()
        ex.getMessageKey() == SEM_VOTACOES_APURADAS

    }
}

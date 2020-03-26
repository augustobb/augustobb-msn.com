package com.votacaoalmoco.repository;

import com.votacaoalmoco.entity.RestauranteEntity;
import com.votacaoalmoco.entity.ResultadoVotacaoSemanaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoVotacaoSemanaRepository extends JpaRepository<ResultadoVotacaoSemanaEntity, Long> {
    List<ResultadoVotacaoSemanaEntity> findAllByOrderByDataAlmocoDesc();
    ResultadoVotacaoSemanaEntity findByEscolhido(RestauranteEntity escolhido);
}

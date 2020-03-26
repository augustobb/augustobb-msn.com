package com.votacaoalmoco.repository;

import com.votacaoalmoco.entity.ContagemVotacao;
import com.votacaoalmoco.entity.VotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<VotoEntity, Long> {

    @Query("SELECT new com.votacaoalmoco.entity.ContagemVotacao(COUNT(v), v.restaurante) " +
            "FROM VotoEntity v " +
            "WHERE v.dataAlmoco = :dia " +
            "GROUP BY v.restaurante " +
            "ORDER BY COUNT(v) DESC")
    List<ContagemVotacao> countVotosByRestaurante(@Param("dia") LocalDate dia);
}

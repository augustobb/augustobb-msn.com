package com.votacaoalmoco.repository;

import com.votacaoalmoco.entity.ContagemVotacao;
import com.votacaoalmoco.entity.RestauranteEntity;
import com.votacaoalmoco.entity.VotoEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<VotoEntity, Long> {

    Optional<VotoEntity> findByDataAlmocoAndMatricula(LocalDate dataAmlmoco, String matricula);

    @Query("SELECT new com.votacaoalmoco.entity.ContagemVotacao(COUNT(v), v.restaurante) " +
            "FROM VotoEntity v " +
            "WHERE v.dataAlmoco = :dia " +
            "GROUP BY v.restaurante " +
            "ORDER BY COUNT(v) DESC")
    List<ContagemVotacao> countVotosByRestauranteOrderByVotos(@Param("dia") LocalDate dia);

    VotoEntity findFirstByRestauranteInAndDataAlmoco(
            List<RestauranteEntity> restaurantes, LocalDate dataAlmoco, Sort sort);
}

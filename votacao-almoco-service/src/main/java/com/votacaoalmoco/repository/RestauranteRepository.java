package com.votacaoalmoco.repository;

import com.votacaoalmoco.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
    RestauranteEntity findByNome(String nome);
}

package com.votacaoalmoco.entity;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ContagemVotacao implements Serializable {

    private static final long serialVersionUID = -1712734233066742353L;

    public ContagemVotacao(Long total, RestauranteEntity restaurante) {
        this.total = total;
        this.restaurante = restaurante;
    }

    private Long total;
    private RestauranteEntity restaurante;
}

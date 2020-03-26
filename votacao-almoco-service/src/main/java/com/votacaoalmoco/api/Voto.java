package com.votacaoalmoco.api;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Voto implements Serializable {
    private static final long serialVersionUID = 7756553512185148697L;

    private Long idRestaurante;
    private String matricula;
}

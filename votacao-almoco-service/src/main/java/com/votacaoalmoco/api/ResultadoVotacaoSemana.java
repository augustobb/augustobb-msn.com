package com.votacaoalmoco.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class ResultadoVotacaoSemana implements Serializable {
    private static final long serialVersionUID = 6889218586960111251L;

    private Restaurante escolhido;
    private LocalDate dataAlmoco;
}

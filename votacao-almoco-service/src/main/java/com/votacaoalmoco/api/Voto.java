package com.votacaoalmoco.api;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class Voto implements Serializable {
    private static final long serialVersionUID = 7756553512185148697L;

    @NotNull(message = "Identificador de restaurante inválido")
    private Long idRestaurante;

    @NotEmpty(message = "Matrícula inválida")
    private String matricula;

    private LocalDate dataAlmoco;
    private LocalDateTime ultimaAtualizacao;
}

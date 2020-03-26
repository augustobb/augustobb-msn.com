package com.votacaoalmoco.api;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
public class Restaurante implements Serializable {
    private static final long serialVersionUID = 4291474863911584089L;

    private Long id;

    @NotEmpty(message = "Name may not be empty")
    private String nome;
}

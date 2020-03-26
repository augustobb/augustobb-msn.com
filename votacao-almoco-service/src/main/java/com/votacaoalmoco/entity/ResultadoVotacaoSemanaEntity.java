package com.votacaoalmoco.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resultado_votacao_semana")
/*
* Entidade para registrar os resultados de votação da semana
* */
public class ResultadoVotacaoSemanaEntity implements Serializable {

    private static final long serialVersionUID = -5156546434961384453L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_escolhido", referencedColumnName = "id", nullable = false)
    private RestauranteEntity escolhido;

    @Column(name = "dataAlmoco", nullable = false)
    private LocalDate dataAlmoco;

}

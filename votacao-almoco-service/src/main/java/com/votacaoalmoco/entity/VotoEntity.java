package com.votacaoalmoco.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "voto")
public class VotoEntity implements Serializable {

    private static final long serialVersionUID = 4649186256243596793L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_restaurante", referencedColumnName = "id", nullable = false)
    private RestauranteEntity restaurante;

    @Column(name = "matricula", nullable = false)
    private String matricula;

    @Column(name = "data_almoco", nullable = false)
    private LocalDate dataAlmoco;

    @Column(name = "ultima_atualizacao", nullable = false)
    private LocalDateTime ultimaAtualizacao;

    @PrePersist
    public void prePersist() {
        ultimaAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        ultimaAtualizacao = LocalDateTime.now();
    }
}

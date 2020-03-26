package com.votacaoalmoco.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Getter
@Component
public class VotacaoProperties {

    @Value("${votacao.horaLimite}")
    private LocalTime horaLimite;

}

package com.votacaoalmoco.service;

import com.votacaoalmoco.properties.VotacaoProperties;
import com.votacaoalmoco.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class DataAlmocoService {

    private final VotacaoProperties votacaoProperties;

    @Autowired
    public DataAlmocoService(VotacaoProperties votacaoProperties) {
        this.votacaoProperties = votacaoProperties;
    }

    // identificar data do próximo almoço
    // -- se passou do horário limite, considerar o dia seguinte
    // -- desconsiderar final de semana: se for sabado, domingo ou sexta pós horário limite, considerar próxima segunda
    public LocalDate getDataProximoAlmoco() {
        LocalDate dataProximoAlmoco = LocalDate.now();
        LocalTime horarioAgora = LocalTime.now();
        if(votacaoProperties.getHoraLimite().isBefore(horarioAgora)) {
            dataProximoAlmoco = dataProximoAlmoco.plusDays(1);
        }
        if(DataUtils.isSabado(dataProximoAlmoco)) {
            dataProximoAlmoco = dataProximoAlmoco.plusDays(1);
        }
        if(DataUtils.isDomingo(dataProximoAlmoco)) {
            dataProximoAlmoco = dataProximoAlmoco.plusDays(1);
        }
        return dataProximoAlmoco;
    }
}

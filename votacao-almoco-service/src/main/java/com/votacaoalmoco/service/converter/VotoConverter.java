package com.votacaoalmoco.service.converter;

import com.votacaoalmoco.api.Voto;
import com.votacaoalmoco.entity.VotoEntity;
import org.springframework.stereotype.Service;

@Service
public class VotoConverter implements EntityConverter<VotoEntity, Voto> {
    @Override
    public Voto toApi(VotoEntity entity) {
        return Voto.builder()
                .idRestaurante(entity.getRestaurante().getId())
                .matricula(entity.getMatricula())
                .dataAlmoco(entity.getDataAlmoco())
                .ultimaAtualizacao(entity.getUltimaAtualizacao())
                .build();
    }
}

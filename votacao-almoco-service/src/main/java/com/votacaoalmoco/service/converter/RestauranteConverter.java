package com.votacaoalmoco.service.converter;

import com.votacaoalmoco.api.Restaurante;
import com.votacaoalmoco.entity.RestauranteEntity;
import org.springframework.stereotype.Service;

@Service
public class RestauranteConverter implements EntityConverter<RestauranteEntity, Restaurante> {

    @Override
    public RestauranteEntity toEntity(Restaurante restaurante) {
        return RestauranteEntity.builder()
                .id(restaurante.getId())
                .nome(restaurante.getNome())
                .build();
    }

    @Override
    public Restaurante toApi(RestauranteEntity entity) {
        return Restaurante.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .build();
    }
}

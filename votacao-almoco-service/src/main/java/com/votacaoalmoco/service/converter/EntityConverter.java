package com.votacaoalmoco.service.converter;

public interface EntityConverter <E, A> {

    default E toEntity (A apiObject){
        return null;
    }

    A toApi (E entity);

}

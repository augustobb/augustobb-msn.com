package com.votacaoalmoco.service;

import com.votacaoalmoco.api.Restaurante;
import com.votacaoalmoco.entity.RestauranteEntity;
import com.votacaoalmoco.exception.BusinessException;
import com.votacaoalmoco.message.MessageKey;
import com.votacaoalmoco.repository.RestauranteRepository;
import com.votacaoalmoco.service.converter.RestauranteConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class RestauranteService {

    private final RestauranteRepository repository;
    private final RestauranteConverter converter;

    @Autowired
    public RestauranteService(RestauranteRepository repository, RestauranteConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Transactional
    public Restaurante incluir(Restaurante restaurante) {
        validarRestaurante(restaurante);
        RestauranteEntity restauranteSalvo = repository.save(converter.toEntity(restaurante));
        return converter.toApi(restauranteSalvo);
    }

    private void validarRestaurante(Restaurante restaurante) {
        if(nonNull(repository.findByNome(restaurante.getNome()))) {
            throw new BusinessException(MessageKey.RESTAURANTE_JA_EXISTENTE, restaurante.getNome());
        }
    }

    public List<Restaurante> buscarTodos() {
        return repository.findAll()
                .stream()
                .map(converter::toApi)
                .collect(Collectors.toList());
    }
}

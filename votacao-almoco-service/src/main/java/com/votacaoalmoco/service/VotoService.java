package com.votacaoalmoco.service;

import com.votacaoalmoco.api.Voto;
import com.votacaoalmoco.entity.RestauranteEntity;
import com.votacaoalmoco.entity.VotoEntity;
import com.votacaoalmoco.exception.BusinessException;
import com.votacaoalmoco.message.MessageKey;
import com.votacaoalmoco.repository.RestauranteRepository;
import com.votacaoalmoco.repository.VotoRepository;
import com.votacaoalmoco.service.converter.VotoConverter;
import com.votacaoalmoco.service.validator.VotoValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VotoService {

    private final VotoRepository repository;
    private final VotoValidator validator;
    private final DataAlmocoService dataAlmocoService;
    private final RestauranteRepository restauranteRepository;
    private final VotoConverter converter;

    public VotoService(VotoRepository repository, VotoValidator validator, DataAlmocoService dataAlmocoService,
                       RestauranteRepository restauranteRepository, VotoConverter converter) {
        this.repository = repository;
        this.validator = validator;
        this.dataAlmocoService = dataAlmocoService;
        this.restauranteRepository = restauranteRepository;
        this.converter = converter;
    }

    @Transactional
    public void votar(Voto voto) {
        validator.validarVoto(voto);
        LocalDate dataAlmoco = dataAlmocoService.getDataProximoAlmoco();
        RestauranteEntity restaurante = restauranteRepository.findById(voto.getIdRestaurante())
                .orElseThrow(() -> new BusinessException(MessageKey.RESTAURANTE_INEXISTENTE));

        VotoEntity votoEntity = repository.findByDataAlmocoAndMatricula(dataAlmoco, voto.getMatricula())
                .map(votoAnterior -> {
                    votoAnterior.setRestaurante(restaurante);
                    return votoAnterior;
                })
                .orElseGet(() -> VotoEntity.builder()
                        .restaurante(restaurante).matricula(voto.getMatricula()).dataAlmoco(dataAlmoco)
                        .build());

        repository.save(votoEntity);
    }

    public List<Voto> buscarTodos() {
        return repository.findAll()
                .stream()
                .map(converter::toApi)
                .collect(Collectors.toList());
    }
}

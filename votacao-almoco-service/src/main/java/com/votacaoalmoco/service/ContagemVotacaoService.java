package com.votacaoalmoco.service;

import com.votacaoalmoco.entity.ContagemVotacao;
import com.votacaoalmoco.entity.RestauranteEntity;
import com.votacaoalmoco.exception.BusinessException;
import com.votacaoalmoco.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.votacaoalmoco.message.MessageKey.NENHUM_VOTO_NO_DIA;

@Service
public class ContagemVotacaoService {

    private final VotoRepository votoRepository;

    @Autowired
    public ContagemVotacaoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public RestauranteEntity getRestauranteMaisVotado(LocalDate dia) {
        List<ContagemVotacao> contagem = votoRepository.countVotosByRestaurante(dia);
        if(contagem.isEmpty()) {
            throw new BusinessException(NENHUM_VOTO_NO_DIA);
        }
        if(contagem.size() == 1) {
            return contagem.get(0).getRestaurante();
        }
        return null;
    }
}

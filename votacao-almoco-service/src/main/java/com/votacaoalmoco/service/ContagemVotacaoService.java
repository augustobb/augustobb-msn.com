package com.votacaoalmoco.service;

import com.votacaoalmoco.entity.ContagemVotacao;
import com.votacaoalmoco.entity.RestauranteEntity;
import com.votacaoalmoco.exception.BusinessException;
import com.votacaoalmoco.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.votacaoalmoco.message.MessageKey.NENHUM_VOTO_NO_DIA;

@Service
public class ContagemVotacaoService {

    private final VotoRepository votoRepository;

    @Autowired
    public ContagemVotacaoService(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    public RestauranteEntity getRestauranteMaisVotado(LocalDate dia) {
        List<ContagemVotacao> contagem = votoRepository.countVotosByRestauranteOrderByVotos(dia);

        RestauranteEntity restauranteMaisVotado = contagem.stream()
                .findFirst()
                .map(ContagemVotacao::getRestaurante)
                .orElseThrow(() -> new BusinessException(NENHUM_VOTO_NO_DIA));

        if(contagem.size() == 1 || !hasEmpate(contagem)) {
            return restauranteMaisVotado;
        }

        return desempatarContagem(contagem, dia);
    }

    private boolean hasEmpate(List<ContagemVotacao> contagem) {
        return contagem.get(0).getTotal().equals(contagem.get(1).getTotal());
    }

    /* O desempate se dá pelo primeiro voto realizado dentre os restaurantes mais votados */
    private RestauranteEntity desempatarContagem(List<ContagemVotacao> contagem, LocalDate dia) {
        Long maiorNumeroDeVotos = contagem.get(0).getTotal();
        List<RestauranteEntity> restaurantesEmpatados = contagem.stream()
                .filter(c -> c.getTotal().equals(maiorNumeroDeVotos))
                .map(ContagemVotacao::getRestaurante)
                .collect(Collectors.toList());

        Sort sort = Sort.by(Sort.Direction.ASC, "ultimaAtualizacao");
        return votoRepository.findFirstByRestauranteInAndDataAlmoco(restaurantesEmpatados, dia, sort)
                .getRestaurante();
    }
}

package com.votacaoalmoco.service;

import com.votacaoalmoco.api.Voto;
import com.votacaoalmoco.repository.VotoRepository;
import org.springframework.stereotype.Service;

@Service
public class VotoService {

    private final VotoRepository repository;

    public VotoService(VotoRepository repository) {
        this.repository = repository;
    }

    public void votar(Voto voto) {

    }
    // validar voto
    // identificar data do próximo almoço
    // -- se passou do horário limite, considerar o dia seguinte
    // -- desconsiderar final de semana: se for sabado, domingo ou sexta pós horário limite, considerar próxima segunda
    // buscar votos do usuário para esse dia
    // se usuário já votou, alterar restaurante do voto existente e o horário do voto
    // se usuário não votou, salvar novo voto
}

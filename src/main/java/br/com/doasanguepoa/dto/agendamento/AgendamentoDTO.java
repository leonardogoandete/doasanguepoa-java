package br.com.doasanguepoa.dto.agendamento;

import br.com.doasanguepoa.model.Instituicao;
import br.com.doasanguepoa.model.Usuario;

import java.util.Date;

public record AgendamentoDTO(
        Date date,
        Long idInstituicao,
        String hora
) {
}

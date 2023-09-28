package br.com.doasanguepoa.dto.agendamento;

import java.util.Date;

public record AgendamentoDTO(
        Date date,
        Long idInstituicao,
        String hora
) {
}

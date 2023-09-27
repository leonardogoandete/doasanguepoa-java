package br.com.doasanguepoa.repository;

import br.com.doasanguepoa.model.Agendamento;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AgendamentoRepository implements PanacheRepository<Agendamento> {
}

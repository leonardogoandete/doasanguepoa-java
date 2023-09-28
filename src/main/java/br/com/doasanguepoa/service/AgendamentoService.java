package br.com.doasanguepoa.service;

import br.com.doasanguepoa.model.Agendamento;
import br.com.doasanguepoa.repository.AgendamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class AgendamentoService {
    public static final Logger LOGGER = Logger.getLogger(AgendamentoService.class.getName());

    @Inject
    AgendamentoRepository agendamentoRepository;

    public List<Agendamento> listarAgendamentos() {
        return agendamentoRepository.listAll();
    }

    public Agendamento buscarAgendamentoPorId(Long id) {
        return agendamentoRepository.findById(id);
    }


    //USAR DTOs AQUI
    public void adicionarAgendamento(Agendamento agendamento) {

        LOGGER.log(Level.INFO, "Gravando o agendamento: {0}", agendamento);
        agendamentoRepository.persist(agendamento);
    }

    public void deletarAgendamento(Long id) {
        LOGGER.log(Level.INFO, "Buscando agendamento com o id: {0}", id);
        agendamentoRepository.deleteById(id);
    }
}

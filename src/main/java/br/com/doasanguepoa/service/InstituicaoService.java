package br.com.doasanguepoa.service;

import br.com.doasanguepoa.model.Instituicao;
import br.com.doasanguepoa.repository.InstituicaoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class InstituicaoService {

    @Inject
    InstituicaoRepository instituicaoRepository;
    public List<Instituicao> listarInstituicoes() {
        return instituicaoRepository.listAll();
    }

    public Instituicao buscarInstituicaoPorId(Long id) {
        return instituicaoRepository.findById(id);
    }


    //USAR DTOs AQUI
    public void adicionarInstituicao(Instituicao instituicao) {
        instituicaoRepository.persist(instituicao);
    }
}

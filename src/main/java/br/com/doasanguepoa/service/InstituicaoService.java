package br.com.doasanguepoa.service;

import br.com.doasanguepoa.model.Instituicao;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class InstituicaoService {

    public List<Instituicao> listarInstituicoes() {
        return Instituicao.listAll();
    }

    public Instituicao buscarInstituicaoPorId(Long id) {
        return Instituicao.findById(id);
    }
}

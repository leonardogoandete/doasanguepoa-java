package br.com.doasanguepoa.service;

import br.com.doasanguepoa.model.Postagem;
import br.com.doasanguepoa.repository.PostagemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class PostagemService {

    @Inject
    PostagemRepository postagemRepository;
    public List<Postagem> listarPostagens() {
        return postagemRepository.listAll();
    }

    public Postagem buscarPostagemPorId(Long id) {
        return postagemRepository.findById(id);
    }

    public void adicionarPostagem(Postagem postagem) {
        postagemRepository.persist(postagem);
    }

    public void deletarPostagem(Long id) {
        postagemRepository.deleteById(id);
    }
}


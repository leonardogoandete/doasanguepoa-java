package br.com.doasanguepoa.service;

import br.com.doasanguepoa.model.Postagem;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PostagemService {

    public List<Postagem> listarPostagens() {
        return Postagem.listAll();
    }

    public Postagem buscarPostagemPorId(Long id) {
        return Postagem.findById(id);
    }
}


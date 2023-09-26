package br.com.doasanguepoa.repository;

import br.com.doasanguepoa.model.Postagem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostagemRepository implements PanacheRepository<Postagem> {
}

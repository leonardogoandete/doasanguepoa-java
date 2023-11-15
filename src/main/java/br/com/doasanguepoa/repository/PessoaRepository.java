package br.com.doasanguepoa.repository;


import br.com.doasanguepoa.model.Pessoa;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {
    public Pessoa findByDocumento(String documento){
        return find("documento", documento).firstResult();
    }
}

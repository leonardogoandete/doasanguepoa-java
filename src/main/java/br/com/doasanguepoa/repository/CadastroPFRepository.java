package br.com.doasanguepoa.repository;

import br.com.doasanguepoa.model.CadastroPF;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CadastroPFRepository implements PanacheRepository<CadastroPF> {

    public CadastroPF findByCpf(String cpf) {
        return find("cpf = ?1", cpf).firstResult();
    }
}

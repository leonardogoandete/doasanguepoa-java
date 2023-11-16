package br.com.doasanguepoa.repository;

import br.com.doasanguepoa.model.CadastroPJ;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CadastroPJRepository implements PanacheRepository<CadastroPJ> {


    public CadastroPJ findByCnpj(String cnpj) {
        return find("cpf = ?1", cnpj).firstResult();
    }
}

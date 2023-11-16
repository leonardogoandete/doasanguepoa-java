package br.com.doasanguepoa.repository;

import br.com.doasanguepoa.model.CadastroPJ;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class CadastroPJRepository implements PanacheRepository<CadastroPJ> {


    public CadastroPJ findByCnpj(String cnpj) {
        return find("cpf = ?1", cnpj).firstResult();
    }
}

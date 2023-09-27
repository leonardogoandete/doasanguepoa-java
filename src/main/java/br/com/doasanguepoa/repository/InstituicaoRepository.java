package br.com.doasanguepoa.repository;

import br.com.doasanguepoa.model.Instituicao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InstituicaoRepository implements PanacheRepository<Instituicao> {
    public Instituicao findByCnpj(String cnpj){
        return find("cnpj", cnpj).firstResult();
    }
}

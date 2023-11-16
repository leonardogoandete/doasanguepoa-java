package br.com.doasanguepoa.repository;


import br.com.doasanguepoa.model.Cadastro;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CadastroRepository implements PanacheRepository<Cadastro> {
    public Cadastro findByDocumento(String documento){
        return find("documento", documento).firstResult();
    }
}

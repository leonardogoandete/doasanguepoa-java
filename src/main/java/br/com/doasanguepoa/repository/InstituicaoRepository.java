package br.com.doasanguepoa.repository;

import br.com.doasanguepoa.model.Instituicao;
import br.com.doasanguepoa.model.Postagem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class InstituicaoRepository implements PanacheRepository<Instituicao> {
    public Instituicao findByCnpj(String cnpj){
        return find("cnpj", cnpj).firstResult();
    }
    public List<Postagem> findPostagensByInstituicao(String cnpj){
        Instituicao instituicao = find("cnpj", cnpj).firstResult();

        if (instituicao != null) {
            return instituicao.getPostagens(); // Assumindo que "postagens" é o nome do relacionamento na entidade Instituicao
        } else {
            return null; // ou uma lista vazia, dependendo do seu caso
        }
    }
}

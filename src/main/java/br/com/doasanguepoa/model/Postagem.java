package br.com.doasanguepoa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "postagens")
public class Postagem extends PanacheEntity {


    private String titulo;
    @NotBlank @NotEmpty @NotNull
    private String mensagem;

    @ManyToOne
    private Instituicao instituicao;

    public Postagem(){}
    public Postagem(String titulo, String mensagem, Instituicao instituicao) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.instituicao = instituicao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
}

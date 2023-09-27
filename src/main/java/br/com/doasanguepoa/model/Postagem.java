package br.com.doasanguepoa.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "postagens")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;
    private String mensagem;
    @ManyToOne
    @JsonManagedReference
    private Instituicao instituicao;

    public Postagem(String titulo, String mensagem) {
        this.titulo = titulo;
        this.mensagem = mensagem;
    }
    public Postagem(String titulo, String mensagem, Instituicao instituicao) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.instituicao = instituicao;
    }
}

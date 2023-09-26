package br.com.doasanguepoa.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "postagens")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titulo;
    private String mensagem;
    @ManyToOne
    private Instituicao instituicao;
}

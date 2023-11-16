package br.com.doasanguepoa.model;

import br.com.doasanguepoa.enuns.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Cadastro")
// gera uma unica tabela para todas as classes filhas
@Entity
//gera tabelas para cada classe filha
//@MappedSuperclass
public class Cadastro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty
    @NotBlank
    private String nome;
    @NotEmpty
    @NotBlank
    private String endereco;
    @NotEmpty
    @NotBlank
    @Email
    private String email;
    @NotEmpty
    @NotBlank
    private String senha;
    @Enumerated(EnumType.STRING)
    private Role role;


    public Cadastro(String nome, String endereco, String email, String senha, Role role) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

}

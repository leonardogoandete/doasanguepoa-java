package br.com.doasanguepoa.model;

import br.com.doasanguepoa.enuns.Role;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "instituicoes")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank @NotEmpty @NotNull
    private String nome;

    @NotBlank @NotEmpty @NotNull
    private String cnpj;
    @NotBlank @NotEmpty @NotNull
    private String endereco;
    @NotBlank @NotEmpty @NotNull
    private String telefone;

    @NotBlank @NotEmpty @NotNull
    @Email
    private String email;

    @NotBlank @NotEmpty @NotNull
    private String senha;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "instituicao")
    private List<Agendamento> agendamentos = new ArrayList<>();

    @OneToMany(mappedBy = "instituicao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Postagem> postagens = new ArrayList<>();

}

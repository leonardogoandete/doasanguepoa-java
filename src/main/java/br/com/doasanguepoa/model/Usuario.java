package br.com.doasanguepoa.model;

import br.com.doasanguepoa.enuns.Role;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {

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
    private String cpf;
    @NotEmpty
    @NotBlank
    @Email
    private String email;
    @Getter
    @NotEmpty
    @NotBlank
    private String senha;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "usuario")
    private List<Agendamento> agendamentos = new ArrayList<>();
}

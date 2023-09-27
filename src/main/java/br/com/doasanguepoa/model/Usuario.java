package br.com.doasanguepoa.model;

import br.com.doasanguepoa.enuns.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends Pessoa{

    public Usuario(String nome, String endereco, String email, String senha, String cpf) {
        super(nome, endereco, email, senha, Role.USUARIO);
        this.cpf = cpf;
        this.agendamentos = new ArrayList<>();
    }

    public Usuario(String nome, String endereco, String email, String senha, String cpf, Role role) {
        super(nome, endereco, email, senha, role);
        this.cpf = cpf;
        this.agendamentos = new ArrayList<>();
    }

    @NotEmpty
    @NotBlank
    private String cpf;

    @OneToMany(mappedBy = "usuario")
    @JsonBackReference
    private List<Agendamento> agendamentos = new ArrayList<>();

    @Override
    public String toString() {
        return super.toString() + " cpf: " + cpf;
    }
}

package br.com.doasanguepoa.model;

import br.com.doasanguepoa.enuns.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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
public class Instituicao extends Pessoa{

    public Instituicao(String nome, String endereco, String email, String senha, String cnpj) {
        super(nome, endereco, email, senha, Role.INSTITUICAO);
        this.cnpj = cnpj;
        this.agendamentos = new ArrayList<>();
        this.postagens = new ArrayList<>();
    }

    @NotBlank
    @NotEmpty
    @NotNull
    private String cnpj;

    @OneToMany(mappedBy = "instituicao")
    @JsonBackReference
    private List<Agendamento> agendamentos = new ArrayList<>();

    @OneToMany(mappedBy = "instituicao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Postagem> postagens = new ArrayList<>();

    @Override
    public String toString() {
        return super.toString() + " cnpj: " + cnpj;
    }
}

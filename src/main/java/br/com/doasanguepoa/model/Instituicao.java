package br.com.doasanguepoa.model;

import br.com.doasanguepoa.enuns.Role;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instituicoes")
public class Instituicao extends PanacheEntity {
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

    public Instituicao(){}
    public Instituicao(String nome, String cnpj, String endereco, String telefone, String email, String senha, List<Agendamento> agendamentos, List<Postagem> postagens) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.role = Role.INSTITUICAO;
        this.agendamentos = agendamentos;
        this.postagens = postagens;
    }

    public Instituicao(String nome, String cnpj, String endereco, String telefone, String email, String senha) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.role = Role.INSTITUICAO;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role.getRole();
    }

    public void setRoles(Role roles) {
        this.role = roles;
    }

    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    public List<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(List<Postagem> postagens) {
        this.postagens = postagens;
    }
}

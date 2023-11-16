package br.com.doasanguepoa.model;


import br.com.doasanguepoa.enuns.Role;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class CadastroPF extends Cadastro {

    @NotEmpty
    @NotBlank
    private String cpf;

    public CadastroPF() {
    }

    public CadastroPF(String nome, String endereco, String email, String senha, String cpf, Role role) {
        super(nome, endereco, email, senha, role);
        this.cpf = cpf;
    }

    private String getCpf() {
        return cpf;
    }

    private void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
}

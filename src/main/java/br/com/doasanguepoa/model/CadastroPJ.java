package br.com.doasanguepoa.model;


import br.com.doasanguepoa.enuns.Role;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class CadastroPJ extends Cadastro{
    
    @NotEmpty
    @NotBlank
    private String cnpj;

    public CadastroPJ() {
    }

    public CadastroPJ(String nome, String endereco, String email, String senha, String cnpj, Role role) {
        super(nome, endereco, email, senha, role);
        this.cnpj = cnpj;
    }

    private String getCnpj() {
        return cnpj;
    }

    private void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
}

package br.com.doasanguepoa.enuns;

public enum Role {
    USUARIO(1,"Usuario"),INSTITUICAO(2,"Instituicao"),ADMIN(3,"Administrador");
    private final int cod;
    private final String role;
    Role(int cod, String role){
        this.role = role;
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }

    public String getRole() {
        return role;
    }
}

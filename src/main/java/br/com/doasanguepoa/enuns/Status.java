package br.com.doasanguepoa.enuns;

public enum Status {
    PENDENTE(1,"Pendente"),CANCELADO(2,"Cancelado"),CONFIRMADO(3,"Confirmado"),CONCLUIDO(4,"Concluido");
    private final int cod;
    private final String tipo;
    Status(int cod, String tipo){
        this.tipo = tipo;
        this.cod = cod;
    }

    public int getCod() {
        return cod;
    }

    public String getTipo() {
        return tipo;
    }
}

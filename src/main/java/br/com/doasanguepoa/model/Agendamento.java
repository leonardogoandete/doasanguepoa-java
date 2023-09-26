package br.com.doasanguepoa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "agendamentos")
public class Agendamento extends PanacheEntity {
    @NotBlank @NotEmpty @NotNull
    private Date date;

    @ManyToOne
    private Instituicao instituicao;

    @NotBlank @NotEmpty @NotNull
    private String hora;
    @ManyToOne
    private Usuario usuario;

    @NotBlank @NotEmpty @NotNull
    private boolean status;
}

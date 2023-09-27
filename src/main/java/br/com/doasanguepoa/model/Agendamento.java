package br.com.doasanguepoa.model;


import br.com.doasanguepoa.enuns.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agendamentos")
public class Agendamento{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @ManyToOne
    @JsonManagedReference
    private Instituicao instituicao;

    private String hora;
    @ManyToOne
    @JsonManagedReference
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Agendamento(Date date, Instituicao instituicao, String hora, Usuario usuario) {
        this.date = date;
        this.instituicao = instituicao;
        this.hora = hora;
        this.usuario = usuario;
        this.status = Status.PENDENTE;
    }
}

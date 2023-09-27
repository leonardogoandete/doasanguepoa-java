package br.com.doasanguepoa.model;


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

    @NotBlank @NotEmpty @NotNull
    private Date date;

    @ManyToOne
    @JsonManagedReference
    private Instituicao instituicao;

    @NotBlank @NotEmpty @NotNull
    private String hora;
    @ManyToOne
    @JsonManagedReference
    private Usuario usuario;

    @NotBlank @NotEmpty @NotNull
    private boolean status;
}

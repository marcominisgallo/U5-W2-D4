package it.epicode.U5_W2_D4.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Studente {

    @Id
    @GeneratedValue
    private int matricola;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String urlImmagine;

    @ManyToOne
    @JoinColumn(name = "universita_id")
    private Universita universita;
}

package it.epicode.U5_W2_D4.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudenteDto {

    @NotEmpty(message="il nome non può essere vuoto")
    private String nome;
    @NotEmpty
    private String cognome;
    @NotNull
    private LocalDate dataNascita;
    private int universitaId;
}

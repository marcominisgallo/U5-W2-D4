package it.epicode.U5_W2_D4.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UniversitaDto {

    @NotEmpty
    private String nome;
    @NotEmpty
    private String citta;
}

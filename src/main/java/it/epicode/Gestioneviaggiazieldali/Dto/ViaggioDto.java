package it.epicode.Gestioneviaggiazieldali.Dto;

import it.epicode.Gestioneviaggiazieldali.entity.StatoViaggio;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ViaggioDto {
    @NotNull
    private String destinazione;

    @NotNull
    private LocalDate dataInizio;

    @NotNull
    private LocalDate dataFine;

    @NotBlank
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatoViaggio stato;
}

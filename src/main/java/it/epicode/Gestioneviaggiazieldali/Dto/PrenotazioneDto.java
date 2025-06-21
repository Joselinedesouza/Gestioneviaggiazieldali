package it.epicode.Gestioneviaggiazieldali.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrenotazioneDto {
    @NotNull
    private Long dipendenteId;

    @NotNull
    private Long viaggioId;

    @NotNull
    private LocalDate dataRichiesta;

    private String note;
}

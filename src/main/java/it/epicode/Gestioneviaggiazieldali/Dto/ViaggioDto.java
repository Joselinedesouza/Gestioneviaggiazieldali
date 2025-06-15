package it.epicode.Gestioneviaggiazieldali.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ViaggioDto{

    private Long id; // Per update/delete

    @NotBlank(message = "La destinazione è obbligatoria")
    private String destinazione;

    @NotNull(message = "La data è obbligatoria")
    @FutureOrPresent(message = "La data deve essere oggi o futura")
    private LocalDate data;

    @NotBlank(message = "Lo stato è obbligatorio")
    private String stato; // "in programma" o "completato"
}

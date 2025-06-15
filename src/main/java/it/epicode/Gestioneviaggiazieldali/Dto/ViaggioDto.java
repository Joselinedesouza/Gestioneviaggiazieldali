package it.epicode.Gestioneviaggiazieldali.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import it.epicode.Gestioneviaggiazieldali.entity.StatoViaggio;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViaggioDto {

    private Long id; // Per update/delete

    @NotBlank(message = "La destinazione è obbligatoria")
    private String destinazione;

    // Rimosso 'data' se non necessario

    @NotNull(message = "La data di inizio è obbligatoria")
    @FutureOrPresent(message = "La data di inizio deve essere oggi o futura")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataInizio;

    @NotNull(message = "La data di fine è obbligatoria")
    @FutureOrPresent(message = "La data di fine deve essere oggi o futura")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataFine;

    @NotNull(message = "Lo stato è obbligatorio")
    private String stato;  // Usa enum invece di String!

    @NotBlank(message = "La descrizione è obbligatoria")
    private String descrizione;
}

package it.epicode.Gestioneviaggiazieldali.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PrenotazioneDto {

    private Long id;

    @NotNull(message = "L'ID del dipendente è obbligatorio")
    private Long dipendenteId;

    @NotNull(message = "L'ID del viaggio è obbligatorio")
    private Long viaggioId;

    @NotNull(message = "La data della richiesta è obbligatoria")
    private LocalDate dataRichiesta;

    @Size(max = 500, message = "Le note non possono superare i 500 caratteri")
    private String note; // Preferenze volo, alloggio, ecc.
}

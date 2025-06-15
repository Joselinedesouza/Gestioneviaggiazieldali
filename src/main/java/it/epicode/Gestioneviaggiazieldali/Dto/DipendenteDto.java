package it.epicode.Gestioneviaggiazieldali.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class DipendenteDto {

    private Long id;  // per le operazioni di update/delete

    @NotBlank(message = "Username obbligatorio")
    private String username;

    @NotBlank(message = "Nome obbligatorio")
    private String nome;

    @NotBlank(message = "Cognome obbligatorio")
    private String cognome;

    @NotBlank(message = "Email obbligatoria")
    @Email(message = "Formato email non valido")
    private String email;

    // Cloudinary: URL dell'immagine del profilo salvata nel cloud
    private String immagineProfiloUrl;
}

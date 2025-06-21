package it.epicode.Gestioneviaggiazieldali.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Viaggio {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String descrizione;

    @NotBlank
    private String destinazione;

    @NotNull
    private LocalDate dataInizio;

    @NotNull
    private LocalDate dataFine;

    @Enumerated(EnumType.STRING)
    private StatoViaggio stato;
}

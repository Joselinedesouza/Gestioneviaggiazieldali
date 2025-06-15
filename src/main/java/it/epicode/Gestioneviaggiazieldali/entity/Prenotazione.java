package it.epicode.Gestioneviaggiazieldali.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "prenotazioni",
        uniqueConstraints = @UniqueConstraint(columnNames = {"dipendente_id", "data_richiesta"}))
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Un dipendente per ogni prenotazione
    @ManyToOne
    @JoinColumn(name = "dipendente_id", nullable = false)
    private Dipendente dipendente;

    // Un viaggio per ogni prenotazione
    @ManyToOne
    @JoinColumn(name = "viaggio_id", nullable = false)
    private Viaggio viaggio;

    @Column(name = "data_richiesta")
    private LocalDate dataRichiesta;

    private String note;

    private String preferenze;
}

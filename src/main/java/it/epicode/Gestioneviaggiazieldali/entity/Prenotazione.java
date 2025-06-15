package it.epicode.Gestioneviaggiazieldali.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="prenotazioni"
)
public class Prenotazione {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    @NotNull
    private Dipendente dipendente;

    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    @NotNull
    private Viaggio viaggio;

    @NotNull
    private LocalDate dataPrenotazione;

    private String stato;
}

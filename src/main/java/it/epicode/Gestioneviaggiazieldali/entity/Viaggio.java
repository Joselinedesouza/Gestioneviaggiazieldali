package it.epicode.Gestioneviaggiazieldali.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Viaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinazione;

    private LocalDate dataInizio;
    private LocalDate dataFine;

    private String stato; // es: "in programma", "completato"

    @ManyToMany
    @JoinTable(
            name = "viaggio_dipendente",
            joinColumns = @JoinColumn(name = "viaggio_id"),
            inverseJoinColumns = @JoinColumn(name = "dipendente_id")
    )
    private Set<Dipendente> dipendenti = new HashSet<>();
}

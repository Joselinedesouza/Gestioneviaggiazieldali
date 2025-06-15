package it.epicode.Gestioneviaggiazieldali.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "viaggio")
public class Viaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    @NotNull
    @Column(name = "data_inizio")
    private LocalDate dataInizio;

    @NotNull
    @Column(name = "data_fine")
    private LocalDate dataFine;

    @Column(name = "destinazione")
    private String destinazione;




    @Enumerated(EnumType.STRING)
    @Column(name = "stato")
    private StatoViaggio statoViaggio;

    private String descrizione;

    @OneToMany(mappedBy = "viaggio", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni;
    @ManyToMany
    @JoinTable(
            name = "dipendente_viaggio",
            joinColumns = @JoinColumn(name = "viaggio_id"),
            inverseJoinColumns = @JoinColumn(name = "dipendente_id")
    )
    private List<Dipendente> dipendenti;
}

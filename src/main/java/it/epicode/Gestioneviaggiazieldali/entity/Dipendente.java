package it.epicode.Gestioneviaggiazieldali.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dipendente")
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, unique = true)
    private String username;

    @Email
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "dipendenti")
    private Set<Viaggio> viaggi = new HashSet<>();

    @Column(name = "immagine_profilo")
    private String immagineProfilo; // URL da Cloudinary
    @OneToMany(mappedBy = "dipendente", cascade = CascadeType.ALL)
    private List<Prenotazione> prenotazioni;
}

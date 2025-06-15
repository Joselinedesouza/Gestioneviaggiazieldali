package it.epicode.Gestioneviaggiazieldali.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;

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


    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany(mappedBy = "dipendenti")
    private Set<Viaggio> viaggi = new HashSet<>();

    @Column(name = "immagine_profilo")
    private String immagineProfilo; // URL da Cloudinary
}

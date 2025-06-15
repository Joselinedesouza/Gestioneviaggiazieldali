package it.epicode.Gestioneviaggiazieldali.repository;


import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {

}

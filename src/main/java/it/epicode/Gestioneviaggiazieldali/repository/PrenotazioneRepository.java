package it.epicode.Gestioneviaggiazieldali.repository;

import it.epicode.Gestioneviaggiazieldali.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByDipendente_Id(Long dipendenteId);
    List<Prenotazione> findByViaggio_Id(Long viaggioId);
}

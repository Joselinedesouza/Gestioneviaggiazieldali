package it.epicode.Gestioneviaggiazieldali.service;

import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import it.epicode.Gestioneviaggiazieldali.entity.Viaggio;
import it.epicode.Gestioneviaggiazieldali.repository.DipendenteRepository;
import it.epicode.Gestioneviaggiazieldali.repository.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepo;

    @Autowired
    private DipendenteRepository dipendenteRepo;

    public Viaggio assegnaDipendenteAViaggio(Long viaggioId, Long dipendenteId) {
        Viaggio viaggio = viaggioRepo.findById(viaggioId)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
        Dipendente dipendente = dipendenteRepo.findById(dipendenteId)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));

        // Controllo sovrapposizione date per il dipendente
        List<Viaggio> viaggiDipendente = viaggioRepo.findByDipendenti_Id(dipendenteId);

        for (Viaggio v : viaggiDipendente) {
            if (dateSiSovrappongono(v.getDataInizio(), v.getDataFine(), viaggio.getDataInizio(), viaggio.getDataFine())) {
                throw new IllegalStateException("Dipendente già impegnato in un altro viaggio in questo intervallo di date");
            }
        }

        viaggio.getDipendenti().add(dipendente);
        return viaggioRepo.save(viaggio);
    }

    /**
     * Cambia lo stato di un viaggio.
     * @throws EntityNotFoundException se il viaggio non esiste
     */
    public Viaggio cambiaStatoViaggio(Long viaggioId, String stato) {
        Viaggio viaggio = viaggioRepo.findById(viaggioId)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
        viaggio.setStato(stato);
        return viaggioRepo.save(viaggio);
    }

    private boolean dateSiSovrappongono(LocalDate inizio1, LocalDate fine1, LocalDate inizio2, LocalDate fine2) {
        return (inizio1.isBefore(fine2) || inizio1.isEqual(fine2)) &&
                (inizio2.isBefore(fine1) || inizio2.isEqual(fine1));
    }
}

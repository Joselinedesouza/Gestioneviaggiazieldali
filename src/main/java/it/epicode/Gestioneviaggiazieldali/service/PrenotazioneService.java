package it.epicode.Gestioneviaggiazieldali.service;

import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import it.epicode.Gestioneviaggiazieldali.entity.Prenotazione;
import it.epicode.Gestioneviaggiazieldali.entity.Viaggio;
import it.epicode.Gestioneviaggiazieldali.repository.DipendenteRepository;
import it.epicode.Gestioneviaggiazieldali.repository.PrenotazioneRepository;
import it.epicode.Gestioneviaggiazieldali.repository.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepo;

    @Autowired
    private DipendenteRepository dipendenteRepo;

    @Autowired
    private ViaggioRepository viaggioRepo;


    public Prenotazione creaPrenotazione(Long dipendenteId, Long viaggioId) {
        Dipendente dipendente = dipendenteRepo.findById(dipendenteId)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        Viaggio viaggio = viaggioRepo.findById(viaggioId)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        // Controlla se il dipendente ha già prenotazioni che si sovrappongono con questo viaggio
        List<Prenotazione> prenotazioniDipendente = prenotazioneRepo.findByDipendente_Id(dipendenteId);

        for (Prenotazione p : prenotazioniDipendente) {
            Viaggio v = p.getViaggio();
            if (dateSiSovrappongono(v.getDataInizio(), v.getDataFine(), viaggio.getDataInizio(), viaggio.getDataFine())) {
                throw new IllegalStateException("Dipendente ha già una prenotazione per un viaggio in questo intervallo di date");
            }
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazione.setDataPrenotazione(LocalDate.now());
        prenotazione.setStato("confermata");

        return prenotazioneRepo.save(prenotazione);
    }

    public List<Prenotazione> getPrenotazioniByDipendente(Long dipendenteId) {
        return prenotazioneRepo.findByDipendente_Id(dipendenteId);
    }

    public List<Prenotazione> getPrenotazioniByViaggio(Long viaggioId) {
        return prenotazioneRepo.findByViaggio_Id(viaggioId);
    }

    public void cancellaPrenotazione(Long id) {
        if(!prenotazioneRepo.existsById(id)) {
            throw new EntityNotFoundException("Prenotazione non trovata");
        }
        prenotazioneRepo.deleteById(id);
    }

    private boolean dateSiSovrappongono(LocalDate inizio1, LocalDate fine1, LocalDate inizio2, LocalDate fine2) {
        return (inizio1.isBefore(fine2) || inizio1.isEqual(fine2)) &&
                (inizio2.isBefore(fine1) || inizio2.isEqual(fine1));
    }
}

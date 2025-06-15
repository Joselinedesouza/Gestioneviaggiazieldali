package it.epicode.Gestioneviaggiazieldali.Controller;

import it.epicode.Gestioneviaggiazieldali.entity.Prenotazione;
import it.epicode.Gestioneviaggiazieldali.service.PrenotazioneService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping("/dipendente/{dipendenteId}/viaggio/{viaggioId}")
    public ResponseEntity<?> creaPrenotazione(@PathVariable Long dipendenteId, @PathVariable Long viaggioId) {
        try {
            Prenotazione prenotazione = prenotazioneService.creaPrenotazione(dipendenteId, viaggioId);
            return ResponseEntity.ok(prenotazione);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/dipendente/{dipendenteId}")
    public List<Prenotazione> getPrenotazioniPerDipendente(@PathVariable Long dipendenteId) {
        return prenotazioneService.getPrenotazioniByDipendente(dipendenteId);
    }

    @GetMapping("/viaggio/{viaggioId}")
    public List<Prenotazione> getPrenotazioniPerViaggio(@PathVariable Long viaggioId) {
        return prenotazioneService.getPrenotazioniByViaggio(viaggioId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancellaPrenotazione(@PathVariable Long id) {
        try {
            prenotazioneService.cancellaPrenotazione(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

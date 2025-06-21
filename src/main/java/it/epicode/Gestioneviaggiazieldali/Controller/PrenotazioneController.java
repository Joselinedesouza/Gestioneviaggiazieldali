package it.epicode.Gestioneviaggiazieldali.Controller;


import it.epicode.Gestioneviaggiazieldali.Dto.PrenotazioneDto;
import it.epicode.Gestioneviaggiazieldali.entity.Prenotazione;
import it.epicode.Gestioneviaggiazieldali.service.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    public List<Prenotazione> getAll() {
        return prenotazioneService.findAll();
    }

    @PostMapping
    public Prenotazione create(@RequestBody @Valid PrenotazioneDto dto) {
        return prenotazioneService.create(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrenotazione(@PathVariable Long id) {
        prenotazioneService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

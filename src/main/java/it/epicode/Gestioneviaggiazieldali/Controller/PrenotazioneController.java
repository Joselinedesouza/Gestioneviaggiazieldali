package it.epicode.Gestioneviaggiazieldali.Controller;

import it.epicode.Gestioneviaggiazieldali.Dto.PrenotazioneDto;
import it.epicode.Gestioneviaggiazieldali.service.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    private final PrenotazioneService prenotazioneService;

    public PrenotazioneController(PrenotazioneService prenotazioneService) {
        this.prenotazioneService = prenotazioneService;
    }

    @GetMapping
    public List<PrenotazioneDto> getAll() {
        return prenotazioneService.trovaTutti();
    }

    @GetMapping("/{id}")
    public PrenotazioneDto getById(@PathVariable Long id) {
        return prenotazioneService.trovaPerId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneDto creaPrenotazione(@RequestBody @Valid PrenotazioneDto prenotazioneDto) {
        return prenotazioneService.salva(prenotazioneDto);
    }

    @PutMapping("/{id}")
    public PrenotazioneDto aggiorna(@PathVariable Long id,
                                                @RequestBody @Valid PrenotazioneDto prenotazioneDto) {
        return prenotazioneService.aggiorna(id, prenotazioneDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaPrenotazione(@PathVariable Long id) {
        prenotazioneService.elimina(id);
    }
}

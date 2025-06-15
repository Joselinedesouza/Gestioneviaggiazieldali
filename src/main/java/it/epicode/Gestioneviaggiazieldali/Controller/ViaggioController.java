package it.epicode.Gestioneviaggiazieldali.Controller;

import it.epicode.Gestioneviaggiazieldali.Dto.ViaggioDto;
import it.epicode.Gestioneviaggiazieldali.service.ViaggioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
public class ViaggioController {

    private final ViaggioService viaggioService;

    public ViaggioController(ViaggioService viaggioService) {
        this.viaggioService = viaggioService;
    }

    // Trova tutti i viaggi
    @GetMapping
    public List<ViaggioDto> getTuttiViaggi() {
        return viaggioService.trovaTutti();
    }

    // Trova viaggio per ID
    @GetMapping("/{id}")
    public ViaggioDto trovaPerId(@PathVariable Long id) {
        return viaggioService.trovaPerId(id);
    }

    // Crea un nuovo viaggio
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ViaggioDto salva(@Valid @RequestBody ViaggioDto dto) {
        return viaggioService.salva(dto);
    }

    // Aggiorna un viaggio esistente
    @PutMapping("/{id}")
    public ViaggioDto aggiorna(@PathVariable Long id, @Valid @RequestBody ViaggioDto dto) {
        return viaggioService.aggiorna(id, dto);
    }

    // Elimina un viaggio
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void elimina(@PathVariable Long id) {
        viaggioService.elimina(id);
    }

    // Cambia lo stato del viaggio
    @PatchMapping("/{id}/stato")
    public ViaggioDto cambiaStato(@PathVariable Long id, @RequestParam String stato) {
        return viaggioService.cambiaStato(id, stato);
    }
}

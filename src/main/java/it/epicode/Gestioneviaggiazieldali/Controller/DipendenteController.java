package it.epicode.Gestioneviaggiazieldali.Controller;

import it.epicode.Gestioneviaggiazieldali.Dto.DipendenteDto;
import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import it.epicode.Gestioneviaggiazieldali.service.DipendenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    private final DipendenteService dipendenteService;

    public DipendenteController(DipendenteService dipendenteService) {
        this.dipendenteService = dipendenteService;
    }


    @GetMapping
    public ResponseEntity<List<DipendenteDto>> getAll() {
        return ResponseEntity.ok(dipendenteService.trovaTutti());
    }

    // GET per ID
    @GetMapping("/{id}")
    public ResponseEntity<DipendenteDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dipendenteService.trovaPerId(id));
    }

    // Crea nuovo dipendente
    @PostMapping
    public ResponseEntity<DipendenteDto> crea(@RequestBody DipendenteDto dto) {
        return new ResponseEntity<>(dipendenteService.salva(dto), HttpStatus.CREATED);
    }

    // Aggiorna dipendente
    @PutMapping("/{id}")
    public ResponseEntity<DipendenteDto> aggiorna(@PathVariable Long id, @RequestBody DipendenteDto dto) {
        return ResponseEntity.ok(dipendenteService.aggiorna(id, dto));
    }

    //  Elimina dipendente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> elimina(@PathVariable Long id) {
        dipendenteService.elimina(id);
        return ResponseEntity.noContent().build();
    }

    //  Upload immagine profilo (Cloudinary)
    @PostMapping("/{id}/immagine")
    public ResponseEntity<String> aggiornaImmagineProfilo(@PathVariable Long id,
                                                          @RequestParam("file") MultipartFile file) throws IOException {
        Dipendente dip = dipendenteService.aggiornaImmagineProfilo(id, file);
        return new ResponseEntity<>("Immagine aggiornata con successo: " + dip.getImmagineProfilo(), HttpStatus.CREATED);
    }
}

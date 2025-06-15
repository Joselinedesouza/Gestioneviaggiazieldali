package it.epicode.Gestioneviaggiazieldali.Controller;

import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import it.epicode.Gestioneviaggiazieldali.entity.Viaggio;
import it.epicode.Gestioneviaggiazieldali.repository.DipendenteRepository;
import it.epicode.Gestioneviaggiazieldali.repository.ViaggioRepository;
import it.epicode.Gestioneviaggiazieldali.service.ViaggioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path; // <--- Import corretto per Path
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ViaggioController {

    @Autowired
    private ViaggioRepository viaggioRepo;

    @Autowired
    private DipendenteRepository dipRepo;

    @Autowired
    private ViaggioService viaggioService;

    // CRUD Viaggi
    @GetMapping("/viaggi")
    public List<Viaggio> getViaggi() {
        return viaggioRepo.findAll();
    }

    @PostMapping("/viaggi")
    public Viaggio createViaggio(@Valid @RequestBody Viaggio viaggio) {
        return viaggioRepo.save(viaggio);
    }

    @PutMapping("/viaggi/{id}")
    public Viaggio updateViaggio(@PathVariable Long id, @Valid @RequestBody Viaggio viaggioDetails) {
        Viaggio viaggio = viaggioRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
        viaggio.setDestinazione(viaggioDetails.getDestinazione());
        viaggio.setDataInizio(viaggioDetails.getDataInizio());
        viaggio.setDataFine(viaggioDetails.getDataFine());
        viaggio.setStato(viaggioDetails.getStato());
        return viaggioRepo.save(viaggio);
    }

    @DeleteMapping("/viaggi/{id}")
    public ResponseEntity<?> deleteViaggio(@PathVariable Long id) {
        viaggioRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Assegna dipendente a viaggio
    @PostMapping("/viaggi/{viaggioId}/assegna/{dipendenteId}")
    public ResponseEntity<?> assegnaDipendente(@PathVariable Long viaggioId, @PathVariable Long dipendenteId) {
        try {
            Viaggio viaggio = viaggioService.assegnaDipendenteAViaggio(viaggioId, dipendenteId);
            return ResponseEntity.ok(viaggio);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Cambia stato viaggio
    @PatchMapping("/viaggi/{id}/stato")
    public ResponseEntity<?> cambiaStato(@PathVariable Long id, @RequestParam String stato) {
        try {
            Viaggio viaggio = viaggioService.cambiaStatoViaggio(id, stato);
            return ResponseEntity.ok(viaggio);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Upload immagine profilo dipendente
    @PostMapping("/viaggio/{id}/uploadFoto")
    public ResponseEntity<?> uploadFoto(@PathVariable Long id, @RequestParam MultipartFile file) {
        Dipendente dipendente = dipRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File vuoto");
        }

        try {
            String filename = UUID.randomUUID() + "-" + file.getOriginalFilename();
            Path uploadPath = Paths.get("uploads");
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(filename);
            Files.write(filePath, file.getBytes());

            dipendente.setImmagineProfilo(filename);
            dipRepo.save(dipendente);

            return ResponseEntity.ok("Immagine caricata con successo");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore salvataggio file");
        }
    }
}

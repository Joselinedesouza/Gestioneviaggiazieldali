package it.epicode.Gestioneviaggiazieldali.Controller;

import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import it.epicode.Gestioneviaggiazieldali.repository.DipendenteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteRepository dipendenteRepo;

    @GetMapping
    public List<Dipendente> getAllDipendenti() {
        return dipendenteRepo.findAll();
    }

    @PostMapping
    public Dipendente createDipendente(@Valid @RequestBody Dipendente dipendente) {
        return dipendenteRepo.save(dipendente);
    }

    @PutMapping("/{id}")
    public Dipendente updateDipendente(@PathVariable Long id, @Valid @RequestBody Dipendente dipendenteDetails) {
        Dipendente dipendente = dipendenteRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        dipendente.setNome(dipendenteDetails.getNome());
        dipendente.setCognome(dipendenteDetails.getCognome());
        dipendente.setEmail(dipendenteDetails.getEmail());

        return dipendenteRepo.save(dipendente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDipendente(@PathVariable Long id) {
        dipendenteRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Upload immagine profilo (path modificato)
    @PostMapping("/{id}/uploadFoto")
    public ResponseEntity<?> uploadFoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Dipendente dipendente = dipendenteRepo.findById(id)
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
            dipendenteRepo.save(dipendente);

            return ResponseEntity.ok("Immagine caricata con successo");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore salvataggio file");
        }
    }
}

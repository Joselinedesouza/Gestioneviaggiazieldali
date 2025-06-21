package it.epicode.Gestioneviaggiazieldali.Controller;

import it.epicode.Gestioneviaggiazieldali.Dto.DipendenteDto;
import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import it.epicode.Gestioneviaggiazieldali.service.DipendenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public List<Dipendente> getAll() {
        return dipendenteService.findAll();
    }

    @GetMapping("/{id}")
    public Dipendente getById(@PathVariable Long id) {
        return dipendenteService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Dipendente> crea(@RequestBody @Valid DipendenteDto dto) {
        Dipendente nuovo = dipendenteService.create(dto);
        return new ResponseEntity<>(nuovo, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public Dipendente update(@PathVariable Long id, @RequestBody @Valid DipendenteDto dto) {
        return dipendenteService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dipendenteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/uploadFoto")
    public Dipendente uploadFoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        return dipendenteService.uploadImmagine(id, file);
    }
}

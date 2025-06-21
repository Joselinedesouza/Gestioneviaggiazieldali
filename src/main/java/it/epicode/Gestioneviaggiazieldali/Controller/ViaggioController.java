package it.epicode.Gestioneviaggiazieldali.Controller;

import it.epicode.Gestioneviaggiazieldali.Dto.ViaggioDto;
import it.epicode.Gestioneviaggiazieldali.entity.StatoViaggio;
import it.epicode.Gestioneviaggiazieldali.entity.Viaggio;
import it.epicode.Gestioneviaggiazieldali.service.ViaggioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public List<Viaggio> getAll() {
        return viaggioService.findAll();
    }

    @GetMapping("/{id}")
    public Viaggio getById(@PathVariable Long id) {
        return viaggioService.findById(id);
    }

    @PostMapping
    public Viaggio create(@RequestBody @Valid ViaggioDto dto) {
        return viaggioService.create(dto);
    }

    @PutMapping("/{id}")
    public Viaggio update(@PathVariable Long id, @RequestBody @Valid ViaggioDto dto) {
        return viaggioService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        viaggioService.delete(id);
    }

    @PatchMapping("/{id}/stato")
    public Viaggio cambiaStato(@PathVariable Long id, @RequestParam StatoViaggio stato) {
        return viaggioService.aggiornaStato(id, stato);
    }
}

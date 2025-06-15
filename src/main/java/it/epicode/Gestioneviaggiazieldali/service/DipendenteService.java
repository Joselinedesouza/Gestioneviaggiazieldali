package it.epicode.Gestioneviaggiazieldali.service;

import it.epicode.Gestioneviaggiazieldali.Exception.ExceptionNotFound;
import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import it.epicode.Gestioneviaggiazieldali.repository.DipendenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;

    public List<Dipendente> getAll() {
        return dipendenteRepository.findAll();
    }

    public Dipendente getById(final Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("Dipendente non trovato con id: " + id));
    }

    public Dipendente create(final Dipendente dipendente) {
        return dipendenteRepository.save(dipendente);
    }

    public Dipendente update(final Long id, final Dipendente updated) {
        Dipendente existing = getById(id);
        existing.setNome(updated.getNome());
        existing.setCognome(updated.getCognome());
        existing.setUsername(updated.getUsername());
        existing.setEmail(updated.getEmail());
        existing.setImmagineProfilo(updated.getImmagineProfilo());
        return dipendenteRepository.save(existing);
    }

    public void delete(final Long id) {
        if(!dipendenteRepository.existsById(id)) {
            throw new ExceptionNotFound("Dipendente non trovato con id: " + id);
        }
        dipendenteRepository.deleteById(id);
    }
}

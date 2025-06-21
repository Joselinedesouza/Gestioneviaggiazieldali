package it.epicode.Gestioneviaggiazieldali.service;


import it.epicode.Gestioneviaggiazieldali.Dto.PrenotazioneDto;
import it.epicode.Gestioneviaggiazieldali.Exception.BadRequestException;
import it.epicode.Gestioneviaggiazieldali.Exception.ExceptionNotFound;
import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import it.epicode.Gestioneviaggiazieldali.entity.Prenotazione;
import it.epicode.Gestioneviaggiazieldali.entity.Viaggio;
import it.epicode.Gestioneviaggiazieldali.repository.DipendenteRepository;
import it.epicode.Gestioneviaggiazieldali.repository.PrenotazioneRepository;
import it.epicode.Gestioneviaggiazieldali.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    public List<Prenotazione> findAll() {
        return prenotazioneRepository.findAll();
    }

    public Prenotazione create(PrenotazioneDto dto) {
        Dipendente d = dipendenteRepository.findById(dto.getDipendenteId())
                .orElseThrow(() -> new ExceptionNotFound("Dipendente non trovato"));
        Viaggio v = viaggioRepository.findById(dto.getViaggioId())
                .orElseThrow(() -> new ExceptionNotFound("Viaggio non trovato"));

        if (prenotazioneRepository.existsByDipendenteAndDataRichiesta(d, dto.getDataRichiesta())) {
            throw new BadRequestException("Prenotazione già esistente per questo dipendente in quella data");
        }

        Prenotazione p = new Prenotazione();
        p.setDipendente(d);
        p.setViaggio(v);
        p.setDataRichiesta(dto.getDataRichiesta());
        p.setNote(dto.getNote());

        return prenotazioneRepository.save(p);
    }
    public void delete(Long id) {
        Prenotazione p = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("Prenotazione non trovata con id: " + id));
        prenotazioneRepository.delete(p);
    }
}

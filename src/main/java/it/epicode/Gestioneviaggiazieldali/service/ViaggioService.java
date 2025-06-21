package it.epicode.Gestioneviaggiazieldali.service;


import it.epicode.Gestioneviaggiazieldali.Dto.ViaggioDto;
import it.epicode.Gestioneviaggiazieldali.Exception.ExceptionNotFound;
import it.epicode.Gestioneviaggiazieldali.entity.StatoViaggio;
import it.epicode.Gestioneviaggiazieldali.entity.Viaggio;
import it.epicode.Gestioneviaggiazieldali.repository.ViaggioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepo;

    public List<Viaggio> findAll() {
        return viaggioRepo.findAll();
    }

    public Viaggio findById(Long id) {
        return viaggioRepo.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("Viaggio non trovato con id: " + id));
    }

    public Viaggio create(ViaggioDto dto) {
        Viaggio v = new Viaggio();
        v.setDataInizio(dto.getDataInizio());
        v.setDataFine(dto.getDataFine());
        v.setDescrizione(dto.getDescrizione());
        v.setDestinazione(dto.getDestinazione());
        v.setStato(dto.getStato());
        return viaggioRepo.save(v);
    }

    public Viaggio update(Long id, ViaggioDto dto) {
        Viaggio v = findById(id);
        v.setDestinazione(dto.getDestinazione());
        v.setDataInizio(dto.getDataInizio());
        v.setDataFine(dto.getDataFine());
        return viaggioRepo.save(v);
    }

    public void delete(Long id) {
        Viaggio v = findById(id);
        viaggioRepo.delete(v);
    }

    public Viaggio aggiornaStato(Long id, StatoViaggio nuovoStato) {
        Viaggio v = findById(id);
        v.setStato(nuovoStato);
        return viaggioRepo.save(v);
    }
}

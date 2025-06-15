package it.epicode.Gestioneviaggiazieldali.service;


import it.epicode.Gestioneviaggiazieldali.Dto.ViaggioDto;
import it.epicode.Gestioneviaggiazieldali.Mapper.ViaggioMapper;
import it.epicode.Gestioneviaggiazieldali.entity.StatoViaggio;
import it.epicode.Gestioneviaggiazieldali.entity.Viaggio;
import it.epicode.Gestioneviaggiazieldali.repository.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {

    private final ViaggioRepository viaggioRepository;
    private final ViaggioMapper viaggioMapper;

    public ViaggioService(ViaggioRepository viaggioRepository, ViaggioMapper viaggioMapper) {
        this.viaggioRepository = viaggioRepository;
        this.viaggioMapper = viaggioMapper;
    }

    public List<ViaggioDto> trovaTutti() {
        return viaggioRepository.findAll().stream()
                .map(viaggioMapper::toDto)
                .toList();
    }

    public ViaggioDto trovaPerId(Long id) {
        return viaggioMapper.toDto(
                viaggioRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"))
        );
    }

    public ViaggioDto salva(ViaggioDto dto) {
        Viaggio viaggio = viaggioMapper.toEntity(dto);
        return viaggioMapper.toDto(viaggioRepository.save(viaggio));
    }

    public ViaggioDto aggiorna(Long id, ViaggioDto dto) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        viaggio.setDestinazione(dto.getDestinazione());
        viaggio.setDataInizio(dto.getDataInizio());
        viaggio.setDataFine(dto.getDataFine());
        // converto stato String -> enum
        try {
            viaggio.setStatoViaggio(StatoViaggio.valueOf(dto.getStato().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Stato non valido: " + dto.getStato());
        }
        viaggio.setDescrizione(dto.getDescrizione());

        return viaggioMapper.toDto(viaggioRepository.save(viaggio));
    }

    public void elimina(Long id) {
        viaggioRepository.deleteById(id);
    }

    public ViaggioDto cambiaStato(Long id, String nuovoStato) {
        Viaggio viaggio = viaggioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        if (nuovoStato == null) {
            throw new IllegalArgumentException("Lo stato non può essere null");
        }

        try {
            viaggio.setStatoViaggio(StatoViaggio.valueOf(nuovoStato.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Stato non valido: " + nuovoStato);
        }

        return viaggioMapper.toDto(viaggioRepository.save(viaggio));
    }

}

package it.epicode.Gestioneviaggiazieldali.service;

import it.epicode.Gestioneviaggiazieldali.Dto.PrenotazioneDto;
import it.epicode.Gestioneviaggiazieldali.Mapper.PrenotazioneMapper;
import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import it.epicode.Gestioneviaggiazieldali.entity.Prenotazione;
import it.epicode.Gestioneviaggiazieldali.entity.Viaggio;
import it.epicode.Gestioneviaggiazieldali.repository.DipendenteRepository;
import it.epicode.Gestioneviaggiazieldali.repository.PrenotazioneRepository;
import it.epicode.Gestioneviaggiazieldali.repository.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    private final PrenotazioneRepository prenotazioneRepository;
    private final PrenotazioneMapper prenotazioneMapper;
    private final DipendenteRepository dipendenteRepository;
    private final ViaggioRepository viaggioRepository;

    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository,
                               PrenotazioneMapper prenotazioneMapper,
                               DipendenteRepository dipendenteRepository,
                               ViaggioRepository viaggioRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.prenotazioneMapper = prenotazioneMapper;
        this.dipendenteRepository = dipendenteRepository;
        this.viaggioRepository = viaggioRepository;
    }

    public List<PrenotazioneDto> trovaTutti() {
        return prenotazioneRepository.findAll().stream()
                .map(prenotazioneMapper::toDto)
                .toList();
    }

    public PrenotazioneDto trovaPerId(Long id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));
        return prenotazioneMapper.toDto(prenotazione);
    }

    public PrenotazioneDto salva(PrenotazioneDto dto) {
        Prenotazione prenotazione = new Prenotazione();

        Dipendente dipendente = dipendenteRepository.findById(dto.getDipendenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        prenotazione.setDipendente(dipendente);

        Viaggio viaggio = viaggioRepository.findById(dto.getViaggioId())
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
        prenotazione.setViaggio(viaggio);

        prenotazione.setDataRichiesta(dto.getDataRichiesta());
        prenotazione.setNote(dto.getNote());
        prenotazione.setPreferenze(dto.getPreferenze());

        return prenotazioneMapper.toDto(prenotazioneRepository.save(prenotazione));
    }

    public PrenotazioneDto aggiorna(Long id, PrenotazioneDto dto) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));

        prenotazione.setDataRichiesta(dto.getDataRichiesta());
        prenotazione.setNote(dto.getNote());
        prenotazione.setPreferenze(dto.getPreferenze());

        Dipendente dipendente = dipendenteRepository.findById(dto.getDipendenteId())
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        prenotazione.setDipendente(dipendente);

        Viaggio viaggio = viaggioRepository.findById(dto.getViaggioId())
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));
        prenotazione.setViaggio(viaggio);

        return prenotazioneMapper.toDto(prenotazioneRepository.save(prenotazione));
    }

    public void elimina(Long id) {
        prenotazioneRepository.deleteById(id);
    }
}

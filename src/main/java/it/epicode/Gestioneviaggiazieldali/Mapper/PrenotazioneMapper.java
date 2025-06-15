package it.epicode.Gestioneviaggiazieldali.Mapper;

import it.epicode.Gestioneviaggiazieldali.Dto.PrenotazioneDto;
import it.epicode.Gestioneviaggiazieldali.entity.Prenotazione;
import org.springframework.stereotype.Component;

@Component
public class PrenotazioneMapper {

    public PrenotazioneDto toDto(Prenotazione p) {
        PrenotazioneDto dto = new PrenotazioneDto();
        dto.setId(p.getId());
        dto.setDipendenteId(p.getDipendente().getId());
        dto.setViaggioId(p.getViaggio().getId());
        dto.setDataRichiesta(p.getDataRichiesta());
        dto.setNote(p.getNote());
        dto.setPreferenze(p.getPreferenze());
        return dto;
    }

    public Prenotazione toEntity(PrenotazioneDto dto) {
        Prenotazione p = new Prenotazione();
        p.setDataRichiesta(dto.getDataRichiesta());
        p.setNote(dto.getNote());
        p.setPreferenze(dto.getPreferenze());
        return p;
    }
}

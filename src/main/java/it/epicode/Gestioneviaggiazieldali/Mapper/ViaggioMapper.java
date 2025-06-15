package it.epicode.Gestioneviaggiazieldali.Mapper;

import it.epicode.Gestioneviaggiazieldali.Dto.ViaggioDto;
import it.epicode.Gestioneviaggiazieldali.entity.Viaggio;
import org.springframework.stereotype.Component;

@Component
public class ViaggioMapper {

    public ViaggioDto toDto(Viaggio viaggio) {
        ViaggioDto dto = new ViaggioDto();
        dto.setId(viaggio.getId());
        dto.setDestinazione(viaggio.getDestinazione());
        dto.setDataInizio(viaggio.getDataInizio());
        dto.setDataFine(viaggio.getDataFine());
        dto.setDescrizione(dto.getDescrizione());
        return dto;
    }

    public Viaggio toEntity(ViaggioDto dto) {
        Viaggio viaggio = new Viaggio();
        viaggio.setId(dto.getId());
        viaggio.setDestinazione(dto.getDestinazione());
        viaggio.setDataInizio(dto.getDataInizio());
        viaggio.setDataFine(dto.getDataFine());
        viaggio.setDescrizione(dto.getDescrizione());
        return viaggio;
    }
}

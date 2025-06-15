package it.epicode.Gestioneviaggiazieldali.Mapper;


import it.epicode.Gestioneviaggiazieldali.Dto.DipendenteDto;
import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import org.springframework.stereotype.Component;

@Component
public class DipendenteMapper {

    public DipendenteDto toDto(Dipendente dipendente) {
        DipendenteDto dto = new DipendenteDto();
        dto.setId(dipendente.getId());
        dto.setNome(dipendente.getNome());
        dto.setCognome(dipendente.getCognome());
        dto.setEmail(dipendente.getEmail());
        dto.setUsername(dipendente.getUsername());
        dto.setImmagineProfilo(dipendente.getImmagineProfilo());
        return dto;
    }

    public Dipendente toEntity(DipendenteDto dto) {
        Dipendente dipendente = new Dipendente();
        dipendente.setId(dto.getId());
        dipendente.setNome(dto.getNome());
        dipendente.setCognome(dto.getCognome());
        dipendente.setEmail(dto.getEmail());
        dipendente.setUsername(dto.getUsername());
        dipendente.setImmagineProfilo(dto.getImmagineProfilo());
        return dipendente;
    }
}

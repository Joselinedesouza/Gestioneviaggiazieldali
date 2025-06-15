package it.epicode.Gestioneviaggiazieldali.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.Gestioneviaggiazieldali.Dto.DipendenteDto;
import it.epicode.Gestioneviaggiazieldali.Mapper.DipendenteMapper;
import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import it.epicode.Gestioneviaggiazieldali.repository.DipendenteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DipendenteService {

    private final DipendenteRepository dipendenteRepository;
    private final DipendenteMapper dipendenteMapper;
    private final Cloudinary cloudinary;

    public DipendenteService(DipendenteRepository dipendenteRepository, DipendenteMapper dipendenteMapper, Cloudinary cloudinary) {
        this.dipendenteRepository = dipendenteRepository;
        this.dipendenteMapper = dipendenteMapper;
        this.cloudinary = cloudinary;
    }

    public List<DipendenteDto> trovaTutti() {
        return dipendenteRepository.findAll().stream()
                .map(dipendenteMapper::toDto)
                .toList();
    }

    public DipendenteDto trovaPerId(Long id) {
        return dipendenteMapper.toDto(
                dipendenteRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato con id: " + id))
        );
    }

    public DipendenteDto salva(DipendenteDto dto) {
        Dipendente dip = dipendenteMapper.toEntity(dto);
        return dipendenteMapper.toDto(dipendenteRepository.save(dip));
    }

    public DipendenteDto aggiorna(Long id, DipendenteDto dto) {
        Dipendente dip = dipendenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));
        dip.setNome(dto.getNome());
        dip.setCognome(dto.getCognome());
        dip.setEmail(dto.getEmail());
        dip.setUsername(dto.getUsername());
        return dipendenteMapper.toDto(dipendenteRepository.save(dip));
    }

    public void elimina(Long id) {
        dipendenteRepository.deleteById(id);
    }

    public Dipendente aggiornaImmagineProfilo(Long id, MultipartFile file) throws IOException {
        Dipendente dipendente = dipendenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));

        var uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("folder", "dipendenti"));

        String url = (String) uploadResult.get("secure_url");
        dipendente.setImmagineProfilo(url);
        return dipendenteRepository.save(dipendente);
    }
}
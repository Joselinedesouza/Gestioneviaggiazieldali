package it.epicode.Gestioneviaggiazieldali.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.Gestioneviaggiazieldali.Dto.DipendenteDto;
import it.epicode.Gestioneviaggiazieldali.Exception.ExceptionNotFound;
import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import it.epicode.Gestioneviaggiazieldali.repository.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepo;

    @Autowired
    private Cloudinary cloudinary;

    public List<Dipendente> findAll() {
        return dipendenteRepo.findAll();
    }

    public Dipendente findById(Long id) {
        return dipendenteRepo.findById(id)
                .orElseThrow(() -> new ExceptionNotFound("Dipendente non trovato con id: " + id));
    }

    public Dipendente create(DipendenteDto dto) {
        Dipendente d = new Dipendente();
        d.setUsername(dto.getUsername());
        d.setNome(dto.getNome());
        d.setCognome(dto.getCognome());
        d.setEmail(dto.getEmail());
        return dipendenteRepo.save(d);
    }

    public Dipendente update(Long id, DipendenteDto dto) {
        Dipendente d = findById(id);
        d.setUsername(dto.getUsername());
        d.setNome(dto.getNome());
        d.setCognome(dto.getCognome());
        d.setEmail(dto.getEmail());
        return dipendenteRepo.save(d);
    }

    public void delete(Long id) {
        Dipendente d = findById(id);
        dipendenteRepo.delete(d);
    }

    public Dipendente uploadImmagine(Long id, MultipartFile file) throws IOException {
        Dipendente d = findById(id);
        var uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String url = (String) uploadResult.get("url");
        d.setImmagineProfiloUrl(url);
        return dipendenteRepo.save(d);
    }
}

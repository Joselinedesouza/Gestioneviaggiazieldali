package it.epicode.Gestioneviaggiazieldali;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.Gestioneviaggiazieldali.Exception.ExceptionNotFound;
import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import it.epicode.Gestioneviaggiazieldali.repository.DipendenteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final Cloudinary cloudinary;
    private final DipendenteRepository dipendenteRepository;

    public String upload(Long dipendenteId, MultipartFile file) throws IOException {
        Dipendente dipendente = dipendenteRepository.findById(dipendenteId)
                .orElseThrow(() -> new ExceptionNotFound("Dipendente non trovato"));

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                ObjectUtils.asMap("folder", "dipendenti"));

        String imageUrl = uploadResult.get("secure_url").toString();
        dipendente.setImmagineProfilo(imageUrl);
        dipendenteRepository.save(dipendente);

        return imageUrl;
    }
}

package it.epicode.Gestioneviaggiazieldali.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import it.epicode.Gestioneviaggiazieldali.entity.Dipendente;
import it.epicode.Gestioneviaggiazieldali.repository.DipendenteRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class StorageService {

    private final Cloudinary cloudinary;
    private final DipendenteRepository dipendenteRepository;

    public StorageService(DipendenteRepository dipendenteRepository) {
        this.dipendenteRepository = dipendenteRepository;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "tuo_cloud_name",
                "api_key", "la_tua_api_key",
                "api_secret", "la_tua_api_secret"
        ));
    }

    public String store(MultipartFile file, Long dipendenteId) {
        try {
            Dipendente dipendente = dipendenteRepository.findById(dipendenteId)
                    .orElseThrow(() -> new RuntimeException("Dipendente non trovato"));

            Map uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap("folder", "dipendenti/" + dipendenteId));

            String imageUrl = (String) uploadResult.get("secure_url");

            dipendente.setImmagineProfilo(imageUrl);
            dipendenteRepository.save(dipendente);

            return imageUrl;
        } catch (Exception e) {
            throw new RuntimeException("Errore durante l'upload su Cloudinary", e);
        }
    }
}

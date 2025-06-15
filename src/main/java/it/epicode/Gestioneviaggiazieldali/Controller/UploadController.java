// controller/UploadController.java
package it.epicode.Gestioneviaggiazieldali.Controller;


import it.epicode.Gestioneviaggiazieldali.service.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    private final StorageService storageService;

    public UploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/immagine-profilo")
    public ResponseEntity<String> uploadImmagineProfilo(@RequestParam Long dipendenteId,
                                                        @RequestParam MultipartFile file) {
        String url = storageService.store(file, dipendenteId);
        return new ResponseEntity<>(url, HttpStatus.CREATED);
    }
}

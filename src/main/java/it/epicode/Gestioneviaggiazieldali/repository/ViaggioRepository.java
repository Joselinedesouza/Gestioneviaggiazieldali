package it.epicode.Gestioneviaggiazieldali.repository;

import it.epicode.Gestioneviaggiazieldali.entity.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {

}
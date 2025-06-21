package it.epicode.Gestioneviaggiazieldali.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Dipendente {
  @Id
  @GeneratedValue
  private Long id;

  @NotBlank
  private String username;

  @NotBlank
  private String nome;

  @NotBlank
  private String cognome;

  @Email
  private String email;

  private String immagineProfiloUrl;
}
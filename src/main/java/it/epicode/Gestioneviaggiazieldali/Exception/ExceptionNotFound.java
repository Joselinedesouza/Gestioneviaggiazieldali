package it.epicode.Gestioneviaggiazieldali.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class ExceptionNotFound extends RuntimeException {
    public ExceptionNotFound(String message) {
        super(message);
    }
}


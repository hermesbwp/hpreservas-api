package com.hp.hpreservasapi.exception;

import com.hp.hpreservasapi.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NotFoundException extends Exception{
    public NotFoundException(){
        super("Elemento nao encontrado");
    }

    public ResponseEntity throwNotFoundException(){
        return ResponseEntity.notFound().build();
        //return new ResponseEntity<>(new Object(), HttpStatus.NOT_FOUND);
    }
}

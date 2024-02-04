package com.hp.hpreservasapi.exception;

import org.springframework.http.ResponseEntity;

public class DuplicateException extends Exception{
    public DuplicateException(){
        super("Objeto duplicado");
    }

    public ResponseEntity throwDuplicateException(){
        return ResponseEntity.badRequest().build();
    }
}

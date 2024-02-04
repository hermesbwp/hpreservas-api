package com.hp.hpreservasapi.exception;

import com.hp.hpreservasapi.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NotFoundException extends Exception{
    public NotFoundException(){
        super("Elemento nao encontrado");
    }

    public ResponseEntity throwNotFoundException(Long id){
        System.out.println("O identificador "+id+" não existe para o objeto pesquisado.");
        return ResponseEntity.notFound().build();
    }
}

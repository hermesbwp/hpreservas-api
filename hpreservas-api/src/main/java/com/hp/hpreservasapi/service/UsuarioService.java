package com.hp.hpreservasapi.service;

import com.hp.hpreservasapi.exception.NotFoundException;
import com.hp.hpreservasapi.model.Usuario;
import com.hp.hpreservasapi.repository.IUsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    IUsuarioRepository usuarioRepository;
    public Usuario add(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public List<Usuario> todos(){
        return usuarioRepository.findAll();
    }
    public String delete(Long id){
        usuarioRepository.deleteById(id);
        return null;
    }
    public Usuario edit(Usuario usuario,Long id) throws NotFoundException {
        return usuarioRepository.findById(id).map(usrBd->{
            usrBd.setEmail(usuario.getEmail());
            usrBd.setNome(usuario.getNome());
            usrBd.setSenha(usuario.getSenha());
            return usuarioRepository.save(usrBd);
        }).orElseThrow(NotFoundException::new);
    }

    public Usuario get(Long id) throws NotFoundException {
        return usuarioRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}

package com.hp.hpreservasapi.service;

import com.hp.hpreservasapi.model.Usuario;
import com.hp.hpreservasapi.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    IUsuarioRepository usuarioRepository;
    public Usuario add(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public void todos(){
        usuarioRepository.findAll();
    }
    public void delete(Long id){
        usuarioRepository.deleteById(id);
    }
    public Usuario edit(Usuario usuario,Long id){
        return usuarioRepository.findById(id).map(usrBd->{
            usrBd.setEmail(usuario.getEmail());
            usrBd.setNome(usuario.getNome());
            usrBd.setSenha(usuario.getSenha());
            return usuarioRepository.save(usrBd);
        }).orElseThrow();
    }
}

package com.hp.hpreservasapi.service;

import com.hp.hpreservasapi.exception.DuplicateException;
import com.hp.hpreservasapi.exception.NotFoundException;
import com.hp.hpreservasapi.model.Usuario;
import com.hp.hpreservasapi.repository.IQuartoRepository;
import com.hp.hpreservasapi.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    IQuartoRepository quartoRepository;
    public Usuario add(Usuario usuario) throws Exception{
        var u = usuarioRepository.findAll().stream()
                .filter(usr-> usr.getNome().equalsIgnoreCase(usuario.getNome())
                        && usr.getEmail().equalsIgnoreCase(usuario.getEmail())).toList();

        if (u.isEmpty()) {
            usuarioRepository.save(usuario);
        }else{
            System.out.println("Elemento ja cadastrado " + usuario.getNome());
            throw new DuplicateException();
        }
        return usuario;
    }
    public List<Usuario> todos(){
        return usuarioRepository.findAll();
    }
    public Usuario delete(Long id) throws NotFoundException {
        Usuario usr = this.get(id);
        if(usr!=null){
        usuarioRepository.deleteById(id);
        }
        return usr;
    }
    @Transactional
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

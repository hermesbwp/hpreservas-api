package com.hp.hpreservasapi.controller;

import com.hp.hpreservasapi.exception.DuplicateException;
import com.hp.hpreservasapi.exception.NotFoundException;
import com.hp.hpreservasapi.service.UsuarioService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hp.hpreservasapi.model.Usuario;


@RestController
@RequestMapping("/usr")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/todos")
	ResponseEntity all() {
		return new ResponseEntity(usuarioService.todos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity get(@PathVariable Long id) {
		var usr = new Usuario();
		try{
			var u = usuarioService.get(id);
			return new ResponseEntity(u, HttpStatus.OK);
		}catch(NotFoundException e){
			System.out.println("Usuario");
			return e.throwNotFoundException(id);
		}
    }

	@PostMapping("/")
	ResponseEntity add(@RequestBody Usuario usr) throws Exception {
		try{
			var u = usuarioService.add(usr);
			return new ResponseEntity(u, HttpStatus.OK);
		}catch(DuplicateException e){
			return e.throwDuplicateException();
		}
	}
	@SneakyThrows
	@PutMapping("/{id}")
	ResponseEntity<Usuario> edit(@RequestBody Usuario usr, @PathVariable Long id) {
		var u = usuarioService.edit(usr,id);
		return new ResponseEntity<Usuario>(u, HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	ResponseEntity delete(@PathVariable Long id) {
		try{
		var retorno = usuarioService.delete(id);
			return new ResponseEntity(retorno, HttpStatus.OK);
		}catch (NotFoundException e){
			System.out.println("Usuario");
			return e.throwNotFoundException(id);
		}
	}
}

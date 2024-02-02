package com.hp.hpreservasapi.controller;

import com.hp.hpreservasapi.exception.NotFoundException;
import com.hp.hpreservasapi.service.UsuarioService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hp.hpreservasapi.model.Usuario;

import java.util.List;

@RestController
@RequestMapping("/usr")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/hello")
	ResponseEntity<String> hello() {
		return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}

	@GetMapping("/todos")
	ResponseEntity<List<Usuario>> all() {
		return new ResponseEntity<List<Usuario>>(usuarioService.todos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity get(@PathVariable Long id) {
		var usr = new Usuario();
		try{
			var u = usuarioService.get(id);
			return new ResponseEntity(u, HttpStatus.OK);
		}catch(NotFoundException e){
			return e.throwNotFoundException();
		}
    }

	@PostMapping("/")
	ResponseEntity<Usuario> add(@RequestBody Usuario usr) {
		var u = usuarioService.add(usr);
		return new ResponseEntity<Usuario>(u, HttpStatus.OK);
	}
	@SneakyThrows
	@PutMapping("/{id}")
	ResponseEntity<Usuario> edit(@RequestBody Usuario usr, @PathVariable Long id) {
		var u = usuarioService.edit(usr,id);
		return new ResponseEntity<Usuario>(u, HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	ResponseEntity<String> delete(@PathVariable Long id) {
		var retorno = usuarioService.delete(id);
		return new ResponseEntity<String>(retorno, HttpStatus.OK);
	}
}

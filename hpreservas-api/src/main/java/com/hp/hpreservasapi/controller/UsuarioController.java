package com.hp.hpreservasapi.controller;

import com.hp.hpreservasapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hp.hpreservasapi.model.Usuario;

import java.util.List;

@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/hello")
	ResponseEntity<String> hello() {
		return new ResponseEntity<>("Hello World!", HttpStatus.OK);
	}

	@GetMapping("/hello")
	ResponseEntity<List<Usuario>> all() {
		return new ResponseEntity<List<Usuario>>(usuarioService.todos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<Usuario> get(@RequestParam int id) {
		var u = new Usuario();
		return new ResponseEntity<Usuario>(u, HttpStatus.OK);
	}

	@PostMapping("/")
	ResponseEntity<Usuario> add(@RequestBody Usuario usr) {
		var u = new Usuario();
		return new ResponseEntity<Usuario>(u, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	ResponseEntity<Usuario> edit(@RequestBody Usuario usr, @RequestParam int id) {
		var u = new Usuario();
		return new ResponseEntity<Usuario>(u, HttpStatus.OK);
	}

	@DeleteMapping("/")
	ResponseEntity<Usuario> delete(@RequestParam int id) {
		var u = new Usuario();
		return new ResponseEntity<Usuario>(u, HttpStatus.OK);
	}
}

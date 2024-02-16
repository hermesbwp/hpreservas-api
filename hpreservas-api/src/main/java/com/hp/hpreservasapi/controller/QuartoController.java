package com.hp.hpreservasapi.controller;

import com.hp.hpreservasapi.exception.DuplicateException;
import com.hp.hpreservasapi.exception.NotFoundException;
import com.hp.hpreservasapi.model.Quarto;
import com.hp.hpreservasapi.service.QuartoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/qtr")
public class QuartoController {

	@Autowired
	QuartoService quartoService;

	@GetMapping("/todos")
	ResponseEntity<?> all() {
		return new ResponseEntity<List<Quarto>>(quartoService.todos(), HttpStatus.OK);
	}

	@GetMapping("/todos{id}")
	ResponseEntity<?> all(@PathVariable Long id) {
		return new ResponseEntity<List<Quarto>>(quartoService.quartosHotel(id), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<?> get(@PathVariable Long id) {
		var qtr = new Quarto();
		try{
			var u = quartoService.get(id);
			return new ResponseEntity<Quarto>(u, HttpStatus.OK);
		}catch(NotFoundException e){
			System.out.println("Quarto");
			return e.throwNotFoundException(id);
		}
    }

	@PostMapping("/")
	ResponseEntity<?> add(@RequestBody Quarto qtr) throws Exception {
		try{
			var u = quartoService.add(qtr);
			return new ResponseEntity<Quarto>(u, HttpStatus.OK);
		}catch(DuplicateException e){
			return e.throwDuplicateException();
		}
	}
	@SneakyThrows
	@PutMapping("/{id}")
	ResponseEntity<?> edit(@RequestBody Quarto qtr, @PathVariable Long id) {
		var u = quartoService.edit(qtr,id);
		return new ResponseEntity<Quarto>(u, HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	ResponseEntity<?> delete(@PathVariable Long id) {
		try{
		var retorno = quartoService.delete(id);
			return ResponseEntity.noContent().build();
		}catch (NotFoundException e){
			System.out.println("Quarto");
			return e.throwNotFoundException(id);
		}
	}
}

package com.hp.hpreservasapi.controller;

import com.hp.hpreservasapi.exception.DuplicateException;
import com.hp.hpreservasapi.exception.NotFoundException;
import com.hp.hpreservasapi.model.Reserva;
import com.hp.hpreservasapi.service.ReservaService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/usr")
public class ReservaController {

	@Autowired
	ReservaService reservaService;

	@GetMapping("/todos")
	ResponseEntity<List<Reserva>> all() {
		return new ResponseEntity<List<Reserva>>(reservaService.todos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<?> get(@PathVariable Long id) {
		var usr = new Reserva();
		try{
			var u = reservaService.get(id);
			return new ResponseEntity<Reserva>(u, HttpStatus.OK);
		}catch(NotFoundException e){
			System.out.println("Reserva");
			return e.throwNotFoundException(id);
		}
    }

	@PostMapping("/")
	ResponseEntity<?> add(@RequestBody Reserva rvr) throws Exception {
		try{
			var u = reservaService.add(rvr);
			return new ResponseEntity<Reserva>(u, HttpStatus.OK);
		}catch(DuplicateException e){
			return e.throwDuplicateException();
		}
	}
	@SneakyThrows
	@PutMapping("/{id}")
	ResponseEntity<Reserva> edit(@RequestBody Reserva rvr, @PathVariable Long id) {
		var u = reservaService.edit(rvr,id);
		return new ResponseEntity<Reserva>(u, HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	ResponseEntity<?> delete(@PathVariable Long id) {
		try{
		var retorno = reservaService.delete(id);
			return ResponseEntity.noContent().build();
		}catch (NotFoundException e){
			System.out.println("Reserva");
			return e.throwNotFoundException(id);
		}
	}
}

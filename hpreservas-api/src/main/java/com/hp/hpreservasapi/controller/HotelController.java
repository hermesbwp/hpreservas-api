package com.hp.hpreservasapi.controller;

import com.hp.hpreservasapi.exception.DuplicateException;
import com.hp.hpreservasapi.exception.NotFoundException;
import com.hp.hpreservasapi.model.Hotel;
import com.hp.hpreservasapi.model.Usuario;
import com.hp.hpreservasapi.service.HotelService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/htl")
public class HotelController {

	@Autowired
	HotelService hotelService;

	@GetMapping("/todos")
	ResponseEntity<?> all() {
		return new ResponseEntity<List<Hotel>>(hotelService.todos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	ResponseEntity<?> get(@PathVariable Long id) {
		var usr = new Usuario();
		try{
			var u = hotelService.get(id);
			return new ResponseEntity<Hotel>(u, HttpStatus.OK);
		}catch(NotFoundException e){
			System.out.println("Usuario");
			return e.throwNotFoundException(id);
		}
    }

	@PostMapping("/")
	ResponseEntity<?> add(@RequestBody Hotel htl) throws Exception {
		try{
			var u = hotelService.add(htl);
			return new ResponseEntity<Hotel>(u, HttpStatus.OK);
		}catch(DuplicateException e){
			return e.throwDuplicateException();
		}
	}
	@SneakyThrows
	@PutMapping("/{id}")
	ResponseEntity<?> edit(@RequestBody Hotel htl, @PathVariable Long id) {
		var u = hotelService.edit(htl,id);
		return new ResponseEntity<Hotel>(u, HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	ResponseEntity<?> delete(@PathVariable Long id) {
		try{
		var retorno = hotelService.delete(id);
			return ResponseEntity.noContent().build();
		}catch (NotFoundException e){
			System.out.println("Hotel");
			return e.throwNotFoundException(id);
		}
	}
}

package com.hp.hpreservasapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

	@GetMapping("/teste")
	public void teste() {
		System.out.println("testando");
	}
}

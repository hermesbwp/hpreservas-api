package com.hp.hpreservasapi;

import com.hp.hpreservasapi.exception.NotFoundException;
import com.hp.hpreservasapi.model.Usuario;
import com.hp.hpreservasapi.service.UsuarioService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Bean;

import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootApplication
public class HpreservasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HpreservasApiApplication.class, args);
	}
}

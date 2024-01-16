package com.hp.hpreservasapi.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Reserva {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	//Usuario usuarioResponsavel;
	//Quarto quarto;
	Integer qtdPessoas;
	Date checkIn;
	Date checkOut;
}

package com.hp.hpreservasapi.model;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Hotel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	String nome;
	Collection<Quarto> quartos;
}

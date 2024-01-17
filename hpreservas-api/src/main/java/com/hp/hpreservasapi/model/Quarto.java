package com.hp.hpreservasapi.model;

import java.math.BigDecimal;

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
public class Quarto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	String numero;
	Integer capacidadeMaxima;
	BigDecimal preco;
}

package com.hp.hpreservasapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Quarto {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private BigDecimal diaria;
	@ManyToOne
	private Hotel hotel;
	private Integer capacidade;
	private QuartoStatus status;
	public enum QuartoStatus {
		OCUPADO,
		MANUTENCAO,
		PRONTO
	 }
	}

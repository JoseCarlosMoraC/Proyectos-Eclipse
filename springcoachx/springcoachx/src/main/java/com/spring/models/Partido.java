package com.spring.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "partido")
public class Partido {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private LocalDateTime fecha;
	@Column
	private String lugar;
	@Column
	private int golesAFavor;
	@Column
	private int golesEnContra;
	@Column
	private Resultado resultado;

	@ManyToOne
	@JoinColumn(name = "equipo_id", nullable = false)
	@JsonIgnore
	private Equipo equipo;
}

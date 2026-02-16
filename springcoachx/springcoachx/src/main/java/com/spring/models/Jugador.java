package com.spring.models;

import java.time.LocalDate;

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
@Entity(name = "jugador")
public class Jugador {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String nombre;
	@Column
	private String apellidos;
	@Column
	private LocalDate fechaNacimiento;
	@Column
	private int dorsal;
	@Column
	private String posicion;
	@Column
	private boolean activo;

	@ManyToOne
	@JoinColumn(name = "equipo_id", nullable = false)
	@JsonIgnore
	private Equipo equipo;
}

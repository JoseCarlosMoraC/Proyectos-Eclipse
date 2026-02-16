package com.spring.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "equipo")
public class Equipo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column
	private String nombre;
	@Column
	private String categoria;
	@Column
	private String nombreEntrenador;
	@Column
	private boolean activo;

	@OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
	private List<Jugador> jugadores = new ArrayList<>();

	@OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL)
	private List<Partido> partidos = new ArrayList<>();

}

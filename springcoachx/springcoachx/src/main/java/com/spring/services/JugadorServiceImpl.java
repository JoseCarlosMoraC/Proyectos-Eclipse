package com.spring.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.Jugador;
import com.spring.repositories.JugadorRepository;

import exceptions.CoachXNotFoundException;

@Service
public class JugadorServiceImpl implements JugadorService {

	private JugadorRepository jugadorRepository;

	@Autowired
	public JugadorServiceImpl(JugadorRepository jugadorRepository) {
		super();
		this.jugadorRepository = jugadorRepository;
	}

	@Override
	public List<Jugador> findAll() {
		return jugadorRepository.findAll();
	}

	@Override
	public Set<Jugador> findByPosicion(String posicion) {
		return jugadorRepository.findByPosicion(posicion);
	}

	@Override
	public String createJugador(Jugador jugador) {
		String respuesta = "";
		Jugador jugadorAñadido = jugadorRepository.save(jugador);
		if (jugadorAñadido != null) {
			respuesta = jugadorAñadido.getNombre().toString();
		} else {
			respuesta = "No se ha podido añadir el jugador";
		}
		return respuesta;
	}

	@Override
	public Jugador findJugadorById(long id) {
		return jugadorRepository.findById(id)
				.orElseThrow(() -> new CoachXNotFoundException(id));
	}

	@Override
	public Jugador updateNameJugador(long id, Jugador jugador) {
		Jugador jOriginal = this.findJugadorById(id);
		jOriginal.setNombre(jugador.getNombre());
		return jugadorRepository.save(jOriginal);
	}
}
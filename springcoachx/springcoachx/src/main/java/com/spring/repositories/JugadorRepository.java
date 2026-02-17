package com.spring.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.models.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
	List<Jugador> findAll();

	Set<Jugador> findByPosicion(String posicion);;

	Jugador findJugadorById(long id);
}



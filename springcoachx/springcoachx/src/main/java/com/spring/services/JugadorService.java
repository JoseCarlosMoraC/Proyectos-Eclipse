package com.spring.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.spring.models.Jugador;

@Service
public interface JugadorService {
	List<Jugador>  findAll();
    Set<Jugador> findByPosicion(String posicion);
    public String createJugador(Jugador jugador) ;
    public Jugador findJugadorById(long id);
    public Jugador updateNameJugador(long id,  Jugador jugador);
    
}


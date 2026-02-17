package com.spring.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.spring.models.Partido;
import com.spring.models.Resultado;


@Service
public interface PartidoService {
	List<Partido>  findAll();
    Set<Partido> findByResultado(Resultado Resultado);
    public String createPartido(Partido partido) ;
    public Partido findPartidoById(long id);
    public Partido updateNamePartido(long id,  Partido partido);
    
}

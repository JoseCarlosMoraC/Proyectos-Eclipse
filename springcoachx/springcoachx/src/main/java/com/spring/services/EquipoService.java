package com.spring.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.spring.models.Equipo;

@Service
public interface EquipoService {
	List<Equipo>  findAll();
    Set<Equipo> findByCategoria(String categoria);
    public String createEquipo(Equipo equipo) ;
    public Equipo findEquipoById(long id);
    public Equipo updateNameEquipo(long id,  Equipo equipo);
    
}

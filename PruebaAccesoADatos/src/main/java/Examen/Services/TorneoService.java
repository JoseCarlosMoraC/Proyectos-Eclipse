package Examen.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Examen.Exception.TorneoException;
import Examen.Models.Enfrentamiento;
import Examen.Models.Equipo;
import Examen.Repositories.EnfrentamientoRepositorio;
import Examen.Repositories.EquipoRepositorio;

public class TorneoService {
private EnfrentamientoRepositorio repoEnfrentamiento;
private EquipoRepositorio repoEquipo;
private Set<Equipo> listaEquipos;
private Set<Enfrentamiento> listaEnfrentamientos;

public TorneoService(EnfrentamientoRepositorio repoEnfrentamiento, EquipoRepositorio repoEquipo,
		Set<Equipo> listaEquipos, Set<Enfrentamiento> listaEnfrentamientos) {
	super();
	this.repoEnfrentamiento = new EnfrentamientoRepositorio();
	this.repoEquipo = new EquipoRepositorio();
	this.listaEquipos = new HashSet<Equipo>();
	this.listaEnfrentamientos = new HashSet<Enfrentamiento>();
}

public TorneoService() {
	
}


public EnfrentamientoRepositorio getRepoEnfrentamiento() {
	return repoEnfrentamiento;
}

public void setRepoEnfrentamiento(EnfrentamientoRepositorio repoEnfrentamiento) {
	this.repoEnfrentamiento = repoEnfrentamiento;
}

public EquipoRepositorio getRepoEquipo() {
	return repoEquipo;
}

public void setRepoEquipo(EquipoRepositorio repoEquipo) {
	this.repoEquipo = repoEquipo;
}

public Set<Equipo> getListaEquipos() {
	return listaEquipos;
}

public void setListaEquipos(Set<Equipo> listaEquipos) {
	this.listaEquipos = listaEquipos;
}

public Set<Enfrentamiento> getListaEnfrentamientos() {
	return listaEnfrentamientos;
}

public void setListaEnfrentamientos(Set<Enfrentamiento> listaEnfrentamientos) {
	this.listaEnfrentamientos = listaEnfrentamientos;
}

@Override
public String toString() {
	return "TorneoService [repoEnfrentamiento=" + repoEnfrentamiento + ", repoEquipo=" + repoEquipo + ", listaEquipos="
			+ listaEquipos + ", listaEnfrentamientos=" + listaEnfrentamientos + "]";
}

public Equipo getEquipo(String codigo) throws TorneoException {
    return repoEquipo.getEquipo(codigo);
}
public void agregarEnfrentamiento(String codigo) throws TorneoException {
	repoEnfrentamiento.agregarEnfrentamiento(codigo);
}
public void agregarEquipo(String codigo) throws TorneoException {
	repoEquipo.agregarEquipo(codigo);
}
public List<Enfrentamiento> getEnfrentamientosPorEquipo(String equipo) throws TorneoException {
    return repoEnfrentamiento.getEnfrenamientosPorEquipo(equipo);
}
}

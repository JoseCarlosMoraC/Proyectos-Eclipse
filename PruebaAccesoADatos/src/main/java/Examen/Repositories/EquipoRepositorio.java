package Examen.Repositories;

import java.util.Iterator;

import Examen.Exception.TorneoException;
import Examen.Models.Equipo;
import Examen.Services.TorneoService;

public class EquipoRepositorio {
	TorneoService equiServicio = new TorneoService();
	
	 public EquipoRepositorio(TorneoService equiServicio) {
		super();
		this.equiServicio = equiServicio;
	}
	 
	 public EquipoRepositorio() {
		// TODO Auto-generated constructor stub
	}

	 public TorneoService getEquiServicio() {
		return equiServicio;
	}

	 public void setEquiServicio(TorneoService equiServicio) {
		 this.equiServicio = equiServicio;
	 }

	 @Override
	public String toString() {
		return "EquipoRepositorio [equiServicio=" + equiServicio + "]";
	}

	 public Equipo getEquipo(String codigo) throws TorneoException {
	        boolean encontrado = false;
	        Equipo equipo = null;

	        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
	        while (it.hasNext() && !encontrado) {
	        	Equipo e = it.next();
	            if (e.getCodigo().equalsIgnoreCase(codigo)) {
	                encontrado = true;
	                equipo = e;
	            }
	        }

	        if (encontrado)
	            throw new TorneoException("No se encuentra ningún equipo asociado a ese codigo");

	        return equipo;
	    }
	 public void agregarEquipo(String codigo) throws TorneoException {
	        boolean encontrado = false;
	        Equipo equipo = null;

	        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
	        while (it.hasNext() && !encontrado) {
	        	Equipo e = it.next();
	            if (e.getCodigo().equalsIgnoreCase(codigo)) {
	                encontrado = true;
	                equipo = e;
	                equiServicio.getListaEquipos().add(equipo);
	            }
	        }

	        if (encontrado)
	            throw new TorneoException("Ya existe el equipo");
	    }
}

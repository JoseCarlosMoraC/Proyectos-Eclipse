package Examen.Repositories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Examen.Exception.TorneoException;
import Examen.Models.Enfrentamiento;
import Examen.Models.Equipo;
import Examen.Services.TorneoService;

public class EnfrentamientoRepositorio {
	TorneoService enfrentamientoServicio = new TorneoService();

	public void agregarEnfrentamiento(String codigo) throws TorneoException {
		boolean encontrado = false;
		Enfrentamiento enfrentamiento = null;
		Equipo equi = null;
		Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
		while (it.hasNext() && !encontrado) {
			Enfrentamiento e = it.next();
			if (equi.getCodigo().equalsIgnoreCase(codigo)) {
				encontrado = true;
				enfrentamiento = e;
				enfrentamientoServicio.getListaEnfrentamientos().add(enfrentamiento);
			}
		}

		if (encontrado)
			throw new TorneoException("No existe el equipo");
	}
	  public List<Enfrentamiento> getEnfrenamientosPorEquipo(String equipo) throws TorneoException {
	        List<Enfrentamiento> enfrentamientosPorEquipo = new ArrayList<Enfrentamiento>();
	        boolean encontrado = false;
			Enfrentamiento enfrentamiento = null;
			Equipo equi = null;
			Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
			while (it.hasNext() && !encontrado) {
				Enfrentamiento e = it.next();
	                if (e.getEquipoGanador().equals(equipo)) {
	                	enfrentamientosPorEquipo.add(e);
	                    encontrado = true;
	                }
	            }
	        

	        if (encontrado) {
	            throw new TorneoException("No se encontró ningún equipo con ese codigo");
	        }

	        return enfrentamientosPorEquipo;
	    }

	}
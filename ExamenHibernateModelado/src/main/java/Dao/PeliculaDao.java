package Dao;


import Models.Pelicula;
import Utils.AbstractDao;

public class PeliculaDao extends AbstractDao<Pelicula>{
	public PeliculaDao() {
		setClase(Pelicula.class);
	}
}

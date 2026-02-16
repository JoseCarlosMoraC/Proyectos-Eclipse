package Controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Dao.CineDao;
import Dao.PeliculaDao;
import Dao.SalaDao;
import Models.Cine;
import Models.Pelicula;
import Models.Sala;




public class GestionaCines {
	private static final Logger logger = LogManager.getLogger(GestionaCines.class);
	public static void main(String[] args) {
		CineDao cineDao = new CineDao();
		PeliculaDao peliculaDao = new PeliculaDao();
		SalaDao salaDao = new SalaDao();
		
		Pelicula p1 = new Pelicula("Examen de Soraya", "Terror", 120);
		peliculaDao.create(p1);
		Pelicula p2 = new Pelicula("Examen de Javi", "Alegre", 180);
		peliculaDao.create(p2);
		Pelicula p3 = new Pelicula("Examen de Alexandra", "Incertidumbre", 120);
		peliculaDao.create(p3);
		Pelicula p4 = new Pelicula("Examen de Diego", "Divertido", 60);
		peliculaDao.create(p4);
		Pelicula p5 = new Pelicula("Examen de Rafa", "Aburrido", 120);
		peliculaDao.create(p5);
		
		Sala s1 = new Sala("Sala 1", 300);
		Sala s2 = new Sala("Sala 2", 200);
		Sala s3 = new Sala("Sala 3", 100);
		Sala s4 = new Sala("Sala 4", 400);
		s1.addPelicula(p1);
		s2.addPelicula(p1);
		s3.addPelicula(p2);
		s4.addPelicula(p3);
		
		Cine c1 = new Cine("IES Gerena", "Gerena" );
		Cine c2 = new Cine("IES Torre de los Guzmanes", "La Algaba");
		cineDao.create(c1);
		cineDao.create(c2);
		c1.addSala(s3);
		c1.addSala(s2);
		c2.addSala(s1);
		c2.addSala(s4);
		cineDao.mergeaObjeto(c1);
		cineDao.mergeaObjeto(c2);
		
		List<Pelicula> peliculas = peliculaDao.getAll();
		for (Pelicula p : peliculas) {
			logger.debug(p);
		}

		List<Sala> salas = salaDao.getAll();
		for (Sala s : salas) {
			logger.debug(s);
		}

	}

}

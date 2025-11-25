package MondoDB.Models;

import java.util.ArrayList;
import java.util.List;

public class Estudiante {
	   private int id;
	   private String name;
	   private double notaMedia;
	   private List<String> aficiones;

	   public Estudiante() {
	this.aficiones = new ArrayList<String>();
	}

	   public Estudiante(int id, String name, double notaMedia, List<String> aficiones) {
	       this.id = id;
	       this.name = name;
	       this.notaMedia = notaMedia;
	       this.aficiones = aficiones;
	   }
		//TODO
	}


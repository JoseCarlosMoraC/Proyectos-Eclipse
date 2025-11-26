package MondoDB.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Estudiante {
	   private int id;
	   private String name;
	   private double notaMedia;
	   private Direccion direccion;
	   private List<String> aficiones;
	   private List<Puntuacion> puntuacion;

	   public Estudiante() {
	this.aficiones = new ArrayList<String>();
	this.puntuacion = new ArrayList<Puntuacion>();
	}

	   public Estudiante(int id, String name, double notaMedia, List<String> aficiones) {
		super();
		this.id = id;
		this.name = name;
		this.notaMedia = notaMedia;
		this.aficiones = aficiones;
		this.direccion = direccion;
		this.puntuacion = puntuacion;
	   }



	   public Direccion getDireccion() {
		return direccion;
	}

	   public void setDireccion(Direccion direccion) {
		   this.direccion = direccion;
	   }

	

	   public List<Puntuacion> getPuntuacion() {
		return puntuacion;
	}

	   public void setPuntuacion(List<Puntuacion> puntuacion) {
		   this.puntuacion = puntuacion;
	   }

	   public int getId() {
		   return id;
	   }

	   public void setId(int id) {
		   this.id = id;
	   }

	   public String getName() {
		   return name;
	   }

	   public void setName(String name) {
		   this.name = name;
	   }

	   public double getNotaMedia() {
		   return notaMedia;
	   }

	   public void setNotaMedia(double notaMedia) {
		   this.notaMedia = notaMedia;
	   }

	   public List<String> getAficiones() {
		   return aficiones;
	   }

	   public void setAficiones(List<String> aficiones) {
		   this.aficiones = aficiones;
	   }

	   @Override
	   public int hashCode() {
		return Objects.hash(aficiones, direccion, id, name, notaMedia, puntuacion);
	   }

	   @Override
	   public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		return Objects.equals(aficiones, other.aficiones) && Objects.equals(direccion, other.direccion)
				&& id == other.id && Objects.equals(name, other.name)
				&& Double.doubleToLongBits(notaMedia) == Double.doubleToLongBits(other.notaMedia)
				&& Objects.equals(puntuacion, other.puntuacion);
	   }

	   @Override
	   public String toString() {
		return "Estudiante [id=" + id + ", name=" + name + ", notaMedia=" + notaMedia + ", direccion=" + direccion
				+ ", aficiones=" + aficiones + ", puntuacion=" + puntuacion + "]";
	   }

	
	  
	}


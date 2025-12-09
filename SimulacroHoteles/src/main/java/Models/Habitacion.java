package Models;

public class Habitacion {
private Tipo tipo;
private double precio;
private int capacidad;
private boolean disponible;

public Habitacion(Tipo tipo, double precio, int capacidad, boolean disponible) {
	super();
	this.tipo = tipo;
	this.precio = precio;
	this.capacidad = capacidad;
	this.disponible = disponible;
}

public Habitacion() {
	// TODO Auto-generated constructor stub
}

public Tipo getTipo() {
	return tipo;
}

public void setTipo(Tipo tipo) {
	this.tipo = tipo;
}

public double getPrecio() {
	return precio;
}

public void setPrecio(double precio) {
	this.precio = precio;
}

public int getCapacidad() {
	return capacidad;
}

public void setCapacidad(int capacidad) {
	this.capacidad = capacidad;
}

public boolean isDisponible() {
	return disponible;
}

public void setDisponible(boolean disponible) {
	this.disponible = disponible;
}


}

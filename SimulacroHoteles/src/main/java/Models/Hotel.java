package Models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Hotel {
private String idHotel;
private String nombre;
private int estrellas;
private boolean admiteMascotas = false;
private String fechaApertura;
private Ubicacion ubicacion;
private List<Habitacion> habitaciones;

public Hotel(String idHotel, String nombre, int estrellas, boolean admiteMascotas, String fechaApertura,
		Ubicacion ubicacion, List<Habitacion> habitaciones) {
	super();
	this.idHotel = idHotel;
	this.nombre = nombre;
	this.estrellas = estrellas;
	this.admiteMascotas = admiteMascotas;
	this.fechaApertura = fechaApertura;
	this.ubicacion = ubicacion;
	this.habitaciones = habitaciones;
}

public Hotel() {
	// TODO Auto-generated constructor stub
}

public String getIdHotel() {
	return idHotel;
}

public void setIdHotel(String idHotel) {
	this.idHotel = idHotel;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public int getEstrellas() {
	return estrellas;
}

public void setEstrellas(int estrellas) {
	this.estrellas = estrellas;
}

public boolean isAdmiteMascotas() {
	return admiteMascotas;
}

public void setAdmiteMascotas(boolean admiteMascotas) {
	this.admiteMascotas = admiteMascotas;
}

public String getFechaApertura() {
	return fechaApertura;
}

public void setFechaApertura(String fechaApertura) {
	this.fechaApertura = fechaApertura;
}

public Ubicacion getUbicacion() {
	return ubicacion;
}

public void setUbicacion(Ubicacion ubicacion) {
	this.ubicacion = ubicacion;
}

public List<Habitacion> getHabitaciones() {
	return habitaciones;
}

public void setHabitaciones(List<Habitacion> habitaciones) {
	this.habitaciones = habitaciones;
}

@Override
public int hashCode() {
	return Objects.hash(idHotel);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Hotel other = (Hotel) obj;
	return idHotel == other.idHotel;
}



}

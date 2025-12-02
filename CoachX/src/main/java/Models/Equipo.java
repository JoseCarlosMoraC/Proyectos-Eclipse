package Models;

import java.util.List;
import java.util.Objects;

public class Equipo {
private int id;
private String categoria;
private List<Jugador> jugadores;
private Entrenador entrenador;
private Delegado delegado;

public Equipo(int id, String categoria, List<Jugador> jugadores, Entrenador entrenador, Delegado delegado) {
	super();
	this.id = id;
	this.categoria = categoria;
	this.jugadores = jugadores;
	this.entrenador = entrenador;
	this.delegado = delegado;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getCategoria() {
	return categoria;
}

public void setCategoria(String categoria) {
	this.categoria = categoria;
}

public List<Jugador> getJugadores() {
	return jugadores;
}

public void setJugadores(List<Jugador> jugadores) {
	this.jugadores = jugadores;
}

public Entrenador getEntrenador() {
	return entrenador;
}

public void setEntrenador(Entrenador entrenador) {
	this.entrenador = entrenador;
}

public Delegado getDelegado() {
	return delegado;
}

public void setDelegado(Delegado delegado) {
	this.delegado = delegado;
}

@Override
public int hashCode() {
	return Objects.hash(id);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Equipo other = (Equipo) obj;
	return id == other.id;
}



}

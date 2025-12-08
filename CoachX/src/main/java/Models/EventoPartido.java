package Models;

import java.util.Objects;

public class EventoPartido {
private int id;
private int partidoId;
private int jugadorId;
private int minuto;
private String tipoEvento;

public EventoPartido(int id, int partidoId, int jugadorId, int minuto, String tipoEvento) {
	super();
	this.id = id;
	this.partidoId = partidoId;
	this.jugadorId = jugadorId;
	this.minuto = minuto;
	this.tipoEvento = tipoEvento;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getPartidoId() {
	return partidoId;
}

public void setPartidoId(int partidoId) {
	this.partidoId = partidoId;
}

public int getJugadorId() {
	return jugadorId;
}

public void setJugadorId(int jugadorId) {
	this.jugadorId = jugadorId;
}

public int getMinuto() {
	return minuto;
}

public void setMinuto(int minuto) {
	this.minuto = minuto;
}

public String getTipoEvento() {
	return tipoEvento;
}

public void setTipoEvento(String tipoEvento) {
	this.tipoEvento = tipoEvento;
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
	EventoPartido other = (EventoPartido) obj;
	return id == other.id;
}



}

package Repaso.Chat.Models;

import java.time.LocalDate;
import java.util.Objects;

public class Conversacion {
private String id;
private String pregunta;
private String respuesta;
private LocalDate fechaConversacion;
private int numValoracionesPositivas;
private TipoAgente tipo;

public Conversacion() {
	super();
}


public Conversacion(String pregunta, String respuesta, TipoAgente tipo) {
	super();
	this.pregunta = pregunta;
	this.respuesta = respuesta;
	this.tipo = tipo;
}


public Conversacion(String id, String pregunta, String respuesta, LocalDate fechaConversacion,
		int numValoracionesPositivas, TipoAgente tipo) {
	super();
	this.id = id;
	this.pregunta = pregunta;
	this.respuesta = respuesta;
	this.fechaConversacion = fechaConversacion;
	this.numValoracionesPositivas = numValoracionesPositivas;
	this.tipo = tipo;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getPregunta() {
	return pregunta;
}

public void setPregunta(String pregunta) {
	this.pregunta = pregunta;
}

public String getRespuesta() {
	return respuesta;
}

public void setRespuesta(String respuesta) {
	this.respuesta = respuesta;
}

public LocalDate getFechaConversacion() {
	return fechaConversacion;
}

public void setFechaConversacion(LocalDate fechaConversacion) {
	this.fechaConversacion = fechaConversacion;
}

public int getNumValoracionesPositivas() {
	return numValoracionesPositivas;
}

public void setNumValoracionesPositivas(int numValoracionesPositivas) {
	this.numValoracionesPositivas = numValoracionesPositivas;
}

public TipoAgente getTipo() {
	return tipo;
}

public void setTipo(TipoAgente tipo) {
	this.tipo = tipo;
}



@Override
public int hashCode() {
	return Objects.hash(fechaConversacion, id, numValoracionesPositivas, pregunta, respuesta, tipo);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Conversacion other = (Conversacion) obj;
	return Objects.equals(fechaConversacion, other.fechaConversacion) && Objects.equals(id, other.id)
			&& numValoracionesPositivas == other.numValoracionesPositivas && Objects.equals(pregunta, other.pregunta)
			&& Objects.equals(respuesta, other.respuesta) && tipo == other.tipo;
}

@Override
public String toString() {
	return "Conversacion [id=" + id + ", pregunta=" + pregunta + ", respuesta=" + respuesta + ", fechaConversacion="
			+ fechaConversacion + ", numValoracionesPositivas=" + numValoracionesPositivas + ", tipo=" + tipo + "]";
}



}
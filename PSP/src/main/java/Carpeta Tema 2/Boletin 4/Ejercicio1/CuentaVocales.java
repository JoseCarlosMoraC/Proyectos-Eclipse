package Tema2.Boletin4.Ejercicio1;

import java.util.Objects;

public class CuentaVocales {
private String texto;
private int numTotalVocales;
private int contador = 0;

public CuentaVocales(String texto, int numTotalVocales, int contador) {
	super();
	this.texto = texto;
	this.numTotalVocales = numTotalVocales;
	this.contador = contador;
}

public String getTexto() {
	return texto;
}

public void setTexto(String texto) {
	this.texto = texto;
}

public int getNumTotalVocales() {
	return numTotalVocales;
}

public void setNumTotalVocales(int numTotalVocales) {
	this.numTotalVocales = numTotalVocales;
}

public int getContador() {
	return contador;
}

public void setContador(int contador) {
	this.contador = contador;
}

@Override
public int hashCode() {
	return Objects.hash(contador, numTotalVocales, texto);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	CuentaVocales other = (CuentaVocales) obj;
	return contador == other.contador && numTotalVocales == other.numTotalVocales && Objects.equals(texto, other.texto);
}

@Override
public String toString() {
	return "CuentaVocales [texto=" + texto + ", numTotalVocales=" + numTotalVocales + ", contador=" + contador + "]";
}



}

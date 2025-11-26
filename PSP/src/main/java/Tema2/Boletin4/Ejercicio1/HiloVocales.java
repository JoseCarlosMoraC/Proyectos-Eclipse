package Tema2.Boletin4.Ejercicio1;

import java.util.Iterator;
import java.util.Objects;

public class HiloVocales extends Thread{
private String vocal;
private int numVocales;
private int contador;
private CuentaVocales cuenta;

public HiloVocales(String vocal, int numVocales, int contador, CuentaVocales cuenta) {
	super();
	this.vocal = vocal;
	this.numVocales = numVocales;
	this.contador = contador;
	this.cuenta = cuenta;
}

public String getVocal() {
	return vocal;
}

public void setVocal(String vocal) {
	this.vocal = vocal;
}

public int getNumVocales() {
	return numVocales;
}

public void setNumVocales(int numVocales) {
	this.numVocales = numVocales;
}

public int getContador() {
	return contador;
}

public void setContador(int contador) {
	this.contador = contador;
}

public CuentaVocales getCuenta() {
	return cuenta;
}

public void setCuenta(CuentaVocales cuenta) {
	this.cuenta = cuenta;
}

@Override
public String toString() {
	return "HiloVocales [vocal=" + vocal + ", numVocales=" + numVocales + ", contador=" + contador + ", cuenta="
			+ cuenta + "]";
}

public void contarVocal(String vocal, String texto) {
	for (String textoContar : texto) {
		
	}
}
}
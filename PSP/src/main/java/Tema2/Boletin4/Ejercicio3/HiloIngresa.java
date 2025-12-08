package Tema2.Boletin4.Ejercicio3;

import java.util.Objects;

public class HiloIngresa extends Thread{
private double importe;
private CuentaBancaria cuenta;


public HiloIngresa(double importe) {
	super();
	this.importe = ((double)(Math.random()*500+1));
	
}

public double getImporte() {
	return importe;
}

public void setImporte(double importe) {
	this.importe = importe;
}

public CuentaBancaria getCuenta() {
	return cuenta;
}

public void setCuenta(CuentaBancaria cuenta) {
	this.cuenta = cuenta;
}

@Override
public int hashCode() {
	return Objects.hash(importe);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	HiloIngresa other = (HiloIngresa) obj;
	return Double.doubleToLongBits(importe) == Double.doubleToLongBits(other.importe);
}

@Override
public void run() {
	// TODO Auto-generated method stub
	cuenta.ingresarDinero(importe);
	super.run();
}

}

package Tema2.Boletin4.Ejercicio3;

import java.util.Objects;

public class HiloRetira extends Thread{
private double retiro;
private CuentaBancaria cuenta;


public HiloRetira(double retiro) {
	super();
	this.retiro = ((double)(Math.random()*500+1));
	
}

public double getRetiro() {
	return retiro;
}

public void setRetiro(double retiro) {
	this.retiro = retiro;
}

public CuentaBancaria getCuenta() {
	return cuenta;
}

public void setCuenta(CuentaBancaria cuenta) {
	this.cuenta = cuenta;
}

@Override
public int hashCode() {
	return Objects.hash(retiro);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	HiloRetira other = (HiloRetira) obj;
	return Double.doubleToLongBits(retiro) == Double.doubleToLongBits(other.retiro);
}

@Override
public String toString() {
	return "HiloRetira [importe=" + retiro + "]";
}

@Override
public void run() {
	// TODO Auto-generated method stub
	try {
		cuenta.retirarDinero(retiro);
	} catch (CuentaException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	super.run();
}


}

package Tema1.Boletin2;

import java.util.Objects;

public class ResultadoComparacion {
private String nombre;
private ValorComparacion ValorComparacion;

public ResultadoComparacion(String nombre, Tema1.Boletin2.ValorComparacion valorComparacion) {
	super();
	this.nombre = nombre;
	ValorComparacion = valorComparacion;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public ValorComparacion getValorComparacion() {
	return ValorComparacion;
}
public void setValorComparacion(ValorComparacion valorComparacion) {
	ValorComparacion = valorComparacion;
}
@Override
public int hashCode() {
	return Objects.hash(nombre);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	ResultadoComparacion other = (ResultadoComparacion) obj;
	return Objects.equals(nombre, other.nombre);
}


}

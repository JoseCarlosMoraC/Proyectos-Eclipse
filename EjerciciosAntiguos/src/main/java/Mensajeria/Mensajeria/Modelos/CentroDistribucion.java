package Mensajeria.Mensajeria.Modelos;

import java.util.Objects;

public class CentroDistribucion {
	private String codigoCentro;
	private String nombre;
	private String pais;

	public CentroDistribucion(String codigoCentro, String nombre, String pais) {
		this.codigoCentro = codigoCentro;
		this.nombre = nombre;
		this.pais = pais;
	}

	public String getCodigoCentro() {
		return codigoCentro;
	}

	public String getPais() {
		return pais;
	}

	@Override
	public String toString() {
		return nombre + " (" + codigoCentro + ") - " + pais;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoCentro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CentroDistribucion other = (CentroDistribucion) obj;
		return Objects.equals(codigoCentro, other.codigoCentro);
	}
}


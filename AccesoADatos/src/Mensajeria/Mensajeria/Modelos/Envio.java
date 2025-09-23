package Mensajeria.Mensajeria.Modelos;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Objects;



public class Envio {
	private LocalDate fechaEnvio;
	private String codigoSeguimiento;
	private double pesoKg;
	private String codigoCentroDestino;
	private EstadoEnvio estado;

	public Envio(LocalDate fechaEnvio, String codigoSeguimiento, double pesoKg, String codigoCentroDestino) {
		this.fechaEnvio = fechaEnvio;
		this.codigoSeguimiento = codigoSeguimiento;
		this.pesoKg = pesoKg;
		this.codigoCentroDestino = codigoCentroDestino;
		this.estado = EstadoEnvio.PENDIENTE;
	}

	public LocalDate getFechaEnvio() {
		return fechaEnvio;
	}

	public String getCodigoSeguimiento() {
		return codigoSeguimiento;
	}

	public String getCodigoCentroDestino() {
		return codigoCentroDestino;
	}

	public double getPesoKg() {
		return pesoKg;
	}

	public void setPesoKg(double pesoKg) {
		this.pesoKg = pesoKg;
	}

	public EstadoEnvio getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnvio estado) {
		this.estado = estado;
	}

	public void setFechaEnvio(LocalDate fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public void setCodigoSeguimiento(String codigoSeguimiento) {
		this.codigoSeguimiento = codigoSeguimiento;
	}

	public void setCodigoCentroDestino(String codigoCentroDestino) {
		this.codigoCentroDestino = codigoCentroDestino;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Envio))
			return false;
		Envio envio = (Envio) o;
		return fechaEnvio.equals(envio.fechaEnvio) && codigoSeguimiento.equals(envio.codigoSeguimiento);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaEnvio, codigoSeguimiento);
	}

	@Override
	public String toString() {
		return codigoSeguimiento + " (" + fechaEnvio + ")";
	}
	
}

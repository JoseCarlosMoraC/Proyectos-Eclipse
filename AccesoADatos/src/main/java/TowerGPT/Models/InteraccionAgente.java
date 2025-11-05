package TowerGPT.Models;

import java.util.Objects;

public class InteraccionAgente {
    private String identificador; 
    private TipoAgente tipo;
    private String peticion;
    private String respuesta;
    private double tiempoEjecucion;
    private int numValoracionesPositivas;
    private double porcentajeAcierto;

    public InteraccionAgente() {

    }

    public InteraccionAgente(String identificador, TipoAgente tipo, String peticion, String respuesta,
                             double tiempoEjecucion, int numValoracionesPositivas, double porcentajeAcierto) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.peticion = peticion;
        this.respuesta = respuesta;
        this.tiempoEjecucion = tiempoEjecucion;
        this.numValoracionesPositivas = numValoracionesPositivas;
        this.porcentajeAcierto = porcentajeAcierto;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public TipoAgente getTipo() {
        return tipo;
    }

    public void setTipo(TipoAgente tipo) {
        this.tipo = tipo;
    }

    public String getPeticion() {
        return peticion;
    }

    public void setPeticion(String peticion) {
        this.peticion = peticion;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public double getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public void setTiempoEjecucion(double tiempoEjecucion) {
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public int getNumValoracionesPositivas() {
        return numValoracionesPositivas;
    }

    public void setNumValoracionesPositivas(int numValoracionesPositivas) {
        this.numValoracionesPositivas = numValoracionesPositivas;
    }

    public double getPorcentajeAcierto() {
        return porcentajeAcierto;
    }

    public void setPorcentajeAcierto(double porcentajeAcierto) {
        this.porcentajeAcierto = porcentajeAcierto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        InteraccionAgente other = (InteraccionAgente) obj;
        return Objects.equals(identificador, other.identificador);
    }

	@Override
	public String toString() {
		return "InteraccionAgente [identificador=" + identificador + ", tipo=" + tipo + ", peticion=" + peticion
				+ ", respuesta=" + respuesta + ", tiempoEjecucion=" + tiempoEjecucion + ", numValoracionesPositivas="
				+ numValoracionesPositivas + ", porcentajeAcierto=" + porcentajeAcierto + "]";
	}

  
}

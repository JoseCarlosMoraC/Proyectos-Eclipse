package Juegos.Modelo;

import java.util.Objects;

public class EstudioDesarrollo {
    private String nombreEstudio;
    private String desarrolladorPrincipal;
    private String pais;

    public EstudioDesarrollo(String nombreEstudio, String desarrolladorPrincipal, String pais) {
        this.nombreEstudio = nombreEstudio;
        this.desarrolladorPrincipal = desarrolladorPrincipal;
        this.pais = pais;
    }

    public String getNombreEstudio() {
        return nombreEstudio;
    }

    public String getDesarrolladorPrincipal() {
        return desarrolladorPrincipal;
    }

    public String getPais() {
        return pais;
    }

    @Override
    public String toString() {
        return nombreEstudio + " - " + desarrolladorPrincipal + " (" + pais + ")";
    }

	@Override
	public int hashCode() {
		return Objects.hash(nombreEstudio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstudioDesarrollo other = (EstudioDesarrollo) obj;
		return Objects.equals(nombreEstudio, other.nombreEstudio);
	}
    
}


package Tema1.Simulacro.Formula.Models;

import java.util.Objects;

/*
 * Clase Piloto: cada piloto pertenece a un equipo (idEquipo).
 * El enunciado pide relacionarlos después, por eso guardamos solo el id del equipo,
 * sin tener el objeto Equipo aquí.
 */
public class Piloto {
    private String identificadorPiloto; // ID único para igualdad en Set
    private String nombrePiloto;
    private int puntos;
    private String identificadorEquipo; // Se usa para vincularlo al Equipo
    private String pais;

    public Piloto() {
    }

    public Piloto(String identificadorPiloto, String nombrePiloto, int puntos, String identificadorEquipo, String pais) {
        super();
        this.identificadorPiloto = identificadorPiloto;
        this.nombrePiloto = nombrePiloto;
        this.puntos = puntos;
        this.identificadorEquipo = identificadorEquipo;
        this.pais = pais;
    }

    public String getIdentificadorPiloto() {
        return identificadorPiloto;
    }

    public void setIdentificadorPiloto(String identificadorPiloto) {
        this.identificadorPiloto = identificadorPiloto;
    }

    public String getNombrePiloto() {
        return nombrePiloto;
    }

    public void setNombrePiloto(String nombrePiloto) {
        this.nombrePiloto = nombrePiloto;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getIdentificadorEquipo() {
        return identificadorEquipo;
    }

    public void setIdentificadorEquipo(String identificadorEquipo) {
        this.identificadorEquipo = identificadorEquipo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    /*
     * Necesario para permitir uso en Set y evitar duplicados.
     */
    @Override
    public int hashCode() {
        return Objects.hash(identificadorPiloto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Piloto other = (Piloto) obj;
        return Objects.equals(identificadorPiloto, other.identificadorPiloto);
    }

    @Override
    public String toString() {
        return "Piloto [identificadorPiloto=" + identificadorPiloto + ", nombrePiloto=" + nombrePiloto + ", puntos="
                + puntos + ", identificadorEquipo=" + identificadorEquipo + ", pais=" + pais + "]";
    }
}
<<<<<<< HEAD
=======

public Piloto() {
	// TODO Auto-generated constructor stub
}

public int getIdentificadorPiloto() {
	return identificadorPiloto;
}

public void setIdentificadorPiloto(int identificadorPiloto) {
	this.identificadorPiloto = identificadorPiloto;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}

public String getPais() {
	return pais;
}

public void setPais(String pais) {
	this.pais = pais;
}

public int getPuntos() {
	return puntos;
}

public void setPuntos(int puntos) {
	this.puntos = puntos;
}

@Override
public int hashCode() {
	return Objects.hash(identificadorPiloto);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Piloto other = (Piloto) obj;
	return identificadorPiloto == other.identificadorPiloto;
}

@Override
public String toString() {
	return "Piloto [identificadorPiloto=" + identificadorPiloto + ", nombre=" + nombre + ", pais=" + pais + ", puntos="
			+ puntos + "]";
}


}
>>>>>>> 8c3952bc8e9faa626c6090ce1b50c2febeed66fe

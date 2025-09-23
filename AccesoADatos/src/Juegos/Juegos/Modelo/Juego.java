package Juegos.Modelo;

import java.util.Objects;


// Implementa Comparable para ordenar según descargas
public class Juego implements Comparable<Juego> {
	
    // Atributos principales de un juego
    private String titulo;
    private String añoPublicacion;
    private GeneroJuego genero;
    private int numDescargas;
    private EstadoJuego estado;

    // Constructor para crear un juego, por defecto con estado EN_REVISION
    public Juego(String titulo, String añoPublicacion, GeneroJuego genero, int numDescargas) {
        this.titulo = titulo;
        this.añoPublicacion = añoPublicacion;
        this.genero = genero;
        this.numDescargas = numDescargas;
        this.estado = EstadoJuego.EN_REVISION; // Estado inicial por defecto
    }

    // Getters para acceder a los atributos
    public String getTitulo() {
        return titulo;
    }

    public String getAñoPublicacion() {
        return añoPublicacion;
    }

    public GeneroJuego getGenero() {
        return genero;
    }

    public int getNumDescargas() {
        return numDescargas;
    }

    public EstadoJuego getEstado() {
        return estado;
    }

    // Setter para poder modificar el estado del juego
    public void setEstado(EstadoJuego estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return titulo + " (" + genero + ", " + añoPublicacion + ") - Descargas: " + numDescargas + " - Estado: " + estado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(añoPublicacion, titulo);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)  // Mismo objeto
            return true;
        if (obj == null)  // No es igual a null
            return false;
        if (getClass() != obj.getClass())  // Mismo tipo
            return false;
        Juego other = (Juego) obj;
        // Comparo título y año, si ambos coinciden son el mismo juego
        return Objects.equals(añoPublicacion, other.añoPublicacion) && Objects.equals(titulo, other.titulo);
    }

    // Implemento compareTo para ordenar juegos por número de descargas de forma descendente
    @Override
    public int compareTo(Juego o) {
        // Ordeno de mayor a menor número de descargas usando Integer.compare porque es primitivo
        return Integer.compare(o.numDescargas, this.numDescargas);
    }
}

package Simulacro.BancoDeAlimentos.Models;

import java.time.LocalDate;
import java.util.Objects;

/*
 * Clase Trabajador
 * Representa a un colaborador del Banco de Alimentos.
 * Según el enunciado, debemos almacenar:
 * nombre, DNI (identificador único), fecha de nacimiento,
 * tipo (ASALARIADO o VOLUNTARIO) y el id del centro donde trabaja.
 */
public class Trabajador {
    private String nombre;
    private String dni; // Identificador único → por eso equals/hashCode basados en él
    private LocalDate fecha;
    private Tipo tipo; // Enum que distingue entre ASALARIADO y VOLUNTARIO
    private String idCentro; // Para saber a qué centro pertenece
    private CentroLogistico centrologistico; // Referencia al centro (relación bidireccional opcional)

    /*
     * Constructor principal.
     * Recibe los datos del trabajador excepto el centro.
     * El idCentro debería asignarse cuando se registre en un CentroLogistico.
     */
    public Trabajador(String nombre, String dni, LocalDate fecha, Tipo tipo) {
        this.nombre = nombre;
        this.dni = dni;
        this.fecha = fecha;
        this.tipo = tipo;
        // Aquí se usa centrologistico antes de que tenga valor → peligro de NullPointer.
        // En la lectura por XML esto se corrige después con setIdCentro(...)
        this.idCentro = centrologistico != null ? centrologistico.getId() : null;
    }

    public Trabajador() {
        // Constructor vacío para crear objetos y rellenarlos después (DOM lo necesita)
    }

    // Getters y setters generados
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }

    public String getIdCentro() { return idCentro; }
    public void setIdCentro(String idCentro) { this.idCentro = idCentro; }

    public CentroLogistico getCentrologistico() { return centrologistico; }
    public void setCentrologistico(CentroLogistico centrologistico) { this.centrologistico = centrologistico; }

    // Dos trabajadores son iguales si tienen el mismo DNI
    @Override
    public int hashCode() { return Objects.hash(dni); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Trabajador other = (Trabajador) obj;
        return Objects.equals(dni, other.dni);
    }

    @Override
    public String toString() {
        return "Trabajador [nombre=" + nombre + ", dni=" + dni + ", fecha=" + fecha + ", tipo=" + tipo + ", idCentro="
                + idCentro + "]";
    }
}

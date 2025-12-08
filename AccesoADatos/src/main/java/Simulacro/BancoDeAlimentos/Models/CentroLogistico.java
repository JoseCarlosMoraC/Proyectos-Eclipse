package Simulacro.BancoDeAlimentos.Models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
 * Clase CentroLogistico
 * Representa uno de los centros del banco de alimentos.
 * Según el enunciado, debe almacenar:
 * id, nombre, ciudad, número de comedores abastecidos
 * y la lista de colaboradores que trabajan en él.
 */
public class CentroLogistico {

    private String id; // Identificador único
    private String nombre;
    private String ciudad;
    private int numComedores;
    private Set<Trabajador> trabajadores; // Estructura Set → evita duplicados automáticamente

    // Constructor principal
    public CentroLogistico(String id, String nombre, String ciudad, int numComedores, Set<Trabajador> trabajadores) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.numComedores = numComedores;
        // Aunque recibe trabajadores, los sustituyes siempre por uno nuevo → innecesario
        this.trabajadores = new HashSet<>();
    }

    // Constructor vacío para DOM
    public CentroLogistico() {
        this.trabajadores = new HashSet<>();
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public int getNumComedores() { return numComedores; }
    public void setNumComedores(int numComedores) { this.numComedores = numComedores; }

    public Set<Trabajador> getTrabajadores() { return trabajadores; }
    public void setTrabajadores(Set<Trabajador> trabajadores) { this.trabajadores = trabajadores; }

    // Dos centros son iguales si su id coincide
    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CentroLogistico other = (CentroLogistico) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "CentroLogistico [id=" + id + ", nombre=" + nombre + ", ciudad=" + ciudad + ", numComedores=" + numComedores
                + ", trabajadores=" + trabajadores + "]";
    }
}

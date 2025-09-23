package EldenRing.EldenRing;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

// Clase que representa a un Sinluz en el mundo de Elden Ring
// Cada Sinluz tiene un id autoincremental, un nombre y una colección de encuentros
public class Sinluz implements Comparable<Sinluz> {
    private int id;
    private static int contador;
    private String nombre;
    private Set<Encuentro> encuentros;

    // Uso un HashSet porque el enunciado da a entender que no deben repetirse encuentros
    // Además, HashSet no garantiza orden, y eso está bien para este caso
    public Sinluz(String nombre) {
        this.id = contador++;
        this.nombre = nombre;
        this.encuentros = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Sinluz.contador = contador;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<Encuentro> getEncuentros() {
        return encuentros;
    }

    public void setEncuentros(Set<Encuentro> encuentros) {
        this.encuentros = encuentros;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Dos Sinluz son iguales si tienen el mismo id
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Sinluz other = (Sinluz) obj;
        return id == other.id;
    }

    // Para ordenar los Sinluz alfabéticamente por su nombre
    @Override
    public int compareTo(Sinluz s) {
        return this.nombre.compareToIgnoreCase(s.nombre);
    }

    @Override
    public String toString() {
        return "Sinluz [id=" + id + ", nombre=" + nombre + ", encuentros=" + encuentros + "]";
    }

    // Método para agregar un encuentro al conjunto
    public void agregarEncuentro(Encuentro e) {
        encuentros.add(e); // Como es un Set, si ya existe uno igual, no lo duplica
    }
    public void tieneEncuentro(int dificultad) {
    	boolean encontrado = false;
    	Sinluz s = null;
    	Iterator<Encuentro> it = encuentros.iterator();

        while (it.hasNext()) {
            Encuentro e = it.next();

            // Compruebo si la dificultad del encuentro es mayor que la que pide
            if (e.getDificultad() > dificultad) {
          
                // Si lo es, lo muestro por pantalla
                System.out.println(e);
            }
    }
		
}
}

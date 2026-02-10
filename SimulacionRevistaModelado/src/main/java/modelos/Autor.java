package modelos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autor")
public class Autor {
    
    @Id  
    private String dni;
    private String nombre;
    private String email;

    @ManyToMany(mappedBy = "autores")  
    private Set<Articulo> articulos;

    public Autor() {
        super();
        this.articulos = new HashSet<>();  
    }

    public Autor(String dni) {
        super();
        this.dni = dni;
        this.articulos = new HashSet<>();
    }

    public Autor(String dni, String nombre, String email) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.email = email;
        this.articulos = new HashSet<>();  
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Autor other = (Autor) obj;
        return Objects.equals(dni, other.dni);  
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Articulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(Set<Articulo> articulos) {
        this.articulos = articulos;
    }

    public void addArticulo(Articulo a) {
        this.articulos.add(a);
        
        if (!a.getAutores().contains(this)) {
            a.getAutores().add(this);
        }
    }

    public void removeArticulo(Articulo a) {
        this.articulos.remove(a);
        
        if (a.getAutores().contains(this)) {
            a.getAutores().remove(this);
        }
    }
}
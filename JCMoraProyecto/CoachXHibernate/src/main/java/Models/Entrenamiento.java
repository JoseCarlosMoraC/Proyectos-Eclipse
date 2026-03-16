package Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "entrenamientos")
public class Entrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column
    private Integer duracion;

    @Column(length = 50)
    private String tipo;

    @Column(length = 500)
    private String objetivo;

    @Column(name = "carga_fisica")
    private Integer cargaFisica; 


    @ManyToMany(mappedBy = "entrenamientos")
    private Set<Jugador> jugadores = new HashSet<>();

  
    public Entrenamiento() {}

    public Entrenamiento(LocalDateTime fecha, Integer duracion, String tipo, String objetivo) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.tipo = tipo;
        this.objetivo = objetivo;
    }


    public void addJugador(Jugador jugador) {
        this.jugadores.add(jugador);
        jugador.getEntrenamientos().add(this);
    }

    public void removeJugador(Jugador jugador) {
        this.jugadores.remove(jugador);
        jugador.getEntrenamientos().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Integer getCargaFisica() {
        return cargaFisica;
    }

    public void setCargaFisica(Integer cargaFisica) {
        this.cargaFisica = cargaFisica;
    }

    public Set<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Set<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", duracion=" + duracion +
                ", tipo='" + tipo + '\'' +
                ", asistentes=" + jugadores.size() +
                '}';
    }
}
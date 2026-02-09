package ModelsReunion;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reunion")
public class Reunion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Primary key

    private LocalDateTime fecha;
    private String asunto;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idSala" )  // Foreign key column
    private Sala sala;  // Entity reference

    // Constructors
    public Reunion() {}

    public Reunion(Long id, LocalDateTime fecha, String asunto, Sala sala) {
        this.id = id;
        this.fecha = fecha;
        this.asunto = asunto;
        this.sala = sala;
    }

    public Reunion(LocalDateTime fecha, String asunto) {
        this.fecha = fecha;
        this.asunto = asunto;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getAsunto() { return asunto; }
    public void setAsunto(String asunto) { this.asunto = asunto; }

    public Sala getSala() { return sala; }
    public void setSala(Sala sala) { this.sala = sala; }

    @Override
    public String toString() {
        return "Reunion [id=" + id + ", fecha=" + fecha + ", asunto=" + asunto + ", sala=" + sala + "]";
    }
}

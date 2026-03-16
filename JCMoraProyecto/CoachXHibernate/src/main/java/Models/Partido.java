package Models;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "partidos")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false, length = 100)
    private String rival;

    @Column(length = 100)
    private String lugar;

    @Column(name = "goles_favor")
    private Integer golesFavor;

    @Column(name = "goles_contra")
    private Integer golesContra;

    @Column(length = 20)
    private String resultado; 


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

 
    public Partido() {}

    public Partido(LocalDateTime fecha, String rival, String lugar) {
        this.fecha = fecha;
        this.rival = rival;
        this.lugar = lugar;
    }


    public void calcularResultado() {
        if (golesFavor != null && golesContra != null) {
            if (golesFavor > golesContra) {
                this.resultado = "Victoria";
            } else if (golesFavor < golesContra) {
                this.resultado = "Derrota";
            } else {
                this.resultado = "Empate";
            }
        }
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

    public String getRival() {
        return rival;
    }

    public void setRival(String rival) {
        this.rival = rival;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Integer getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(Integer golesFavor) {
        this.golesFavor = golesFavor;
        calcularResultado();
    }

    public Integer getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(Integer golesContra) {
        this.golesContra = golesContra;
        calcularResultado();
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", rival='" + rival + '\'' +
                ", resultado=" + (golesFavor != null ? golesFavor + "-" + golesContra : "Pendiente") +
                '}';
    }
}
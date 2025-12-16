package Models;

import java.time.LocalDate;

public class Partida {

    private int id;
    private int torneoId;
    private Jugador narradorId;
    private LocalDate fecha;
    private Resultado resultado;

    public Partida() {}

    public Partida(int id, int torneoId, Jugador narradorId, LocalDate fecha, Resultado resultado) {
        this.id = id;
        this.torneoId = torneoId;
        this.narradorId = narradorId;
        this.fecha = fecha;
        this.resultado = resultado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getTorneoId() { return torneoId; }
    public void setTorneoId(int torneoId) { this.torneoId = torneoId; }

    public Jugador getNarradorId() { return narradorId; }
    public void setNarradorId(Jugador narradorId) { this.narradorId = narradorId; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Resultado getResultado() { return resultado; }
    public void setResultado(Resultado resultado) { this.resultado = resultado; }

    @Override
    public String toString() {
        return "Partida [id=" + id + ", torneoId=" + torneoId + ", narradorId=" + narradorId + ", fecha=" + fecha + ", resultado=" + resultado + "]";
    }
}

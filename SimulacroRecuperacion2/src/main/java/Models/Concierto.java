package Models;

import java.util.Objects;

public class Concierto {
    private int id;
    private String fecha;
    private String descripcion;
    private GeneroMusical genero;
    private Artista artistaCabecera;

    public Concierto(int id, String fecha, String descripcion, GeneroMusical genero, Artista artistaCabecera) {
        super();
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.genero = genero;
        this.artistaCabecera = artistaCabecera;
    }

    public Concierto() {
        // Constructor vacío
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public GeneroMusical getGenero() {
        return genero;
    }

    public void setGenero(GeneroMusical genero) {
        this.genero = genero;
    }

    public Artista getArtistaCabecera() {
        return artistaCabecera;
    }

    public void setArtistaCabecera(Artista artistaCabecera) {
        this.artistaCabecera = artistaCabecera;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Concierto other = (Concierto) obj;
        return Objects.equals(fecha, other.fecha);
    }

    @Override
    public String toString() {
        return "Concierto [id=" + id + ", fecha=" + fecha + ", descripcion=" + descripcion 
                + ", genero=" + genero + ", artistaCabecera=" + artistaCabecera + "]";
    }
}
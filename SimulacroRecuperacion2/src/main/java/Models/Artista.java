package Models;

import java.util.Objects;

public class Artista {
    private String nombreArtistico;
    private String codigo;
    private int numIntegrantes;
    private String emailManager;

    public Artista(String nombreArtistico, String codigo, int numIntegrantes, String emailManager) {
        super();
        this.nombreArtistico = nombreArtistico;
        this.codigo = codigo;
        this.numIntegrantes = numIntegrantes;
        this.emailManager = emailManager;
    }

    public Artista() {
        // Constructor vacío
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getNumIntegrantes() {
        return numIntegrantes;
    }

    public void setNumIntegrantes(int numIntegrantes) {
        this.numIntegrantes = numIntegrantes;
    }

    public String getEmailManager() {
        return emailManager;
    }

    public void setEmailManager(String emailManager) {
        this.emailManager = emailManager;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Artista other = (Artista) obj;
        return Objects.equals(codigo, other.codigo);
    }

    @Override
    public String toString() {
        return "Artista [nombreArtistico=" + nombreArtistico + ", codigo=" + codigo + ", numIntegrantes=" 
                + numIntegrantes + ", emailManager=" + emailManager + "]";
    }
}
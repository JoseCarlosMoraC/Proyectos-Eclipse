package ExamenPrueba.ExamenPrueba;

import java.util.Objects;

public class Observacion {
    private String docente;
    private String mensaje;

    public Observacion(String docente, String mensaje) {
        this.docente = docente;
        this.mensaje = mensaje;
    }

    public String getDocente() {
        return docente;
    }

    public String getMensaje() {
        return mensaje;
    }

    @Override
    public String toString() {
        return docente + ": " + mensaje;
    }

    @Override
    public int hashCode() {
        return Objects.hash(docente, mensaje);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Observacion)) return false;
        Observacion o = (Observacion) obj;
        return docente.equals(o.docente) && mensaje.equals(o.mensaje);
    }
}

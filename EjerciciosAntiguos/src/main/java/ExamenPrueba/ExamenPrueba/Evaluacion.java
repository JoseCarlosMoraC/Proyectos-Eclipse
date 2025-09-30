package ExamenPrueba.ExamenPrueba;

import java.time.LocalDate;
import java.util.Objects;

public class Evaluacion {
    private LocalDate fecha;
    private Alumno alumno;
    private String asignatura;
    private double nota;
    private EstadoEvaluacion estado;

    public Evaluacion(LocalDate fecha, Alumno alumno, String asignatura) {
        this.fecha = fecha;
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.nota = -1; // No calificada
        this.estado = EstadoEvaluacion.PENDIENTE;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public double getNota() {
        return nota;
    }

    public EstadoEvaluacion getEstado() {
        return estado;
    }

    public void setNota(double nota) {
        this.nota = nota;
        this.estado = EstadoEvaluacion.CALIFICADA;
    }

    public void setEstado(EstadoEvaluacion estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evaluacion)) return false;
        Evaluacion e = (Evaluacion) o;
        return fecha.equals(e.fecha) &&
               alumno.equals(e.alumno) &&
               asignatura.equalsIgnoreCase(e.asignatura);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, alumno, asignatura.toLowerCase());
    }

    @Override
    public String toString() {
        return asignatura + " - " + alumno + " (" + fecha + ")";
    }
}
package SimulacroFormula1.Formula.Repositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import SimulacroFormula1.Formula.Exception.Formula1Exception;
import SimulacroFormula1.Formula.Models.Equipo;
import SimulacroFormula1.Formula.Models.Piloto;



/*
 * Repositorio: Gestiona equipos y pilotos.
 * El enunciado pide:
 *  - Añadir equipo sin duplicados.
 *  - Añadir piloto a su equipo usando iteradores, sin streams, sin lambdas.
 *  - Obtener pilotos con puntos superiores a un valor.
 */
public class RepositorioEquipos {
    private Set<Equipo> listaEquipos;

    public RepositorioEquipos() {
        this.listaEquipos = new HashSet<Equipo>();
    }

    public Set<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(Set<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    /*
     * Método para agregar un equipo comprobando que no exista previamente.
     * Se usa Iterator como exige el enunciado.
     */
    public void agregarEquipo(Equipo e) throws Formula1Exception {
        boolean encontrado = false;

        Iterator<Equipo> it = listaEquipos.iterator();
        while (it.hasNext() && !encontrado) {
            Equipo eq = it.next();
            if (eq.getIdentificadorEquipo().equals(e.getIdentificadorEquipo())) {
                encontrado = true;
            }
        }

        if (encontrado)
            throw new Formula1Exception("El equipo ya existe");

        listaEquipos.add(e);
    }

    /*
     * Método para asignar piloto a su equipo.
     * Se recorre el Set con Iterator, como especifica el ejercicio.
     * Se compara por idEquipo (relación Documento -> Memoria)
     */
    public void agregarPilotoAEquipo(Piloto p) throws Formula1Exception {
        boolean encontrado = false;

        Iterator<Equipo> it = listaEquipos.iterator();
        while (it.hasNext() && !encontrado) {
            Equipo eq = it.next();
            if (eq.getIdentificadorEquipo().equals(p.getIdentificadorEquipo())) {
                eq.getPilotos().add(p);
                encontrado = true;
            }
        }

        if (!encontrado)
            throw new Formula1Exception("No existe un equipo con ese id");
    }

    /*
     * Devuelve lista de pilotos con puntos mayores al valor indicado.
     */
    public List<Piloto> getPilotosConPuntosMayor(int puntos) throws Formula1Exception {
        List<Piloto> lista = new ArrayList<Piloto>();
        boolean encontrado = false;

        Iterator<Equipo> it = listaEquipos.iterator();
        while (it.hasNext()) {
            Equipo eq = it.next();

            Iterator<Piloto> itP = eq.getPilotos().iterator();
            while (itP.hasNext()) {
                Piloto p = itP.next();
                if (p.getPuntos() > puntos) {
                    lista.add(p);
                    encontrado = true;
                }
            }
        }

        if (!encontrado)
            throw new Formula1Exception("No hay pilotos con más de " + puntos + " puntos");

        return lista;
    }
}

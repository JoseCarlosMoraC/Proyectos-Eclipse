package Simulacro.BancoDeAlimentos.Repositories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import Simulacro.BancoDeAlimentos.Exception.BancoException;
import Simulacro.BancoDeAlimentos.Models.CentroLogistico;
import Simulacro.BancoDeAlimentos.Models.Tipo;
import Simulacro.BancoDeAlimentos.Models.Trabajador;

public class BancoAlimentos {

    // Conjunto de centros logísticos
    private Set<CentroLogistico> listaCentros;

    // Constructor: inicializa la lista de centros como vacía
    public BancoAlimentos() {
        super();
        this.listaCentros = new HashSet<CentroLogistico>();
    }

    // Devuelve la lista de centros
    public Set<CentroLogistico> getListaCentros() {
        return listaCentros;
    }

    // Asigna la lista de centros
    public void setListaCentros(Set<CentroLogistico> listaCentros) {
        this.listaCentros = listaCentros;
    }

    // HashCode basado en la lista de centros
    @Override
    public int hashCode() {
        return Objects.hash(listaCentros);
    }

    // Equals basado en la lista de centros
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BancoAlimentos other = (BancoAlimentos) obj;
        return Objects.equals(listaCentros, other.listaCentros);
    }

    // Representación en texto de la clase
    @Override
    public String toString() {
        return "BancoAlimentos [listaCentros=" + listaCentros + "]";
    }

    /**
     * Agrega un centro logístico nuevo.
     * Lanza excepción si el centro ya existe.
     */
    public void agregarCentroLogistico(String id) throws BancoException {
        boolean encontrado = false;
        CentroLogistico centro = null;

        Iterator<CentroLogistico> it = listaCentros.iterator();
        while (it.hasNext() && !encontrado) {
            CentroLogistico c = it.next();
            if (c.getId().equalsIgnoreCase(id)) {
                encontrado = true;
                centro = c;
                listaCentros.add(centro);
            }
        }

        if (encontrado)
            throw new BancoException("Ya existe el banco");
    }

    /**
     * Agrega un trabajador a un centro específico.
     * Lanza excepción si el centro o el trabajador ya existen.
     */
    public void agregarTrabajadorACentro(String dni, String centro) throws BancoException {
        boolean encontrado = false;
        CentroLogistico centroEncontrado = null;

        // Buscar el centro por ID
        Iterator<CentroLogistico> itCentros = listaCentros.iterator();
        while (itCentros.hasNext() && !encontrado) {
            CentroLogistico c = itCentros.next();
            if (c.getId().equalsIgnoreCase(centro)) {
                centroEncontrado = c;
            }
        }

        if (!encontrado) {
            throw new BancoException("No se encontró el centro logístico");
        }

        // Verificar si el trabajador ya existe
        Iterator<Trabajador> itTrabajadores = centroEncontrado.getTrabajadores().iterator();
        while (itTrabajadores.hasNext() && !encontrado) {
            Trabajador t = itTrabajadores.next();
            if (t.getDni().equalsIgnoreCase(dni)) {
                throw new BancoException("El trabajador ya existe en este centro");
            } else {
                centroEncontrado.getTrabajadores().add(t);
            }
        }
    }

    /**
     * Devuelve un centro logístico por su ID.
     * Lanza excepción si no existe.
     */
    public CentroLogistico getCentroLogistico(String id) throws BancoException {
        boolean encontrado = false;
        CentroLogistico centro = null;

        Iterator<CentroLogistico> it = listaCentros.iterator();
        while (it.hasNext() && !encontrado) {
            CentroLogistico c = it.next();
            if (c.getId().equalsIgnoreCase(id)) {
                encontrado = true;
                centro = c;
            }
        }

        if (!encontrado)
            throw new BancoException("No se encuentra ningún centro asociado a ese id");

        return centro;
    }

    /**
     * Devuelve un trabajador por su DNI.
     * Lanza excepción si no se encuentra.
     */
    public Trabajador getTrabajador(String dni) throws BancoException {
        boolean encontrado = false;
        Trabajador trabajador = null;

        Iterator<CentroLogistico> itCentros = listaCentros.iterator();
        while (itCentros.hasNext()) {
            CentroLogistico centro = itCentros.next();

            Iterator<Trabajador> it = centro.getTrabajadores().iterator();
            while (it.hasNext() && !encontrado) {
                Trabajador t = it.next();
                if (t.getDni().equalsIgnoreCase(dni)) {
                    encontrado = true;
                    trabajador = t;
                } 
                if(!encontrado) {
                    throw new BancoException("No se encuentra ningún trabajador asociado a ese dni");
                }
            }
        }
        return trabajador;
    }

    /**
     * Devuelve una lista de trabajadores filtrada por tipo (ASALARIADO o VOLUNTARIO).
     * Lanza excepción si no se encuentra ninguno del tipo especificado.
     */
    public List<Trabajador> getColaboradoresPorTipo(String tipo) throws BancoException {
        List<Trabajador> trabajadoresPorTipo = new ArrayList<Trabajador>();
        boolean encontrado = false;

        Iterator<CentroLogistico> itCentros = listaCentros.iterator();
        while (itCentros.hasNext()) {
            CentroLogistico centro = itCentros.next();

            Iterator<Trabajador> it = equ.iterator();
            while (it.hasNext()) {
                Trabajador t = it.next();
                if (t.getTipo().equals(Tipo.valueOf(tipo.toUpperCase()))) {
                    trabajadoresPorTipo.add(t);
                    encontrado = true;
                }
            }
        }

        if (!encontrado) {
            throw new BancoException("No se encontró ningún colaborador de tipo " + tipo);
        }

        return trabajadoresPorTipo;
    }

}

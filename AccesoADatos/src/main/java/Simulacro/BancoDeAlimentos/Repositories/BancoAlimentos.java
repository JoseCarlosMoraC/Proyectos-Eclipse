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

/*
 * Clase BancoAlimentos
 * Contiene la colección de centros logísticos.
 * Es la clase donde se realiza la gestión:
 *  - Agregar centro logístico (controlando duplicados)
 *  - Agregar trabajador a un centro (controlando duplicados)
 *  - Obtener centro por ID
 *  - Obtener trabajador por DNI
 *  - Obtener colaboradores según tipo (ASALARIADO o VOLUNTARIO)
 */
public class BancoAlimentos {

    private Set<CentroLogistico> listaCentros; // Se usa Set para evitar centros duplicados

    public BancoAlimentos() {
        this.listaCentros = new HashSet<>();
    }

    public Set<CentroLogistico> getListaCentros() { return listaCentros; }
    public void setListaCentros(Set<CentroLogistico> listaCentros) { this.listaCentros = listaCentros; }

    @Override
    public int hashCode() { return Objects.hash(listaCentros); }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BancoAlimentos other = (BancoAlimentos) obj;
        return Objects.equals(listaCentros, other.listaCentros);
    }

    @Override
    public String toString() {
        return "BancoAlimentos [listaCentros=" + listaCentros + "]";
    }

    /*
     * Agregar un centro logístico.
     * Según el enunciado, si ya existe un centro con ese ID, se debe lanzar BancoException.
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
            }
        }

        if (encontrado)
            throw new BancoException("Ya existe el centro con ese ID"); // Se cumple el requisito del enunciado

        listaCentros.add(new CentroLogistico()); // Aquí solo se añade uno nuevo vacío
        // (En un caso real se recibirían los datos del centro)
    }

    /*
     * Agregar trabajador a un centro dado.
     * Si el centro no existe → excepción.
     * Si el trabajador ya está asociado → excepción.
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
                encontrado = true;
            }
        }

        if (!encontrado)
            throw new BancoException("No se encontró el centro logístico");

        // Comprobar trabajador duplicado dentro del centro
        Iterator<Trabajador> itTrabajadores = centroEncontrado.getTrabajadores().iterator();
        while (itTrabajadores.hasNext()) {
            Trabajador t = itTrabajadores.next();
            if (t.getDni().equalsIgnoreCase(dni))
                throw new BancoException("El trabajador ya existe en este centro");
        }

        // Si no estaba repetido, se debería agregar el trabajador,
        // pero falta el objeto trabajador. El método está incompleto lógicamente,
        // aunque respeta la estructura del ejercicio.
    }

    /*
     * Obtener un centro por ID.
     * Si no se encuentra → BancoException.
     */
    public CentroLogistico getCentroLogistico(String id) throws BancoException {
        for (CentroLogistico c : listaCentros) {
            if (c.getId().equalsIgnoreCase(id))
                return c;
        }
        throw new BancoException("No se encuentra ningún centro asociado a ese id");
    }

    /*
     * Obtener un trabajador por DNI recorriendo todos los centros.
     * Si no se encuentra → BancoException.
     */
    public Trabajador getTrabajador(String dni) throws BancoException {
        for (CentroLogistico centro : listaCentros) {
            for (Trabajador t : centro.getTrabajadores()) {
                if (t.getDni().equalsIgnoreCase(dni))
                    return t;
            }
        }
        throw new BancoException("No se encuentra ningún trabajador asociado a ese dni");
    }

    /*
     * Apartado 3 del enunciado:
     * Devolver lista de colaboradores según el Tipo (VOLUNTARIO o ASALARIADO).
     */
    public List<Trabajador> getColaboradoresPorTipo(String tipo) throws BancoException {
        List<Trabajador> trabajadoresPorTipo = new ArrayList<>();

        for (CentroLogistico centro : listaCentros) {
            for (Trabajador t : centro.getTrabajadores()) {
                if (t.getTipo().equals(Tipo.valueOf(tipo.toUpperCase())))
                    trabajadoresPorTipo.add(t);
            }
        }

        if (trabajadoresPorTipo.isEmpty())
            throw new BancoException("No se encontró ningún colaborador de tipo " + tipo);

        return trabajadoresPorTipo;
    }
}

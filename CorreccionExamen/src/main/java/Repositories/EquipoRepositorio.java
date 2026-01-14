package Repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Exception.TorneoException;
import Models.Equipo;
import Services.TorneoService;


public class EquipoRepositorio {
    private TorneoService equiServicio;

    public EquipoRepositorio(TorneoService equiServicio) {
        this.equiServicio = equiServicio;
    }

    public EquipoRepositorio() {
        // Constructor vacío - NO crear TorneoService aquí
    }

    public TorneoService getEquiServicio() {
        return equiServicio;
    }

    public void setEquiServicio(TorneoService equiServicio) {
        this.equiServicio = equiServicio;
    }

    @Override
    public String toString() {
        return "EquipoRepositorio [equiServicio=" + equiServicio + "]";
    }

    /**
     * Obtiene un equipo por su código usando Iterator
     */
    public Equipo getEquipo(String codigo) throws TorneoException {
        boolean encontrado = false;
        Equipo equipo = null;

        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext() && !encontrado) {
            Equipo e = it.next();
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                encontrado = true;
                equipo = e;
            }
        }

        if (!encontrado) {
            throw new TorneoException("No se encuentra ningún equipo asociado a ese codigo");
        }

        return equipo;
    }

    /**
     * Agrega un equipo a la lista usando Iterator para verificar
     */
    public void agregarEquipo(Equipo equipo) throws TorneoException {
        boolean existe = false;
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext() && !existe) {
            Equipo e = it.next();
            if (e.getCodigo().equalsIgnoreCase(equipo.getCodigo())) {
                existe = true;
            }
        }
        
        if (existe) {
            throw new TorneoException("Ya existe el equipo con código: " + equipo.getCodigo());
        }
        
        equiServicio.getListaEquipos().add(equipo);
    }

    /**
     * Elimina un equipo por su código usando Iterator
     */
    public void eliminarEquipo(String codigo) throws TorneoException {
        boolean encontrado = false;
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext() && !encontrado) {
            Equipo e = it.next();
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                it.remove(); // Usar remove del Iterator
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            throw new TorneoException("No se encuentra el equipo con código: " + codigo);
        }
    }

    /**
     * Actualiza los datos de un equipo usando Iterator
     */
    public void actualizarEquipo(Equipo equipoActualizado) throws TorneoException {
        boolean encontrado = false;
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext() && !encontrado) {
            Equipo e = it.next();
            if (e.getCodigo().equalsIgnoreCase(equipoActualizado.getCodigo())) {
                e.setNombreCompleto(equipoActualizado.getNombreCompleto());
                e.setEmailContacto(equipoActualizado.getEmailContacto());
                e.setNumJugadores(equipoActualizado.getNumJugadores());
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            throw new TorneoException("No se encuentra el equipo con código: " + equipoActualizado.getCodigo());
        }
    }

    /**
     * Obtiene todos los equipos usando Iterator
     */
    public List<Equipo> getTodosLosEquipos() {
        List<Equipo> lista = new ArrayList<>();
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext()) {
            lista.add(it.next());
        }
        
        return lista;
    }

    /**
     * Filtra equipos por número mínimo de jugadores usando Iterator
     */
    public List<Equipo> getEquiposPorNumJugadores(int numMinimo) {
        List<Equipo> equiposFiltrados = new ArrayList<>();
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext()) {
            Equipo e = it.next();
            if (e.getNumJugadores() >= numMinimo) {
                equiposFiltrados.add(e);
            }
        }
        
        return equiposFiltrados;
    }

    /**
     * Busca equipos por nombre usando Iterator
     */
    public List<Equipo> buscarEquiposPorNombre(String texto) {
        List<Equipo> equiposEncontrados = new ArrayList<>();
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext()) {
            Equipo e = it.next();
            if (e.getNombreCompleto().toLowerCase().contains(texto.toLowerCase())) {
                equiposEncontrados.add(e);
            }
        }
        
        return equiposEncontrados;
    }

    /**
     * Obtiene el equipo con más jugadores usando Iterator
     */
    public Equipo getEquipoConMasJugadores() throws TorneoException {
        if (equiServicio.getListaEquipos().isEmpty()) {
            throw new TorneoException("No hay equipos en el sistema");
        }

        Equipo equipoMax = null;
        int maxJugadores = 0;

        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext()) {
            Equipo e = it.next();
            if (e.getNumJugadores() > maxJugadores) {
                maxJugadores = e.getNumJugadores();
                equipoMax = e;
            }
        }

        return equipoMax;
    }

    /**
     * Obtiene el equipo con menos jugadores usando Iterator
     */
    public Equipo getEquipoConMenosJugadores() throws TorneoException {
        if (equiServicio.getListaEquipos().isEmpty()) {
            throw new TorneoException("No hay equipos en el sistema");
        }

        Equipo equipoMin = null;
        int minJugadores = Integer.MAX_VALUE;

        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext()) {
            Equipo e = it.next();
            if (e.getNumJugadores() < minJugadores) {
                minJugadores = e.getNumJugadores();
                equipoMin = e;
            }
        }

        return equipoMin;
    }

    /**
     * Calcula el promedio de jugadores por equipo usando Iterator
     */
    public double getPromedioJugadoresPorEquipo() throws TorneoException {
        if (equiServicio.getListaEquipos().isEmpty()) {
            throw new TorneoException("No hay equipos en el sistema");
        }

        int totalJugadores = 0;
        int contador = 0;
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext()) {
            Equipo e = it.next();
            totalJugadores += e.getNumJugadores();
            contador++;
        }

        return (double) totalJugadores / contador;
    }

    /**
     * Cuenta el número total de equipos
     */
    public int contarEquipos() {
        return equiServicio.getListaEquipos().size();
    }

    /**
     * Verifica si existe un equipo con un código específico usando Iterator
     */
    public boolean existeEquipo(String codigo) {
        boolean existe = false;
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext() && !existe) {
            Equipo e = it.next();
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                existe = true;
            }
        }
        
        return existe;
    }

    /**
     * Obtiene equipos ordenados por número de jugadores usando Iterator
     */
    public List<Equipo> getEquiposOrdenadosPorJugadores() {
        List<Equipo> equiposOrdenados = new ArrayList<>();
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext()) {
            equiposOrdenados.add(it.next());
        }
        
        equiposOrdenados.sort((e1, e2) -> Integer.compare(e1.getNumJugadores(), e2.getNumJugadores()));
        
        return equiposOrdenados;
    }

    /**
     * Obtiene equipos ordenados por nombre alfabéticamente usando Iterator
     */
    public List<Equipo> getEquiposOrdenadosPorNombre() {
        List<Equipo> equiposOrdenados = new ArrayList<>();
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext()) {
            equiposOrdenados.add(it.next());
        }
        
        equiposOrdenados.sort((e1, e2) -> e1.getNombreCompleto().compareToIgnoreCase(e2.getNombreCompleto()));
        
        return equiposOrdenados;
    }

    /**
     * Obtiene equipos con un número exacto de jugadores usando Iterator
     */
    public List<Equipo> getEquiposPorNumeroExactoJugadores(int numero) {
        List<Equipo> equiposFiltrados = new ArrayList<>();
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext()) {
            Equipo e = it.next();
            if (e.getNumJugadores() == numero) {
                equiposFiltrados.add(e);
            }
        }
        
        return equiposFiltrados;
    }

    /**
     * Obtiene estadísticas de equipos por número de jugadores usando Iterator
     */
    public Map<Integer, Integer> getEstadisticasPorNumJugadores() {
        Map<Integer, Integer> estadisticas = new HashMap<>();
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext()) {
            Equipo e = it.next();
            int numJugadores = e.getNumJugadores();
            estadisticas.put(numJugadores, estadisticas.getOrDefault(numJugadores, 0) + 1);
        }
        
        return estadisticas;
    }

    /**
     * Obtiene la lista de códigos de todos los equipos usando Iterator
     */
    public List<String> getCodigosEquipos() {
        List<String> codigos = new ArrayList<>();
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext()) {
            Equipo e = it.next();
            codigos.add(e.getCodigo());
        }
        
        return codigos;
    }

    /**
     * Busca equipos por dominio de email usando Iterator
     */
    public List<Equipo> getEquiposPorDominioEmail(String dominio) {
        List<Equipo> equiposFiltrados = new ArrayList<>();
        
        Iterator<Equipo> it = equiServicio.getListaEquipos().iterator();
        while (it.hasNext()) {
            Equipo e = it.next();
            if (e.getEmailContacto().toLowerCase().contains(dominio.toLowerCase())) {
                equiposFiltrados.add(e);
            }
        }
        
        return equiposFiltrados;
    }

    /**
     * Agrega una lista de equipos al repositorio usando Iterator
     * Captura e imprime excepciones a nivel de log
     */
    public void agregarListaEquipos(List<Equipo> equipos) {
        org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(EquipoRepositorio.class);
        
        Iterator<Equipo> it = equipos.iterator();
        while (it.hasNext()) {
            Equipo equipo = it.next();
            try {
                agregarEquipo(equipo);
                logger.info("Equipo agregado correctamente: " + equipo.getCodigo());
            } catch (TorneoException e) {
                logger.error("Error al agregar equipo " + equipo.getCodigo() + ": " + e.getMessage());
            }
        }
    }
}
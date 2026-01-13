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
     * Obtiene un equipo por su código
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
     * Agrega un equipo a la lista
     */
    public void agregarEquipo(Equipo equipo) throws TorneoException {
        if (equiServicio.getListaEquipos().contains(equipo)) {
            throw new TorneoException("Ya existe el equipo con código: " + equipo.getCodigo());
        }
        
        equiServicio.getListaEquipos().add(equipo);
    }

    /**
     * Elimina un equipo por su código
     */
    public void eliminarEquipo(String codigo) throws TorneoException {
        Equipo equipo = getEquipo(codigo);
        equiServicio.getListaEquipos().remove(equipo);
    }

    /**
     * Actualiza los datos de un equipo
     */
    public void actualizarEquipo(Equipo equipoActualizado) throws TorneoException {
        Equipo equipoExistente = getEquipo(equipoActualizado.getCodigo());
        
        equipoExistente.setNombreCompleto(equipoActualizado.getNombreCompleto());
        equipoExistente.setEmailContacto(equipoActualizado.getEmailContacto());
        equipoExistente.setNumJugadores(equipoActualizado.getNumJugadores());
    }

    /**
     * Obtiene todos los equipos
     */
    public List<Equipo> getTodosLosEquipos() {
        return new ArrayList<>(equiServicio.getListaEquipos());
    }

    /**
     * Filtra equipos por número mínimo de jugadores
     */
    public List<Equipo> getEquiposPorNumJugadores(int numMinimo) {
        List<Equipo> equiposFiltrados = new ArrayList<>();
        
        for (Equipo e : equiServicio.getListaEquipos()) {
            if (e.getNumJugadores() >= numMinimo) {
                equiposFiltrados.add(e);
            }
        }
        
        return equiposFiltrados;
    }

    /**
     * Busca equipos por nombre (contiene texto)
     */
    public List<Equipo> buscarEquiposPorNombre(String texto) {
        List<Equipo> equiposEncontrados = new ArrayList<>();
        
        for (Equipo e : equiServicio.getListaEquipos()) {
            if (e.getNombreCompleto().toLowerCase().contains(texto.toLowerCase())) {
                equiposEncontrados.add(e);
            }
        }
        
        return equiposEncontrados;
    }

    /**
     * Obtiene el equipo con más jugadores
     */
    public Equipo getEquipoConMasJugadores() throws TorneoException {
        if (equiServicio.getListaEquipos().isEmpty()) {
            throw new TorneoException("No hay equipos en el sistema");
        }

        Equipo equipoMax = null;
        int maxJugadores = 0;

        for (Equipo e : equiServicio.getListaEquipos()) {
            if (e.getNumJugadores() > maxJugadores) {
                maxJugadores = e.getNumJugadores();
                equipoMax = e;
            }
        }

        return equipoMax;
    }

    /**
     * Obtiene el equipo con menos jugadores
     */
    public Equipo getEquipoConMenosJugadores() throws TorneoException {
        if (equiServicio.getListaEquipos().isEmpty()) {
            throw new TorneoException("No hay equipos en el sistema");
        }

        Equipo equipoMin = null;
        int minJugadores = Integer.MAX_VALUE;

        for (Equipo e : equiServicio.getListaEquipos()) {
            if (e.getNumJugadores() < minJugadores) {
                minJugadores = e.getNumJugadores();
                equipoMin = e;
            }
        }

        return equipoMin;
    }

    /**
     * Calcula el promedio de jugadores por equipo
     */
    public double getPromedioJugadoresPorEquipo() throws TorneoException {
        if (equiServicio.getListaEquipos().isEmpty()) {
            throw new TorneoException("No hay equipos en el sistema");
        }

        int totalJugadores = 0;
        for (Equipo e : equiServicio.getListaEquipos()) {
            totalJugadores += e.getNumJugadores();
        }

        return (double) totalJugadores / equiServicio.getListaEquipos().size();
    }

    /**
     * Cuenta el número total de equipos
     */
    public int contarEquipos() {
        return equiServicio.getListaEquipos().size();
    }

    /**
     * Verifica si existe un equipo con un código específico
     */
    public boolean existeEquipo(String codigo) {
        for (Equipo e : equiServicio.getListaEquipos()) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene equipos ordenados por número de jugadores (ascendente)
     */
    public List<Equipo> getEquiposOrdenadosPorJugadores() {
        List<Equipo> equiposOrdenados = new ArrayList<>(equiServicio.getListaEquipos());
        
        equiposOrdenados.sort((e1, e2) -> Integer.compare(e1.getNumJugadores(), e2.getNumJugadores()));
        
        return equiposOrdenados;
    }

    /**
     * Obtiene equipos ordenados por nombre alfabéticamente
     */
    public List<Equipo> getEquiposOrdenadosPorNombre() {
        List<Equipo> equiposOrdenados = new ArrayList<>(equiServicio.getListaEquipos());
        
        equiposOrdenados.sort((e1, e2) -> e1.getNombreCompleto().compareToIgnoreCase(e2.getNombreCompleto()));
        
        return equiposOrdenados;
    }

    /**
     * Obtiene equipos con un número exacto de jugadores
     */
    public List<Equipo> getEquiposPorNumeroExactoJugadores(int numero) {
        List<Equipo> equiposFiltrados = new ArrayList<>();
        
        for (Equipo e : equiServicio.getListaEquipos()) {
            if (e.getNumJugadores() == numero) {
                equiposFiltrados.add(e);
            }
        }
        
        return equiposFiltrados;
    }

    /**
     * Obtiene estadísticas de equipos por número de jugadores
     */
    public Map<Integer, Integer> getEstadisticasPorNumJugadores() {
        Map<Integer, Integer> estadisticas = new HashMap<>();
        
        for (Equipo e : equiServicio.getListaEquipos()) {
            int numJugadores = e.getNumJugadores();
            estadisticas.put(numJugadores, estadisticas.getOrDefault(numJugadores, 0) + 1);
        }
        
        return estadisticas;
    }

    /**
     * Obtiene la lista de códigos de todos los equipos
     */
    public List<String> getCodigosEquipos() {
        List<String> codigos = new ArrayList<>();
        
        for (Equipo e : equiServicio.getListaEquipos()) {
            codigos.add(e.getCodigo());
        }
        
        return codigos;
    }

    /**
     * Busca equipos por dominio de email
     */
    public List<Equipo> getEquiposPorDominioEmail(String dominio) {
        List<Equipo> equiposFiltrados = new ArrayList<>();
        
        for (Equipo e : equiServicio.getListaEquipos()) {
            if (e.getEmailContacto().toLowerCase().contains(dominio.toLowerCase())) {
                equiposFiltrados.add(e);
            }
        }
        
        return equiposFiltrados;
    
}}
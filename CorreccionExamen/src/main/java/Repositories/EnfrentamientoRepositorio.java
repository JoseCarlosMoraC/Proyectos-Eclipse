package Repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Exception.TorneoException;
import Models.Enfrentamiento;
import Models.Videojuego;
import Services.TorneoService;



public class EnfrentamientoRepositorio {
    private TorneoService enfrentamientoServicio;

    public EnfrentamientoRepositorio() {
        // Constructor vacío - NO crear TorneoService aquí
    }

    public EnfrentamientoRepositorio(TorneoService enfrentamientoServicio) {
        this.enfrentamientoServicio = enfrentamientoServicio;
    }

    public TorneoService getEnfrentamientoServicio() {
        return enfrentamientoServicio;
    }

    public void setEnfrentamientoServicio(TorneoService enfrentamientoServicio) {
        this.enfrentamientoServicio = enfrentamientoServicio;
    }

    /**
     * Agrega un enfrentamiento
     */
    public void agregarEnfrentamiento(Enfrentamiento enfrentamiento) throws TorneoException {
        if (enfrentamientoServicio.getListaEnfrentamientos().contains(enfrentamiento)) {
            throw new TorneoException("El enfrentamiento ya existe");
        }
        
        enfrentamientoServicio.getListaEnfrentamientos().add(enfrentamiento);
    }

    /**
     * Obtiene enfrentamientos ganados por un equipo
     */
    public List<Enfrentamiento> getEnfrentamientosPorEquipo(String codigoEquipo) throws TorneoException {
        List<Enfrentamiento> enfrentamientosPorEquipo = new ArrayList<>();
        
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            if (e.getEquipoGanador() != null && 
                e.getEquipoGanador().getCodigo().equalsIgnoreCase(codigoEquipo)) {
                enfrentamientosPorEquipo.add(e);
            }
        }

        if (enfrentamientosPorEquipo.isEmpty()) {
            throw new TorneoException("No se encontró ningún enfrentamiento para el equipo: " + codigoEquipo);
        }

        return enfrentamientosPorEquipo;
    }

    /**
     * Obtiene un enfrentamiento por su ID
     */
    public Enfrentamiento getEnfrentamientoPorId(int id) throws TorneoException {
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            if (e.getId() == id) {
                return e;
            }
        }
        
        throw new TorneoException("No se encontró ningún enfrentamiento con ID: " + id);
    }

    /**
     * Elimina un enfrentamiento por su ID
     */
    public void eliminarEnfrentamiento(int id) throws TorneoException {
        Enfrentamiento enfrentamiento = getEnfrentamientoPorId(id);
        enfrentamientoServicio.getListaEnfrentamientos().remove(enfrentamiento);
    }

    /**
     * Actualiza un enfrentamiento
     */
    public void actualizarEnfrentamiento(Enfrentamiento enfrentamientoActualizado) throws TorneoException {
        Enfrentamiento enfrentamientoExistente = getEnfrentamientoPorId(enfrentamientoActualizado.getId());
        
        enfrentamientoExistente.setFecha(enfrentamientoActualizado.getFecha());
        enfrentamientoExistente.setDescripcion(enfrentamientoActualizado.getDescripcion());
        enfrentamientoExistente.setVideojuego(enfrentamientoActualizado.getVideojuego());
        enfrentamientoExistente.setEquipoGanador(enfrentamientoActualizado.getEquipoGanador());
    }

    /**
     * Obtiene todos los enfrentamientos
     */
    public List<Enfrentamiento> getTodosLosEnfrentamientos() {
        return new ArrayList<>(enfrentamientoServicio.getListaEnfrentamientos());
    }

    /**
     * Filtra enfrentamientos por videojuego
     */
    public List<Enfrentamiento> getEnfrentamientosPorVideojuego(Videojuego videojuego) {
        List<Enfrentamiento> enfrentamientosFiltrados = new ArrayList<>();
        
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            if (e.getVideojuego() == videojuego) {
                enfrentamientosFiltrados.add(e);
            }
        }
        
        return enfrentamientosFiltrados;
    }

    /**
     * Filtra enfrentamientos por fecha
     */
    public List<Enfrentamiento> getEnfrentamientosPorFecha(String fecha) {
        List<Enfrentamiento> enfrentamientosFiltrados = new ArrayList<>();
        
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            if (e.getFecha().equals(fecha)) {
                enfrentamientosFiltrados.add(e);
            }
        }
        
        return enfrentamientosFiltrados;
    }

    /**
     * Filtra enfrentamientos por descripción (contiene texto)
     */
    public List<Enfrentamiento> getEnfrentamientosPorDescripcion(String textoDescripcion) {
        List<Enfrentamiento> enfrentamientosFiltrados = new ArrayList<>();
        
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            if (e.getDescripcion().toLowerCase().contains(textoDescripcion.toLowerCase())) {
                enfrentamientosFiltrados.add(e);
            }
        }
        
        return enfrentamientosFiltrados;
    }

    /**
     * Cuenta victorias por equipo
     */
    public Map<String, Integer> getEstadisticasVictoriasPorEquipo() {
        Map<String, Integer> estadisticas = new HashMap<>();
        
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            if (e.getEquipoGanador() != null) {
                String codigo = e.getEquipoGanador().getCodigo();
                estadisticas.put(codigo, estadisticas.getOrDefault(codigo, 0) + 1);
            }
        }
        
        return estadisticas;
    }

    /**
     * Cuenta enfrentamientos por videojuego
     */
    public Map<Videojuego, Integer> getEstadisticasPorVideojuego() {
        Map<Videojuego, Integer> estadisticas = new HashMap<>();
        
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            Videojuego videojuego = e.getVideojuego();
            estadisticas.put(videojuego, estadisticas.getOrDefault(videojuego, 0) + 1);
        }
        
        return estadisticas;
    }

    /**
     * Obtiene el equipo con más victorias
     */
    public String getEquipoConMasVictorias() throws TorneoException {
        Map<String, Integer> victorias = getEstadisticasVictoriasPorEquipo();
        
        if (victorias.isEmpty()) {
            throw new TorneoException("No hay enfrentamientos registrados");
        }

        String equipoGanador = null;
        int maxVictorias = 0;
        
        for (Map.Entry<String, Integer> entry : victorias.entrySet()) {
            if (entry.getValue() > maxVictorias) {
                maxVictorias = entry.getValue();
                equipoGanador = entry.getKey();
            }
        }

        return equipoGanador;
    }

    /**
     * Obtiene equipos sin victorias
     */
    public List<String> getEquiposSinVictorias(List<String> todosLosCodigos) {
        List<String> equiposConVictorias = new ArrayList<>();
        
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            if (e.getEquipoGanador() != null) {
                equiposConVictorias.add(e.getEquipoGanador().getCodigo());
            }
        }
        
        List<String> equiposSinVictorias = new ArrayList<>();
        for (String codigo : todosLosCodigos) {
            if (!equiposConVictorias.contains(codigo)) {
                equiposSinVictorias.add(codigo);
            }
        }
        
        return equiposSinVictorias;
    }

    /**
     * Genera ranking de equipos por victorias
     */
    public List<Map.Entry<String, Integer>> getRankingEquiposPorVictorias() {
        Map<String, Integer> victorias = getEstadisticasVictoriasPorEquipo();
        
        List<Map.Entry<String, Integer>> ranking = new ArrayList<>(victorias.entrySet());
        ranking.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        return ranking;
    }

    /**
     * Cuenta total de enfrentamientos
     */
    public int contarEnfrentamientos() {
        return enfrentamientoServicio.getListaEnfrentamientos().size();
    }

    /**
     * Cuenta enfrentamientos de un videojuego específico
     */
    public int contarEnfrentamientosPorVideojuego(Videojuego videojuego) {
        int contador = 0;
        
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            if (e.getVideojuego() == videojuego) {
                contador++;
            }
        }
        
        return contador;
    }

    /**
     * Obtiene enfrentamientos ordenados por fecha
     */
    public List<Enfrentamiento> getEnfrentamientosOrdenadosPorFecha() {
        List<Enfrentamiento> enfrentamientosOrdenados = new ArrayList<>(enfrentamientoServicio.getListaEnfrentamientos());
        
        enfrentamientosOrdenados.sort((e1, e2) -> e1.getFecha().compareTo(e2.getFecha()));
        
        return enfrentamientosOrdenados;
    }

    /**
     * Obtiene enfrentamientos ordenados por ID
     */
    public List<Enfrentamiento> getEnfrentamientosOrdenadosPorId() {
        List<Enfrentamiento> enfrentamientosOrdenados = new ArrayList<>(enfrentamientoServicio.getListaEnfrentamientos());
        
        enfrentamientosOrdenados.sort((e1, e2) -> Integer.compare(e1.getId(), e2.getId()));
        
        return enfrentamientosOrdenados;
    }

    /**
     * Verifica si existe un enfrentamiento con un ID específico
     */
    public boolean existeEnfrentamiento(int id) {
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            if (e.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Agrupa enfrentamientos por videojuego
     */
    public Map<Videojuego, List<Enfrentamiento>> getEnfrentamientosAgrupadosPorVideojuego() {
        Map<Videojuego, List<Enfrentamiento>> agrupados = new HashMap<>();
        
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            Videojuego videojuego = e.getVideojuego();
            if (!agrupados.containsKey(videojuego)) {
                agrupados.put(videojuego, new ArrayList<>());
            }
            agrupados.get(videojuego).add(e);
        }
        
        return agrupados;
    }

    /**
     * Agrupa enfrentamientos por fecha
     */
    public Map<String, List<Enfrentamiento>> getEnfrentamientosAgrupadosPorFecha() {
        Map<String, List<Enfrentamiento>> agrupados = new HashMap<>();
        
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            String fecha = e.getFecha();
            if (!agrupados.containsKey(fecha)) {
                agrupados.put(fecha, new ArrayList<>());
            }
            agrupados.get(fecha).add(e);
        }
        
        return agrupados;
    }

    /**
     * Obtiene el número de victorias de un equipo específico
     */
    public int getNumVictoriasEquipo(String codigoEquipo) {
        int victorias = 0;
        
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            if (e.getEquipoGanador() != null && 
                e.getEquipoGanador().getCodigo().equalsIgnoreCase(codigoEquipo)) {
                victorias++;
            }
        }
        
        return victorias;
    }

    /**
     * Obtiene victorias de un equipo en un videojuego específico
     */
    public int getNumVictoriasEquipoPorVideojuego(String codigoEquipo, Videojuego videojuego) {
        int victorias = 0;
        
        for (Enfrentamiento e : enfrentamientoServicio.getListaEnfrentamientos()) {
            if (e.getEquipoGanador() != null && 
                e.getEquipoGanador().getCodigo().equalsIgnoreCase(codigoEquipo) &&
                e.getVideojuego() == videojuego) {
                victorias++;
            }
        }
        
        return victorias;
    }
}	
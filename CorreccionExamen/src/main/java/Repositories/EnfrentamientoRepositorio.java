package Repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
     * Agrega un enfrentamiento usando Iterator para verificar
     */
    public void agregarEnfrentamiento(Enfrentamiento enfrentamiento) throws TorneoException {
        boolean existe = false;
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext() && !existe) {
            Enfrentamiento e = it.next();
            if (e.getId() == enfrentamiento.getId()) {
                existe = true;
            }
        }
        
        if (existe) {
            throw new TorneoException("El enfrentamiento ya existe con ID: " + enfrentamiento.getId());
        }
        
        enfrentamientoServicio.getListaEnfrentamientos().add(enfrentamiento);
    }

    /**
     * Obtiene enfrentamientos ganados por un equipo usando Iterator
     */
    public List<Enfrentamiento> getEnfrentamientosPorEquipo(String codigoEquipo) throws TorneoException {
        List<Enfrentamiento> enfrentamientosPorEquipo = new ArrayList<>();
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            Enfrentamiento e = it.next();
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
     * Obtiene un enfrentamiento por su ID usando Iterator
     */
    public Enfrentamiento getEnfrentamientoPorId(int id) throws TorneoException {
        boolean encontrado = false;
        Enfrentamiento enfrentamiento = null;
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext() && !encontrado) {
            Enfrentamiento e = it.next();
            if (e.getId() == id) {
                encontrado = true;
                enfrentamiento = e;
            }
        }
        
        if (!encontrado) {
            throw new TorneoException("No se encontró ningún enfrentamiento con ID: " + id);
        }
        
        return enfrentamiento;
    }

    /**
     * Elimina un enfrentamiento por su ID usando Iterator
     */
    public void eliminarEnfrentamiento(int id) throws TorneoException {
        boolean encontrado = false;
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext() && !encontrado) {
            Enfrentamiento e = it.next();
            if (e.getId() == id) {
                it.remove(); // Usar remove del Iterator
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            throw new TorneoException("No se encontró el enfrentamiento con ID: " + id);
        }
    }

    /**
     * Actualiza un enfrentamiento usando Iterator
     */
    public void actualizarEnfrentamiento(Enfrentamiento enfrentamientoActualizado) throws TorneoException {
        boolean encontrado = false;
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext() && !encontrado) {
            Enfrentamiento e = it.next();
            if (e.getId() == enfrentamientoActualizado.getId()) {
                e.setFecha(enfrentamientoActualizado.getFecha());
                e.setDescripcion(enfrentamientoActualizado.getDescripcion());
                e.setVideojuego(enfrentamientoActualizado.getVideojuego());
                e.setEquipoGanador(enfrentamientoActualizado.getEquipoGanador());
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            throw new TorneoException("No se encontró el enfrentamiento con ID: " + enfrentamientoActualizado.getId());
        }
    }

    /**
     * Obtiene todos los enfrentamientos usando Iterator
     */
    public List<Enfrentamiento> getTodosLosEnfrentamientos() {
        List<Enfrentamiento> lista = new ArrayList<>();
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            lista.add(it.next());
        }
        
        return lista;
    }

    /**
     * Filtra enfrentamientos por videojuego usando Iterator
     */
    public List<Enfrentamiento> getEnfrentamientosPorVideojuego(Videojuego videojuego) {
        List<Enfrentamiento> enfrentamientosFiltrados = new ArrayList<>();
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            Enfrentamiento e = it.next();
            if (e.getVideojuego() == videojuego) {
                enfrentamientosFiltrados.add(e);
            }
        }
        
        return enfrentamientosFiltrados;
    }

    /**
     * Filtra enfrentamientos por fecha usando Iterator
     */
    public List<Enfrentamiento> getEnfrentamientosPorFecha(String fecha) {
        List<Enfrentamiento> enfrentamientosFiltrados = new ArrayList<>();
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            Enfrentamiento e = it.next();
            if (e.getFecha().equals(fecha)) {
                enfrentamientosFiltrados.add(e);
            }
        }
        
        return enfrentamientosFiltrados;
    }

    /**
     * Filtra enfrentamientos por descripción usando Iterator
     */
    public List<Enfrentamiento> getEnfrentamientosPorDescripcion(String textoDescripcion) {
        List<Enfrentamiento> enfrentamientosFiltrados = new ArrayList<>();
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            Enfrentamiento e = it.next();
            if (e.getDescripcion().toLowerCase().contains(textoDescripcion.toLowerCase())) {
                enfrentamientosFiltrados.add(e);
            }
        }
        
        return enfrentamientosFiltrados;
    }

    /**
     * Cuenta victorias por equipo usando Iterator
     */
    public Map<String, Integer> getEstadisticasVictoriasPorEquipo() {
        Map<String, Integer> estadisticas = new HashMap<>();
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            Enfrentamiento e = it.next();
            if (e.getEquipoGanador() != null) {
                String codigo = e.getEquipoGanador().getCodigo();
                estadisticas.put(codigo, estadisticas.getOrDefault(codigo, 0) + 1);
            }
        }
        
        return estadisticas;
    }

    /**
     * Cuenta enfrentamientos por videojuego usando Iterator
     */
    public Map<Videojuego, Integer> getEstadisticasPorVideojuego() {
        Map<Videojuego, Integer> estadisticas = new HashMap<>();
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            Enfrentamiento e = it.next();
            Videojuego videojuego = e.getVideojuego();
            estadisticas.put(videojuego, estadisticas.getOrDefault(videojuego, 0) + 1);
        }
        
        return estadisticas;
    }

    /**
     * Obtiene el equipo con más victorias usando Iterator
     */
    public String getEquipoConMasVictorias() throws TorneoException {
        Map<String, Integer> victorias = getEstadisticasVictoriasPorEquipo();
        
        if (victorias.isEmpty()) {
            throw new TorneoException("No hay enfrentamientos registrados");
        }

        String equipoGanador = null;
        int maxVictorias = 0;
        
        Iterator<Map.Entry<String, Integer>> it = victorias.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            if (entry.getValue() > maxVictorias) {
                maxVictorias = entry.getValue();
                equipoGanador = entry.getKey();
            }
        }

        return equipoGanador;
    }

    /**
     * Obtiene equipos sin victorias usando Iterator
     */
    public List<String> getEquiposSinVictorias(List<String> todosLosCodigos) {
        List<String> equiposConVictorias = new ArrayList<>();
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            Enfrentamiento e = it.next();
            if (e.getEquipoGanador() != null) {
                equiposConVictorias.add(e.getEquipoGanador().getCodigo());
            }
        }
        
        List<String> equiposSinVictorias = new ArrayList<>();
        Iterator<String> itCodigos = todosLosCodigos.iterator();
        while (itCodigos.hasNext()) {
            String codigo = itCodigos.next();
            if (!equiposConVictorias.contains(codigo)) {
                equiposSinVictorias.add(codigo);
            }
        }
        
        return equiposSinVictorias;
    }

    /**
     * Genera ranking de equipos por victorias usando Iterator
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
     * Cuenta enfrentamientos de un videojuego específico usando Iterator
     */
    public int contarEnfrentamientosPorVideojuego(Videojuego videojuego) {
        int contador = 0;
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            Enfrentamiento e = it.next();
            if (e.getVideojuego() == videojuego) {
                contador++;
            }
        }
        
        return contador;
    }

    /**
     * Obtiene enfrentamientos ordenados por fecha usando Iterator
     */
    public List<Enfrentamiento> getEnfrentamientosOrdenadosPorFecha() {
        List<Enfrentamiento> enfrentamientosOrdenados = new ArrayList<>();
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            enfrentamientosOrdenados.add(it.next());
        }
        
        enfrentamientosOrdenados.sort((e1, e2) -> e1.getFecha().compareTo(e2.getFecha()));
        
        return enfrentamientosOrdenados;
    }

    /**
     * Obtiene enfrentamientos ordenados por ID usando Iterator
     */
    public List<Enfrentamiento> getEnfrentamientosOrdenadosPorId() {
        List<Enfrentamiento> enfrentamientosOrdenados = new ArrayList<>();
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            enfrentamientosOrdenados.add(it.next());
        }
        
        enfrentamientosOrdenados.sort((e1, e2) -> Integer.compare(e1.getId(), e2.getId()));
        
        return enfrentamientosOrdenados;
    }

    /**
     * Verifica si existe un enfrentamiento con un ID específico usando Iterator
     */
    public boolean existeEnfrentamiento(int id) {
        boolean existe = false;
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext() && !existe) {
            Enfrentamiento e = it.next();
            if (e.getId() == id) {
                existe = true;
            }
        }
        
        return existe;
    }

    /**
     * Agrupa enfrentamientos por videojuego usando Iterator
     */
    public Map<Videojuego, List<Enfrentamiento>> getEnfrentamientosAgrupadosPorVideojuego() {
        Map<Videojuego, List<Enfrentamiento>> agrupados = new HashMap<>();
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            Enfrentamiento e = it.next();
            Videojuego videojuego = e.getVideojuego();
            if (!agrupados.containsKey(videojuego)) {
                agrupados.put(videojuego, new ArrayList<>());
            }
            agrupados.get(videojuego).add(e);
        }
        
        return agrupados;
    }

    /**
     * Agrupa enfrentamientos por fecha usando Iterator
     */
    public Map<String, List<Enfrentamiento>> getEnfrentamientosAgrupadosPorFecha() {
        Map<String, List<Enfrentamiento>> agrupados = new HashMap<>();
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            Enfrentamiento e = it.next();
            String fecha = e.getFecha();
            if (!agrupados.containsKey(fecha)) {
                agrupados.put(fecha, new ArrayList<>());
            }
            agrupados.get(fecha).add(e);
        }
        
        return agrupados;
    }

    /**
     * Obtiene el número de victorias de un equipo específico usando Iterator
     */
    public int getNumVictoriasEquipo(String codigoEquipo) {
        int victorias = 0;
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            Enfrentamiento e = it.next();
            if (e.getEquipoGanador() != null && 
                e.getEquipoGanador().getCodigo().equalsIgnoreCase(codigoEquipo)) {
                victorias++;
            }
        }
        
        return victorias;
    }

    /**
     * Obtiene victorias de un equipo en un videojuego específico usando Iterator
     */
    public int getNumVictoriasEquipoPorVideojuego(String codigoEquipo, Videojuego videojuego) {
        int victorias = 0;
        
        Iterator<Enfrentamiento> it = enfrentamientoServicio.getListaEnfrentamientos().iterator();
        while (it.hasNext()) {
            Enfrentamiento e = it.next();
            if (e.getEquipoGanador() != null && 
                e.getEquipoGanador().getCodigo().equalsIgnoreCase(codigoEquipo) &&
                e.getVideojuego() == videojuego) {
                victorias++;
            }
        }
        
        return victorias;
    }

    /**
     * Agrega una lista de enfrentamientos al repositorio usando Iterator
     * Captura e imprime excepciones a nivel de log
     */
    public void agregarListaEnfrentamientos(List<Enfrentamiento> enfrentamientos) {
        org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(EnfrentamientoRepositorio.class);
        
        Iterator<Enfrentamiento> it = enfrentamientos.iterator();
        while (it.hasNext()) {
            Enfrentamiento enfrentamiento = it.next();
            try {
                agregarEnfrentamiento(enfrentamiento);
                logger.info("Enfrentamiento agregado correctamente: " + enfrentamiento.getId());
            } catch (TorneoException e) {
                logger.error("Error al agregar enfrentamiento " + enfrentamiento.getId() + ": " + e.getMessage());
            }
        }
    }
}
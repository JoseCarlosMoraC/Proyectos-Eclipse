package Services;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Exception.TorneoException;
import Models.Enfrentamiento;
import Models.Equipo;
import Models.Videojuego;
import Repositories.EnfrentamientoRepositorio;
import Repositories.EquipoRepositorio;


public class TorneoService {
    private EnfrentamientoRepositorio repoEnfrentamiento;
    private EquipoRepositorio repoEquipo;
    private Set<Equipo> listaEquipos;
    private Set<Enfrentamiento> listaEnfrentamientos;

    public TorneoService() {
        this.listaEquipos = new HashSet<>();
        this.listaEnfrentamientos = new HashSet<>();
        
        // Crear repositorios y asignarles este servicio
        this.repoEnfrentamiento = new EnfrentamientoRepositorio();
        this.repoEnfrentamiento.setEnfrentamientoServicio(this);
        
        this.repoEquipo = new EquipoRepositorio();
        this.repoEquipo.setEquiServicio(this);
    }

    // ==================== GETTERS Y SETTERS ====================

    public EnfrentamientoRepositorio getRepoEnfrentamiento() {
        return repoEnfrentamiento;
    }

    public void setRepoEnfrentamiento(EnfrentamientoRepositorio repoEnfrentamiento) {
        this.repoEnfrentamiento = repoEnfrentamiento;
    }

    public EquipoRepositorio getRepoEquipo() {
        return repoEquipo;
    }

    public void setRepoEquipo(EquipoRepositorio repoEquipo) {
        this.repoEquipo = repoEquipo;
    }

    public Set<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(Set<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    public Set<Enfrentamiento> getListaEnfrentamientos() {
        return listaEnfrentamientos;
    }

    public void setListaEnfrentamientos(Set<Enfrentamiento> listaEnfrentamientos) {
        this.listaEnfrentamientos = listaEnfrentamientos;
    }

    @Override
    public String toString() {
        return "TorneoService [repoEnfrentamiento=" + repoEnfrentamiento + ", repoEquipo=" + repoEquipo + ", listaEquipos="
                + listaEquipos + ", listaEnfrentamientos=" + listaEnfrentamientos + "]";
    }

    // ==================== MÉTODOS DE EQUIPOS ====================

    /**
     * Obtiene un equipo por código
     */
    public Equipo getEquipo(String codigo) throws TorneoException {
        return repoEquipo.getEquipo(codigo);
    }

    /**
     * Agrega un equipo
     */
    public void agregarEquipo(Equipo equipo) throws TorneoException {
        repoEquipo.agregarEquipo(equipo);
    }

    /**
     * Elimina un equipo
     */
    public void eliminarEquipo(String codigo) throws TorneoException {
        repoEquipo.eliminarEquipo(codigo);
    }

    /**
     * Actualiza un equipo
     */
    public void actualizarEquipo(Equipo equipo) throws TorneoException {
        repoEquipo.actualizarEquipo(equipo);
    }

    /**
     * Obtiene todos los equipos
     */
    public List<Equipo> getTodosLosEquipos() {
        return repoEquipo.getTodosLosEquipos();
    }

    /**
     * Filtra equipos por número mínimo de jugadores
     */
    public List<Equipo> getEquiposPorNumJugadores(int numMinimo) {
        return repoEquipo.getEquiposPorNumJugadores(numMinimo);
    }

    /**
     * Busca equipos por nombre
     */
    public List<Equipo> buscarEquiposPorNombre(String texto) {
        return repoEquipo.buscarEquiposPorNombre(texto);
    }

    /**
     * Obtiene el equipo con más jugadores
     */
    public Equipo getEquipoConMasJugadores() throws TorneoException {
        return repoEquipo.getEquipoConMasJugadores();
    }

    /**
     * Obtiene el equipo con menos jugadores
     */
    public Equipo getEquipoConMenosJugadores() throws TorneoException {
        return repoEquipo.getEquipoConMenosJugadores();
    }

    /**
     * Calcula el promedio de jugadores por equipo
     */
    public double getPromedioJugadoresPorEquipo() throws TorneoException {
        return repoEquipo.getPromedioJugadoresPorEquipo();
    }

    /**
     * Cuenta equipos
     */
    public int contarEquipos() {
        return repoEquipo.contarEquipos();
    }

    /**
     * Verifica si existe un equipo
     */
    public boolean existeEquipo(String codigo) {
        return repoEquipo.existeEquipo(codigo);
    }

    /**
     * Obtiene equipos ordenados por jugadores
     */
    public List<Equipo> getEquiposOrdenadosPorJugadores() {
        return repoEquipo.getEquiposOrdenadosPorJugadores();
    }

    /**
     * Obtiene equipos ordenados por nombre
     */
    public List<Equipo> getEquiposOrdenadosPorNombre() {
        return repoEquipo.getEquiposOrdenadosPorNombre();
    }

    /**
     * Obtiene equipos con número exacto de jugadores
     */
    public List<Equipo> getEquiposPorNumeroExactoJugadores(int numero) {
        return repoEquipo.getEquiposPorNumeroExactoJugadores(numero);
    }

    /**
     * Obtiene estadísticas por número de jugadores
     */
    public Map<Integer, Integer> getEstadisticasPorNumJugadores() {
        return repoEquipo.getEstadisticasPorNumJugadores();
    }

    /**
     * Obtiene códigos de todos los equipos
     */
    public List<String> getCodigosEquipos() {
        return repoEquipo.getCodigosEquipos();
    }

    /**
     * Obtiene equipos por dominio de email
     */
    public List<Equipo> getEquiposPorDominioEmail(String dominio) {
        return repoEquipo.getEquiposPorDominioEmail(dominio);
    }

    // ==================== MÉTODOS DE ENFRENTAMIENTOS ====================

    /**
     * Agrega un enfrentamiento
     */
    public void agregarEnfrentamiento(Enfrentamiento enfrentamiento) throws TorneoException {
        repoEnfrentamiento.agregarEnfrentamiento(enfrentamiento);
    }

    /**
     * Obtiene enfrentamientos por equipo
     */
    public List<Enfrentamiento> getEnfrentamientosPorEquipo(String codigoEquipo) throws TorneoException {
        return repoEnfrentamiento.getEnfrentamientosPorEquipo(codigoEquipo);
    }

    /**
     * Obtiene un enfrentamiento por ID
     */
    public Enfrentamiento getEnfrentamientoPorId(int id) throws TorneoException {
        return repoEnfrentamiento.getEnfrentamientoPorId(id);
    }

    /**
     * Elimina un enfrentamiento
     */
    public void eliminarEnfrentamiento(int id) throws TorneoException {
        repoEnfrentamiento.eliminarEnfrentamiento(id);
    }

    /**
     * Actualiza un enfrentamiento
     */
    public void actualizarEnfrentamiento(Enfrentamiento enfrentamiento) throws TorneoException {
        repoEnfrentamiento.actualizarEnfrentamiento(enfrentamiento);
    }

    /**
     * Obtiene todos los enfrentamientos
     */
    public List<Enfrentamiento> getTodosLosEnfrentamientos() {
        return repoEnfrentamiento.getTodosLosEnfrentamientos();
    }

    /**
     * Filtra enfrentamientos por videojuego
     */
    public List<Enfrentamiento> getEnfrentamientosPorVideojuego(Videojuego videojuego) {
        return repoEnfrentamiento.getEnfrentamientosPorVideojuego(videojuego);
    }

    /**
     * Filtra enfrentamientos por fecha
     */
    public List<Enfrentamiento> getEnfrentamientosPorFecha(String fecha) {
        return repoEnfrentamiento.getEnfrentamientosPorFecha(fecha);
    }

    /**
     * Filtra enfrentamientos por descripción
     */
    public List<Enfrentamiento> getEnfrentamientosPorDescripcion(String textoDescripcion) {
        return repoEnfrentamiento.getEnfrentamientosPorDescripcion(textoDescripcion);
    }

    /**
     * Obtiene estadísticas de victorias por equipo
     */
    public Map<String, Integer> getEstadisticasVictoriasPorEquipo() {
        return repoEnfrentamiento.getEstadisticasVictoriasPorEquipo();
    }

    /**
     * Obtiene estadísticas por videojuego
     */
    public Map<Videojuego, Integer> getEstadisticasPorVideojuego() {
        return repoEnfrentamiento.getEstadisticasPorVideojuego();
    }

    /**
     * Obtiene el equipo con más victorias
     */
    public String getEquipoConMasVictorias() throws TorneoException {
        return repoEnfrentamiento.getEquipoConMasVictorias();
    }

    /**
     * Obtiene equipos sin victorias
     */
    public List<String> getEquiposSinVictorias() {
        List<String> todosLosCodigos = getCodigosEquipos();
        return repoEnfrentamiento.getEquiposSinVictorias(todosLosCodigos);
    }

    /**
     * Genera ranking de equipos por victorias
     */
    public List<Map.Entry<String, Integer>> getRankingEquiposPorVictorias() {
        return repoEnfrentamiento.getRankingEquiposPorVictorias();
    }

    /**
     * Cuenta enfrentamientos
     */
    public int contarEnfrentamientos() {
        return repoEnfrentamiento.contarEnfrentamientos();
    }

    /**
     * Cuenta enfrentamientos por videojuego
     */
    public int contarEnfrentamientosPorVideojuego(Videojuego videojuego) {
        return repoEnfrentamiento.contarEnfrentamientosPorVideojuego(videojuego);
    }

    /**
     * Obtiene enfrentamientos ordenados por fecha
     */
    public List<Enfrentamiento> getEnfrentamientosOrdenadosPorFecha() {
        return repoEnfrentamiento.getEnfrentamientosOrdenadosPorFecha();
    }

    /**
     * Obtiene enfrentamientos ordenados por ID
     */
    public List<Enfrentamiento> getEnfrentamientosOrdenadosPorId() {
        return repoEnfrentamiento.getEnfrentamientosOrdenadosPorId();
    }

    /**
     * Verifica si existe un enfrentamiento
     */
    public boolean existeEnfrentamiento(int id) {
        return repoEnfrentamiento.existeEnfrentamiento(id);
    }

    /**
     * Agrupa enfrentamientos por videojuego
     */
    public Map<Videojuego, List<Enfrentamiento>> getEnfrentamientosAgrupadosPorVideojuego() {
        return repoEnfrentamiento.getEnfrentamientosAgrupadosPorVideojuego();
    }

    /**
     * Agrupa enfrentamientos por fecha
     */
    public Map<String, List<Enfrentamiento>> getEnfrentamientosAgrupadosPorFecha() {
        return repoEnfrentamiento.getEnfrentamientosAgrupadosPorFecha();
    }

    /**
     * Obtiene número de victorias de un equipo
     */
    public int getNumVictoriasEquipo(String codigoEquipo) {
        return repoEnfrentamiento.getNumVictoriasEquipo(codigoEquipo);
    }

    /**
     * Obtiene victorias de un equipo por videojuego
     */
    public int getNumVictoriasEquipoPorVideojuego(String codigoEquipo, Videojuego videojuego) {
        return repoEnfrentamiento.getNumVictoriasEquipoPorVideojuego(codigoEquipo, videojuego);
    }

    // ==================== MÉTODOS COMBINADOS (EQUIPO + ENFRENTAMIENTO) ====================

    /**
     * Obtiene información completa de un equipo con sus victorias
     */
    public String getInformacionCompletaEquipo(String codigo) throws TorneoException {
        Equipo equipo = getEquipo(codigo);
        int victorias = getNumVictoriasEquipo(codigo);
        
        StringBuilder info = new StringBuilder();
        info.append("=== INFORMACIÓN DEL EQUIPO ===\n");
        info.append("Código: ").append(equipo.getCodigo()).append("\n");
        info.append("Nombre: ").append(equipo.getNombreCompleto()).append("\n");
        info.append("Email: ").append(equipo.getEmailContacto()).append("\n");
        info.append("Jugadores: ").append(equipo.getNumJugadores()).append("\n");
        info.append("Victorias: ").append(victorias).append("\n");
        
        return info.toString();
    }

    /**
     * Obtiene estadísticas generales del torneo
     */
    public String getEstadisticasGenerales() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTADÍSTICAS GENERALES DEL TORNEO ===\n");
        stats.append("Total de equipos: ").append(contarEquipos()).append("\n");
        stats.append("Total de enfrentamientos: ").append(contarEnfrentamientos()).append("\n");
        
        try {
            stats.append("Promedio jugadores por equipo: ")
                 .append(String.format("%.2f", getPromedioJugadoresPorEquipo())).append("\n");
        } catch (TorneoException e) {
            stats.append("Promedio jugadores: N/A\n");
        }
        
        stats.append("\nEnfrentamientos por videojuego:\n");
        stats.append("- LOL: ").append(contarEnfrentamientosPorVideojuego(Videojuego.LOL)).append("\n");
        stats.append("- VALORANT: ").append(contarEnfrentamientosPorVideojuego(Videojuego.VALORANT)).append("\n");
        stats.append("- CSGO: ").append(contarEnfrentamientosPorVideojuego(Videojuego.CSGO)).append("\n");
        
        try {
            stats.append("\nEquipo con más victorias: ").append(getEquipoConMasVictorias()).append("\n");
        } catch (TorneoException e) {
            stats.append("\nEquipo con más victorias: N/A\n");
        }
        
        return stats.toString();
    }

    /**
     * Obtiene el top N equipos con más victorias
     */
    public List<Map.Entry<String, Integer>> getTopNEquipos(int n) {
        List<Map.Entry<String, Integer>> ranking = getRankingEquiposPorVictorias();
        
        if (ranking.size() > n) {
            return ranking.subList(0, n);
        }
        
        return ranking;
    }

    /**
     * Verifica si un equipo ha ganado algún enfrentamiento
     */
    public boolean equipoTieneVictorias(String codigoEquipo) {
        return getNumVictoriasEquipo(codigoEquipo) > 0;
    }

    /**
     * Obtiene el videojuego con más enfrentamientos
     */
    public Videojuego getVideojuegoConMasEnfrentamientos() throws TorneoException {
        Map<Videojuego, Integer> estadisticas = getEstadisticasPorVideojuego();
        
        if (estadisticas.isEmpty()) {
            throw new TorneoException("No hay enfrentamientos registrados");
        }
        
        Videojuego videojuegoMax = null;
        int maxEnfrentamientos = 0;
        
        for (Map.Entry<Videojuego, Integer> entry : estadisticas.entrySet()) {
            if (entry.getValue() > maxEnfrentamientos) {
                maxEnfrentamientos = entry.getValue();
                videojuegoMax = entry.getKey();
            }
        }
        
        return videojuegoMax;
    }

    /**
     * Agrega una lista de equipos usando el repositorio
     */
    public void agregarListaEquipos(List<Equipo> equipos) {
        repoEquipo.agregarListaEquipos(equipos);
    }

    /**
     * Agrega una lista de enfrentamientos usando el repositorio
     */
    public void agregarListaEnfrentamientos(List<Enfrentamiento> enfrentamientos) {
        repoEnfrentamiento.agregarListaEnfrentamientos(enfrentamientos);
    }
}
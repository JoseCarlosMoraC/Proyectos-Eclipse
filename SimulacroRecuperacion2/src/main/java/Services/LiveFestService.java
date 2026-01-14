package Services;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Exception.LiveFestException;
import Models.Artista;
import Models.Concierto;
import Models.GeneroMusical;
import Repositories.ArtistaRepositorio;
import Repositories.ConciertoRepositorio;

public class LiveFestService {
    private ConciertoRepositorio repoConcierto;
    private ArtistaRepositorio repoArtista;
    private Set<Artista> listaArtistas;
    private Set<Concierto> listaConciertos;

    public LiveFestService() {
        this.listaArtistas = new HashSet<>();
        this.listaConciertos = new HashSet<>();
        
        // Crear repositorios y asignarles este servicio
        this.repoConcierto = new ConciertoRepositorio();
        this.repoConcierto.setConciertoServicio(this);
        
        this.repoArtista = new ArtistaRepositorio();
        this.repoArtista.setArtistaServicio(this);
    }

    // ==================== GETTERS Y SETTERS ====================

    public ConciertoRepositorio getRepoConcierto() {
        return repoConcierto;
    }

    public void setRepoConcierto(ConciertoRepositorio repoConcierto) {
        this.repoConcierto = repoConcierto;
    }

    public ArtistaRepositorio getRepoArtista() {
        return repoArtista;
    }

    public void setRepoArtista(ArtistaRepositorio repoArtista) {
        this.repoArtista = repoArtista;
    }

    public Set<Artista> getListaArtistas() {
        return listaArtistas;
    }

    public void setListaArtistas(Set<Artista> listaArtistas) {
        this.listaArtistas = listaArtistas;
    }

    public Set<Concierto> getListaConciertos() {
        return listaConciertos;
    }

    public void setListaConciertos(Set<Concierto> listaConciertos) {
        this.listaConciertos = listaConciertos;
    }

    @Override
    public String toString() {
        return "LiveFestService [repoConcierto=" + repoConcierto + ", repoArtista=" + repoArtista 
                + ", listaArtistas=" + listaArtistas + ", listaConciertos=" + listaConciertos + "]";
    }

    // ==================== MÉTODOS DE ARTISTAS ====================

    /**
     * Obtiene un artista por código
     */
    public Artista getArtista(String codigo) throws LiveFestException {
        return repoArtista.getArtista(codigo);
    }

    /**
     * Agrega un artista
     */
    public void agregarArtista(Artista artista) throws LiveFestException {
        repoArtista.agregarArtista(artista);
    }

    /**
     * Elimina un artista
     */
    public void eliminarArtista(String codigo) throws LiveFestException {
        repoArtista.eliminarArtista(codigo);
    }

    /**
     * Actualiza un artista
     */
    public void actualizarArtista(Artista artista) throws LiveFestException {
        repoArtista.actualizarArtista(artista);
    }

    /**
     * Obtiene todos los artistas
     */
    public List<Artista> getTodosLosArtistas() {
        return repoArtista.getTodosLosArtistas();
    }

    /**
     * Filtra artistas por número mínimo de integrantes
     */
    public List<Artista> getArtistasPorNumIntegrantes(int numMinimo) {
        return repoArtista.getArtistasPorNumIntegrantes(numMinimo);
    }

    /**
     * Busca artistas por nombre
     */
    public List<Artista> buscarArtistasPorNombre(String texto) {
        return repoArtista.buscarArtistasPorNombre(texto);
    }

    /**
     * Obtiene el artista con más integrantes
     */
    public Artista getArtistaConMasIntegrantes() throws LiveFestException {
        return repoArtista.getArtistaConMasIntegrantes();
    }

    /**
     * Obtiene el artista con menos integrantes
     */
    public Artista getArtistaConMenosIntegrantes() throws LiveFestException {
        return repoArtista.getArtistaConMenosIntegrantes();
    }

    /**
     * Calcula el promedio de integrantes por artista
     */
    public double getPromedioIntegrantesPorArtista() throws LiveFestException {
        return repoArtista.getPromedioIntegrantesPorArtista();
    }

    /**
     * Cuenta artistas
     */
    public int contarArtistas() {
        return repoArtista.contarArtistas();
    }

    /**
     * Verifica si existe un artista
     */
    public boolean existeArtista(String codigo) {
        return repoArtista.existeArtista(codigo);
    }

    /**
     * Obtiene artistas ordenados por integrantes
     */
    public List<Artista> getArtistasOrdenadosPorIntegrantes() {
        return repoArtista.getArtistasOrdenadosPorIntegrantes();
    }

    /**
     * Obtiene artistas ordenados por nombre
     */
    public List<Artista> getArtistasOrdenadosPorNombre() {
        return repoArtista.getArtistasOrdenadosPorNombre();
    }

    /**
     * Obtiene artistas con número exacto de integrantes
     */
    public List<Artista> getArtistasPorNumeroExactoIntegrantes(int numero) {
        return repoArtista.getArtistasPorNumeroExactoIntegrantes(numero);
    }

    /**
     * Obtiene estadísticas por número de integrantes
     */
    public Map<Integer, Integer> getEstadisticasPorNumIntegrantes() {
        return repoArtista.getEstadisticasPorNumIntegrantes();
    }

    /**
     * Obtiene códigos de todos los artistas
     */
    public List<String> getCodigosArtistas() {
        return repoArtista.getCodigosArtistas();
    }

    /**
     * Obtiene artistas por dominio de email
     */
    public List<Artista> getArtistasPorDominioEmail(String dominio) {
        return repoArtista.getArtistasPorDominioEmail(dominio);
    }

    // ==================== MÉTODOS DE CONCIERTOS ====================

    /**
     * Agrega un concierto
     */
    public void agregarConcierto(Concierto concierto) throws LiveFestException {
        repoConcierto.agregarConcierto(concierto);
    }

    /**
     * Obtiene conciertos por artista
     */
    public List<Concierto> getConciertosPorArtista(String codigoArtista) throws LiveFestException {
        return repoConcierto.getConciertosPorArtista(codigoArtista);
    }

    /**
     * Obtiene un concierto por ID
     */
    public Concierto getConciertoporId(int id) throws LiveFestException {
        return repoConcierto.getConciertoporId(id);
    }

    /**
     * Elimina un concierto
     */
    public void eliminarConcierto(int id) throws LiveFestException {
        repoConcierto.eliminarConcierto(id);
    }

    /**
     * Actualiza un concierto
     */
    public void actualizarConcierto(Concierto concierto) throws LiveFestException {
        repoConcierto.actualizarConcierto(concierto);
    }

    /**
     * Obtiene todos los conciertos
     */
    public List<Concierto> getTodosLosConciertos() {
        return repoConcierto.getTodosLosConciertos();
    }

    /**
     * Filtra conciertos por género musical
     */
    public List<Concierto> getConciertosPorGenero(GeneroMusical genero) {
        return repoConcierto.getConciertosPorGenero(genero);
    }

    /**
     * Filtra conciertos por fecha
     */
    public List<Concierto> getConciertosPorFecha(String fecha) {
        return repoConcierto.getConciertosPorFecha(fecha);
    }

    /**
     * Filtra conciertos por descripción
     */
    public List<Concierto> getConciertosPorDescripcion(String textoDescripcion) {
        return repoConcierto.getConciertosPorDescripcion(textoDescripcion);
    }

    /**
     * Obtiene estadísticas de actuaciones por artista
     */
    public Map<String, Integer> getEstadisticasActuacionesPorArtista() {
        return repoConcierto.getEstadisticasActuacionesPorArtista();
    }

    /**
     * Obtiene estadísticas por género
     */
    public Map<GeneroMusical, Integer> getEstadisticasPorGenero() {
        return repoConcierto.getEstadisticasPorGenero();
    }

    /**
     * Obtiene el artista con más actuaciones
     */
    public String getArtistaConMasActuaciones() throws LiveFestException {
        return repoConcierto.getArtistaConMasActuaciones();
    }

    /**
     * Obtiene artistas sin actuaciones
     */
    public List<String> getArtistasSinActuaciones() {
        List<String> todosLosCodigos = getCodigosArtistas();
        return repoConcierto.getArtistasSinActuaciones(todosLosCodigos);
    }

    /**
     * Genera ranking de artistas por actuaciones
     */
    public List<Map.Entry<String, Integer>> getRankingArtistasPorActuaciones() {
        return repoConcierto.getRankingArtistasPorActuaciones();
    }

    /**
     * Cuenta conciertos
     */
    public int contarConciertos() {
        return repoConcierto.contarConciertos();
    }

    /**
     * Cuenta conciertos por género
     */
    public int contarConciertosPorGenero(GeneroMusical genero) {
        return repoConcierto.contarConciertosPorGenero(genero);
    }

    /**
     * Obtiene conciertos ordenados por fecha
     */
    public List<Concierto> getConciertosordenadosPorFecha() {
        return repoConcierto.getConciertosordenadosPorFecha();
    }

    /**
     * Obtiene conciertos ordenados por ID
     */
    public List<Concierto> getConciertosordenadosPorId() {
        return repoConcierto.getConciertosordenadosPorId();
    }

    /**
     * Verifica si existe un concierto
     */
    public boolean existeConcierto(int id) {
        return repoConcierto.existeConcierto(id);
    }

    /**
     * Agrupa conciertos por género musical
     */
    public Map<GeneroMusical, List<Concierto>> getConciertosAgrupadosPorGenero() {
        return repoConcierto.getConciertosAgrupadosPorGenero();
    }

    /**
     * Agrupa conciertos por fecha
     */
    public Map<String, List<Concierto>> getConciertosAgrupadosPorFecha() {
        return repoConcierto.getConciertosAgrupadosPorFecha();
    }

    /**
     * Obtiene número de actuaciones de un artista
     */
    public int getNumActuacionesArtista(String codigoArtista) {
        return repoConcierto.getNumActuacionesArtista(codigoArtista);
    }

    /**
     * Obtiene actuaciones de un artista por género
     */
    public int getNumActuacionesArtistaPorGenero(String codigoArtista, GeneroMusical genero) {
        return repoConcierto.getNumActuacionesArtistaPorGenero(codigoArtista, genero);
    }

    // ==================== MÉTODOS COMBINADOS (ARTISTA + CONCIERTO) ====================

    /**
     * Obtiene información completa de un artista con sus actuaciones
     */
    public String getInformacionCompletaArtista(String codigo) throws LiveFestException {
        Artista artista = getArtista(codigo);
        int actuaciones = getNumActuacionesArtista(codigo);
        
        StringBuilder info = new StringBuilder();
        info.append("=== INFORMACIÓN DEL ARTISTA ===\n");
        info.append("Código: ").append(artista.getCodigo()).append("\n");
        info.append("Nombre: ").append(artista.getNombreArtistico()).append("\n");
        info.append("Email Manager: ").append(artista.getEmailManager()).append("\n");
        info.append("Integrantes: ").append(artista.getNumIntegrantes()).append("\n");
        info.append("Actuaciones: ").append(actuaciones).append("\n");
        
        return info.toString();
    }

    /**
     * Obtiene estadísticas generales del festival
     */
    public String getEstadisticasGenerales() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTADÍSTICAS GENERALES DEL LIVEFEST ===\n");
        stats.append("Total de artistas: ").append(contarArtistas()).append("\n");
        stats.append("Total de conciertos: ").append(contarConciertos()).append("\n");
        
        try {
            stats.append("Promedio integrantes por artista: ")
                 .append(String.format("%.2f", getPromedioIntegrantesPorArtista())).append("\n");
        } catch (LiveFestException e) {
            stats.append("Promedio integrantes: N/A\n");
        }
        
        stats.append("\nConciertos por género musical:\n");
        stats.append("- ROCK: ").append(contarConciertosPorGenero(GeneroMusical.ROCK)).append("\n");
        stats.append("- POP: ").append(contarConciertosPorGenero(GeneroMusical.POP)).append("\n");
        stats.append("- ELECTRONICA: ").append(contarConciertosPorGenero(GeneroMusical.ELECTRONICA)).append("\n");
        
        try {
            stats.append("\nArtista con más actuaciones: ").append(getArtistaConMasActuaciones()).append("\n");
        } catch (LiveFestException e) {
            stats.append("\nArtista con más actuaciones: N/A\n");
        }
        
        return stats.toString();
    }

    /**
     * Obtiene el top N artistas con más actuaciones
     */
    public List<Map.Entry<String, Integer>> getTopNArtistas(int n) {
        List<Map.Entry<String, Integer>> ranking = getRankingArtistasPorActuaciones();
        
        if (ranking.size() > n) {
            return ranking.subList(0, n);
        }
        
        return ranking;
    }

    /**
     * Verifica si un artista ha tenido alguna actuación
     */
    public boolean artistaTieneActuaciones(String codigoArtista) {
        return getNumActuacionesArtista(codigoArtista) > 0;
    }

    /**
     * Obtiene el género con más conciertos
     */
    public GeneroMusical getGeneroConMasConciertos() throws LiveFestException {
        Map<GeneroMusical, Integer> estadisticas = getEstadisticasPorGenero();
        
        if (estadisticas.isEmpty()) {
            throw new LiveFestException("No hay conciertos registrados");
        }
        
        GeneroMusical generoMax = null;
        int maxConciertos = 0;
        
        for (Map.Entry<GeneroMusical, Integer> entry : estadisticas.entrySet()) {
            if (entry.getValue() > maxConciertos) {
                maxConciertos = entry.getValue();
                generoMax = entry.getKey();
            }
        }
        
        return generoMax;
    }

    /**
     * Agrega una lista de artistas usando el repositorio
     */
    public void agregarListaArtistas(List<Artista> artistas) {
        repoArtista.agregarListaArtistas(artistas);
    }

    /**
     * Agrega una lista de conciertos usando el repositorio
     */
    public void agregarListaConciertos(List<Concierto> conciertos) {
        repoConcierto.agregarListaConciertos(conciertos);
    }
}
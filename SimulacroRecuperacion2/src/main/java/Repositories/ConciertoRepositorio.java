package Repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Exception.LiveFestException;
import Models.Concierto;
import Models.GeneroMusical;
import Services.LiveFestService;

public class ConciertoRepositorio {
    private LiveFestService conciertoServicio;

    public ConciertoRepositorio() {
        // Constructor vacío - NO crear LiveFestService aquí
    }

    public ConciertoRepositorio(LiveFestService conciertoServicio) {
        this.conciertoServicio = conciertoServicio;
    }

    public LiveFestService getConciertoServicio() {
        return conciertoServicio;
    }

    public void setConciertoServicio(LiveFestService conciertoServicio) {
        this.conciertoServicio = conciertoServicio;
    }

    /**
     * Agrega un concierto usando Iterator para verificar
     */
    public void agregarConcierto(Concierto concierto) throws LiveFestException {
        boolean existe = false;
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext() && !existe) {
            Concierto c = it.next();
            if (c.getId() == concierto.getId()) {
                existe = true;
            }
        }
        
        if (existe) {
            throw new LiveFestException("El concierto ya existe con ID: " + concierto.getId());
        }
        
        conciertoServicio.getListaConciertos().add(concierto);
    }

    /**
     * Obtiene conciertos donde actuó un artista como cabecera usando Iterator
     */
    public List<Concierto> getConciertosPorArtista(String codigoArtista) throws LiveFestException {
        List<Concierto> conciertosPorArtista = new ArrayList<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getArtistaCabecera() != null && 
                c.getArtistaCabecera().getCodigo().equalsIgnoreCase(codigoArtista)) {
                conciertosPorArtista.add(c);
            }
        }

        if (conciertosPorArtista.isEmpty()) {
            throw new LiveFestException("No se encontró ningún concierto para el artista: " + codigoArtista);
        }

        return conciertosPorArtista;
    }

    /**
     * Obtiene un concierto por su ID usando Iterator
     */
    public Concierto getConciertoporId(int id) throws LiveFestException {
        boolean encontrado = false;
        Concierto concierto = null;
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext() && !encontrado) {
            Concierto c = it.next();
            if (c.getId() == id) {
                encontrado = true;
                concierto = c;
            }
        }
        
        if (!encontrado) {
            throw new LiveFestException("No se encontró ningún concierto con ID: " + id);
        }
        
        return concierto;
    }

    /**
     * Elimina un concierto por su ID usando Iterator
     */
    public void eliminarConcierto(int id) throws LiveFestException {
        boolean encontrado = false;
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext() && !encontrado) {
            Concierto c = it.next();
            if (c.getId() == id) {
                it.remove();
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            throw new LiveFestException("No se encontró el concierto con ID: " + id);
        }
    }

    /**
     * Actualiza un concierto usando Iterator
     */
    public void actualizarConcierto(Concierto conciertoActualizado) throws LiveFestException {
        boolean encontrado = false;
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext() && !encontrado) {
            Concierto c = it.next();
            if (c.getId() == conciertoActualizado.getId()) {
                c.setFecha(conciertoActualizado.getFecha());
                c.setDescripcion(conciertoActualizado.getDescripcion());
                c.setGenero(conciertoActualizado.getGenero());
                c.setArtistaCabecera(conciertoActualizado.getArtistaCabecera());
                encontrado = true;
            }
        }
        
        if (!encontrado) {
            throw new LiveFestException("No se encontró el concierto con ID: " + conciertoActualizado.getId());
        }
    }

    /**
     * Obtiene todos los conciertos usando Iterator
     */
    public List<Concierto> getTodosLosConciertos() {
        List<Concierto> lista = new ArrayList<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            lista.add(it.next());
        }
        
        return lista;
    }

    /**
     * Filtra conciertos por género musical usando Iterator
     */
    public List<Concierto> getConciertosPorGenero(GeneroMusical genero) {
        List<Concierto> conciertosFiltrados = new ArrayList<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getGenero() == genero) {
                conciertosFiltrados.add(c);
            }
        }
        
        return conciertosFiltrados;
    }

    /**
     * Filtra conciertos por fecha usando Iterator
     */
    public List<Concierto> getConciertosPorFecha(String fecha) {
        List<Concierto> conciertosFiltrados = new ArrayList<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getFecha().equals(fecha)) {
                conciertosFiltrados.add(c);
            }
        }
        
        return conciertosFiltrados;
    }

    /**
     * Filtra conciertos por descripción usando Iterator
     */
    public List<Concierto> getConciertosPorDescripcion(String textoDescripcion) {
        List<Concierto> conciertosFiltrados = new ArrayList<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getDescripcion().toLowerCase().contains(textoDescripcion.toLowerCase())) {
                conciertosFiltrados.add(c);
            }
        }
        
        return conciertosFiltrados;
    }

    /**
     * Cuenta actuaciones por artista usando Iterator
     */
    public Map<String, Integer> getEstadisticasActuacionesPorArtista() {
        Map<String, Integer> estadisticas = new HashMap<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getArtistaCabecera() != null) {
                String codigo = c.getArtistaCabecera().getCodigo();
                estadisticas.put(codigo, estadisticas.getOrDefault(codigo, 0) + 1);
            }
        }
        
        return estadisticas;
    }

    /**
     * Cuenta conciertos por género musical usando Iterator
     */
    public Map<GeneroMusical, Integer> getEstadisticasPorGenero() {
        Map<GeneroMusical, Integer> estadisticas = new HashMap<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            GeneroMusical genero = c.getGenero();
            estadisticas.put(genero, estadisticas.getOrDefault(genero, 0) + 1);
        }
        
        return estadisticas;
    }

    /**
     * Obtiene el artista con más actuaciones usando Iterator
     */
    public String getArtistaConMasActuaciones() throws LiveFestException {
        Map<String, Integer> actuaciones = getEstadisticasActuacionesPorArtista();
        
        if (actuaciones.isEmpty()) {
            throw new LiveFestException("No hay conciertos registrados");
        }

        String artistaEstrella = null;
        int maxActuaciones = 0;
        
        Iterator<Map.Entry<String, Integer>> it = actuaciones.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            if (entry.getValue() > maxActuaciones) {
                maxActuaciones = entry.getValue();
                artistaEstrella = entry.getKey();
            }
        }

        return artistaEstrella;
    }

    /**
     * Obtiene artistas sin actuaciones usando Iterator
     */
    public List<String> getArtistasSinActuaciones(List<String> todosLosCodigos) {
        List<String> artistasConActuaciones = new ArrayList<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getArtistaCabecera() != null) {
                artistasConActuaciones.add(c.getArtistaCabecera().getCodigo());
            }
        }
        
        List<String> artistasSinActuaciones = new ArrayList<>();
        Iterator<String> itCodigos = todosLosCodigos.iterator();
        while (itCodigos.hasNext()) {
            String codigo = itCodigos.next();
            if (!artistasConActuaciones.contains(codigo)) {
                artistasSinActuaciones.add(codigo);
            }
        }
        
        return artistasSinActuaciones;
    }

    /**
     * Genera ranking de artistas por actuaciones usando Iterator
     */
    public List<Map.Entry<String, Integer>> getRankingArtistasPorActuaciones() {
        Map<String, Integer> actuaciones = getEstadisticasActuacionesPorArtista();
        
        List<Map.Entry<String, Integer>> ranking = new ArrayList<>(actuaciones.entrySet());
        ranking.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        return ranking;
    }

    /**
     * Cuenta total de conciertos
     */
    public int contarConciertos() {
        return conciertoServicio.getListaConciertos().size();
    }

    /**
     * Cuenta conciertos de un género específico usando Iterator
     */
    public int contarConciertosPorGenero(GeneroMusical genero) {
        int contador = 0;
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getGenero() == genero) {
                contador++;
            }
        }
        
        return contador;
    }

    /**
     * Obtiene conciertos ordenados por fecha usando Iterator
     */
    public List<Concierto> getConciertosordenadosPorFecha() {
        List<Concierto> conciertosOrdenados = new ArrayList<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            conciertosOrdenados.add(it.next());
        }
        
        conciertosOrdenados.sort((c1, c2) -> c1.getFecha().compareTo(c2.getFecha()));
        
        return conciertosOrdenados;
    }

    /**
     * Obtiene conciertos ordenados por ID usando Iterator
     */
    public List<Concierto> getConciertosordenadosPorId() {
        List<Concierto> conciertosOrdenados = new ArrayList<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            conciertosOrdenados.add(it.next());
        }
        
        conciertosOrdenados.sort((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
        
        return conciertosOrdenados;
    }

    /**
     * Verifica si existe un concierto con un ID específico usando Iterator
     */
    public boolean existeConcierto(int id) {
        boolean existe = false;
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext() && !existe) {
            Concierto c = it.next();
            if (c.getId() == id) {
                existe = true;
            }
        }
        
        return existe;
    }

    /**
     * Agrupa conciertos por género musical usando Iterator
     */
    public Map<GeneroMusical, List<Concierto>> getConciertosAgrupadosPorGenero() {
        Map<GeneroMusical, List<Concierto>> agrupados = new HashMap<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            GeneroMusical genero = c.getGenero();
            if (!agrupados.containsKey(genero)) {
                agrupados.put(genero, new ArrayList<>());
            }
            agrupados.get(genero).add(c);
        }
        
        return agrupados;
    }

    /**
     * Agrupa conciertos por fecha usando Iterator
     */
    public Map<String, List<Concierto>> getConciertosAgrupadosPorFecha() {
        Map<String, List<Concierto>> agrupados = new HashMap<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            String fecha = c.getFecha();
            if (!agrupados.containsKey(fecha)) {
                agrupados.put(fecha, new ArrayList<>());
            }
            agrupados.get(fecha).add(c);
        }
        
        return agrupados;
    }

    /**
     * Obtiene el número de actuaciones de un artista específico usando Iterator
     */
    public int getNumActuacionesArtista(String codigoArtista) {
        int actuaciones = 0;
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getArtistaCabecera() != null && 
                c.getArtistaCabecera().getCodigo().equalsIgnoreCase(codigoArtista)) {
                actuaciones++;
            }
        }
        
        return actuaciones;
    }

    /**
     * Obtiene actuaciones de un artista en un género específico usando Iterator
     */
    public int getNumActuacionesArtistaPorGenero(String codigoArtista, GeneroMusical genero) {
        int actuaciones = 0;
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getArtistaCabecera() != null && 
                c.getArtistaCabecera().getCodigo().equalsIgnoreCase(codigoArtista) &&
                c.getGenero() == genero) {
                actuaciones++;
            }
        }
        
        return actuaciones;
    }

    /**
     * Agrega una lista de conciertos al repositorio usando Iterator
     * Captura e imprime excepciones a nivel de log
     */
    public void agregarListaConciertos(List<Concierto> conciertos) {
        org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(ConciertoRepositorio.class);
        
        Iterator<Concierto> it = conciertos.iterator();
        while (it.hasNext()) {
            Concierto concierto = it.next();
            try {
                agregarConcierto(concierto);
                logger.info("Concierto agregado correctamente: " + concierto.getId());
            } catch (LiveFestException e) {
                logger.error("Error al agregar concierto " + concierto.getId() + ": " + e.getMessage());
            }
        }
    }
}
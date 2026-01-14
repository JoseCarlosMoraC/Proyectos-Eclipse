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
        // Constructor vacío
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
     * Agrega una lista de conciertos con manejo de excepciones
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

    /**
     * Obtiene conciertos por banda usando Iterator
     */
    public List<Concierto> getConciertosPorBanda(String codigoBanda) throws LiveFestException {
        List<Concierto> conciertosPorBanda = new ArrayList<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getBandaHeadliner() != null && 
                c.getBandaHeadliner().getCodigo().equalsIgnoreCase(codigoBanda)) {
                conciertosPorBanda.add(c);
            }
        }

        if (conciertosPorBanda.isEmpty()) {
            throw new LiveFestException("No se encontró ningún concierto para la banda: " + codigoBanda);
        }

        return conciertosPorBanda;
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
     * Filtra conciertos por género usando Iterator
     */
    public List<Concierto> getConciertosPorGenero(GeneroMusical genero) {
        List<Concierto> conciertosFiltrados = new ArrayList<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getGeneroMusical() == genero) {
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
     * Filtra conciertos por escenario usando Iterator
     */
    public List<Concierto> getConciertosPorEscenario(String escenario) {
        List<Concierto> conciertosFiltrados = new ArrayList<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getEscenario().equalsIgnoreCase(escenario)) {
                conciertosFiltrados.add(c);
            }
        }
        
        return conciertosFiltrados;
    }

    /**
     * Cuenta conciertos por banda usando Iterator
     */
    public Map<String, Integer> getEstadisticasConciertosPorBanda() {
        Map<String, Integer> estadisticas = new HashMap<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getBandaHeadliner() != null) {
                String codigo = c.getBandaHeadliner().getCodigo();
                estadisticas.put(codigo, estadisticas.getOrDefault(codigo, 0) + 1);
            }
        }
        
        return estadisticas;
    }

    /**
     * Cuenta conciertos por género usando Iterator
     */
    public Map<GeneroMusical, Integer> getEstadisticasPorGenero() {
        Map<GeneroMusical, Integer> estadisticas = new HashMap<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            GeneroMusical genero = c.getGeneroMusical();
            estadisticas.put(genero, estadisticas.getOrDefault(genero, 0) + 1);
        }
        
        return estadisticas;
    }

    /**
     * Obtiene la banda con más conciertos usando Iterator
     */
    public String getBandaConMasConciertos() throws LiveFestException {
        Map<String, Integer> conciertos = getEstadisticasConciertosPorBanda();
        
        if (conciertos.isEmpty()) {
            throw new LiveFestException("No hay conciertos registrados");
        }

        String bandaTop = null;
        int maxConciertos = 0;
        
        Iterator<Map.Entry<String, Integer>> it = conciertos.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            if (entry.getValue() > maxConciertos) {
                maxConciertos = entry.getValue();
                bandaTop = entry.getKey();
            }
        }

        return bandaTop;
    }

    /**
     * Genera ranking de bandas por conciertos usando Iterator
     */
    public List<Map.Entry<String, Integer>> getRankingBandasPorConciertos() {
        Map<String, Integer> conciertos = getEstadisticasConciertosPorBanda();
        
        List<Map.Entry<String, Integer>> ranking = new ArrayList<>(conciertos.entrySet());
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
     * Cuenta conciertos por género específico usando Iterator
     */
    public int contarConciertosPorGenero(GeneroMusical genero) {
        int contador = 0;
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getGeneroMusical() == genero) {
                contador++;
            }
        }
        
        return contador;
    }

    /**
     * Obtiene el número de conciertos de una banda específica usando Iterator
     */
    public int getNumConciertosBanda(String codigoBanda) {
        int conciertos = 0;
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            if (c.getBandaHeadliner() != null && 
                c.getBandaHeadliner().getCodigo().equalsIgnoreCase(codigoBanda)) {
                conciertos++;
            }
        }
        
        return conciertos;
    }

    /**
     * Agrupa conciertos por género usando Iterator
     */
    public Map<GeneroMusical, List<Concierto>> getConciertosAgrupadosPorGenero() {
        Map<GeneroMusical, List<Concierto>> agrupados = new HashMap<>();
        
        Iterator<Concierto> it = conciertoServicio.getListaConciertos().iterator();
        while (it.hasNext()) {
            Concierto c = it.next();
            GeneroMusical genero = c.getGeneroMusical();
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
}
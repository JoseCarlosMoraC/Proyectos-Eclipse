package Services;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Exception.LiveFestException;
import Models.Banda;
import Models.Concierto;
import Models.GeneroMusical;
import Repositories.BandaRepositorio;
import Repositories.ConciertoRepositorio;

public class LiveFestService {
    private BandaRepositorio repoBanda;
    private ConciertoRepositorio repoConcierto;
    private Set<Banda> listaBandas;
    private Set<Concierto> listaConciertos;

    public LiveFestService() {
        this.listaBandas = new HashSet<>();
        this.listaConciertos = new HashSet<>();
        
        // Crear repositorios y asignarles este servicio
        this.repoBanda = new BandaRepositorio();
        this.repoBanda.setBandaServicio(this);
        
        this.repoConcierto = new ConciertoRepositorio();
        this.repoConcierto.setConciertoServicio(this);
    }

    // Getters y Setters
    public BandaRepositorio getRepoBanda() {
        return repoBanda;
    }

    public void setRepoBanda(BandaRepositorio repoBanda) {
        this.repoBanda = repoBanda;
    }

    public ConciertoRepositorio getRepoConcierto() {
        return repoConcierto;
    }

    public void setRepoConcierto(ConciertoRepositorio repoConcierto) {
        this.repoConcierto = repoConcierto;
    }

    public Set<Banda> getListaBandas() {
        return listaBandas;
    }

    public void setListaBandas(Set<Banda> listaBandas) {
        this.listaBandas = listaBandas;
    }

    public Set<Concierto> getListaConciertos() {
        return listaConciertos;
    }

    public void setListaConciertos(Set<Concierto> listaConciertos) {
        this.listaConciertos = listaConciertos;
    }

    // Métodos de Bandas
    public Banda getBanda(String codigo) throws LiveFestException {
        return repoBanda.getBanda(codigo);
    }

    public void agregarBanda(Banda banda) throws LiveFestException {
        repoBanda.agregarBanda(banda);
    }

    public void agregarListaBandas(List<Banda> bandas) {
        repoBanda.agregarListaBandas(bandas);
    }

    public List<Banda> getTodasLasBandas() {
        return repoBanda.getTodasLasBandas();
    }

    public List<Banda> getBandasPorPais(String pais) {
        return repoBanda.getBandasPorPais(pais);
    }

    public List<Banda> getBandasPorNumIntegrantes(int numMinimo) {
        return repoBanda.getBandasPorNumIntegrantes(numMinimo);
    }

    public int contarBandas() {
        return repoBanda.contarBandas();
    }

    // Métodos de Conciertos
    public void agregarConcierto(Concierto concierto) throws LiveFestException {
        repoConcierto.agregarConcierto(concierto);
    }

    public void agregarListaConciertos(List<Concierto> conciertos) {
        repoConcierto.agregarListaConciertos(conciertos);
    }

    public List<Concierto> getConciertosPorBanda(String codigoBanda) throws LiveFestException {
        return repoConcierto.getConciertosPorBanda(codigoBanda);
    }

    public List<Concierto> getTodosLosConciertos() {
        return repoConcierto.getTodosLosConciertos();
    }

    public List<Concierto> getConciertosPorGenero(GeneroMusical genero) {
        return repoConcierto.getConciertosPorGenero(genero);
    }

    public List<Concierto> getConciertosPorFecha(String fecha) {
        return repoConcierto.getConciertosPorFecha(fecha);
    }

    public List<Concierto> getConciertosPorEscenario(String escenario) {
        return repoConcierto.getConciertosPorEscenario(escenario);
    }

    public Map<String, Integer> getEstadisticasConciertosPorBanda() {
        return repoConcierto.getEstadisticasConciertosPorBanda();
    }

    public Map<GeneroMusical, Integer> getEstadisticasPorGenero() {
        return repoConcierto.getEstadisticasPorGenero();
    }

    public String getBandaConMasConciertos() throws LiveFestException {
        return repoConcierto.getBandaConMasConciertos();
    }

    public List<Map.Entry<String, Integer>> getRankingBandasPorConciertos() {
        return repoConcierto.getRankingBandasPorConciertos();
    }

    public int contarConciertos() {
        return repoConcierto.contarConciertos();
    }

    // Métodos combinados
    public String getInformacionCompletaBanda(String codigo) throws LiveFestException {
        Banda banda = getBanda(codigo);
        int conciertos = repoConcierto.getNumConciertosBanda(codigo);
        
        StringBuilder info = new StringBuilder();
        info.append("=== INFORMACIÓN DE LA BANDA ===\n");
        info.append("Código: ").append(banda.getCodigo()).append("\n");
        info.append("Nombre: ").append(banda.getNombre()).append("\n");
        info.append("País: ").append(banda.getPaisOrigen()).append("\n");
        info.append("Integrantes: ").append(banda.getNumIntegrantes()).append("\n");
        info.append("Email: ").append(banda.getEmailContacto()).append("\n");
        info.append("Conciertos: ").append(conciertos).append("\n");
        
        return info.toString();
    }

    public String getEstadisticasGenerales() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== ESTADÍSTICAS GENERALES LIVEFEST ===\n");
        stats.append("Total de bandas: ").append(contarBandas()).append("\n");
        stats.append("Total de conciertos: ").append(contarConciertos()).append("\n");
        
        stats.append("\nConciertos por género:\n");
        stats.append("- ROCK: ").append(repoConcierto.contarConciertosPorGenero(GeneroMusical.ROCK)).append("\n");
        stats.append("- POP: ").append(repoConcierto.contarConciertosPorGenero(GeneroMusical.POP)).append("\n");
        stats.append("- ELECTRONICA: ").append(repoConcierto.contarConciertosPorGenero(GeneroMusical.ELECTRONICA)).append("\n");
        stats.append("- HIPHOP: ").append(repoConcierto.contarConciertosPorGenero(GeneroMusical.HIPHOP)).append("\n");
        stats.append("- JAZZ: ").append(repoConcierto.contarConciertosPorGenero(GeneroMusical.JAZZ)).append("\n");
        
        try {
            stats.append("\nBanda con más conciertos: ").append(getBandaConMasConciertos()).append("\n");
        } catch (LiveFestException e) {
            stats.append("\nBanda con más conciertos: N/A\n");
        }
        
        return stats.toString();
    }
}
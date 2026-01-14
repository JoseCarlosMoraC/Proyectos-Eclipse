package Repositories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Exception.LiveFestException;
import Models.Banda;
import Services.LiveFestService;


public class BandaRepositorio {
    private LiveFestService bandaServicio;

    public BandaRepositorio() {
        // Constructor vacío
    }

    public BandaRepositorio(LiveFestService bandaServicio) {
        this.bandaServicio = bandaServicio;
    }

    public LiveFestService getBandaServicio() {
        return bandaServicio;
    }

    public void setBandaServicio(LiveFestService bandaServicio) {
        this.bandaServicio = bandaServicio;
    }

    /**
     * Obtiene una banda por su código usando Iterator
     */
    public Banda getBanda(String codigo) throws LiveFestException {
        boolean encontrado = false;
        Banda banda = null;

        Iterator<Banda> it = bandaServicio.getListaBandas().iterator();
        while (it.hasNext() && !encontrado) {
            Banda b = it.next();
            if (b.getCodigo().equalsIgnoreCase(codigo)) {
                encontrado = true;
                banda = b;
            }
        }

        if (!encontrado) {
            throw new LiveFestException("No se encuentra ninguna banda con código: " + codigo);
        }

        return banda;
    }

    /**
     * Agrega una banda usando Iterator para verificar
     */
    public void agregarBanda(Banda banda) throws LiveFestException {
        boolean existe = false;
        
        Iterator<Banda> it = bandaServicio.getListaBandas().iterator();
        while (it.hasNext() && !existe) {
            Banda b = it.next();
            if (b.getCodigo().equalsIgnoreCase(banda.getCodigo())) {
                existe = true;
            }
        }
        
        if (existe) {
            throw new LiveFestException("Ya existe la banda con código: " + banda.getCodigo());
        }
        
        bandaServicio.getListaBandas().add(banda);
    }

    /**
     * Agrega una lista de bandas con manejo de excepciones
     */
    public void agregarListaBandas(List<Banda> bandas) {
        org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(BandaRepositorio.class);
        
        Iterator<Banda> it = bandas.iterator();
        while (it.hasNext()) {
            Banda banda = it.next();
            try {
                agregarBanda(banda);
                logger.info("Banda agregada correctamente: " + banda.getCodigo());
            } catch (LiveFestException e) {
                logger.error("Error al agregar banda " + banda.getCodigo() + ": " + e.getMessage());
            }
        }
    }

    /**
     * Obtiene todas las bandas usando Iterator
     */
    public List<Banda> getTodasLasBandas() {
        List<Banda> lista = new ArrayList<>();
        
        Iterator<Banda> it = bandaServicio.getListaBandas().iterator();
        while (it.hasNext()) {
            lista.add(it.next());
        }
        
        return lista;
    }

    /**
     * Filtra bandas por país usando Iterator
     */
    public List<Banda> getBandasPorPais(String pais) {
        List<Banda> bandasFiltradas = new ArrayList<>();
        
        Iterator<Banda> it = bandaServicio.getListaBandas().iterator();
        while (it.hasNext()) {
            Banda b = it.next();
            if (b.getPaisOrigen().equalsIgnoreCase(pais)) {
                bandasFiltradas.add(b);
            }
        }
        
        return bandasFiltradas;
    }

    /**
     * Filtra bandas por número mínimo de integrantes usando Iterator
     */
    public List<Banda> getBandasPorNumIntegrantes(int numMinimo) {
        List<Banda> bandasFiltradas = new ArrayList<>();
        
        Iterator<Banda> it = bandaServicio.getListaBandas().iterator();
        while (it.hasNext()) {
            Banda b = it.next();
            if (b.getNumIntegrantes() >= numMinimo) {
                bandasFiltradas.add(b);
            }
        }
        
        return bandasFiltradas;
    }

    /**
     * Busca bandas por nombre usando Iterator
     */
    public List<Banda> buscarBandasPorNombre(String texto) {
        List<Banda> bandasEncontradas = new ArrayList<>();
        
        Iterator<Banda> it = bandaServicio.getListaBandas().iterator();
        while (it.hasNext()) {
            Banda b = it.next();
            if (b.getNombre().toLowerCase().contains(texto.toLowerCase())) {
                bandasEncontradas.add(b);
            }
        }
        
        return bandasEncontradas;
    }

    /**
     * Cuenta el total de bandas
     */
    public int contarBandas() {
        return bandaServicio.getListaBandas().size();
    }

    /**
     * Verifica si existe una banda usando Iterator
     */
    public boolean existeBanda(String codigo) {
        boolean existe = false;
        
        Iterator<Banda> it = bandaServicio.getListaBandas().iterator();
        while (it.hasNext() && !existe) {
            Banda b = it.next();
            if (b.getCodigo().equalsIgnoreCase(codigo)) {
                existe = true;
            }
        }
        
        return existe;
    }

    /**
     * Obtiene bandas ordenadas por nombre usando Iterator
     */
    public List<Banda> getBandasOrdenadasPorNombre() {
        List<Banda> bandasOrdenadas = new ArrayList<>();
        
        Iterator<Banda> it = bandaServicio.getListaBandas().iterator();
        while (it.hasNext()) {
            bandasOrdenadas.add(it.next());
        }
        
        bandasOrdenadas.sort((b1, b2) -> b1.getNombre().compareToIgnoreCase(b2.getNombre()));
        
        return bandasOrdenadas;
    }

    /**
     * Obtiene la lista de códigos de todas las bandas usando Iterator
     */
    public List<String> getCodigosBandas() {
        List<String> codigos = new ArrayList<>();
        
        Iterator<Banda> it = bandaServicio.getListaBandas().iterator();
        while (it.hasNext()) {
            Banda b = it.next();
            codigos.add(b.getCodigo());
        }
        
        return codigos;
    }
}
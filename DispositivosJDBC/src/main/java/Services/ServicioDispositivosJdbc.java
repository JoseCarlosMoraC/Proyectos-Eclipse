package Services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exceptions.MiExcepcion;
import Models.Dispositivo;
import Repositories.RepositorioDispositivosJdbc;

public class ServicioDispositivosJdbc {

    private static final Logger logger =
            LogManager.getLogger(ServicioDispositivosJdbc.class);

    private RepositorioDispositivosJdbc repo;

    public ServicioDispositivosJdbc() throws MiExcepcion {
        super();
        this.repo = new RepositorioDispositivosJdbc();
    }

    public RepositorioDispositivosJdbc getRepo() {
        return repo;
    }

    public void setRepo(RepositorioDispositivosJdbc repo) {
        this.repo = repo;
    }

    public void addDispositivo(Dispositivo d) {
        try {
            repo.agregarDispositivo(d);
        } catch (MiExcepcion e) {
            logger.error("No se pudo añadir dispositivo: " + e.getMessage());
        }
    }

    public List<Dispositivo> dispositivosPorCategoria(String categoria) {
        List<Dispositivo> lista = new ArrayList<>();
        try {
            lista = repo.dispositivosPorCategoria(categoria);
        } catch (MiExcepcion e) {
            logger.error("Error categoria: " + e.getMessage());
        }
        return lista;
    }

    public double stockTotal() {
        double total = 0;
        try {
            total = repo.stockTotal();
        } catch (MiExcepcion e) {
            logger.error("Error stock total: " + e.getMessage());
        }
        return total;
    }

    public List<Dispositivo> dispositivosOrdenadosPorPrecio() {
        List<Dispositivo> lista = new ArrayList<>();
        try {
            lista = repo.ordenarPorPrecioDesc();
        } catch (MiExcepcion e) {
            logger.error("Error orden precio: " + e.getMessage());
        }
        return lista;
    }
    public List<String> puntuacionMediaPorDispositivo() {
        List<String> lista = new ArrayList<>();
        try {
            lista = repo.puntuacionMediaPorDispositivo();
        } catch (MiExcepcion e) {
            logger.error("Error media por dispositivo: " + e.getMessage());
        }
        return lista;
    }

}

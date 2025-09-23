package Mensajeria.Mensajeria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import Mensajeria.Exception.EnvioException;
import Mensajeria.Modelos.CentroDistribucion;
import Mensajeria.Modelos.Envio;
import Mensajeria.Modelos.EstadoEnvio;

public class RepositorioEnvios {
    // Mapa que asocia cada envío con el conjunto de centros de distribución que ha recorrido
    private Map<Envio, HashSet<CentroDistribucion>> envios;

    public RepositorioEnvios() {
        super();
        this.envios = new HashMap<Envio, HashSet<CentroDistribucion>>();
    }

    /**
     * Método para agregar un centro de distribución a un envío, dado número de seguimiento y fecha.
     * Si el envío no existe, se crea uno nuevo con peso 0 y código de destino vacío.
     */
    public void agregarCentroDistribucion(String numSeguimiento, LocalDate fecha, CentroDistribucion centro) {
        // Intentamos buscar el envío existente por fecha y número de seguimiento
        Envio envioExistente = null;
        try {
            envioExistente = this.buscarEnvio(fecha, numSeguimiento);
        } catch (EnvioException e) {
            // No se encontró el envío, se creará uno nuevo más adelante
        }

        // Si no existe, creamos un nuevo envío con peso 0 y código de destino vacío
        if (envioExistente == null) {
            envioExistente = new Envio(fecha, numSeguimiento, 0.0, "");
            envios.put(envioExistente, new HashSet<>());
        }

        // Agregamos el centro de distribución al conjunto asociado a ese envío
        envios.get(envioExistente).add(centro);
    }

    /**
     * Busca un envío por fecha y número de seguimiento.
     * Devuelve el envío si lo encuentra o null si no.
     */
    public Envio buscarEnvio(LocalDate fechaEnvio, String numeroSeguimiento) throws EnvioException {
        boolean encontrado = false;
        Envio envio = null;

        // Recorremos las claves (envíos) del mapa usando un iterador
        Iterator<Envio> it = envios.keySet().iterator();
        while (it.hasNext() && !encontrado) {
            Envio e = it.next();

            // Comparamos número de seguimiento ignorando mayúsculas y la fecha
            if (e.getCodigoSeguimiento().equalsIgnoreCase(numeroSeguimiento) && e.getFechaEnvio().equals(fechaEnvio)) {
                encontrado = true;
                envio = e;
            }
        }

        return envio;
    }

    /**
     * Devuelve una lista de envíos que hayan pasado por un centro de distribución con código dado.
     */
    public List<Envio> filtrarEnviosPorCentro(String codigoCentro) {
        List<Envio> enviosFiltrados = new ArrayList<>();

        // Recorremos todos los envíos y verificamos si alguno de sus centros coincide con el código dado
        Iterator<Envio> it = envios.keySet().iterator();
        while (it.hasNext()) {
            Envio envio = it.next();
            HashSet<CentroDistribucion> centros = envios.get(envio);

            Iterator<CentroDistribucion> itCentro = centros.iterator();
            while (itCentro.hasNext()) {
                CentroDistribucion centro = itCentro.next();
                if (centro.getCodigoCentro().equalsIgnoreCase(codigoCentro)) {
                    enviosFiltrados.add(envio);
                    break; // Ya encontramos un centro que coincide, no es necesario seguir buscando para este envío
                }
            }
        }
        return enviosFiltrados;
    }

    /**
     * Verifica si un envío (identificado por fecha y número) ha pasado por un centro específico.
     * Devuelve true si pasó por el centro, false en caso contrario.
     */
    public boolean buscarCentroDistribucion(LocalDate fechaEnvio, String numeroSeguimiento, String codigoCentro) {
        Iterator<Envio> it = envios.keySet().iterator();
        while (it.hasNext()) {
            Envio envio = it.next();
            if (envio.getFechaEnvio().equals(fechaEnvio)
                    && envio.getCodigoSeguimiento().equalsIgnoreCase(numeroSeguimiento)) {

                HashSet<CentroDistribucion> centros = envios.get(envio);
                Iterator<CentroDistribucion> itCentro = centros.iterator();
                while (itCentro.hasNext()) {
                    CentroDistribucion centro = itCentro.next();
                    if (centro.getCodigoCentro().equalsIgnoreCase(codigoCentro)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Comprueba si un centro es el centro final del envío y, de ser así, actualiza el estado del envío a EN_ENTREGA.
     * Devuelve true si el centro es el centro final, false en caso contrario.
     */
    public boolean isCentroFinal(LocalDate fechaEnvio, String numeroSeguimiento, String codigoCentro) {
        Iterator<Envio> it = envios.keySet().iterator();
        while (it.hasNext()) {
            Envio envio = it.next();
            if (envio.getFechaEnvio().equals(fechaEnvio)
                    && envio.getCodigoSeguimiento().equalsIgnoreCase(numeroSeguimiento)) {

                if (envio.getCodigoCentroDestino().equalsIgnoreCase(codigoCentro)) {
                    envio.setEstado(EstadoEnvio.EN_ENTREGA); // Cambia estado a EN_ENTREGA
                    return true;
                }
            }
        }
        return false;
    }
}

package TowerGPT.Service;

import java.util.List;
import java.util.Map;

import TowerGPT.Models.InteraccionAgente;
import TowerGPT.Models.TipoAgente;

public interface IServicioEstadistica {

    public Map<TipoAgente, List<InteraccionAgente>> agruparInteraccionesPorTipo(Map<String, InteraccionAgente> interacciones);

    public void grabarResumenEstadistica(String rutaFicheroNombre, Map<String, InteraccionAgente> interacciones);


    public InteraccionAgente obtenerInteraaccionConMejorValoracion(Map<String, InteraccionAgente> interacciones);

    public void grabarFicheroCSV(String rutaNombre, Map<String, InteraccionAgente> interacciones);

 
    public double calcularTiempoMedioPorTipo(TipoAgente tipo, Map<String, InteraccionAgente> interacciones);


    public double calcularPorcentajeAciertoMedioPorTipo(TipoAgente tipo, Map<String, InteraccionAgente> interacciones);
}

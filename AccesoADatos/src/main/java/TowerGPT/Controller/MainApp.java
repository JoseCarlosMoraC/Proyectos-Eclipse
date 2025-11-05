package TowerGPT.Controller;

import TowerGPT.Models.InteraccionAgente;
import TowerGPT.Models.TipoAgente;
import TowerGPT.Repository.RepositorioInteracciones;
import TowerGPT.Service.ServicioEstadisticaImpl;

import java.util.Map;

public class MainApp {
    public static void main(String[] args) {
    	String rutaJson = "src/main/java/TowerGPT/JSON/Interacciones.json";

    	String resumenRuta = "src/main/java/TowerGPT/JSON/resumenEstadistica.txt";


  
        ServicioEstadisticaImpl servicio = new ServicioEstadisticaImpl();
        RepositorioInteracciones repo = new RepositorioInteracciones(servicio);

        repo.cargarRegistrosDesdeJSON(rutaJson);

  
        System.out.println("=== Interacción con mejor valoración ===");
        String mejor = repo.obtenerInteraccionConMejorValoracion();
        System.out.println(mejor);

     
        Map<String, InteraccionAgente> reg = repo.getRegistroInteracciones();
        if (!reg.isEmpty()) {
            String primeraKey = null;
            for (String k : reg.keySet()) {
                primeraKey = k;
                break;
            }
            if (primeraKey != null) {
                InteraccionAgente primera = reg.get(primeraKey);
                System.out.println("\nAntes de modificar:");
                System.out.println(primera);

          
                repo.incrementaNumeroValoraciones(primera.getIdentificador());
                repo.actualizaPorcentajeInteraccion(primera, 88.8);

                System.out.println("Después de modificar:");
                System.out.println(reg.get(primeraKey));
            }
        }

     
        System.out.println("\n=== Interacción con mayor porcentaje de acierto ===");
        InteraccionAgente maxAcierto = null;
        for (String k : reg.keySet()) {
            InteraccionAgente ia = reg.get(k);
            if (ia != null) {
                if (maxAcierto == null || ia.getPorcentajeAcierto() > maxAcierto.getPorcentajeAcierto()) {
                    maxAcierto = ia;
                }
            }
        }
        System.out.println(maxAcierto != null ? maxAcierto.toString() : "No hay interacciones.");


        repo.grabarResumenEstadistica(resumenRuta);
        System.out.println("\nResumen grabado en: " + resumenRuta);

  
        System.out.println("\n=== Interacciones con acierto > 70% ordenadas ===");
        String filtradas = repo.obtenerInteraccionesAciertoMayorOrdenadas(70.0);
        System.out.println(filtradas);


        repo.grabarFicherosCSV();
        System.out.println("CSV generado en src/main/resources/salidaOrdenada.csv");
    }
}

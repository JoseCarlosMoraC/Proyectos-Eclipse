package TowerGPT.Service;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import TowerGPT.Models.InteraccionAgente;
import TowerGPT.Models.TipoAgente;

public class ServicioEstadisticaImpl implements IServicioEstadistica {

    public ServicioEstadisticaImpl() {
    }

    @Override
    public Map<TipoAgente, List<InteraccionAgente>> agruparInteraccionesPorTipo(Map<String, InteraccionAgente> interacciones) {
        Map<TipoAgente, List<InteraccionAgente>> mapa = new HashMap<>();
        mapa.put(TipoAgente.HUMANO, new ArrayList<InteraccionAgente>());
        mapa.put(TipoAgente.IA, new ArrayList<InteraccionAgente>());

        if (interacciones == null) return mapa;

        for (String key : interacciones.keySet()) {
            InteraccionAgente ia = interacciones.get(key);
            if (ia != null) {
                if (ia.getTipo() == TipoAgente.HUMANO) {
                    mapa.get(TipoAgente.HUMANO).add(ia);
                } else {
                    mapa.get(TipoAgente.IA).add(ia);
                }
            }
        }
        return mapa;
    }

    @Override
    public void grabarResumenEstadistica(String rutaFicheroNombre, Map<String, InteraccionAgente> interacciones) {
        PrintWriter out = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(rutaFicheroNombre);
            out = new PrintWriter(fw);

            int total = 0;
            if (interacciones != null) total = interacciones.size();

            double tiempoHumano = calcularTiempoMedioPorTipo(TipoAgente.HUMANO, interacciones);
            double tiempoIA = calcularTiempoMedioPorTipo(TipoAgente.IA, interacciones);

            double aciertoHumano = calcularPorcentajeAciertoMedioPorTipo(TipoAgente.HUMANO, interacciones);
            double aciertoIA = calcularPorcentajeAciertoMedioPorTipo(TipoAgente.IA, interacciones);

            int totalHumanos = 0;
            int totalIAs = 0;
            int sumValorHumano = 0;
            int sumValorIA = 0;

            if (interacciones != null) {
                for (String key : interacciones.keySet()) {
                    InteraccionAgente ia = interacciones.get(key);
                    if (ia != null) {
                        if (ia.getTipo() == TipoAgente.HUMANO) {
                            totalHumanos++;
                            sumValorHumano += ia.getNumValoracionesPositivas();
                        } else {
                            totalIAs++;
                            sumValorIA += ia.getNumValoracionesPositivas();
                        }
                    }
                }
            }

            double mediaValorHumano = (totalHumanos == 0) ? 0.0 : (double) sumValorHumano / totalHumanos;
            double mediaValorIA = (totalIAs == 0) ? 0.0 : (double) sumValorIA / totalIAs;

            String tipoMasTiempo = "NINGUNO";
            double tiempoMas = 0.0;
            if (tiempoHumano >= tiempoIA) {
                tipoMasTiempo = "Humanos";
                tiempoMas = tiempoHumano;
            } else {
                tipoMasTiempo = "IAs";
                tiempoMas = tiempoIA;
            }

            out.println("RESUMEN DE INTERACCIONES:");
            out.println("-------------------------------------------------------------------------------------------------------------");
            out.println("Se han efectuado un total de " + total + " interacciones:");
            out.println("Las interacciones que han tomado más tiempo han sido las efectuadas por " + tipoMasTiempo
                    + " con un tiempo medio de " + String.format(Locale.US, "%.3f", tiempoMas) + " segundos.");
            out.println("De todas las interacciones:");
            out.println("- " + totalHumanos + " han sido efectuadas por Humanos con una valoración media de "
                    + String.format(Locale.US, "%.1f", mediaValorHumano) + " y una tasa de acierto del "
                    + String.format(Locale.US, "%.0f", aciertoHumano) + "%");
            out.println("- " + totalIAs + " han sido efectuadas por IAs con una valoración media de "
                    + String.format(Locale.US, "%.1f", mediaValorIA) + " y una tasa de acierto del "
                    + String.format(Locale.US, "%.0f", aciertoIA) + "%");

            out.flush();
        } catch (Exception e) {
            System.out.println("Error al escribir resumen: " + e.getMessage());
        } finally {
            try {
                if (fw != null) fw.close();
                if (out != null) out.close();
            } catch (Exception ex) {
         
            }
        }
    }

    @Override
    public InteraccionAgente obtenerInteraaccionConMejorValoracion(Map<String, InteraccionAgente> interacciones) {
        if (interacciones == null || interacciones.isEmpty()) return null;
        InteraccionAgente mejor = null;
        int maxValor = Integer.MIN_VALUE;
        double mejorAcierto = -1.0;

        for (String key : interacciones.keySet()) {
            InteraccionAgente ia = interacciones.get(key);
            if (ia != null) {
                int val = ia.getNumValoracionesPositivas();
                double ac = ia.getPorcentajeAcierto();
                if (val > maxValor) {
                    mejor = ia;
                    maxValor = val;
                    mejorAcierto = ac;
                } else if (val == maxValor) {

                    if (ac > mejorAcierto) {
                        mejor = ia;
                        mejorAcierto = ac;
                    }
                }
            }
        }
        return mejor;
    }

    @Override
    public void grabarFicheroCSV(String rutaNombre, Map<String, InteraccionAgente> interacciones) {
        PrintWriter out = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(rutaNombre);
            out = new PrintWriter(fw);
            out.println("identificador,tipo,peticion,respuesta,tiempoEjecucion,numValoracionesPositivas,porcentajeAcierto");

        
            List<String> keys = new ArrayList<String>();
            if (interacciones != null) {
                for (String k : interacciones.keySet()) {
                    keys.add(k);
                }
            }

            for (int i = 0; i < keys.size() - 1; i++) {
                for (int j = i + 1; j < keys.size(); j++) {
                    if (keys.get(i).compareTo(keys.get(j)) > 0) {
                        String tmp = keys.get(i);
                        keys.set(i, keys.get(j));
                        keys.set(j, tmp);
                    }
                }
            }

            for (int i = 0; i < keys.size(); i++) {
                String k = keys.get(i);
                InteraccionAgente ia = interacciones.get(k);
                if (ia != null) {
                    String pet = (ia.getPeticion() == null) ? "" : ia.getPeticion().replace(",", " ");
                    String res = (ia.getRespuesta() == null) ? "" : ia.getRespuesta().replace(",", " ");
                    out.printf(Locale.US, "%s,%s,%s,%s,%.3f,%d,%.2f%n",
                            ia.getIdentificador(), ia.getTipo(), pet, res,
                            ia.getTiempoEjecucion(), ia.getNumValoracionesPositivas(), ia.getPorcentajeAcierto());
                }
            }
            out.flush();
        } catch (Exception e) {
            System.out.println("Error escribiendo CSV: " + e.getMessage());
        } finally {
            try {
                if (fw != null) fw.close();
                if (out != null) out.close();
            } catch (Exception ex) {
           
            }
        }
    }

    @Override
    public double calcularTiempoMedioPorTipo(TipoAgente tipo, Map<String, InteraccionAgente> interacciones) {
        if (interacciones == null || interacciones.isEmpty()) return 0.0;
        double suma = 0.0;
        int contador = 0;
        for (String key : interacciones.keySet()) {
            InteraccionAgente ia = interacciones.get(key);
            if (ia != null && ia.getTipo() == tipo) {
                suma += ia.getTiempoEjecucion();
                contador++;
            }
        }
        if (contador == 0) return 0.0;
        return suma / contador;
    }

    @Override
    public double calcularPorcentajeAciertoMedioPorTipo(TipoAgente tipo, Map<String, InteraccionAgente> interacciones) {
        if (interacciones == null || interacciones.isEmpty()) return 0.0;
        double suma = 0.0;
        int contador = 0;
        for (String key : interacciones.keySet()) {
            InteraccionAgente ia = interacciones.get(key);
            if (ia != null && ia.getTipo() == tipo) {
                suma += ia.getPorcentajeAcierto();
                contador++;
            }
        }
        if (contador == 0) return 0.0;
        return suma / contador;
    }
}

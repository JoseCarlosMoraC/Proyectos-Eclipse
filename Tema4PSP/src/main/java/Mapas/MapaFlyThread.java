package Mapas;

import java.util.HashMap;
import java.util.Map;

public class MapaFlyThread {

    // Mapa que guarda:
    // clave -> número de asiento
    // valor -> nombre del cliente
    private Map<Integer, String> reservaasientos;

    public MapaFlyThread() {
        // Inicializamos el mapa vacío
        this.reservaasientos = new HashMap<>();
    }

    public Map<Integer, String> getReservaasientos() {
        return reservaasientos;
    }

    public void setReservaasientos(Map<Integer, String> reservaasientos) {
        this.reservaasientos = reservaasientos;
    }

    @Override
    public String toString() {
        return "MapaFlyThread [reservaasientos=" + reservaasientos + "]";
    }

    // synchronized → solo UN hilo puede ejecutar este método a la vez
    // Evita que dos clientes reserven el mismo asiento simultáneamente
    public synchronized String reservarasiento(String asientocliente) {

        String resultado = "";

        // El cliente manda algo tipo: "5 y Juan"
        String[] asientoclientesplit = asientocliente.split("y");

        int numero = Integer.parseInt(asientoclientesplit[0].trim());
        String nombre = asientoclientesplit[1].trim();

        // Si el asiento NO está reservado
        if (!reservaasientos.containsKey(numero)) {
            reservaasientos.put(numero, nombre);
            resultado = "Su asiento ha sido reservado: " + numero + " " + nombre;
        } else {
            // Si el asiento ya existe
            resultado = "Este asiento ya está reservado mamon, mira "
                    + reservaasientos.get(numero);
        }

        return resultado;
    }
}

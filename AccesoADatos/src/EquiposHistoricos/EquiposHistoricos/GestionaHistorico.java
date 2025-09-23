package EquiposHistoricos.EquiposHistoricos;

import java.util.Iterator;
import java.util.Set;



public class GestionaHistorico {
    public static void main(String[] args) {
        RepositorioEquipo repo = new RepositorioEquipo();

        // Crear equipos
        Equipo equipo1 = new Equipo("Recreativo", 2000, 10000, 18, 2022);
        Equipo equipo2 = new Equipo("Recreativo", 2000, 10000, 15, 2023);
        Equipo equipo3 = new Equipo("Malaga", 1900, 50000, 13, 2022);
        Equipo equipo4 = new Equipo("Malaga", 1900, 60000, 4, 2023);
        Equipo equipo5 = new Equipo("Cadiz", 2005, 100000, 8, 2022);
        Equipo equipo6 = new Equipo("Cadiz", 2005, 10000, 5, 2023);

        // Agregar equipos al repositorio
        repo.getEquipos().add(equipo1);
        repo.getEquipos().add(equipo2);
        repo.getEquipos().add(equipo3);
        repo.getEquipos().add(equipo4);
        repo.getEquipos().add(equipo5);
        repo.getEquipos().add(equipo6);

        try {
            // Añadir jugadores válidos
            repo.addJugador("Recreativo", 2022, new Jugador(1, "Juan", "Perez", 24, Posicion.Portero, 900));
            repo.addJugador("Recreativo", 2022, new Jugador(2, "Luis", "Sanchez", 25, Posicion.Central, 1000));

            repo.addJugador("Malaga", 2022, new Jugador(3, "Carlos", "Lopez", 26, Posicion.Defensa, 800));
            repo.addJugador("Malaga", 2022, new Jugador(4, "Pedro", "Gomez", 27, Posicion.Centrocampista, 700));

            repo.addJugador("Cadiz", 2022, new Jugador(5, "Andres", "Diaz", 28, Posicion.Delantero, 600));
            repo.addJugador("Cadiz", 2022, new Jugador(6, "Sergio", "Ruiz", 29, Posicion.Delantero, 500));

            // Intentar añadir jugador que supere el presupuesto
            repo.addJugador("Recreativo", 2022, new Jugador(7, "Pepe", "Martinez", 30, Posicion.Defensa, 15000));
        } catch (FutbolException e) {
            System.out.println("Excepción capturada: " + e.getMessage());
        }

        // Mostrar histórico de jugadores de Recreativo
        System.out.println("Histórico de jugadores de Recreativo:");
        Set<Jugador> historico = repo.historicoJugadores("Recreativo");
        Iterator<Jugador> it = historico.iterator();
        while (it.hasNext()) {
            Jugador j = it.next();
            System.out.println(j.getNombre() + " " + j.getApellido() + " - Sueldo: " + j.getSueldo());
        }

        // Mostrar jugador más caro
        Jugador caro = repo.getJugadorMasCaro();
        if (caro != null) {
            System.out.println("\nJugador más caro:");
            System.out.println(caro.getNombre() + " " + caro.getApellido() + " - Sueldo: " + caro.getSueldo());
        }
    }
}

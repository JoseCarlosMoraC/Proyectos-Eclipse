package EquiposHistoricosPrueba.EquiposHistoricosPrueba;

import java.util.Iterator;
import java.util.List;

public class GestionaHistorico {
    private Repositorio repo;

    public GestionaHistorico() {
        // Creamos repositorio vacío
        repo = new Repositorio(null);

        // Creamos equipos con sus años y presupuestos
        Equipo recre2022 = new Equipo("Recre", 1889, 100000, 5, 2022, null);
        Equipo malaga2023 = new Equipo("Málaga", 1904, 80000, 8, 2023, null);
        Equipo cadiz2022 = new Equipo("Cádiz", 1910, 50000, 12, 2022, null);

        // Añadimos equipos al repositorio sin for, usando iterator
        // Como repo.equipos es HashSet, usamos add directamente
        repo.getEquipos().add(recre2022);
        repo.getEquipos().add(malaga2023);
        repo.getEquipos().add(cadiz2022);
    }

    // Apartado 4: Agrega 2 jugadores a cada equipo sin usar for
    public void agregaJugadoresAEq() {
        try {
            Jugador j1 = new Jugador(1, "Juan", "Pérez", 25, Posicion.Delantero, 30000);
            Jugador j2 = new Jugador(2, "Luis", "García", 28, Posicion.Defensa, 25000);
            repo.addJugador("Recre", 2022, j1);
            repo.addJugador("Recre", 2022, j2);

            Jugador j3 = new Jugador(3, "Carlos", "Lopez", 30, Posicion.Mediocentro, 35000);
            Jugador j4 = new Jugador(4, "Miguel", "Ramirez", 27, Posicion.Portero, 20000);
            repo.addJugador("Málaga", 2023, j3);
            repo.addJugador("Málaga", 2023, j4);

            Jugador j5 = new Jugador(5, "Alberto", "Santos", 22, Posicion.Defensa, 22000);
            Jugador j6 = new Jugador(6, "Pedro", "Jimenez", 24, Posicion.Delantero, 28000);
            repo.addJugador("Cádiz", 2022, j5);
            repo.addJugador("Cádiz", 2022, j6);
        } catch (FutbolException e) {
            System.out.println("Error añadiendo jugadores: " + e.getMessage());
        }
    }

    // Apartado 4: Método para probar excepciones, sin for
    public void pruebaExcepciones() {
        try {
            Jugador j7 = new Jugador(7, "Raúl", "Fernández", 26, Posicion.Mediocentro, 15000);
            repo.addJugador("Betis", 2022, j7); // Equipo inexistente
        } catch (FutbolException e) {
            System.out.println("Excepción capturada (equipo inexistente): " + e.getMessage());
        }

        try {
            Jugador j8 = new Jugador(8, "Manuel", "Torres", 29, Posicion.Delantero, 1000000);
            repo.addJugador("Cádiz", 2022, j8); // Presupuesto insuficiente
        } catch (FutbolException e) {
            System.out.println("Excepción capturada (presupuesto insuficiente): " + e.getMessage());
        }
    }

    // Apartado 5: Mostrar jugador más caro
    public void mostrarJugadorMasCaro() {
        Jugador caro = repo.getJugadorMasCaro();
        if (caro != null) {
            System.out.println("Jugador más caro: " + caro);
        } else {
            System.out.println("No hay jugadores en el histórico.");
        }
    }

    // Apartado 5: Mostrar histórico de jugadores sin for
    public void mostrarHistoricoPorEquipo(String nombreEquipo) {
        List<Jugador> historico = repo.getHistoricoJugadores(nombreEquipo);
        System.out.println("Histórico de jugadores de " + nombreEquipo + ":");
        Iterator<Jugador> it = historico.iterator();
        while (it.hasNext()) {
            Jugador j = it.next();
            System.out.println("- " + j.getNombre() + " (Sueldo: " + j.getSueldo() + ")");
        }
    }

    // Main para ejecutar pruebas
    public static void main(String[] args) {
        GestionaHistorico gestiona = new GestionaHistorico();

        // Añadimos jugadores a los equipos
        gestiona.agregaJugadoresAEq();

        // Probamos excepciones
        gestiona.pruebaExcepciones();

        // Mostramos jugador más caro
        gestiona.mostrarJugadorMasCaro();

        // Mostramos histórico para Recre y Cádiz
        gestiona.mostrarHistoricoPorEquipo("Recre");
        gestiona.mostrarHistoricoPorEquipo("Cádiz");
    }
}

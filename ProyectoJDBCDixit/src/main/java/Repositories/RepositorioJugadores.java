package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Config.MySqlConector;
import Exceptions.MiExcepcion;
import Models.Jugador;

/*
 * RepositorioJugadores
 * -------------------
 * Clase encargada de gestionar los jugadores en la base de datos.
 * Contiene métodos para cargar, agregar y consultar jugadores.
 */
public class RepositorioJugadores {

    // Logger para registrar información y errores
    private static final Logger logger = LogManager.getLogger(RepositorioJugadores.class);

    // Conector a la base de datos
    private MySqlConector conector;

    // Lista en memoria de jugadores cargados desde la base de datos
    private List<Jugador> listaJugadores;

    /*
     * Constructor
     * Inicializa el conector y carga los jugadores en la lista
     */
    public RepositorioJugadores() throws MiExcepcion {
        super();
        this.conector = new MySqlConector(); // Crear conector a BD
        this.listaJugadores = cargar(); // Cargar lista de jugadores desde la BD
    }

    // Getters y setters del conector y de la lista de jugadores
    public MySqlConector getConector() { return conector; }
    public void setConector(MySqlConector conector) { this.conector = conector; }
    public List<Jugador> getListaJugadores() { return listaJugadores; }
    public void setListaJugadores(List<Jugador> listaJugadores) { this.listaJugadores = listaJugadores; }

    /*
     * cargar
     * -------
     * Método privado que carga todos los jugadores desde la base de datos.
     * Devuelve una lista con los jugadores existentes.
     */
    private List<Jugador> cargar() throws MiExcepcion {
        List<Jugador> lista = new ArrayList<>();
        try {
            Connection conexion = conector.getConnect(); // Conexión a la BD
            Statement sentencia = conexion.createStatement(); // Crear sentencia SQL
            String sql = "SELECT * FROM MoraBD.jugadores"; // Consulta SQL para todos los jugadores
            ResultSet resultado = sentencia.executeQuery(sql); // Ejecutar consulta

            // Iterar sobre los resultados y crear objetos Jugador
            while(resultado.next()) {
                Jugador jugador = new Jugador();
                jugador.setId(resultado.getInt("id")); // ID del jugador
                jugador.setNombre(resultado.getString("nombre")); // Nombre del jugador
                jugador.setEmail(resultado.getString("email")); // Email del jugador
                jugador.setPuntosTotales(resultado.getInt("puntosTotales")); // Puntos acumulados
                lista.add(jugador); // Agregar jugador a la lista en memoria
            }
        } catch (SQLException e) {
            logger.error("Error al cargar jugadores: " + e.getMessage()); // Registrar error
        }
        return lista; // Retorna la lista completa de jugadores
    }

    /*
     * agregarJugador
     * --------------
     * Inserta un nuevo jugador en la base de datos y lo añade a la lista en memoria
     */
    public void agregarJugador(Jugador jugador) throws MiExcepcion{
        String agregar = "INSERT INTO MoraBD.jugadores (nombre, email, puntosTotales) VALUES (?,?,?)";
        try {
            Connection conexion = conector.getConnect(); // Conexión a la BD
            PreparedStatement ps = conexion.prepareStatement(agregar, Statement.RETURN_GENERATED_KEYS); // Preparar sentencia para insertar y obtener ID

            // Asignar valores de jugador al PreparedStatement
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getEmail());
            ps.setInt(3, jugador.getPuntosTotales());

            ps.executeUpdate(); // Ejecutar inserción

            ResultSet rs = ps.getGeneratedKeys(); // Obtener ID generado por la BD
            if(rs.next()) {
                jugador.setId(rs.getInt(1)); // Asignar ID al objeto
            }

            logger.info("Jugador añadido: "+ jugador.getNombre()); // Log de información
            this.listaJugadores.add(jugador); // Añadir jugador a la lista en memoria
        } catch (SQLException e) {
            throw new MiExcepcion("Error al añadir jugador: " + e.getMessage()); // Lanzar excepción personalizada
        }
    }

    /*
     * mostrarJugadorConMayorPuntuacion
     * --------------------------------
     * Consulta el jugador con mayor puntuación en la BD.
     * Devuelve un objeto Jugador con los datos completos.
     */
    public Jugador mostrarJugadorConMayorPuntuacion() throws MiExcepcion{
        String mostrarJugador = "SELECT * FROM MoraBD.jugadores ORDER BY puntosTotales desc limit 1 ";
        Jugador jugador = new Jugador();
        try {
            Connection conexion = conector.getConnect(); // Conexión a la BD
            PreparedStatement ps = conexion.prepareStatement(mostrarJugador); // Preparar consulta
            ResultSet rs = ps.executeQuery(); // Ejecutar consulta

            if(rs.next()) { // Si hay resultado, crear objeto Jugador
                jugador.setId(rs.getInt("id"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setEmail(rs.getString("email"));
                jugador.setPuntosTotales(rs.getInt("puntosTotales"));
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error al obtener jugador con mayor puntuación: " + e.getMessage());
        }
        return jugador; // Retorna el jugador con mayor puntuación
    }

    /*
     * mostrarPuntuaciones
     * ------------------
     * Recupera todos los jugadores con su puntuación y los ordena de mayor a menor.
     * Devuelve una lista de jugadores con nombre y puntosTotales.
     */
    public List<Jugador> mostrarPuntuaciones() throws MiExcepcion{
        List<Jugador> listaPuntuacion = new ArrayList<>();
        String puntuacion = "SELECT nombre, puntosTotales from MoraBD.jugadores ORDER BY puntosTotales desc";

        try {
            Connection conexion = conector.getConnect(); // Conexión a la BD
            PreparedStatement ps = conexion.prepareStatement(puntuacion); // Preparar consulta
            ResultSet rs = ps.executeQuery(); // Ejecutar consulta

            // Iterar resultados y crear objetos Jugador
            while(rs.next()) {
                Jugador j = new Jugador();
                j.setNombre(rs.getString("nombre"));
                j.setPuntosTotales(rs.getInt("puntosTotales"));
                listaPuntuacion.add(j); // Añadir a la lista
            }

            logger.info("Listado de puntuaciones obtenido correctamente");

        } catch (Exception e) {
            throw new MiExcepcion("Error al mostrar puntuaciones: " + e.getMessage()); // Lanzar excepción personalizada
        }
        return listaPuntuacion; // Retorna la lista de puntuaciones
    }

    // -------------------------
    // MÉTODOS ADICIONALES DE POSIBLES EXÁMENES
    // -------------------------

    /*
     * Buscar jugador por email
     */
    public Jugador buscarPorEmail(String email) throws MiExcepcion {
        Jugador jugador = null;
        String sql = "SELECT * FROM MoraBD.jugadores WHERE email = ?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                jugador = new Jugador();
                jugador.setId(rs.getInt("id"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setEmail(rs.getString("email"));
                jugador.setPuntosTotales(rs.getInt("puntosTotales"));
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al buscar jugador por email: " + e.getMessage());
        }
        return jugador;
    }

    /*
     * Buscar jugador por nombre (posible lista de jugadores con nombres similares)
     */
    public List<Jugador> buscarPorNombre(String nombre) throws MiExcepcion {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM MoraBD.jugadores WHERE nombre LIKE ?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Jugador j = new Jugador();
                j.setId(rs.getInt("id"));
                j.setNombre(rs.getString("nombre"));
                j.setEmail(rs.getString("email"));
                j.setPuntosTotales(rs.getInt("puntosTotales"));
                lista.add(j);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al buscar jugador por nombre: " + e.getMessage());
        }
        return lista;
    }

    /*
     * Actualizar puntos de un jugador por ID
     */
    public void actualizarPuntos(int idJugador, int puntos) throws MiExcepcion {
        String sql = "UPDATE MoraBD.jugadores SET puntosTotales = ? WHERE id = ?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, puntos);
            ps.setInt(2, idJugador);
            ps.executeUpdate();
        } catch(SQLException e) {
            throw new MiExcepcion("Error al actualizar puntos: " + e.getMessage());
        }
    }

    /*
     * Obtener jugador con menor puntuación
     */
    public Jugador jugadorConMenorPuntuacion() throws MiExcepcion {
        Jugador jugador = new Jugador();
        String sql = "SELECT * FROM MoraBD.jugadores ORDER BY puntosTotales ASC LIMIT 1";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                jugador.setId(rs.getInt("id"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setEmail(rs.getString("email"));
                jugador.setPuntosTotales(rs.getInt("puntosTotales"));
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al obtener jugador con menor puntuación: " + e.getMessage());
        }
        return jugador;
    }

    /*
     * Contar número total de jugadores
     */
    public int contarJugadores() throws MiExcepcion {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM MoraBD.jugadores";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) total = rs.getInt(1);
        } catch(SQLException e) {
            throw new MiExcepcion("Error al contar jugadores: " + e.getMessage());
        }
        return total;
    }

    /*
     * Mostrar jugadores con puntos mayores a un valor dado
     */
    public List<Jugador> jugadoresConPuntosMayoresA(int puntos) throws MiExcepcion {
        List<Jugador> lista = new ArrayList<>();
        String sql = "SELECT * FROM MoraBD.jugadores WHERE puntosTotales > ?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, puntos);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Jugador j = new Jugador();
                j.setId(rs.getInt("id"));
                j.setNombre(rs.getString("nombre"));
                j.setEmail(rs.getString("email"));
                j.setPuntosTotales(rs.getInt("puntosTotales"));
                lista.add(j);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al obtener jugadores con puntos mayores a " + puntos + ": " + e.getMessage());
        }
        return lista;
    }
    // -------------------------
    // CONSULTAS CON JOIN
    // -------------------------

    /*
     * obtenerJugadoresConPartidas
     * ---------------------------
     * Devuelve todos los jugadores junto con el número de partidas en las que han sido narradores.
     * Utiliza LEFT JOIN para incluir jugadores que no hayan sido narradores.
     */
    public List<String> obtenerJugadoresConPartidas() throws MiExcepcion {
        List<String> resultado = new ArrayList<>();
        String sql = "SELECT j.id, j.nombre, j.puntosTotales, COUNT(p.id) as numPartidas " +
                     "FROM MoraBD.jugadores j " +
                     "LEFT JOIN MoraBD.partidas p ON j.id = p.narrador_id " +
                     "GROUP BY j.id, j.nombre, j.puntosTotales " +
                     "ORDER BY numPartidas DESC";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String fila = "Jugador: " + rs.getString("nombre") +
                              ", Puntos: " + rs.getInt("puntosTotales") +
                              ", Partidas como narrador: " + rs.getInt("numPartidas");
                resultado.add(fila);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al obtener jugadores con JOIN: " + e.getMessage());
        }
        return resultado;
    }

    /*
     * obtenerJugadoresConPartidasYResultados
     * --------------------------------------
     * Devuelve todos los jugadores junto con sus partidas y los resultados de cada partida.
     * JOIN entre jugadores y partidas mostrando información completa.
     */
    public List<String> obtenerJugadoresConPartidasYResultados() throws MiExcepcion {
        List<String> resultado = new ArrayList<>();
        String sql = "SELECT j.nombre as jugador, p.id as partidaId, p.resultado, p.fecha " +
                     "FROM MoraBD.jugadores j " +
                     "LEFT JOIN MoraBD.partidas p ON j.id = p.narrador_id " +
                     "ORDER BY j.nombre, p.fecha ASC";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String fila = "Jugador: " + rs.getString("jugador") +
                              ", Partida ID: " + rs.getInt("partidaId") +
                              ", Resultado: " + rs.getString("resultado") +
                              ", Fecha: " + rs.getDate("fecha");
                resultado.add(fila);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al obtener jugadores con partidas y resultados: " + e.getMessage());
        }
        return resultado;
    }

    /*
     * obtenerJugadoresConMayorNumeroDePartidas
     * -----------------------------------------
     * Devuelve jugadores ordenados por el número de partidas como narrador, de mayor a menor.
     */
    public List<String> obtenerJugadoresConMayorNumeroDePartidas() throws MiExcepcion {
        List<String> resultado = new ArrayList<>();
        String sql = "SELECT j.nombre, COUNT(p.id) as numPartidas " +
                     "FROM MoraBD.jugadores j " +
                     "LEFT JOIN MoraBD.partidas p ON j.id = p.narrador_id " +
                     "GROUP BY j.nombre " +
                     "ORDER BY numPartidas DESC";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String fila = "Jugador: " + rs.getString("nombre") +
                              ", Número de partidas: " + rs.getInt("numPartidas");
                resultado.add(fila);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al obtener jugadores con mayor número de partidas: " + e.getMessage());
        }
        return resultado;
    }


}

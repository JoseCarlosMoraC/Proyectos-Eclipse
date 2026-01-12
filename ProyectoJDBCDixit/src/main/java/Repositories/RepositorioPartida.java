package Repositories;

import java.sql.Connection;
import java.sql.Date;
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
import Models.Partida;
import Models.Resultado;

/*
 * RepositorioPartida
 * -----------------
 * Clase encargada de gestionar la tabla de partidas en la base de datos.
 * Permite cargar partidas, agregarlas, contar partidas y actualizar puntuaciones de jugadores según el resultado.
 */
public class RepositorioPartida {

    private static final Logger logger = LogManager.getLogger(RepositorioPartida.class);
    private MySqlConector conector;
    private List<Partida> listaPartidas;

    public MySqlConector getConector() { return conector; }
    public void setConector(MySqlConector conector) { this.conector = conector; }
    public List<Partida> getListaPartidas() { return listaPartidas; }
    public void setListaPartidas(List<Partida> listaPartidas) { this.listaPartidas = listaPartidas; }

    public RepositorioPartida() throws MiExcepcion {
        super();
        this.conector = new MySqlConector();
        this.listaPartidas = cargar();
    }

    private List<Partida> cargar() throws MiExcepcion {
        List<Partida> lista = new ArrayList<>();
        try {
            Connection conexion = conector.getConnect();
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM MoraBD.partidas";
            ResultSet rs = sentencia.executeQuery(sql);

            while (rs.next()){
                Partida p = new Partida();
                p.setId(rs.getInt("id"));
                p.setTorneoId(rs.getInt("torneo_id"));
                int narradorId = rs.getInt("narrador_id");
                Jugador buscarid = new Jugador();
                buscarid.setId(narradorId);
                p.setNarradorId(buscarid);
                Date fechaSql = rs.getDate("fecha");
                if (fechaSql != null) p.setFecha(fechaSql.toLocalDate());
                p.setResultado(Resultado.valueOf(rs.getString("resultado")));
                lista.add(p);
            }
        } catch (SQLException e) {
            logger.error("Error al cargar partidas: " + e.getMessage());
        }
        return lista;
    }

    public int contarPartidas() {
        int totalPartidas = 0;
        String contarPartidas = "SELECT count(*) FROM MoraBD.partidas";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(contarPartidas);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) totalPartidas = rs.getInt(1);
        } catch (SQLException e) {
            logger.error("Error al contar partidas: " + e.getMessage());
        }
        return totalPartidas;
    }

    public void agregarPartido(Partida partida) throws MiExcepcion {
        if (contarPartidas() >= 5) {
            throw new MiExcepcion("No se pueden añadir más de 5 partidas");
        }
        String agregar = "INSERT INTO MoraBD.partidas (torneo_id,narrador_id,fecha,resultado) VALUES (?,?,?,?)";
        try {
            Connection connection = conector.getConnect();
            PreparedStatement ps = connection.prepareStatement(agregar);
            ps.setInt(1, partida.getTorneoId());
            ps.setInt(2, partida.getNarradorId().getId());
            ps.setDate(3, Date.valueOf(partida.getFecha()));
            ps.setString(4, partida.getResultado().name());
            ps.executeUpdate();
            this.listaPartidas.add(partida);
        } catch (SQLException e) {
            throw new MiExcepcion("Error al añadir partida: " + e.getMessage());
        }
    }

    public void actualizarPuntuacionNarrador(int idJugador, Resultado resultado) throws MiExcepcion {
        String actualizarPuntos = "UPDATE MoraBD.jugadores SET puntosTotales = puntosTotales + 3 WHERE id = ?";
        try {
            Connection connection = conector.getConnect();
            PreparedStatement ps = connection.prepareStatement(actualizarPuntos);
            if (resultado.equals(Resultado.ALGUNOS)) {
                ps.setInt(1, idJugador);
                ps.executeUpdate();
            }
            logger.info("Puntuación narrador actualizada correctamente");
        } catch (Exception e) {
            logger.error("Error SQL al actualizar la puntuación: " + e.getMessage());
            throw new MiExcepcion("Error al actualizar la puntuación del jugador " + idJugador + ": " + e.getMessage());
        }
        this.listaPartidas = cargar();
    }

    public void actualizarPuntuacionNOAcertante(int idJugador, Resultado resultado) throws MiExcepcion {
        String actualizarPuntos = "UPDATE MoraBD.jugadores SET puntosTotales = puntosTotales + 2 WHERE id = ?";
        try {
            Connection connection = conector.getConnect();
            PreparedStatement ps = connection.prepareStatement(actualizarPuntos);
            if (resultado.equals(Resultado.TODOS) || resultado.equals(Resultado.NADIE)) {
                ps.setInt(1, idJugador);
                ps.executeUpdate();
            }
            logger.info("Puntuación No acertante actualizada correctamente");
            this.listaPartidas = cargar();
        } catch (Exception e) {
            logger.error("Error SQL al actualizar la puntuación: " + e.getMessage());
            throw new MiExcepcion("Error al actualizar la puntuación del jugador " + idJugador + ": " + e.getMessage());
        }
    }

    public void actualizarPuntuacionAcertante(int idJugador, Resultado resultado) throws MiExcepcion {
        String sql;
        if (resultado == Resultado.TODOS || resultado == Resultado.NADIE) {
            sql = "UPDATE MoraBD.jugadores SET puntosTotales = puntosTotales + 2 WHERE id = ?";
        } else {
            sql = "UPDATE MoraBD.jugadores SET puntosTotales = puntosTotales + 3 WHERE id = ?";
        }
        try {
            Connection connection = conector.getConnect();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idJugador);
            ps.executeUpdate();
            logger.info("Puntuación acertante actualizada");
        } catch (SQLException e) {
            throw new MiExcepcion("Error al actualizar puntuación acertante");
        }
    }

    public List<Partida> mostrarPartidas() throws MiExcepcion {
        List<Partida> listaPartidas = new ArrayList<>();
        String partidasPorFecha = "SELECT * FROM MoraBD.partidas order by fecha asc";
        try {
            Connection connection = conector.getConnect();
            PreparedStatement ps = connection.prepareStatement(partidasPorFecha);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Partida p = new Partida();
                p.setId(rs.getInt("id"));
                p.setTorneoId(rs.getInt("torneo_id"));
                int narradorId = rs.getInt("narrador_id");
                Jugador buscarid = new Jugador();
                buscarid.setId(narradorId);
                p.setNarradorId(buscarid);
                p.setFecha(rs.getDate("fecha").toLocalDate());
                p.setResultado(Resultado.valueOf(rs.getString("resultado")));
                listaPartidas.add(p);
            }
        } catch (Exception e) {
            throw new MiExcepcion("Error al mostrar partidas: " + e.getMessage());
        }
        return listaPartidas;
    }

    // -------------------------
    // MÉTODOS ADICIONALES DE POSIBLES EXÁMENES
    // -------------------------

    // Buscar partidas por torneo
    public List<Partida> buscarPorTorneo(int torneoId) throws MiExcepcion {
        List<Partida> lista = new ArrayList<>();
        String sql = "SELECT * FROM MoraBD.partidas WHERE torneo_id = ?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, torneoId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Partida p = new Partida();
                p.setId(rs.getInt("id"));
                p.setTorneoId(rs.getInt("torneo_id"));
                Jugador narrador = new Jugador();
                narrador.setId(rs.getInt("narrador_id"));
                p.setNarradorId(narrador);
                p.setFecha(rs.getDate("fecha").toLocalDate());
                p.setResultado(Resultado.valueOf(rs.getString("resultado")));
                lista.add(p);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al buscar partidas por torneo: " + e.getMessage());
        }
        return lista;
    }

    // Buscar partidas por narrador
    public List<Partida> buscarPorNarrador(int narradorId) throws MiExcepcion {
        List<Partida> lista = new ArrayList<>();
        String sql = "SELECT * FROM MoraBD.partidas WHERE narrador_id = ?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, narradorId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Partida p = new Partida();
                p.setId(rs.getInt("id"));
                p.setTorneoId(rs.getInt("torneo_id"));
                Jugador narrador = new Jugador();
                narrador.setId(rs.getInt("narrador_id"));
                p.setNarradorId(narrador);
                p.setFecha(rs.getDate("fecha").toLocalDate());
                p.setResultado(Resultado.valueOf(rs.getString("resultado")));
                lista.add(p);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al buscar partidas por narrador: " + e.getMessage());
        }
        return lista;
    }

    // Buscar partidas por resultado
    public List<Partida> buscarPorResultado(Resultado resultado) throws MiExcepcion {
        List<Partida> lista = new ArrayList<>();
        String sql = "SELECT * FROM MoraBD.partidas WHERE resultado = ?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, resultado.name());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Partida p = new Partida();
                p.setId(rs.getInt("id"));
                p.setTorneoId(rs.getInt("torneo_id"));
                Jugador narrador = new Jugador();
                narrador.setId(rs.getInt("narrador_id"));
                p.setNarradorId(narrador);
                p.setFecha(rs.getDate("fecha").toLocalDate());
                p.setResultado(Resultado.valueOf(rs.getString("resultado")));
                lista.add(p);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al buscar partidas por resultado: " + e.getMessage());
        }
        return lista;
    }

    // Buscar partidas por fecha específica
    public List<Partida> buscarPorFecha(Date fecha) throws MiExcepcion {
        List<Partida> lista = new ArrayList<>();
        String sql = "SELECT * FROM MoraBD.partidas WHERE fecha = ?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setDate(1, fecha);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Partida p = new Partida();
                p.setId(rs.getInt("id"));
                p.setTorneoId(rs.getInt("torneo_id"));
                Jugador narrador = new Jugador();
                narrador.setId(rs.getInt("narrador_id"));
                p.setNarradorId(narrador);
                p.setFecha(rs.getDate("fecha").toLocalDate());
                p.setResultado(Resultado.valueOf(rs.getString("resultado")));
                lista.add(p);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al buscar partidas por fecha: " + e.getMessage());
        }
        return lista;
    }

    // Buscar partidas por rango de fechas
    public List<Partida> buscarPorRangoFechas(Date fechaInicio, Date fechaFin) throws MiExcepcion {
        List<Partida> lista = new ArrayList<>();
        String sql = "SELECT * FROM MoraBD.partidas WHERE fecha BETWEEN ? AND ?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setDate(1, fechaInicio);
            ps.setDate(2, fechaFin);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Partida p = new Partida();
                p.setId(rs.getInt("id"));
                p.setTorneoId(rs.getInt("torneo_id"));
                Jugador narrador = new Jugador();
                narrador.setId(rs.getInt("narrador_id"));
                p.setNarradorId(narrador);
                p.setFecha(rs.getDate("fecha").toLocalDate());
                p.setResultado(Resultado.valueOf(rs.getString("resultado")));
                lista.add(p);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al buscar partidas por rango de fechas: " + e.getMessage());
        }
        return lista;
    }

    // Contar partidas por resultado
    public int contarPorResultado(Resultado resultado) throws MiExcepcion {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM MoraBD.partidas WHERE resultado = ?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, resultado.name());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) total = rs.getInt(1);
        } catch(SQLException e) {
            throw new MiExcepcion("Error al contar partidas por resultado: " + e.getMessage());
        }
        return total;
    }

    // Contar partidas por torneo
    public int contarPorTorneo(int torneoId) throws MiExcepcion {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM MoraBD.partidas WHERE torneo_id = ?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, torneoId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) total = rs.getInt(1);
        } catch(SQLException e) {
            throw new MiExcepcion("Error al contar partidas por torneo: " + e.getMessage());
        }
        return total;
    }
    // -------------------------
    // CONSULTAS CON JOIN
    // -------------------------

    /*
     * mostrarPartidasConNarrador
     * --------------------------
     * Devuelve todas las partidas incluyendo el nombre del narrador.
     * Utiliza JOIN entre partidas y jugadores.
     */
    public List<String> mostrarPartidasConNarrador() throws MiExcepcion {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT p.id as partidaId, p.fecha, p.resultado, j.nombre as narrador " +
                     "FROM MoraBD.partidas p " +
                     "JOIN MoraBD.jugadores j ON p.narrador_id = j.id " +
                     "ORDER BY p.fecha ASC";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String fila = "Partida ID: " + rs.getInt("partidaId") +
                              ", Fecha: " + rs.getDate("fecha") +
                              ", Resultado: " + rs.getString("resultado") +
                              ", Narrador: " + rs.getString("narrador");
                lista.add(fila);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al mostrar partidas con narrador: " + e.getMessage());
        }
        return lista;
    }

    /*
     * mostrarPartidasConDetalles
     * --------------------------
     * Devuelve partidas con información completa del narrador y puntos del narrador.
     */
    public List<String> mostrarPartidasConDetalles() throws MiExcepcion {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT p.id as partidaId, p.fecha, p.resultado, j.nombre as narrador, j.puntosTotales " +
                     "FROM MoraBD.partidas p " +
                     "JOIN MoraBD.jugadores j ON p.narrador_id = j.id " +
                     "ORDER BY p.fecha ASC";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String fila = "Partida ID: " + rs.getInt("partidaId") +
                              ", Fecha: " + rs.getDate("fecha") +
                              ", Resultado: " + rs.getString("resultado") +
                              ", Narrador: " + rs.getString("narrador") +
                              ", Puntos narrador: " + rs.getInt("puntosTotales");
                lista.add(fila);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al mostrar partidas con detalles: " + e.getMessage());
        }
        return lista;
    }

    /*
     * contarPartidasPorResultadoConJOIN
     * ---------------------------------
     * Devuelve el número de partidas por cada resultado junto con la suma de puntos de los narradores.
     */
    public List<String> contarPartidasPorResultadoConJOIN() throws MiExcepcion {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT p.resultado, COUNT(p.id) as numPartidas, SUM(j.puntosTotales) as totalPuntosNarradores " +
                     "FROM MoraBD.partidas p " +
                     "JOIN MoraBD.jugadores j ON p.narrador_id = j.id " +
                     "GROUP BY p.resultado";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String fila = "Resultado: " + rs.getString("resultado") +
                              ", Número de partidas: " + rs.getInt("numPartidas") +
                              ", Total puntos de narradores: " + rs.getInt("totalPuntosNarradores");
                lista.add(fila);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al contar partidas por resultado con JOIN: " + e.getMessage());
        }
        return lista;
    }

    /*
     * mostrarPartidasConNarradorYRangoFechas
     * --------------------------------------
     * Devuelve partidas con nombre del narrador filtrando por rango de fechas.
     */
    public List<String> mostrarPartidasConNarradorYRangoFechas(java.sql.Date fechaInicio, java.sql.Date fechaFin) throws MiExcepcion {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT p.id as partidaId, p.fecha, p.resultado, j.nombre as narrador " +
                     "FROM MoraBD.partidas p " +
                     "JOIN MoraBD.jugadores j ON p.narrador_id = j.id " +
                     "WHERE p.fecha BETWEEN ? AND ? " +
                     "ORDER BY p.fecha ASC";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setDate(1, fechaInicio);
            ps.setDate(2, fechaFin);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                String fila = "Partida ID: " + rs.getInt("partidaId") +
                              ", Fecha: " + rs.getDate("fecha") +
                              ", Resultado: " + rs.getString("resultado") +
                              ", Narrador: " + rs.getString("narrador");
                lista.add(fila);
            }
        } catch(SQLException e) {
            throw new MiExcepcion("Error al mostrar partidas con narrador y rango de fechas: " + e.getMessage());
        }
        return lista;
    }

}

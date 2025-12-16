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
                if (fechaSql != null) {
                    p.setFecha(fechaSql.toLocalDate());
                }
                p.setResultado(Resultado.valueOf(rs.getString("resultado")));
                lista.add(p);
            }
        } catch (SQLException e) {
            // TODO: handle exception
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

            if (rs.next()) {
                totalPartidas = rs.getInt(1);
            }

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
}

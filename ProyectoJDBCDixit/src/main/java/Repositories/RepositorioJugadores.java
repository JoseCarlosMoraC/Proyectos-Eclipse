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

public class RepositorioJugadores {

    private static final Logger logger = LogManager.getLogger(RepositorioJugadores.class);

    private MySqlConector conector;
    private List<Jugador> listaJugadores;

    public RepositorioJugadores() throws MiExcepcion {
        super();
        this.conector = new MySqlConector();
        this.listaJugadores = cargar();
    }

    public MySqlConector getConector() {
        return conector;
    }

    public void setConector(MySqlConector conector) {
        this.conector = conector;
    }

    public List<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    private List<Jugador> cargar() throws MiExcepcion {
        List<Jugador> lista = new ArrayList<>();
        try {
            Connection conexion = conector.getConnect();
            Statement sentencia = conexion.createStatement();
            String sql = "SELECT * FROM MoraBD.jugadores";
            ResultSet resultado = sentencia.executeQuery(sql);
            while(resultado.next()) {
                Jugador jugador = new Jugador();
                jugador.setId(resultado.getInt("id"));
                jugador.setNombre(resultado.getString("nombre"));
                jugador.setEmail(resultado.getString("email"));
                jugador.setPuntosTotales(resultado.getInt("puntosTotales"));
                lista.add(jugador);
            }
        } catch (SQLException e) {
            // TODO: handle exception
        }
        return lista;
    }

    public void agregarJugador(Jugador jugador) throws MiExcepcion{
        String agregar = "INSERT INTO MoraBD.jugadores (nombre, email, puntosTotales) VALUES (?,?,?)";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(agregar, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, jugador.getNombre());
            ps.setString(2, jugador.getEmail());
            ps.setInt(3, jugador.getPuntosTotales());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                jugador.setId(rs.getInt(1));
            }
            logger.info("Jugador añadido: "+ jugador.getNombre());
            this.listaJugadores.add(jugador);
        } catch (SQLException e) {
            throw new MiExcepcion("Error al añadir jugador: " + e.getMessage());
        }
    }

    public Jugador mostrarJugadorConMayorPuntuacion() throws MiExcepcion{
        String mostrarJugador = "SELECT * FROM MoraBD.jugadores ORDER BY puntosTotales desc limit 1 ";
        Jugador jugador = new Jugador();
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(mostrarJugador);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                jugador.setId(rs.getInt("id"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setEmail(rs.getString("email"));
                jugador.setPuntosTotales(rs.getInt("puntosTotales"));
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error bb: " + e.getMessage());
        }
        return jugador;
    }

    public List<Jugador> mostrarPuntuaciones() throws MiExcepcion{
        List<Jugador> listaPuntuacion = new ArrayList<>();
        String puntuacion = "SELECT nombre, puntosTotales from MoraBD.jugadores ORDER BY puntosTotales desc";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(puntuacion);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Jugador j = new Jugador();
                j.setNombre(rs.getString("nombre"));
                j.setPuntosTotales(rs.getInt("puntosTotales"));
                listaPuntuacion.add(j);
            }
            logger.info("Jugador con mayor puntuacion: "+ listaPuntuacion.toString());
        } catch (Exception e) {
            throw new MiExcepcion("Error: " + e.getMessage());
        }
        return listaPuntuacion;
    }
}

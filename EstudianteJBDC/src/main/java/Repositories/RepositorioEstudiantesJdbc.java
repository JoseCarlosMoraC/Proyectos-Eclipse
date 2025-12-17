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
import Models.Estudiante;

public class RepositorioEstudiantesJdbc {

    private static final Logger logger = LogManager.getLogger(RepositorioEstudiantesJdbc.class);

    private MySqlConector conector;
    private List<Estudiante> listaEstudiantes;

    public RepositorioEstudiantesJdbc() throws MiExcepcion {
        super();
        this.conector = new MySqlConector();
        this.listaEstudiantes = cargar();
    }

    // ===== GETTERS / SETTERS =====
    public MySqlConector getConector() {
        return conector;
    }

    public void setConector(MySqlConector conector) {
        this.conector = conector;
    }

    public List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    // ===== CARGAR =====
    private List<Estudiante> cargar() throws MiExcepcion {
        List<Estudiante> lista = new ArrayList<>();
        try {
            Connection conexion = conector.getConnect();
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM estudiantes";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Estudiante e = new Estudiante();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setNotaMedia(rs.getDouble("nota_media"));
                lista.add(e);
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error cargando estudiantes: " + e.getMessage());
        }
        return lista;
    }

    // ===== CRUD =====
    public void agregarEstudiante(Estudiante e) throws MiExcepcion {
        String sql = "INSERT INTO estudiantes (nombre, nota_media) VALUES (?,?)";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, e.getNombre());
            ps.setDouble(2, e.getNotaMedia());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                e.setId(rs.getInt(1));
            }

            listaEstudiantes.add(e);
            logger.info("Estudiante añadido: " + e.getNombre());

        } catch (SQLException ex) {
            throw new MiExcepcion("Error al añadir estudiante: " + ex.getMessage());
        }
    }

    // ===== CONSULTAS =====

    // notaMedia < ?
    public List<Estudiante> estudiantesNotaMenor(double nota) throws MiExcepcion {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes WHERE nota_media < ?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setDouble(1, nota);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Estudiante e = new Estudiante();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setNotaMedia(rs.getDouble("nota_media"));
                lista.add(e);
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error consulta nota menor: " + e.getMessage());
        }
        return lista;
    }

    // estudiantes por ciudad (DESC)
    public List<Estudiante> estudiantesPorCiudad(String ciudad) throws MiExcepcion {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT e.id, e.nombre, e.nota_media "
                   + "FROM estudiantes e "
                   + "JOIN direcciones d ON e.id = d.estudiante_id "
                   + "WHERE d.ciudad = ? "
                   + "ORDER BY e.nombre DESC";

        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, ciudad);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Estudiante e = new Estudiante();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setNotaMedia(rs.getDouble("nota_media"));
                lista.add(e);
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error consulta ciudad: " + e.getMessage());
        }
        return lista;
    }

    // media scores por estudiante
    public List<String> mediaScoresPorEstudiante() throws MiExcepcion {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT e.nombre, AVG(s.valor) AS media "
                   + "FROM estudiantes e "
                   + "JOIN scores s ON e.id = s.estudiante_id "
                   + "GROUP BY e.nombre";

        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("nombre") + " -> media: " + rs.getDouble("media"));
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error media scores: " + e.getMessage());
        }
        return lista;
    }

    // numero de scores por tipo
    public List<String> numeroScoresPorTipo() throws MiExcepcion {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT tipo, COUNT(*) AS total FROM scores GROUP BY tipo";

        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(rs.getString("tipo") + " -> " + rs.getInt("total"));
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error numero scores: " + e.getMessage());
        }
        return lista;
    }
}

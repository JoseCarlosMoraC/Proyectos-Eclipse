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
import Models.Direccion;
import Models.Estudiante;
import Models.Score;

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
    public MySqlConector getConector() { return conector; }
    public void setConector(MySqlConector conector) { this.conector = conector; }
    public List<Estudiante> getListaEstudiantes() { return listaEstudiantes; }
    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) { this.listaEstudiantes = listaEstudiantes; }

    // ===== CARGAR ESTUDIANTES =====
    private List<Estudiante> cargar() throws MiExcepcion {
        List<Estudiante> lista = new ArrayList<>();
        try {
            Connection conexion = conector.getConnect();
            Statement st = conexion.createStatement();

            // SELECT * FROM estudiantes: trae todos los estudiantes
            // Se usa cuando necesitamos todos los datos sin filtro
            String sql = "SELECT * FROM estudiantes";

            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Estudiante e = new Estudiante();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setEmail(rs.getString("email"));
                e.setNotaMedia(rs.getDouble("nota_media"));
                lista.add(e);
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error cargando estudiantes: " + e.getMessage());
        }
        return lista;
    }

    // ===== CRUD ESTUDIANTE =====
    public void agregarEstudiante(Estudiante e) throws MiExcepcion {
        // INSERT INTO: agrega un nuevo estudiante a la base de datos
        String sql = "INSERT INTO estudiantes (nombre, email, nota_media) VALUES (?,?,?)";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getEmail());
            ps.setDouble(3, e.getNotaMedia());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                e.setId(rs.getInt(1)); // Obtener el ID generado automáticamente
            }

            listaEstudiantes.add(e);
            logger.info("Estudiante añadido: " + e.getNombre());
        } catch (SQLException ex) {
            throw new MiExcepcion("Error al añadir estudiante: " + ex.getMessage());
        }
    }

    public void actualizarEstudiante(Estudiante e) throws MiExcepcion {
        // UPDATE: modifica datos de un estudiante existente
        // WHERE id=? limita la actualización al estudiante indicado
        String sql = "UPDATE estudiantes SET nombre=?, email=?, nota_media=? WHERE id=?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getEmail());
            ps.setDouble(3, e.getNotaMedia());
            ps.setInt(4, e.getId());
            ps.executeUpdate();
            logger.info("Estudiante actualizado: " + e.getNombre());
        } catch (SQLException ex) {
            throw new MiExcepcion("Error al actualizar estudiante: " + ex.getMessage());
        }
    }

    public void eliminarEstudiante(int id) throws MiExcepcion {
        // DELETE FROM: elimina un estudiante por su ID
        String sql = "DELETE FROM estudiantes WHERE id=?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Estudiante eliminado con ID: " + id);
        } catch (SQLException ex) {
            throw new MiExcepcion("Error al eliminar estudiante: " + ex.getMessage());
        }
    }

    public Estudiante getEstudiantePorId(int id) throws MiExcepcion {
        // SELECT * WHERE id=? busca un estudiante específico
        String sql = "SELECT * FROM estudiantes WHERE id=?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Estudiante e = new Estudiante();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setEmail(rs.getString("email"));
                e.setNotaMedia(rs.getDouble("nota_media"));
                return e;
            }
        } catch (SQLException ex) {
            throw new MiExcepcion("Error al buscar estudiante por ID: " + ex.getMessage());
        }
        return null;
    }

    // ===== CONSULTAS AVANZADAS =====

    // Trae estudiantes cuya nota media sea menor que un valor dado
    public List<Estudiante> estudiantesNotaMenor(double nota) throws MiExcepcion {
        List<Estudiante> lista = new ArrayList<>();
        // WHERE nota_media < ? sirve para filtrar por condición
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
                e.setEmail(rs.getString("email"));
                e.setNotaMedia(rs.getDouble("nota_media"));
                lista.add(e);
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error consulta nota menor: " + e.getMessage());
        }
        return lista;
    }

    // Trae estudiantes que viven en una ciudad específica
    public List<Estudiante> estudiantesPorCiudad(String ciudad) throws MiExcepcion {
        List<Estudiante> lista = new ArrayList<>();
        // JOIN estudiantes + direcciones: para combinar tablas relacionadas
        // WHERE filtra por ciudad
        // ORDER BY ordena los resultados por nombre descendente
        String sql = "SELECT e.id, e.nombre, e.email, e.nota_media "
                   + "FROM estudiantes e "
                   + "JOIN direcciones d ON e.id = d.estudiante_id "
                   + "WHERE d.ciudad=? "
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
                e.setEmail(rs.getString("email"));
                e.setNotaMedia(rs.getDouble("nota_media"));
                lista.add(e);
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error consulta ciudad: " + e.getMessage());
        }
        return lista;
    }

    // Calcula la media de los scores por estudiante
    public List<String> mediaScoresPorEstudiante() throws MiExcepcion {
        List<String> lista = new ArrayList<>();
        // AVG(s.puntuacion) = función agregada para calcular promedio
        // JOIN estudiantes + scores = relaciona cada estudiante con sus scores
        // GROUP BY e.nombre = agrupa los resultados por estudiante
        String sql = "SELECT e.nombre, AVG(s.puntuacion) AS media "
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

    // Cuenta el número de scores por tipo
    public List<String> numeroScoresPorTipo() throws MiExcepcion {
        List<String> lista = new ArrayList<>();
        // COUNT(*) = cuenta registros
        // GROUP BY tipo = agrupa resultados por tipo de score
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

    // ===== MÉTODOS EXTRA COMPLETOS =====

    // Ordenar estudiantes por nombre ascendente
    public List<Estudiante> ordenarPorNombreAsc() throws MiExcepcion {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes ORDER BY nombre ASC"; // ORDER BY ASC = ascendente
        try (Connection conexion = conector.getConnect();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Estudiante e = new Estudiante();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setEmail(rs.getString("email"));
                e.setNotaMedia(rs.getDouble("nota_media"));
                lista.add(e);
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error ordenando por nombre asc: " + e.getMessage());
        }
        return lista;
    }

    // Ordenar estudiantes por nota descendente
    public List<Estudiante> ordenarPorNotaDesc() throws MiExcepcion {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes ORDER BY nota_media DESC"; // DESC = descendente
        try (Connection conexion = conector.getConnect();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Estudiante e = new Estudiante();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setEmail(rs.getString("email"));
                e.setNotaMedia(rs.getDouble("nota_media"));
                lista.add(e);
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error ordenando por nota descendente: " + e.getMessage());
        }
        return lista;
    }

    // Incrementar nota de estudiante
    public void incrementarNota(int id, double incremento) throws MiExcepcion {
        // UPDATE estudiantes SET nota_media = nota_media + ? WHERE id=? incrementa la nota
        String sql = "UPDATE estudiantes SET nota_media = nota_media + ? WHERE id=?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setDouble(1, incremento);
            ps.setInt(2, id);
            ps.executeUpdate();
            logger.info("Incrementada nota estudiante ID " + id + " en " + incremento);
        } catch (SQLException e) {
            throw new MiExcepcion("Error al incrementar nota: " + e.getMessage());
        }
    }

    // Buscar estudiantes por nombre parcial
    public List<Estudiante> buscarEstudiantesPorNombre(String nombre) throws MiExcepcion {
        List<Estudiante> lista = new ArrayList<>();
        // LIKE ? con %nombre% permite buscar coincidencias parciales
        String sql = "SELECT * FROM estudiantes WHERE nombre LIKE ?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Estudiante e = new Estudiante();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setEmail(rs.getString("email"));
                e.setNotaMedia(rs.getDouble("nota_media"));
                lista.add(e);
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error buscando estudiantes por nombre: " + e.getMessage());
        }
        return lista;
    }

    // ===== MÉTODOS DIRECCION =====

    // Agregar dirección
    public void agregarDireccion(Direccion d) throws MiExcepcion {
        String sql = "INSERT INTO direcciones (calle, ciudad, estudiante_id) VALUES (?,?,?)";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, d.getCalle());
            ps.setString(2, d.getCiudad());
            ps.setInt(3, d.getEstudianteId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                d.setId(rs.getInt(1));
            }
            logger.info("Dirección añadida para estudiante ID " + d.getEstudianteId());
        } catch (SQLException ex) {
            throw new MiExcepcion("Error al añadir dirección: " + ex.getMessage());
        }
    }

    // Listar direcciones de un estudiante
    public List<Direccion> listarDirecciones(int estudianteId) throws MiExcepcion {
        List<Direccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM direcciones WHERE estudiante_id=?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, estudianteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Direccion d = new Direccion();
                d.setId(rs.getInt("id"));
                d.setCalle(rs.getString("calle"));
                d.setCiudad(rs.getString("ciudad"));
                d.setEstudianteId(rs.getInt("estudiante_id"));
                lista.add(d);
            }
        } catch (SQLException ex) {
            throw new MiExcepcion("Error al listar direcciones: " + ex.getMessage());
        }
        return lista;
    }

    // ===== MÉTODOS SCORE =====

    // Agregar score
    public void agregarScore(Score s) throws MiExcepcion {
        String sql = "INSERT INTO scores (tipo, puntuacion, estudiante_id) VALUES (?,?,?)";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, s.getTipo());
            ps.setDouble(2, s.getPuntuacion());
            ps.setInt(3, s.getEstudianteId());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                s.setId(rs.getInt(1));
            }
            logger.info("Score añadido para estudiante ID " + s.getEstudianteId());
        } catch (SQLException ex) {
            throw new MiExcepcion("Error al añadir score: " + ex.getMessage());
        }
    }

    // Listar scores de un estudiante
    public List<Score> listarScores(int estudianteId) throws MiExcepcion {
        List<Score> lista = new ArrayList<>();
        String sql = "SELECT * FROM scores WHERE estudiante_id=?";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, estudianteId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Score s = new Score();
                s.setId(rs.getInt("id"));
                s.setTipo(rs.getString("tipo"));
                s.setPuntuacion(rs.getDouble("puntuacion"));
                s.setEstudianteId(rs.getInt("estudiante_id"));
                lista.add(s);
            }
        } catch (SQLException ex) {
            throw new MiExcepcion("Error al listar scores: " + ex.getMessage());
        }
        return lista;
    }

}

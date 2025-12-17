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
import Models.Dispositivo;

public class RepositorioDispositivosJdbc {

    private static final Logger logger =
            LogManager.getLogger(RepositorioDispositivosJdbc.class);

    private MySqlConector conector;
    private List<Dispositivo> listaDispositivos;

    public RepositorioDispositivosJdbc() throws MiExcepcion {
        super();
        this.conector = new MySqlConector();
        this.listaDispositivos = cargar();
    }

    public MySqlConector getConector() {
        return conector;
    }

    public void setConector(MySqlConector conector) {
        this.conector = conector;
    }

    public List<Dispositivo> getListaDispositivos() {
        return listaDispositivos;
    }

    public void setListaDispositivos(List<Dispositivo> listaDispositivos) {
        this.listaDispositivos = listaDispositivos;
    }

    /* =======================
       CARGAR
       ======================= */
    private List<Dispositivo> cargar() throws MiExcepcion {
        List<Dispositivo> lista = new ArrayList<>();
        try {
            Connection conexion = conector.getConnect();
            Statement st = conexion.createStatement();
            String sql = "SELECT * FROM dispositivos";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Dispositivo d = new Dispositivo();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                d.setCategoria(rs.getString("categoria"));
                d.setPrecio(rs.getDouble("precio"));
                d.setStock(rs.getInt("stock"));
                lista.add(d);
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error al cargar dispositivos");
        }
        return lista;
    }

    /* =======================
       CRUD
       ======================= */
    public void agregarDispositivo(Dispositivo d) throws MiExcepcion {
        String sql =
            "INSERT INTO dispositivos (nombre,categoria,precio,stock) VALUES (?,?,?,?)";
        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps =
                conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, d.getNombre());
            ps.setString(2, d.getCategoria());
            ps.setDouble(3, d.getPrecio());
            ps.setInt(4, d.getStock());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                d.setId(rs.getInt(1));
            }

            this.listaDispositivos.add(d);
            logger.info("Dispositivo añadido: " + d.getNombre());

        } catch (SQLException e) {
            throw new MiExcepcion("Error al añadir dispositivo");
        }
    }

    /* =======================
       CONSULTAS ENUNCIADO
       ======================= */

    public List<Dispositivo> dispositivosPorCategoria(String categoria)
            throws MiExcepcion {

        List<Dispositivo> lista = new ArrayList<>();
        String sql = "SELECT * FROM dispositivos WHERE categoria=?";

        try {
            Connection conexion = conector.getConnect();
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, categoria);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Dispositivo d = new Dispositivo();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                d.setCategoria(rs.getString("categoria"));
                d.setPrecio(rs.getDouble("precio"));
                d.setStock(rs.getInt("stock"));
                lista.add(d);
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error por categoria");
        }
        return lista;
    }

    public double stockTotal() throws MiExcepcion {
        String sql = "SELECT SUM(stock) total FROM dispositivos";
        double total = 0;

        try {
            Connection conexion = conector.getConnect();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error stock total");
        }
        return total;
    }

    public List<Dispositivo> ordenarPorPrecioDesc() throws MiExcepcion {
        List<Dispositivo> lista = new ArrayList<>();
        String sql = "SELECT * FROM dispositivos ORDER BY precio DESC";

        try {
            Connection conexion = conector.getConnect();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Dispositivo d = new Dispositivo();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                d.setCategoria(rs.getString("categoria"));
                d.setPrecio(rs.getDouble("precio"));
                d.setStock(rs.getInt("stock"));
                lista.add(d);
            }
        } catch (SQLException e) {
            throw new MiExcepcion("Error ordenar dispositivos");
        }
        return lista;
    }
    /* =======================
    MEDIA VALORACIONES
    ======================= */
 public List<String> puntuacionMediaPorDispositivo() throws MiExcepcion {

     List<String> lista = new ArrayList<>();

     String sql =
         "SELECT d.nombre, AVG(v.puntuacion) AS media " +
         "FROM dispositivos d " +
         "JOIN valoraciones v ON d.id = v.dispositivo_id " +
         "GROUP BY d.nombre";

     try {
         Connection conexion = conector.getConnect();
         PreparedStatement ps = conexion.prepareStatement(sql);
         ResultSet rs = ps.executeQuery();

         while (rs.next()) {
             String resultado =
                 rs.getString("nombre") + " -> media: " + rs.getDouble("media");
             lista.add(resultado);
         }

     } catch (SQLException e) {
         throw new MiExcepcion("Error media valoraciones");
     }

     return lista;
 }

}

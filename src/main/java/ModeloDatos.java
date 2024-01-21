import java.sql.*;
import java.util.logging.Logger;

public class ModeloDatos {

    private static final Logger logger = Logger.getLogger(ModeloDatos.class.getName());

    private Connection con;
    private Statement set;
    private ResultSet rs;

    private static final String ERROR = "El error es: ";

    public void abrirConexion() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Con variables de entorno
            String dbHost = System.getenv("DATABASE_HOST") == null ? System.getProperty("DATABASE_HOST") : System.getenv("DATABASE_HOST");
            String dbPort = System.getenv("DATABASE_PORT") == null ? System.getProperty("DATABASE_PORT") : System.getenv("DATABASE_PORT");
            String dbName = System.getenv("DATABASE_NAME") == null ? System.getProperty("DATABASE_NAME") : System.getenv("DATABASE_NAME");
            String dbUser = System.getenv("DATABASE_USER") == null ? System.getProperty("DATABASE_USER") : System.getenv("DATABASE_USER");
            String dbPass = System.getenv("DATABASE_PASS") == null ? System.getProperty("DATABASE_PASS") : System.getenv("DATABASE_PASS");

            String url = dbHost + ":" + dbPort + "/" + dbName;
            con = DriverManager.getConnection(url, dbUser, dbPass);

        } catch (Exception e) {
            // No se ha conectado
            logger.severe("No se ha podido conectar");
            logger.severe(ERROR + e.getMessage());
        }
    }

    public boolean existeJugador(String nombre) {
        boolean existe = false;
        String cad;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Jugadores");
            while (rs.next()) {
                cad = rs.getString("Nombre");
                cad = cad.trim();
                if (cad.compareTo(nombre.trim()) == 0) {
                    existe = true;
                }
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            // No lee de la tabla
            logger.warning("No lee de la tabla");
            logger.warning(ERROR + e.getMessage());
        }
        return (existe);
    }

    public void actualizarJugador(String nombre) {
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Jugadores SET votos=votos+1 WHERE nombre " + " LIKE '%" + nombre + "%'");
            rs.close();
            set.close();
        } catch (Exception e) {
            // No modifica la tabla
            logger.warning("No modifica la tabla");
            logger.warning(ERROR + e.getMessage());
        }
    }

    public void insertarJugador(String nombre) {
        try {
            set = con.createStatement();
            set.executeUpdate("INSERT INTO Jugadores " + " (nombre,votos) VALUES ('" + nombre + "',1)");
            rs.close();
            set.close();
        } catch (Exception e) {
            // No inserta en la tabla
            logger.warning("No inserta en la tabla");
            logger.warning(ERROR + e.getMessage());
        }
    }

    public void reiniciarVotos(){
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Jugadores SET votos=0");
            rs.close();
            set.close();
        } catch (Exception e) {
            // No modifica la tabla
            logger.warning("No modifica la tabla");
            logger.warning(ERROR + e.getMessage());
        }
    }

    public int getVotosJugador(String nombre) {
        int votos = 0;
        try {
            set = con.createStatement();
            rs = set.executeQuery("SELECT votos FROM Jugadores WHERE nombre " + " LIKE '%" + nombre + "%'");
            while (rs.next()) {
                votos = rs.getInt("votos");
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            // No lee de la tabla
            logger.warning("No lee de la tabla");
            logger.warning(ERROR + e.getMessage());
        }
        return (votos);
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }

}

package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ModeloDatos {

    private static final Logger log = LogManager.getLogger(ModeloDatos.class);

    private Connection con;
    private Statement set;
    private ResultSet rs;

    public void abrirConexion() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Con variables de entorno
            String dbHost = System.getenv().get("DATABASE_HOST");
            String dbPort = System.getenv().get("DATABASE_PORT");
            String dbName = System.getenv().get("DATABASE_NAME");
            String dbUser = System.getenv().get("DATABASE_USER");
            String dbPass = System.getenv().get("DATABASE_PASS");

            String url = dbHost + ":" + dbPort + "/" + dbName;
            con = DriverManager.getConnection(url, dbUser, dbPass);

        } catch (Exception e) {
            // No se ha conectado
            log.info("No se ha podido conectar");
            log.info("El error es: " + e.getMessage());
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
            log.info("No lee de la tabla");
            log.info("El error es: " + e.getMessage());
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
            log.info("No modifica la tabla");
            log.info("El error es: " + e.getMessage());
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
            log.info("No inserta en la tabla");
            log.info("El error es: " + e.getMessage());
        }
    }

    public void cerrarConexion() {
        try {
            con.close();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public boolean votosACero() {
        boolean indicador = false;
        try {
            set = con.createStatement();
            set.executeUpdate("UPDATE Jugadores SET votos = 0;");
            indicador = true;
            set.close();
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
        }
        return indicador;
    }


    public List<Jugador> getJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
        try {
            abrirConexion();
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Jugadores;");
            while (rs.next()) {
                Jugador player = new Jugador();
                player.setId(rs.getInt("id"));
                player.setNombre(rs.getString("nombre"));
                player.setTotalVotos(rs.getInt("votos"));
                jugadores.add(player);
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
        }
        return jugadores;
    }

    public Jugador getJugador(String nombreJugador) {
        Jugador jugador = new Jugador();
        try {
            abrirConexion();
            set = con.createStatement();
            rs = set.executeQuery("SELECT * FROM Jugadores WHERE nombre='" + nombreJugador + "';");
            while (rs.next()) {
                jugador.setId(rs.getInt("id"));
                jugador.setNombre(rs.getString("nombre"));
                jugador.setTotalVotos(rs.getInt("votos"));
            }
            rs.close();
            set.close();
        } catch (Exception e) {
            log.info("Error " + e.getMessage());
        }

        return jugador;
    }

}

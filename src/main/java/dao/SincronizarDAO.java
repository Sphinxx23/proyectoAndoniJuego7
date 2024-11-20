/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexJSON.ConexJSON;
import static conexJSON.ConexJSON.consultarJson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author andon
 */
public class SincronizarDAO {

    VideojuegoDAOPostgre videojuegoDAOPostgre;
    JugadorDAOPostgreeSQL jugadorDAOPostgreeSQL;

    VideojuegoDAOMySQL videojuegoDAOMySQL = new VideojuegoDAOMySQL();
    JugadorDAOMySQL jugadorDAOMySQL = new JugadorDAOMySQL();

    VideojuegoDAOSQLite videojuegoDAOSQLite = new VideojuegoDAOSQLite();
    JugadorDAOSQLite jugadorDAOSQLite = new JugadorDAOSQLite();
    int servidor;
    private static final String URLSQLITE = ConexJSON.consultarJson("sqlite");
    private static final String URLP = consultarJson("postgres");
    private static final String URLM = consultarJson("mysql");

    public boolean sincronizarBasesNubeALocal() throws SQLException, ClassNotFoundException {
        List<VideojuegoDAOPostgre> listaJuegosPostgre = videojuegoDAOPostgre.obtenerJuegosObjeto();
        List<JugadorDAOPostgreeSQL> listaJugadoresPostgre = jugadorDAOPostgreeSQL.obtenerJugadoresObjeto();

        List<VideojuegoDAOMySQL> listaJuegosMySQL = videojuegoDAOMySQL.obtenerJuegosObjeto();
        List<JugadorDAOMySQL> listaJugadoresMySQL = jugadorDAOMySQL.obtenerJugadoresObjeto();

        try {
            actualizarVideojuegosDePostgre(listaJuegosPostgre);
            actualizarJugadoresDePostgre(listaJugadoresPostgre);

            Class.forName("com.mysql.cj.jdbc.Driver");
            actualizarVideojuegosDeMySQL(listaJuegosMySQL);
            actualizarJugadoresDeMySQL(listaJugadoresMySQL);
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    private void actualizarVideojuegosDePostgre(List<VideojuegoDAOPostgre> listaJuegosPostgre) throws SQLException {
        if (listaJuegosPostgre.isEmpty()) {
            return; // O manejar de otra manera si la lista está vacía
        }

        String consulta = "UPDATE videojuego SET player_count = ?, total_sessions = ?, last_session = ? WHERE isbn = ? AND BD = 1";
        try (Connection conexion = DriverManager.getConnection(URLSQLITE); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            for (VideojuegoDAOPostgre videojuego : listaJuegosPostgre) {
                try {
                    statement.setInt(1, videojuego.getPlayer_count());
                    statement.setInt(2, videojuego.getSession_count());
                    statement.setString(3, videojuego.getLast_session());
                    statement.setString(4, videojuego.getIsbn());

                    statement.executeUpdate();
                } catch (SQLException e) {
                    // Si ocurre un error con un videojuego específico, lo manejamos y seguimos con el siguiente
                    //System.out.println("Error al actualizar el videojuego con ISBN " + videojuego.getIsbn() + ": " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // O usar un logger para registrar el error en la conexión general
        }
    }

    private void actualizarJugadoresDePostgre(List<JugadorDAOPostgreeSQL> listaJugadoresPostgre) {
        if (listaJugadoresPostgre.isEmpty()) {
            return; // O manejar de otra manera si la lista está vacía
        }

        String consulta = "UPDATE jugador SET nick_name = ? ,experience = ?, life_level = ?, coins = ?, session_count = ?, last_login = ? WHERE user_id = ? AND BD = 1";
        try (Connection conexion = DriverManager.getConnection(URLSQLITE); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            for (JugadorDAOPostgreeSQL jugador : listaJugadoresPostgre) {
                try {
                    statement.setString(1, jugador.getNickName());
                    statement.setInt(2, jugador.getExperience());
                    statement.setInt(3, jugador.getLifeLevel());
                    statement.setInt(4, jugador.getCoins());
                    statement.setInt(5, jugador.getSession_count());
                    statement.setString(6, jugador.getLast_login());
                    statement.setInt(7, jugador.getUser_id());

                    statement.executeUpdate();
                } catch (SQLException e) {
                    // Si ocurre un error con un videojuego específico, lo manejamos y seguimos con el siguiente
                    //System.out.println("Error al actualizar el jugador con id " + jugador.getUser_id()+ ": " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // O usar un logger para registrar el error en la conexión general
        }
    }

    private void actualizarVideojuegosDeMySQL(List<VideojuegoDAOMySQL> listaJuegosMySQL) {
        if (listaJuegosMySQL.isEmpty()) {
            return; // O manejar de otra manera si la lista está vacía
        }

        String consulta = "UPDATE videojuego SET player_count = ?, total_sessions = ?, last_session = ? WHERE isbn = ? AND BD = 2";
        try (Connection conexion = DriverManager.getConnection(URLSQLITE); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            for (VideojuegoDAOMySQL videojuego : listaJuegosMySQL) {
                try {
                    statement.setInt(1, videojuego.getPlayer_count());
                    statement.setInt(2, videojuego.getSession_count());
                    statement.setString(3, videojuego.getLast_session());
                    statement.setString(4, videojuego.getIsbn());

                    statement.executeUpdate();
                } catch (SQLException e) {
                    // Si ocurre un error con un videojuego específico, lo manejamos y seguimos con el siguiente
                    //System.out.println("Error al actualizar el videojuego con ISBN " + videojuego.getIsbn() + ": " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // O usar un logger para registrar el error en la conexión general
        }
    }

    private void actualizarJugadoresDeMySQL(List<JugadorDAOMySQL> listaJugadoresMySQL) {
        if (listaJugadoresMySQL == null || listaJugadoresMySQL.isEmpty()) {
            return; // O manejar de otra manera si la lista está vacía
        }

        String consulta = "UPDATE jugador SET nick_name = ? ,experience = ?, life_level = ?, coins = ?, session_count = ?, last_login = ? WHERE user_id = ? AND BD = 2";
        try (Connection conexion = DriverManager.getConnection(URLSQLITE); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            for (JugadorDAOMySQL jugador : listaJugadoresMySQL) {
                try {
                    statement.setString(1, jugador.getNickName());
                    statement.setInt(2, jugador.getExperience());
                    statement.setInt(3, jugador.getLifeLevel());
                    statement.setInt(4, jugador.getCoins());
                    statement.setInt(5, jugador.getSession_count());
                    statement.setString(6, jugador.getLast_login());
                    statement.setInt(7, jugador.getUser_id());

                    statement.executeUpdate();
                } catch (SQLException e) {
                    // Si ocurre un error con un videojuego específico, lo manejamos y seguimos con el siguiente
                    //System.out.println("Error al actualizar el jugador con id " + jugador.getUser_id()+ ": " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // O usar un logger para registrar el error en la conexión general
        }
    }

    public boolean sincronizarLocalANube() {
        List<VideojuegoDAOSQLite> listaJuegoSQLite = videojuegoDAOSQLite.obtenerJuegosObjeto();
        List<JugadorDAOSQLite> listJugadoresSQLite = jugadorDAOSQLite.obtenerJugadoresObjeto();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            actualizarJuegosNube(listaJuegoSQLite);
            actualizarJugadoresNube(listJugadoresSQLite);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void actualizarJuegosNube(List<VideojuegoDAOSQLite> listaJuegoSQLite) {
        if (listaJuegoSQLite.isEmpty()) {
            return; // O manejar de otra manera si la lista está vacía
        }

        String consulta = "UPDATE videojuego SET player_count = ?, total_sessions = ?, last_session = ? WHERE isbn = ? AND title = ?";

        actualizarJuegosPostgre(consulta, listaJuegoSQLite);

        actualizarJuegosMySQL(consulta, listaJuegoSQLite);
    }

    public void actualizarJuegosPostgre(String consulta, List<VideojuegoDAOSQLite> listaJuegoSQLite) {
        try (Connection conexion = DriverManager.getConnection(URLP); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            for (VideojuegoDAOSQLite videojuego : listaJuegoSQLite) {
                try {
                    statement.setInt(1, videojuego.getPlayer_count());
                    statement.setInt(2, videojuego.getSession_count());
                    statement.setString(3, videojuego.getLast_session());
                    statement.setString(4, videojuego.getIsbn());
                    statement.setString(5, videojuego.getNombreJuego());

                    statement.executeUpdate();
                } catch (SQLException e) {
                    // Si ocurre un error con un videojuego específico, lo manejamos y seguimos con el siguiente
                    //System.out.println("Error al actualizar el videojuego con ISBN " + videojuego.getIsbn() + ": " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // O usar un logger para registrar el error en la conexión general
        }
    }

    public void actualizarJuegosMySQL(String consulta, List<VideojuegoDAOSQLite> listaJuegoSQLite) {
        try (Connection conexion = DriverManager.getConnection(URLM); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            for (VideojuegoDAOSQLite videojuego : listaJuegoSQLite) {
                try {
                    statement.setInt(1, videojuego.getPlayer_count());
                    statement.setInt(2, videojuego.getSession_count());
                    statement.setString(3, videojuego.getLast_session());
                    statement.setString(4, videojuego.getIsbn());
                    statement.setString(5, videojuego.getNombreJuego());

                    statement.executeUpdate();
                } catch (SQLException e) {
                    // Si ocurre un error con un videojuego específico, lo manejamos y seguimos con el siguiente
                    //System.out.println("Error al actualizar el videojuego con ISBN " + videojuego.getIsbn() + ": " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // O usar un logger para registrar el error en la conexión general
        }
    }

    private void actualizarJugadoresNube(List<JugadorDAOSQLite> listJugadoresSQLite) {
        if (listJugadoresSQLite.isEmpty()) {
            return; // O manejar de otra manera si la lista está vacía
        }

        String consulta = "UPDATE jugador SET experience = ?, life_level = ?, coins = ?, session_count = ?, last_login = ? WHERE user_id = ? AND nick_name = ?";

        actualizarJugadoresNubePostgre(consulta, listJugadoresSQLite);

        actualizarJugadoresNubeMySQL(consulta, listJugadoresSQLite);
    }

    public void actualizarJugadoresNubePostgre(String consulta, List<JugadorDAOSQLite> listJugadoresSQLite) {
        try (Connection conexion = DriverManager.getConnection(URLP); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            for (JugadorDAOSQLite jugador : listJugadoresSQLite) {
                try {
                    statement.setInt(1, jugador.getExperience());
                    statement.setInt(2, jugador.getLife_level());
                    statement.setInt(3, jugador.getCoins());
                    statement.setInt(4, jugador.getSession_count());
                    statement.setString(5, jugador.getLast_login());
                    statement.setInt(6, jugador.getUser_id());
                    statement.setString(7, jugador.getNick_name());

                    statement.executeUpdate();
                } catch (SQLException e) {
                    // Si ocurre un error con un videojuego específico, lo manejamos y seguimos con el siguiente
                    //System.out.println("Error al actualizar el jugador con id " + jugador.getUser_id()+ ": " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // O usar un logger para registrar el error en la conexión general
        }
    }

    private void actualizarJugadoresNubeMySQL(String consulta, List<JugadorDAOSQLite> listJugadoresSQLite) {
        try (Connection conexion = DriverManager.getConnection(URLM); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            for (JugadorDAOSQLite jugador : listJugadoresSQLite) {
                try {
                    statement.setInt(1, jugador.getExperience());
                    statement.setInt(2, jugador.getLife_level());
                    statement.setInt(3, jugador.getCoins());
                    statement.setInt(4, jugador.getSession_count());
                    statement.setString(5, jugador.getLast_login());
                    statement.setInt(6, jugador.getUser_id());
                    statement.setString(7, jugador.getNick_name());

                    statement.executeUpdate();
                } catch (SQLException e) {
                    // Si ocurre un error con un videojuego específico, lo manejamos y seguimos con el siguiente
                    //System.out.println("Error al actualizar el jugador con id " + jugador.getUser_id()+ ": " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // O usar un logger para registrar el error en la conexión general
        }
    }

}

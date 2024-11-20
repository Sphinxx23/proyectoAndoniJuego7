/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexJSON.ConexJSON;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andon
 */
public class VideojuegoDAOSQLite {

    private String isbn, nombreJuego, last_session;
    private int player_count, session_count, bd;
    private static final String URL = ConexJSON.consultarJson("sqlite");

    public VideojuegoDAOSQLite(String isbn, String nombreJuego, String last_session, int player_count, int session_count, int bd) {
        this.isbn = isbn;
        this.nombreJuego = nombreJuego;
        this.last_session = last_session;
        this.player_count = player_count;
        this.session_count = session_count;
        this.bd = bd;
    }

    public VideojuegoDAOSQLite(String isbn, String nombreJuego) {
        this.isbn = isbn;
        this.nombreJuego = nombreJuego;
    }

    public VideojuegoDAOSQLite() {
        this("", "");
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    public String getLast_session() {
        return last_session;
    }

    public void setLast_session(String last_session) {
        this.last_session = last_session;
    }

    public int getPlayer_count() {
        return player_count;
    }

    public void setPlayer_count(int player_count) {
        this.player_count = player_count;
    }

    public int getSession_count() {
        return session_count;
    }

    public void setSession_count(int session_count) {
        this.session_count = session_count;
    }

    public int getBd() {
        return bd;
    }

    public void setBd(int bd) {
        this.bd = bd;
    }

    public static List<String> obtenerJuegos(int idJugador, String nombreJugador) {
        List<String> listaJugadores = new LinkedList();

        JugadorDAOSQLite jugador = obtenerJugador(idJugador, nombreJugador);

        String consulta = "SELECT isbn, title FROM videojuego where BD = ?";

        try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            statement.setInt(1, jugador.getBd());
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                String isbn = resultado.getString("isbn");
                String nombreJuego = resultado.getString("title");

                VideojuegoDAOMySQL juego = new VideojuegoDAOMySQL(isbn, nombreJuego);
                
                String lineaJuego = juego.toString();

                listaJugadores.add(lineaJuego);
            }

            if (listaJugadores.isEmpty()) {
                return null;
            } else {
                return listaJugadores;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static JugadorDAOSQLite obtenerJugador(int idJugador, String nombreJugador) {

        String consulta = "SELECT * FROM jugador where user_id = ? AND nick_name = ?";

        try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            statement.setInt(1, idJugador);
            statement.setString(2, nombreJugador);

            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                int user_id = resultado.getInt("user_id");
                String nickName = resultado.getString("nick_name");
                int experience = resultado.getInt("experience");
                int lifeLevel = resultado.getInt("life_level");
                int coins = resultado.getInt("coins");
                int session_count = resultado.getInt("session_count");
                String last_login = resultado.getString("last_login");
                int BD = resultado.getInt("BD");

                JugadorDAOSQLite jugador = new JugadorDAOSQLite(user_id, experience, lifeLevel, coins, session_count, last_login, nickName, BD);
                return jugador;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-12s", isbn, nombreJuego);
    }

    public boolean actualizarVidejuego(String isbnJuego, String nombreJuego, int idJugador, String nombreJugador) {
        LocalDateTime last_session = LocalDateTime.now();

        try {
            int playCoun;
            playCoun = consultaPlayerCount(isbnJuego, nombreJuego, idJugador, nombreJugador);

            if (playCoun != 1) {
                String sql = "UPDATE videojuego SET total_sessions = total_sessions + 1, last_session = ? WHERE isbn = ? AND title = ?";

                try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(sql)) {

                    statement.setString(1, last_session.toString());
                    statement.setString(2, isbnJuego);
                    statement.setString(3, nombreJuego);
                    statement.executeUpdate();

                    return true;
                } catch (SQLException e) {
                    return false;
                }
            }

            if (playCoun == 1) {
                String sql = "UPDATE videojuego SET player_count = player_count + 1,total_sessions = total_sessions + 1, last_session = ? WHERE isbn = ?";

                try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(sql)) {

                    statement.setString(1, last_session.toString());
                    statement.setString(2, isbnJuego);
                    statement.executeUpdate();

                    return true;
                } catch (SQLException e) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(VideojuegoDAOPostgre.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }

    private int consultaPlayerCount(String isbn, String nombreJuego, int idJugador, String nombreJugador) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM partida WHERE isbn = ? AND title = ? AND user_id = ? AND nick_name = ?";

        try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, isbn);
            statement.setString(2, nombreJuego);
            statement.setInt(3, idJugador);
            statement.setString(4, nombreJugador);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("count"); // Devuelve el número de filas coincidentes
            } else {
                return 1; // Si no hay resultado, devuelve 1
            }

        }

    }

    public boolean comprobarJuego(String isbnJuego, String nombreJuego) {
        String query = "SELECT COUNT(*) FROM videojuego WHERE isbn = ? AND title = ?";

        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement stmt = conn.prepareStatement(query)) {

            // Establecer el parámetro en la consulta
            stmt.setString(1, isbnJuego);
            stmt.setString(2, nombreJuego);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Verificar si el jugador existe
            if (rs.next()) {
                int count = rs.getInt(1);  // Obtener el resultado de la consulta
                return count > 0;  // Si el conteo es mayor que 0, el jugador existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean comprobarIDJugador(int idJugadorJuego) {
        // Consulta SQL para verificar si existe un jugador con el nombre proporcionado
        String query = "SELECT COUNT(*) FROM jugador WHERE user_id = ?";

        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement stmt = conn.prepareStatement(query)) {

            // Establecer el parámetro en la consulta
            stmt.setInt(1, idJugadorJuego);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Verificar si el jugador existe
            if (rs.next()) {
                int count = rs.getInt(1);  // Obtener el resultado de la consulta
                return count > 0;  // Si el conteo es mayor que 0, el jugador existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // El jugador no existe
    }

    public List<VideojuegoDAOSQLite> obtenerJuegosObjeto() {
        List<VideojuegoDAOSQLite> listaJuegos = new LinkedList();

        String consulta = "SELECT * FROM videojuego";

        try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(consulta); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                String isbn = resultado.getString("isbn");
                String nombreJuego = resultado.getString("title");
                int player_count = resultado.getInt("player_count");
                int session_count = resultado.getInt("total_sessions");
                String last_session = resultado.getString("last_session");
                int bd = resultado.getInt("BD");

                VideojuegoDAOSQLite videojuego = new VideojuegoDAOSQLite(isbn, nombreJuego, last_session, player_count, session_count, bd);

                listaJuegos.add(videojuego);
            }

            return listaJuegos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}

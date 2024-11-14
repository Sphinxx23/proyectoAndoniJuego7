/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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

    private String isbn, nombreJuego;
    private static final String URL = "jdbc:sqlite:G:\\2º Superior\\Acceso a datos\\SQLite\\datosLocales.db";

    public VideojuegoDAOSQLite(String isbn, String nombreJuego) {
        this.isbn = isbn;
        this.nombreJuego = nombreJuego;
    }

    public VideojuegoDAOSQLite() {
        this("", "");
    }
    
    public static List<String> obtenerJuegos() {
        List<String> listaJugadores = new LinkedList();

        String consulta = "SELECT isbn, title FROM videojuegos";

        try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(consulta); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                String isbn = resultado.getString("isbn");
                String nombreJuego = resultado.getString("title");

                VideojuegoDAOSQLite videojuego = new VideojuegoDAOSQLite(isbn, nombreJuego);

                String lineaJuego = videojuego.toString();

                listaJugadores.add(lineaJuego);
            }

            if (listaJugadores.size() == 0) {
                return null;
            }else{
                return listaJugadores;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("%-15s %-12s", isbn, nombreJuego);
    }

    public boolean actualizarVidejuego(String isbnJuego, int idJugador) {
        LocalDateTime last_session = LocalDateTime.now();

        try {
            int playCoun;
            playCoun = consultaPlayerCount(isbnJuego, idJugador);

            if (playCoun != 1) {
                String sql = "UPDATE videojuegos SET total_sessions = total_sessions + 1, last_session = ? WHERE isbn = ?";

                try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(sql)) {

                    statement.setString(1, last_session.toString());
                    statement.setString(2, isbnJuego);
                    statement.executeUpdate();

                    return true;
                } catch (SQLException e) {
                    return false;
                }
            }

            if (playCoun == 1) {
                String sql = "UPDATE videojuegos SET player_count = player_count + 1,total_sessions = total_sessions + 1, last_session = ? WHERE isbn = ?";

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

    private int consultaPlayerCount(String isbn, int idJugador) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM partidasJugadas WHERE isbn = ? AND player_id = ?";

        try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, isbn);
            statement.setInt(2, idJugador);

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("count"); // Devuelve el número de filas coincidentes
            } else {
                return 1; // Si no hay resultado, devuelve 1
            }

        }

    }

    public boolean comprobarJuego(String isbnJuego) {
        String query = "SELECT COUNT(*) FROM videojuegos WHERE isbn = ?";

        try (Connection conn = DriverManager.getConnection(URL); PreparedStatement stmt = conn.prepareStatement(query)) {

            // Establecer el parámetro en la consulta
            stmt.setString(1, isbnJuego);

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
}

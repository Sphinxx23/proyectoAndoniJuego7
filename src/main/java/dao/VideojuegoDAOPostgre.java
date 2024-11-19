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
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andon
 */
public class VideojuegoDAOPostgre {

    private String isbn, nombreJuego, last_session;
    private int player_count, session_count;
    private static final String URL = "jdbc:postgresql://ep-broad-union-a29uia00.eu-central-1.aws.neon.tech:5432/proyectoJuego?sslmode=require";
    private static final String USER = "proyectoJuego_owner";
    private static final String PASSWORD = "eb4xsQc0ENkU";

    public VideojuegoDAOPostgre(String isbn, String nombreJuego, int player_count, int session_count, String last_session) {
        this.isbn = isbn;
        this.nombreJuego = nombreJuego;
        this.player_count = player_count;
        this.session_count = session_count;
        this.last_session = last_session;
    }

    public VideojuegoDAOPostgre(String isbn, String nombreJuego) {
        this.isbn = isbn;
        this.nombreJuego = nombreJuego;
    }

    public VideojuegoDAOPostgre() {
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

    public static List<VideojuegoDAOPostgre> obtenerJuegosObjeto() {
        List<VideojuegoDAOPostgre> listaJuegos = new LinkedList();

        String consulta = "SELECT * FROM videojuego";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = conexion.prepareStatement(consulta); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                String isbn = resultado.getString("isbn");
                String nombreJuego = resultado.getString("title");
                int player_count = resultado.getInt("player_count");
                int session_count = resultado.getInt("total_sessions");
                String last_session = resultado.getString("last_session");

                VideojuegoDAOPostgre videojuego = new VideojuegoDAOPostgre(isbn, nombreJuego, player_count, session_count, last_session);

                listaJuegos.add(videojuego);
            }

            return listaJuegos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> obtenerJuegosString() {
        List<String> listaJugadores = new LinkedList();

        String consulta = "SELECT isbn, title FROM videojuego";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = conexion.prepareStatement(consulta); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                String isbn = resultado.getString("isbn");
                String nombreJuego = resultado.getString("title");

                VideojuegoDAOPostgre videojuego = new VideojuegoDAOPostgre(isbn, nombreJuego);

                String lineaJuego = videojuego.toString();

                listaJugadores.add(lineaJuego);
            }

            return listaJugadores;
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
                String sql = "UPDATE videojuego SET total_sessions = total_sessions + 1, last_session = ? WHERE isbn = ?";

                try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = conexion.prepareStatement(sql)) {

                    statement.setString(1, last_session.toString());
                    statement.setString(2, isbnJuego);
                    statement.executeUpdate();

                    return true;
                } catch (SQLException e) {
                    return false;
                }
            }

            if (playCoun == 1) {
                String sql = "UPDATE videojuego SET player_count = player_count + 1,total_sessions = total_sessions + 1, last_session = ? WHERE isbn = ?";

                try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = conexion.prepareStatement(sql)) {

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
        String sql = "SELECT COUNT(*) AS count FROM partida WHERE isbn = ? AND user_id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = conexion.prepareStatement(sql)) {

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
        String query = "SELECT COUNT(*) FROM videojuego WHERE isbn = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(query)) {

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

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(query)) {

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

    public boolean comprobarJuegoSinDescargar(String isbnJuego) {
        String urlSQLite = "jdbc:sqlite:G:\\2º Superior\\Acceso a datos\\SQLite\\datosLocales.db";

        try (Connection conn = DriverManager.getConnection(urlSQLite)) {
            conn.setAutoCommit(false); // Desactiva el auto-commit para usar transacciones

            if (isJuegoDescargado(conn, isbnJuego)) {
                return false; // El juego ya está descargado
            }

            VideojuegoDAOPostgre juegoPost = obtenerJuegoISBN(isbnJuego);
            if (juegoPost != null) {
                descargarJuego(conn, isbnJuego, juegoPost);
                conn.commit(); // Confirmar la transacción
                return true;
            } else {
                System.out.println("Título no encontrado para el ISBN: " + isbnJuego);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private boolean isJuegoDescargado(Connection conn, String isbnJuego) throws SQLException {
        String query = "SELECT COUNT(*) FROM videojuegos WHERE isbn = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, isbnJuego);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    private void descargarJuego(Connection conn, String isbnJuego, VideojuegoDAOPostgre juegoPost) throws SQLException {
        String insertQuery = "INSERT INTO videojuegos (isbn, title, player_count, total_sessions, last_session) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setString(1, juegoPost.getIsbn());
            stmt.setString(2, juegoPost.getNombreJuego());
            stmt.setInt(3, juegoPost.getPlayer_count()); // player_count inicial
            stmt.setInt(4, juegoPost.getSession_count()); // total_sessions inicial
            stmt.setString(5, juegoPost.getLast_session()); // last_session inicial
            stmt.executeUpdate();
            System.out.println("Juego descargado e insertado en la base de datos.");
        }
    }

    private String obtenerTituloJuego(String isbnJuego) {
        String consultaTitulo = "SELECT title FROM videojuego WHERE isbn = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement stmt = conn.prepareStatement(consultaTitulo)) {

            stmt.setString(1, isbnJuego);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("title"); // Obtener el título
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private VideojuegoDAOPostgre obtenerJuegoISBN(String isbnJuego) {

        String consulta = "SELECT * FROM videojuego WHERE isbn = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            statement.setString(1, isbnJuego);

            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                isbn = resultado.getString("isbn");
                nombreJuego = resultado.getString("title");
                player_count = resultado.getInt("player_count");
                session_count = resultado.getInt("total_sessions");
                last_session = resultado.getString("last_session");
            }
            
            VideojuegoDAOPostgre videojuego = new VideojuegoDAOPostgre(isbn, nombreJuego, player_count, session_count, last_session);
            return videojuego;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}

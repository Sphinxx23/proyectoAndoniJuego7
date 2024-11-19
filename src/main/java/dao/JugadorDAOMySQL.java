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

/**
 *
 * @author andon
 */
public class JugadorDAOMySQL {

    private int user_id, experience, lifeLevel, coins, session_count;
    private String nickName, last_login;
    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    public JugadorDAOMySQL(int user_id, int experience, int lifeLevel, int coins, int session_count, String nickName, String last_login) {
        this.user_id = user_id;
        this.experience = experience;
        this.lifeLevel = lifeLevel;
        this.coins = coins;
        this.session_count = session_count;
        this.nickName = nickName;
        this.last_login = last_login;
    }

    public JugadorDAOMySQL(int user_id, int experience, int lifeLevel, int coins, String nickName) {
        this.user_id = user_id;
        this.experience = experience;
        this.lifeLevel = lifeLevel;
        this.coins = coins;
        this.nickName = nickName;
    }

    public JugadorDAOMySQL() {
        this(0, 0, 0, 0, "");
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLifeLevel() {
        return lifeLevel;
    }

    public void setLifeLevel(int lifeLevel) {
        this.lifeLevel = lifeLevel;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSession_count() {
        return session_count;
    }

    public void setSession_count(int session_count) {
        this.session_count = session_count;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public static void main(String[] args) {
        String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_proyectoJueguito";
        String user = "freedb_Andoni";
        String password = "P9F#&y63X9PD#hQ";

        String sql = "SELECT * FROM videojuego";
        try (Connection con = DriverManager.getConnection(url, user, password); PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("isbn"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static List<String> obtenerJugadores() {
        List<String> listaJugadores = new LinkedList<>();

        String consulta = "SELECT user_id, nick_name, experience, life_level, coins FROM jugador ORDER BY user_id";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = conexion.prepareStatement(consulta); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                int user_id = resultado.getInt("user_id");
                String nickName = resultado.getString("nick_name");
                int experience = resultado.getInt("experience");
                int lifeLevel = resultado.getInt("life_level");
                int coins = resultado.getInt("coins");

                // Cambia esta clase si tienes una específica para MySQL
                JugadorDAOMySQL jugadorMySQL = new JugadorDAOMySQL(user_id, experience, lifeLevel, coins, nickName);

                String lineaJugador = jugadorMySQL.toString();

                listaJugadores.add(lineaJugador);
            }

            return listaJugadores;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return String.format("%-6d%-15s %-12d %-15d %-6d", user_id, nickName, experience, lifeLevel, coins);
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

    public boolean actualizarDatosJugador(JugadorDAOMySQL jugadorDAOMySQL) {
        // Valores a actualizar
        int experienceN = jugadorDAOMySQL.getExperience();
        int lifeLevelN = jugadorDAOMySQL.getLifeLevel();
        int coinsN = jugadorDAOMySQL.getCoins();
        int idJugador = jugadorDAOMySQL.getUser_id();
        LocalDateTime lastLogin = LocalDateTime.now();

        String consulta = "UPDATE jugador SET experience = experience + ?, life_level = life_level + ?, "
                + "coins = coins + ?, session_count = session_count + 1, last_login = ? WHERE user_id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            // Establecer los valores en el PreparedStatement
            statement.setInt(1, experienceN);
            statement.setInt(2, lifeLevelN);
            statement.setInt(3, coinsN);
            statement.setString(4, lastLogin.toString());
            statement.setInt(5, idJugador);

            // Ejecutar la consulta de actualización
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0; // Devuelve true si se actualizó alguna fila

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Devuelve false si ocurre algún error
        }
    }

    public List<JugadorDAOMySQL> obtenerJugadoresObjeto() {
        List<JugadorDAOMySQL> listaJugadores = new LinkedList();

        String consulta = "SELECT * FROM jugador";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = conexion.prepareStatement(consulta); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                int user_id = resultado.getInt("user_id");
                String nickName = resultado.getString("nick_name");
                int experience = resultado.getInt("experience");
                int lifeLevel = resultado.getInt("life_level");
                int coins = resultado.getInt("coins");
                int session_count = resultado.getInt("session_count");
                String last_login = resultado.getString("last_login");

                JugadorDAOMySQL jugador = new JugadorDAOMySQL(user_id, experience, lifeLevel, coins, session_count, nickName, last_login);

                listaJugadores.add(jugador);
            }

            return listaJugadores;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean comprobarJugadorDescargado(int idJugadorJuego) {
        String urlSQLite = "jdbc:sqlite:G:\\2º Superior\\Acceso a datos\\SQLite\\datosLocales.db";

        try (Connection conn = DriverManager.getConnection(urlSQLite)) {
            conn.setAutoCommit(false); // Desactiva el auto-commit para usar transacciones

            if (isJugadorDescargado(conn, idJugadorJuego)) {
                return false; // El juego ya está descargado
            }

            JugadorDAOPostgreeSQL jugador = obtenerJugadorID(idJugadorJuego);
            if (jugador != null) {
                descargarJugador(conn, jugador);
                conn.commit(); // Confirmar la transacción
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isJugadorDescargado(Connection conn, int idJugadorJuego) throws SQLException {
        String query = "SELECT COUNT(*) FROM jugador WHERE player_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idJugadorJuego);
            ResultSet rs = stmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    private JugadorDAOPostgreeSQL obtenerJugadorID(int idJugadorJuego) {
        String consulta = "SELECT * FROM jugador where user_id = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            statement.setInt(1, idJugadorJuego);

            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                user_id = resultado.getInt("user_id");
                nickName = resultado.getString("nick_name");
                experience = resultado.getInt("experience");
                lifeLevel = resultado.getInt("life_level");
                coins = resultado.getInt("coins");
                session_count = resultado.getInt("session_count");
                last_login = resultado.getString("last_login");
            }

            JugadorDAOPostgreeSQL jugadorPostgree = new JugadorDAOPostgreeSQL(user_id, experience, lifeLevel, coins, session_count, nickName, last_login);
            return jugadorPostgree;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void descargarJugador(Connection conn, JugadorDAOPostgreeSQL jugador) throws SQLException {
        String insertQuery = "INSERT INTO jugador (player_id, nick_name, experience, life_level, coins, session_count, last_login) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setInt(1, jugador.getUser_id());
            stmt.setString(2, jugador.getNickName());
            stmt.setInt(3, jugador.getExperience()); // player_count inicial
            stmt.setInt(4, jugador.getLifeLevel()); // total_sessions inicial
            stmt.setInt(5, jugador.getCoins()); // last_session inicial
            stmt.setInt(6, jugador.getSession_count()); // last_session inicial
            stmt.setString(7, jugador.getLast_login()); // last_session inicial
            stmt.executeUpdate();
            System.out.println("Juego descargado e insertado en la base de datos.");
        }
    }

}

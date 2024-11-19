/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author andon
 */
public class JugadorDAOSQLite {

    private String URL = "jdbc:sqlite:H:\\2º Superior\\Acceso a datos\\SQLite\\datosLocales.db";
    private int player_id, experience, life_level, coins, session_count;
    private String last_login, nick_name;

    public JugadorDAOSQLite(int player_id, String nick_name, int experience, int life_level, int coins) {
        this.player_id = player_id;
        this.nick_name = nick_name;
        this.experience = experience;
        this.life_level = life_level;
        this.coins = coins;
        this.last_login = LocalDateTime.now().toString();
    }

    public JugadorDAOSQLite(int player_id, int experience, int life_level, int coins, int session_count, String last_login, String nick_name) {
        this.player_id = player_id;
        this.experience = experience;
        this.life_level = life_level;
        this.coins = coins;
        this.session_count = session_count;
        this.last_login = last_login;
        this.nick_name = nick_name;
    }

    public JugadorDAOSQLite() {
        this(0, "", 0, 0, 0);
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLife_level() {
        return life_level;
    }

    public void setLife_level(int life_level) {
        this.life_level = life_level;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public int getSession_count() {
        return session_count;
    }

    public void setSession_count(int session_count) {
        this.session_count = session_count;
    }

    public boolean comprobarJugador(int idJugador, String nombreJugador) {
        String sql = "SELECT COUNT(*) FROM jugador WHERE player_id = ? AND nick_name = ?";
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idJugador);
            pstmt.setString(2, nombreJugador);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<String> obtenerJugadores() {
        List<String> listaJugadores = new LinkedList();

        String consulta = "SELECT player_id, nick_name, experience, life_level, coins FROM jugador ORDER BY player_id";

        try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(consulta); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                player_id = resultado.getInt("player_id");
                nick_name = resultado.getString("nick_name");
                experience = resultado.getInt("experience");
                life_level = resultado.getInt("life_level");
                coins = resultado.getInt("coins");
                last_login = LocalDateTime.now().toString();

                JugadorDAOSQLite jugadorSQLite = new JugadorDAOSQLite(player_id, nick_name, experience, life_level, coins);

                String lineaJugador = jugadorSQLite.toString();

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
        return String.format("%-6d%-15s %-12d %-15d %-6d", player_id, nick_name, experience, life_level, coins);
    }

    public boolean actualizarDatosJugador(JugadorDAOSQLite jugadorDAOSQLite) {
        // Valores a actualizar
        int experienceN = jugadorDAOSQLite.getExperience();
        int lifeLevelN = jugadorDAOSQLite.getLife_level();
        int coinsN = jugadorDAOSQLite.getCoins();
        int idJugador = jugadorDAOSQLite.getPlayer_id();
        LocalDateTime lastLogin = LocalDateTime.now();

        String consulta = "UPDATE jugador SET experience = experience + ?, life_level = life_level + ?, "
                + "coins = coins + ?, session_count = session_count + 1, last_login = ? WHERE player_id = ?";

        try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(consulta)) {

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

    public List<JugadorDAOSQLite> obtenerJugadoresObjeto() {
        List<JugadorDAOSQLite> listaJugadores = new LinkedList();

        String consulta = "SELECT * FROM jugador";

        try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(consulta); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                int user_id = resultado.getInt("player_id");
                String nickName = resultado.getString("nick_name");
                int experience = resultado.getInt("experience");
                int lifeLevel = resultado.getInt("life_level");
                int coins = resultado.getInt("coins");
                int session_count = resultado.getInt("session_count");
                String last_login = resultado.getString("last_login");

                JugadorDAOSQLite jugador = new JugadorDAOSQLite(user_id, experience, lifeLevel, coins, session_count, last_login, nickName );

                listaJugadores.add(jugador);
            }

            return listaJugadores;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}

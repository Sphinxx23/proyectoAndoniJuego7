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

    private String URL = "jdbc:sqlite:G:\\2º Superior\\Acceso a datos\\SQLite\\datosLocales.db";
    private int user_id, experience, life_level, coins, session_count, bd;
    private String last_login, nick_name;

    public JugadorDAOSQLite(int user_id, String nick_name, int experience, int life_level, int coins) {
        this.user_id = user_id;
        this.nick_name = nick_name;
        this.experience = experience;
        this.life_level = life_level;
        this.coins = coins;
        this.last_login = LocalDateTime.now().toString();
    }

    public JugadorDAOSQLite(int user_id, String nick_name, int experience, int life_level, int coins, int bd) {
        this.user_id = user_id;
        this.nick_name = nick_name;
        this.experience = experience;
        this.life_level = life_level;
        this.coins = coins;
        this.last_login = LocalDateTime.now().toString();
        this.bd = bd;
    }

    public JugadorDAOSQLite(int user_id, int experience, int life_level, int coins, int session_count, String last_login, String nick_name, int BD) {
        this.user_id = user_id;
        this.experience = experience;
        this.life_level = life_level;
        this.coins = coins;
        this.session_count = session_count;
        this.last_login = last_login;
        this.nick_name = nick_name;
        this.bd = BD;
    }

    public JugadorDAOSQLite(int user_id, int experience, int life_level, int coins, int session_count, String last_login, String nick_name) {
        this.user_id = user_id;
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

    public int getBd() {
        return bd;
    }

    public void setBd(int bd) {
        this.bd = bd;
    }

    public boolean comprobarJugador(int idJugador, String nombreJugador) {
        String sql = "SELECT COUNT(*) FROM jugador WHERE user_id = ? AND nick_name = ?";
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

        String consulta = "SELECT user_id, nick_name, experience, life_level, coins FROM jugador ORDER BY user_id";

        try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(consulta); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                user_id = resultado.getInt("user_id");
                nick_name = resultado.getString("nick_name");
                experience = resultado.getInt("experience");
                life_level = resultado.getInt("life_level");
                coins = resultado.getInt("coins");
                last_login = LocalDateTime.now().toString();

                JugadorDAOSQLite jugadorSQLite = new JugadorDAOSQLite(user_id, nick_name, experience, life_level, coins);

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
        return String.format("%-6d%-15s %-12d %-15d %-6d", user_id, nick_name, experience, life_level, coins);
    }

    public boolean actualizarDatosJugador(JugadorDAOSQLite jugadorDAOSQLite) {
        String consulta;
        // Valores a actualizar
        int experienceN = jugadorDAOSQLite.getExperience();
        int lifeLevelN = jugadorDAOSQLite.getLife_level();
        int coinsN = jugadorDAOSQLite.getCoins();
        int idJugador = jugadorDAOSQLite.getUser_id();
        String nombre = jugadorDAOSQLite.getNick_name();
        LocalDateTime lastLogin = LocalDateTime.now();

        if (jugadorDAOSQLite.getBd() == 0) {
            consulta = "UPDATE jugador SET experience = experience + ?, life_level = life_level + ?, "
                    + "coins = coins + ?, session_count = session_count + 1, last_login = ? WHERE user_id = ? AND nick_name = ?";
        } else {
            consulta = "UPDATE jugador SET experience = experience + ?, life_level = life_level + ?, "
                    + "coins = coins + ?, session_count = session_count + 1, last_login = ? WHERE user_id = ? AND BD = ?";
        }

        try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            // Establecer los valores en el PreparedStatement
            statement.setInt(1, experienceN);
            statement.setInt(2, lifeLevelN);
            statement.setInt(3, coinsN);
            statement.setString(4, lastLogin.toString());
            statement.setInt(5, idJugador);
            if (jugadorDAOSQLite.getBd() == 0) {
                statement.setString(6, nombre);
            } else {
                statement.setInt(6, jugadorDAOSQLite.getBd());
            }

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
                int user_id = resultado.getInt("user_id");
                String nickName = resultado.getString("nick_name");
                int experience = resultado.getInt("experience");
                int lifeLevel = resultado.getInt("life_level");
                int coins = resultado.getInt("coins");
                int session_count = resultado.getInt("session_count");
                String last_login = resultado.getString("last_login");

                JugadorDAOSQLite jugador = new JugadorDAOSQLite(user_id, experience, lifeLevel, coins, session_count, last_login, nickName);

                listaJugadores.add(jugador);
            }

            return listaJugadores;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}

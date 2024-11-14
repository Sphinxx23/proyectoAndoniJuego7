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
    private int player_id, experience, life_level, coins;
    private String last_login, nick_name;

    public JugadorDAOSQLite(int player_id, String nick_name, int experience, int life_level, int coins, String last_login) {
        this.player_id = player_id;
        this.nick_name = nick_name;
        this.experience = experience;
        this.life_level = life_level;
        this.coins = coins;
        this.last_login = last_login;
    }

    public JugadorDAOSQLite() {
        this(0, "", 0, 0, 0, null);
    }

    public boolean comprobarIDJugador(int idJugador) {
        String sql = "SELECT COUNT(*) FROM jugador WHERE player_id = ?";
        try (Connection con = DriverManager.getConnection(URL); PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idJugador);
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

                JugadorDAOSQLite jugadorSQLite = new JugadorDAOSQLite(player_id, nick_name, experience, life_level, coins, last_login);

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

}

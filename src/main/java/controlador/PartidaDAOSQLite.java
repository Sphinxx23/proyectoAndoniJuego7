/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author Vespertino
 */
public class PartidaDAOSQLite {

    private static final String URL = "jdbc:sqlite:G:\\2º Superior\\Acceso a datos\\SQLite\\datosLocales.db";
    private int user_id, experience, life_level, coins;
    private String isbn;
    private LocalDateTime session_date;

    public PartidaDAOSQLite(String isbn, int user_id, int experience, int life_level, int coins, LocalDateTime session_date) {
        this.isbn = isbn;
        this.user_id = user_id;
        this.experience = experience;
        this.life_level = life_level;
        this.coins = coins;
        this.session_date = session_date;
    }

    public PartidaDAOSQLite() {
        this("", 0, 0, 0, 0, null);
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDateTime getSession_date() {
        return session_date;
    }

    public void setSession_date(LocalDateTime session_date) {
        this.session_date = session_date;
    }

    public boolean crearPartida(PartidaDAOSQLite partidaSQLite) {

        String sql = "INSERT INTO partidasJugadas (isbn, player_id, experience, life_level, coins, session_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL); PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, partidaSQLite.getIsbn());
            statement.setInt(2, partidaSQLite.getUser_id());
            statement.setInt(3, partidaSQLite.getExperience());
            statement.setInt(4, partidaSQLite.getLife_level());
            statement.setInt(5, partidaSQLite.getCoins());
            statement.setString(6, partidaSQLite.getSession_date().toString());

            int lineasCambiadas = statement.executeUpdate();

            if (lineasCambiadas > 0) {
                return true;
            }else{
                return false;
            }
        } catch (SQLException e) {
            return false; // Devuelve false si ocurre algún error
        }
    }
}

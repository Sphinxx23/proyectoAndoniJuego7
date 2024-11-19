/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author andon
 */
public class PartidaDAOMySQL {
    private static final String URL = "jdbc:postgresql://ep-broad-union-a29uia00.eu-central-1.aws.neon.tech:5432/proyectoJuego?sslmode=require";
    private static final String USER = "proyectoJuego_owner";
    private static final String PASSWORD = "eb4xsQc0ENkU";

    public static boolean crearPartida(PartidaDAOMySQL partidaDAOmySQL) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private int user_id, experience, life_level, coins;
    private String isbn;
    private LocalDateTime session_date;

    public PartidaDAOMySQL(String isbn, int user_id, int experience, int life_level, int coins, LocalDateTime session_date) {
        this.isbn = isbn;
        this.user_id = user_id;
        this.experience = experience;
        this.life_level = life_level;
        this.coins = coins;
        this.session_date = session_date;
    }
    public PartidaDAOMySQL() {
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
    
    public boolean crearPartida(PartidaDAOPostgre partidaPostgre) {
        LocalDateTime session_date = LocalDateTime.now();

        String sql = "INSERT INTO partida (isbn, user_id, experience, life_level, coins, session_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); 
                PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, partidaPostgre.getIsbn());
            statement.setInt(2, partidaPostgre.getUser_id());
            statement.setInt(3, partidaPostgre.getExperience());
            statement.setInt(4, partidaPostgre.getLife_level());
            statement.setInt(5, partidaPostgre.getCoins());
            statement.setString(6, partidaPostgre.getSession_date().toString());

            int lineasCambiadas = statement.executeUpdate();

            return lineasCambiadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Devuelve false si ocurre algún error
        }
    }
}

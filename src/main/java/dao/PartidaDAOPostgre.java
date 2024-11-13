/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author andon
 */
public class PartidaDAOPostgre {

    private static final String URL = "jdbc:postgresql://ep-broad-union-a29uia00.eu-central-1.aws.neon.tech:5432/proyectoJuego?sslmode=require";
    private static final String USER = "proyectoJuego_owner";
    private static final String PASSWORD = "eb4xsQc0ENkU";
    private int user_id, experience, life_level, coins;
    private String isbn;
    private LocalDateTime session_date;

    public PartidaDAOPostgre(String isbn, int user_id, int experience, int life_level, int coins, LocalDateTime session_date) {
        this.isbn = isbn;
        this.user_id = user_id;
        this.experience = experience;
        this.life_level = life_level;
        this.coins = coins;
        this.session_date = session_date;
    }

    public PartidaDAOPostgre() {
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

    public List<Integer> simularPartida() {
        int experience, life_level, coins;
        List<Integer> cambiosPartidaSimulada = new LinkedList<>();
        Random random = new Random();

        // Generar valores aleatorios para la partida
        experience = random.nextInt(101); // Experiencia entre 0 y 100
        life_level = random.nextInt(101); // Nivel de vida entre 0 y 100
        coins = random.nextInt(201);      // Monedas entre 0 y 200

        // Agregar los valores generados a la lista
        cambiosPartidaSimulada.add(experience);
        cambiosPartidaSimulada.add(life_level);
        cambiosPartidaSimulada.add(coins);

        return cambiosPartidaSimulada;
    }

    public boolean actualizarDatosPartida(PartidaDAOPostgre partidaActualizada) {
        // Valores a actualizar
        int experienceN = partidaActualizada.getExperience();
        int lifeLevel = partidaActualizada.getLife_level();
        int coinsN = partidaActualizada.getCoins();
        String isbnN = partidaActualizada.getIsbn();
        int idJugador = partidaActualizada.getUser_id();

        String consulta = "UPDATE partida SET experience = ?, life_level = ?, coins = ? WHERE user_id = ? AND isbn = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = conexion.prepareStatement(consulta)) {

            // Establecer los valores en el PreparedStatement
            statement.setInt(1, experienceN);
            statement.setInt(2, lifeLevel);
            statement.setInt(3, coinsN);
            statement.setInt(4, idJugador);
            statement.setString(5, isbnN);

            // Ejecutar la consulta de actualización
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0; // Devuelve true si se actualizó alguna fila

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Devuelve false si ocurre algún error
        }
    }

}

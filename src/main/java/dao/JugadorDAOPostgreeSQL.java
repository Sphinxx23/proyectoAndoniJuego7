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
public class JugadorDAOPostgreeSQL {

    private static final String URL = "jdbc:postgresql://ep-broad-union-a29uia00.eu-central-1.aws.neon.tech:5432/proyectoJuego?sslmode=require";
    private static final String USER = "proyectoJuego_owner";
    private static final String PASSWORD = "eb4xsQc0ENkU";
    private int user_id, experience, lifeLevel, coins;
    private String nickName;

    public JugadorDAOPostgreeSQL(int user_id, int experience, int lifeLevel, int coins, String nickName) {
        this.user_id = user_id;
        this.experience = experience;
        this.lifeLevel = lifeLevel;
        this.coins = coins;
        this.nickName = nickName;
    }

    public JugadorDAOPostgreeSQL() {
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

    public static List<String> obtenerJugadores() {
        List<String> listaJugadores = new LinkedList();

        String consulta = "SELECT user_id, nick_name, experience, life_level, coins FROM jugador ORDER BY user_id";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = conexion.prepareStatement(consulta); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                int user_id = resultado.getInt("user_id");
                String nickName = resultado.getString("nick_name");
                int experience = resultado.getInt("experience");
                int lifeLevel = resultado.getInt("life_level");
                int coins = resultado.getInt("coins");

                JugadorDAOPostgreeSQL jugadorPostgree = new JugadorDAOPostgreeSQL(user_id, experience, lifeLevel, coins, nickName);

                String lineaJugador = jugadorPostgree.toString();

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

    public boolean actualizarDatosJugador(JugadorDAOPostgreeSQL jugadorPostgreeSQL) {
        // Valores a actualizar
        int experienceN = jugadorPostgreeSQL.getExperience();
        int lifeLevelN = jugadorPostgreeSQL.getLifeLevel();
        int coinsN = jugadorPostgreeSQL.getCoins();
        int idJugador = jugadorPostgreeSQL.getUser_id();
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

}

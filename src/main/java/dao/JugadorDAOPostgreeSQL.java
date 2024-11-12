/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
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
    private int experience, lifeLevel, coins;
    private String nickName;

    public JugadorDAOPostgreeSQL(int experience, int lifeLevel, int coins, String nickName) {
        this.experience = experience;
        this.lifeLevel = lifeLevel;
        this.coins = coins;
        this.nickName = nickName;
    }

    public JugadorDAOPostgreeSQL() {
        this(0, 0, 0, "");
    }

    public static List<String> obtenerJugadores() {
        List<String> listaJugadores = new LinkedList();

        String consulta = "SELECT nick_name, experience, life_level, coins FROM jugador";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement statement = conexion.prepareStatement(consulta); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                String nickName = resultado.getString("nick_name");
                int experience = resultado.getInt("experience");
                int lifeLevel = resultado.getInt("life_level");
                int coins = resultado.getInt("coins");

                JugadorDAOPostgreeSQL jugadorPostgree = new JugadorDAOPostgreeSQL(experience, lifeLevel, coins, nickName);

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
        return String.format("%-15s %-12d %-15d %-6d", nickName, experience, lifeLevel, coins);
    }

    public boolean comprobarNombreJugador(String nombreJugador) {
        // Consulta SQL para verificar si existe un jugador con el nombre proporcionado
        String query = "SELECT COUNT(*) FROM jugador WHERE nick_name = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            // Establecer el parámetro en la consulta
            stmt.setString(1, nombreJugador);

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

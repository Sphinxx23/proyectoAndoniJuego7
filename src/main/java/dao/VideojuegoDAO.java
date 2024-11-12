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
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author andon
 */
public class VideojuegoDAO {
    private String isbn, nombreJuego;
    private static final String URL = "jdbc:postgresql://ep-broad-union-a29uia00.eu-central-1.aws.neon.tech:5432/proyectoJuego?sslmode=require";
    private static final String USER = "proyectoJuego_owner";
    private static final String PASSWORD = "eb4xsQc0ENkU";
    
    public VideojuegoDAO(String isbn, String nombreJuego) {
        this.isbn = isbn;
        this.nombreJuego = nombreJuego;
    }
    
    public VideojuegoDAO() {
        this("","");
    }

    public static List<String> obtenerJuegos() {
        List<String> listaJugadores = new LinkedList();

        String consulta = "SELECT isbn, title FROM videojuego";

        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD); 
                PreparedStatement statement = conexion.prepareStatement(consulta); ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                String isbn = resultado.getString("isbn");
                String nombreJuego = resultado.getString("title");
                        
                VideojuegoDAO videojuego = new VideojuegoDAO(isbn, nombreJuego);

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
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import facto.Factory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Jugador;

/**
 *
 * @author PcBox
 */
public class JugadorDAOpostgre implements IDAOJugadorPostgre<Jugador>{

    public JugadorDAOpostgre() {

    }

    @Override
    public void alta(Jugador o) {
        String sql = "INSERT INTO jugador (user_id, nick_name, experience, life_level, coins, session_count, last_login) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Factory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, o.getUser_id());
            pstmt.setString(2, o.getNick_name());
            pstmt.setInt(3, o.getExperience());
            pstmt.setInt(4, o.getLife_level());
            pstmt.setInt(5, o.getCoins());
            pstmt.setInt(6, o.getSession_count());
            pstmt.setString(7, o.getLast_login().toString());

            int lineasCambiadas = pstmt.executeUpdate();

            if (lineasCambiadas == 0) {
                System.out.println("Error al insertar jugador");
            } else {
                System.out.println("Jugador insertado exitosamente.");
            }

        } catch (SQLException e) {     
            System.out.println("Error al insertar" );
        }
    }

    @Override
    public void baja(Jugador darBaja) {

        String sql = "DELETE FROM jugador WHERE user_id = ?";

        try (Connection conn = Factory.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, darBaja.getUser_id());
            int lineasCambiadas = pstmt.executeUpdate();

            if (lineasCambiadas == 0) {
                System.out.println("Error al dar de baja");
            } else {
                System.out.println("Jugador eliminado exitosamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al dar de baja");
        }

    }

    @Override
    public void modificacion(Jugador modificado) {
        String sql = "UPDATE jugador SET nick_name = ?, experience = ?, life_level = ?, coins = ?, session_count = ?, last_login = ? WHERE user_id = ?";

        try (Connection conn = Factory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, modificado.getNick_name());
            pstmt.setInt(2, modificado.getExperience());
            pstmt.setInt(3, modificado.getLife_level());
            pstmt.setInt(4, modificado.getCoins());
            pstmt.setInt(5, modificado.getSession_count());
            pstmt.setString(6, modificado.getLast_login().toString());
            pstmt.setInt(7, modificado.getUser_id());

            int lineasCambiadas = pstmt.executeUpdate();

            if (lineasCambiadas == 0) {
                System.out.println("Error al actualizar");
            } else {
                System.out.println("Jugador actualizado exitosamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar");
        }
    }

    @Override
    public void listaNombre() {
        String sql = "SELECT * FROM jugador ORDER BY nick_name";

        try (Connection conn = Factory.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("user_id") + "\t");
                System.out.print("Nickname: " + rs.getString("nick_name") + "\t");
                System.out.print("Experience: " + rs.getInt("experience") + "\t");
                System.out.print("Life Level: " + rs.getInt("life_level") + "\t");
                System.out.print("Coins: " + rs.getInt("coins") + "\t");
                System.out.print("Session Count: " + rs.getInt("session_count") + "\t");
                System.out.println("Last Session: " + rs.getString("last_login") + "\t");
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al listar por nombre");
        }
    }

    @Override
    public void listaExperiencia() {
        String sql = "SELECT * FROM jugador ORDER BY experience DESC";

        try (Connection conn = Factory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("user_id") + "\t");
                System.out.print("Nickname: " + rs.getString("nick_name") + "\t");
                System.out.print("Experience: " + rs.getInt("experience") + "\t");
                System.out.print("Life Level: " + rs.getInt("life_level") + "\t");
                System.out.print("Coins: " + rs.getInt("coins") + "\t");
                System.out.print("Session Count: " + rs.getInt("session_count") + "\t");
                System.out.println("Last Session: " + rs.getString("last_login") + "\t");
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al listar por xp");
        }
    }

    @Override
    public void top10xp() {
        String sql = "SELECT * FROM jugador ORDER BY experience LIMIT 10";

        try (Connection conn = Factory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("user_id") + "\t");
                System.out.print("Nickname: " + rs.getString("nick_name") + "\t");
                System.out.print("Experience: " + rs.getInt("experience") + "\t");
                System.out.print("Life Level: " + rs.getInt("life_level") + "\t");
                System.out.print("Coins: " + rs.getInt("coins") + "\t");
                System.out.print("Session Count: " + rs.getInt("session_count") + "\t");
                System.out.println("Last Session: " + rs.getString("last_login") + "\t");
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el top10");
        }
    }

    @Override
    public void consulta(Jugador o) {
         String sql = "SELECT * FROM jugador WHERE user_id = ?";

        try (Connection conn = Factory.getConnection(); 
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, o.getUser_id());
                        
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("user_id") + "\t");
                System.out.print("Nickname: " + rs.getString("nick_name") + "\t");
                System.out.print("Experience: " + rs.getInt("experience") + "\t");
                System.out.print("Life Level: " + rs.getInt("life_level") + "\t");
                System.out.print("Coins: " + rs.getInt("coins") + "\t");
                System.out.print("Session Count: " + rs.getInt("session_count") + "\t");
                System.out.println("Last Session: " + rs.getString("last_login") + "\t");
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar al jugador");
        }
    }


}

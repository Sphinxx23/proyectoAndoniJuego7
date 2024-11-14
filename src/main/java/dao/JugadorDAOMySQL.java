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

/**
 *
 * @author andon
 */
public class JugadorDAOMySQL {

    public static void main(String[] args) {
        String url = "jdbc:mysql://sql.freedb.tech:3306/freedb_proyectoJueguito";
        String user = "freedb_Andoni";
        String password = "P9F#&y63X9PD#hQ";

        String sql = "SELECT * FROM videojuego";
        try (Connection con = DriverManager.getConnection(url, user, password); PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("isbn"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}

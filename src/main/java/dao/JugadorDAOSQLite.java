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
public class JugadorDAOSQLite {

    private static String url = "jdbc:sqlite:G:\\2º Superior\\Acceso a datos\\SQLite\\datosLocales.db";

    public static void main(String[] args) throws SQLException {
        String driver = "org.sqlite.JDBC";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("Controlador JDBC de SQLite no encontrado: " + e.toString());
        }

        // Establecer conexion
        Connection con = DriverManager.getConnection(url);
        Statement stmt = con.createStatement();
        String orden = "INSERT INTO jugador VALUES (2, 'HOLA', 2, 3, 4, 5, 'HOY')";

        // Ejecutar el INSERT con executeUpdate, ya que no devuelve un ResultSet
        int rowsInserted = stmt.executeUpdate(orden);
        System.out.println("Filas insertadas: " + rowsInserted);

        stmt.close();
        con.close();
    }

    public boolean existeJugador(int idJugador) {
        String sql = "SELECT COUNT(*) FROM jugador WHERE player_id = ?";
        try (Connection con = DriverManager.getConnection(url); 
                PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, idJugador);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public List<String> obtenerJugadores(){
        List<String> jugadores = new LinkedList();
        
        return jugadores;
    }
}

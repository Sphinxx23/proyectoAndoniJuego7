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
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Partida;

/**
 *
 * @author PcBox
 */
public class PartidaDAOpostgre implements IDAOPartidaPostgre<Partida> {

    @Override
    public void simularPartida(Partida o) {
        boolean vid = false, jug = false;

        Connection conn = null;
        try {
            conn = Factory.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(PartidaDAOpostgre.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            jug = consultaJug(o, conn);
            vid = consultaVid(o, conn);
        } catch (SQLException ex) {
            Logger.getLogger(PartidaDAOpostgre.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (jug == true && vid == true) {
            alta(o, conn);
            actualizarJugador(o, conn);
            actualizarVideojuego(o, conn);
        } else {
            System.out.println("Imposible crear partida, jugador o videojuego no existen en esta BD");
        }

    }

    private void alta(Partida o, Connection conn) {
        String sql = "INSERT INTO partida (isbn, user_id, experience, life_level, coins, session_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, o.getIsbn());
            pstmt.setInt(2, o.getUser_id());
            pstmt.setInt(3, o.getExperience());
            pstmt.setInt(4, o.getLife_level());
            pstmt.setInt(5, o.getCoins());
            pstmt.setString(6, o.getSession_date().toString());

            int lineasCambiadas = pstmt.executeUpdate();

            if (lineasCambiadas == 0) {
                System.out.println("Error al insertar");
            } else {
                System.out.println("Partida insertada exitosamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar");
        }
    }

    private boolean consultaJug(Partida o, Connection conn) throws SQLException {

        String sql = "SELECT * FROM jugador WHERE user_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, o.getUser_id());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        }
        return false;

    }

    private boolean consultaVid(Partida o, Connection conn) throws SQLException {

        String sql = "SELECT * FROM videojuego WHERE isbn = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, o.getIsbn());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return true;
            }
        }
        return false;

    }

    private int consultaPlayerCount(Partida o, Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM partida WHERE isbn = ? AND user_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, o.getIsbn());
            pstmt.setInt(2, o.getUser_id());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("count"); // Devuelve el número de filas coincidentes
            } else {
                return 1; // Si no hay resultado, devuelve 1
            }

        }

    }

    private void actualizarVideojuego(Partida o, Connection conn) {
        try {
            int playCoun;
            playCoun = consultaPlayerCount(o, conn);

            if (playCoun != 1) {
                String sql = "UPDATE videojuego SET total_sessions = total_sessions + 1, last_session = ? WHERE isbn = ?";

                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    pstmt.setString(1, o.getSession_date().toString());
                    pstmt.setString(2, o.getIsbn());
                    pstmt.executeQuery();

                } catch (SQLException e) {
                    System.out.println("Error al actualizar");
                }
            }

            if (playCoun == 1) {
                String sql = "UPDATE videojuego SET player_count = player_count + 1,total_sessions = total_sessions + 1, last_session = ? WHERE isbn = ?";

                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    pstmt.setString(1, o.getSession_date().toString());
                    pstmt.setString(2, o.getIsbn());
                    pstmt.executeQuery();

                } catch (SQLException e) {
                    System.out.println("Error al actualizar");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartidaDAOpostgre.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void actualizarJugador(Partida o, Connection conn) {

        String sql = "UPDATE jugador SET experience = experience + ?, life_level = life_level + ?, coins = coins + ?, session_count = session_count + 1, last_login = ? WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, o.getExperience());
            pstmt.setInt(2, o.getLife_level());
            pstmt.setInt(3, o.getCoins());
            pstmt.setString(4, o.getSession_date().toString());
            pstmt.setInt(5, o.getUser_id());

            pstmt.executeQuery();

        } catch (SQLException e) {
            System.out.println("Error al actualizar");
        }
    }
}

package dao;

import facto.Factory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Videojuego;

public class VideojuegoDAOpostgre implements IDAOVideojuegoPostgre<Videojuego> {

    public VideojuegoDAOpostgre() {

    }

    @Override
    public void alta(Videojuego o) {
        String sql = "INSERT INTO videojuego (isbn, title, player_count, total_sessions, last_session) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Factory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, o.getIsbn());
            pstmt.setString(2, o.getTitle());
            pstmt.setInt(3, o.getPlayer_count());
            pstmt.setInt(4, o.getTotal_sessions());
            pstmt.setString(5, o.getLast_session().toString());

            int lineasCambiadas = pstmt.executeUpdate();

            if (lineasCambiadas == 0) {
                System.out.println("Error al insertar");
            } else {
                System.out.println("Videojuego insertado exitosamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar");
        }
    }

    @Override
    public void baja(Videojuego darBaja) {

        String sql = "DELETE FROM videojuego WHERE isbn = ?";

        try (Connection conn = Factory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, darBaja.getIsbn());
           int lineasCambiadas = pstmt.executeUpdate();

            if (lineasCambiadas == 0) {
                System.out.println("Error al dar de baja");
            } else {
                System.out.println("Videojuego eliminado exitosamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al dar de baja");
        }

    }

    @Override
    public void modificacion(Videojuego modificado) {
        String sql = "UPDATE videojuego SET title = ?, player_count = ?, total_sessions = ?, last_session = ? WHERE isbn = ?";

        try (Connection conn = Factory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, modificado.getTitle());
            pstmt.setInt(2, modificado.getPlayer_count());
            pstmt.setInt(3, modificado.getTotal_sessions());
            pstmt.setString(4, modificado.getLast_session().toString());
            pstmt.setString(5, modificado.getIsbn());

            int lineasCambiadas = pstmt.executeUpdate();

            if (lineasCambiadas == 0) {
                System.out.println("Error al actualizar");
            } else {
                System.out.println("Videojuego actualizado exitosamente.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar");
        }
    }

    @Override
    public void jugadoresRegistrados(Videojuego v) {
        String sql = "SELECT player_count FROM videojuego WHERE isbn = ?";

        try (Connection conn = Factory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, v.getIsbn());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Jugadores totales: " + rs.getInt("player_count"));

                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar datos");
        }
    }

    @Override
    public void sesionesTotales(Videojuego v) {
        String sql = "SELECT total_sessions FROM videojuego WHERE isbn = ?";

        try (Connection conn = Factory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, v.getIsbn());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Jugadores totales: " + rs.getInt("total_sessions"));

                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar datos");
        }
    }

    @Override
    public void ultimaSesion(Videojuego v) {
        String sql = "SELECT last_session FROM videojuego WHERE isbn = ?";

        try (Connection conn = Factory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, v.getIsbn());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Ultima sesion: " + rs.getString("last_session"));

                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar datos");
        }
    }

    @Override
    public void listar() {
        String sql = "SELECT * FROM videojuego ORDER BY isbn";

        try (Connection conn = Factory.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                System.out.print("ISBN: " + rs.getString("isbn") + "\t");
                System.out.print("Titulo: " + rs.getString("title") + "\t");
                System.out.print("Total Jugadores: " + rs.getInt("player_count") + "\t");
                System.out.print("Sesiones totales: " + rs.getInt("total_sessions") + "\t");
                System.out.println("Ultima Sesion: " + rs.getString("last_session") + "\t");
                System.out.println("------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al listar");
        }
    }
}

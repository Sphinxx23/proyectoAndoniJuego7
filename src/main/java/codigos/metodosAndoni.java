///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package codigos;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
///**
// *
// * @author Vespertino
// */
//public class metodosAndoni {
//
//    /*
//   
//    La tabla de PARTIDA de sqlite en local, debe tener los siguientes campos:
//        -isbn
//        -user_id
//        -experience
//        -life_level
//        -coins
//        -session_date
//        -base_datos            un int que sea 1 si la bd donde se descarga el juego es postgre y 2 si es mySql
//   
//   
//   
//     */
// /*
//     
//       meter antes de dar de alta una partida:
//   
//    arriba del todo:
//    private final String  URLPOSTGRE = "jdbc:postgresql://ep-broad-union-a29uia00.eu-central-1.aws.neon.tech/proyectoJuego?user=proyectoJuego_owner&password=eb4xsQc0ENkU&sslmode=require";
//     private final String  URLMYSQL = "";
//   
//    en el metodo de dar alta:
//   
//    try {
//
//            Connection conn1 = DriverManager.getConnection(URLPOSTGRE);
//            Connection conn2 = DriverManager.getConnection(URLMYSQL);
//
//            jug1 = consultaJug(o, conn1);
//            vid1 = consultaVid(o, conn1);
//
//            jug2 = consultaJug(o, conn2);
//            vid2 = consultaVid(o, conn2);
//
//            if (jug1 == true && vid1 == true) {
//                // meter numero 1 en el atributo base_datos al crear partida
//            } else if (jug2 == true && vid2 == true) {
//                // meter numero 1 en el atributo base_datos al crear partida
//            } else {
//                // jugador y juego son de diferentes bd online, no puede hacerse
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(PartidaDAOpostgre.class.getName()).log(Level.SEVERE, null, ex);
//        }
//   
//   
//   
//   
//    Metodos de consulta usados:
//   
//    private boolean consultaJug(Partida o, Connection conn) throws SQLException {
//
//        String sql = "SELECT * FROM jugador WHERE user_id = ?";
//
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, o.getUser_id());
//
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                return true;
//            }
//        }
//        return false;
//
//    }
//
//    private boolean consultaVid(Partida o, Connection conn) throws SQLException {
//
//        String sql = "SELECT * FROM videojuego WHERE isbn = ?";
//
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, o.getIsbn());
//
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                return true;
//            }
//        }
//        return false;
//
//    }
//   
//   
//     
//     
//     
//     */
//    public void updatearPartida(Partida o) throws SQLException {
//        String url = " ";
//       
//        if (o.base_datos == 1) {
//            url=URLPOSTGRE;
//        } else if (o.base_datos == 2) {
//            url=URLMYSQL;
//        } else {
//            System.out.println("Error");
//        }
//
//        Connection connFin = DriverManager.getConnection(url);
//
//        alta(o, connFin);
//        actualizarJugador(o, connFin);
//        actualizarVideojuego(o, connFin);
//
//    }
//
//    private int consultaPlayerCount(Partida o, Connection conn) throws SQLException {
//        String sql = "SELECT COUNT(*) AS count FROM partida WHERE isbn = ? AND user_id = ?";
//
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, o.getIsbn());
//            pstmt.setInt(2, o.getUser_id());
//
//            ResultSet rs = pstmt.executeQuery();
//
//            if (rs.next()) {
//                return rs.getInt("count"); // Devuelve el número de filas coincidentes
//            } else {
//                return 1; // Si no hay resultado, devuelve 1
//            }
//
//        }
//
//    }
//
//    private void actualizarVideojuego(Partida o, Connection conn) {
//        try {
//            int playCoun;
//            playCoun = consultaPlayerCount(o, conn);
//
//            if (playCoun != 1) {
//                String sql = "UPDATE videojuego SET total_sessions = total_sessions + 1, last_session = ? WHERE isbn = ?";
//
//                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//                    pstmt.setString(1, o.getSession_date().toString());
//                    pstmt.setString(2, o.getIsbn());
//                    pstmt.executeUpdate();
//
//                } catch (SQLException e) {
//                    System.out.println("Error al actualizar");
//                }
//            }
//
//            if (playCoun == 1) {
//                String sql = "UPDATE videojuego SET player_count = player_count + 1,total_sessions = total_sessions + 1, last_session = ? WHERE isbn = ?";
//
//                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//                    pstmt.setString(1, o.getSession_date().toString());
//                    pstmt.setString(2, o.getIsbn());
//                    pstmt.executeUpdate();
//
//                } catch (SQLException e) {
//                    System.out.println("Error al actualizar");
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(PartidaDAOpostgre.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    private void actualizarJugador(Partida o, Connection conn) {
//
//        String sql = "UPDATE jugador SET experience = experience + ?, life_level = life_level + ?, coins = coins + ?, session_count = session_count + 1, last_login = ? WHERE user_id = ?";
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setInt(1, o.getExperience());
//            pstmt.setInt(2, o.getLife_level());
//            pstmt.setInt(3, o.getCoins());
//            pstmt.setString(4, o.getSession_date().toString());
//            pstmt.setInt(5, o.getUser_id());
//
//            pstmt.executeUpdate();
//
//        } catch (SQLException e) {
//            System.out.println("Error al actualizar");
//        }
//    }
//
//    private void alta(Partida o, Connection conn) {
//        String sql = "INSERT INTO partida (isbn, user_id, experience, life_level, coins, session_date) VALUES (?, ?, ?, ?, ?, ?)";
//
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//            pstmt.setString(1, o.getIsbn());
//            pstmt.setInt(2, o.getUser_id());
//            pstmt.setInt(3, o.getExperience());
//            pstmt.setInt(4, o.getLife_level());
//            pstmt.setInt(5, o.getCoins());
//            pstmt.setString(6, o.getSession_date().toString());
//
//            int lineasCambiadas = pstmt.executeUpdate();
//
//            if (lineasCambiadas == 0) {
//                System.out.println("Error al insertar");
//            } else {
//                System.out.println("Partida insertada exitosamente.");
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error al insertar");
//        }
//    }
//
//}
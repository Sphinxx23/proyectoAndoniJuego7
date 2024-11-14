/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.PartidaDAOPostgre;
import dao.VideojuegoDAOPostgre;
import java.time.LocalDateTime;
import java.util.List;
import vista.Vista;

/**
 *
 * @author Vespertino
 */
public class ControladorPartida {

    PartidaDAOPostgre partidaDAOPostgre;
    PartidaDAOSQLite partidaDAOSQLite;
    Vista vista;

    public ControladorPartida(Vista vista) {
        this.vista = vista;
    }

    public void setModeloPartidaPostgre(PartidaDAOPostgre p) {
        this.partidaDAOPostgre = p;
    }
    
    public void setModeloPartidaSQLite(PartidaDAOSQLite p) {
        this.partidaDAOSQLite = p;
    }

    public List<Integer> simularPartida() {
        return partidaDAOPostgre.simularPartida();
    }

    public boolean crearPartidaSimulada(List<Integer> cambiosPartidaJugada, int servidorLinea, int idJugadorJuego, String isbnJuego) {
        switch (servidorLinea) {
            case 1:
                //Postgre
                PartidaDAOPostgre partidaPostgre = new PartidaDAOPostgre(isbnJuego, idJugadorJuego, cambiosPartidaJugada.get(0), cambiosPartidaJugada.get(1), cambiosPartidaJugada.get(2), LocalDateTime.now());
                return partidaDAOPostgre.crearPartida(partidaPostgre);
            case 2:
                //MySQL
                return false;
            case 3:
                //SQLite
                PartidaDAOSQLite partidaSQLite = new PartidaDAOSQLite(isbnJuego,idJugadorJuego,cambiosPartidaJugada.get(0), cambiosPartidaJugada.get(1), cambiosPartidaJugada.get(2), LocalDateTime.now());
                return partidaDAOSQLite.crearPartida(partidaSQLite);
            default:
                return false;
        }
    }
}

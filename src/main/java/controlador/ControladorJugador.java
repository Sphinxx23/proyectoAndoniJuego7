/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.JugadorDAOMySQL;
import dao.JugadorDAOPostgreeSQL;
import dao.JugadorDAOSQLite;
import java.util.LinkedList;
import java.util.List;
import vista.Vista;

/**
 *
 * @author andon
 */
public class ControladorJugador {

    private JugadorDAOSQLite jugadorSQLite;
    private JugadorDAOPostgreeSQL jugadorPostgreeSQL;
    private JugadorDAOMySQL jugadorMySQL;
    private Vista vista;

    public ControladorJugador(Vista vista) {
        this.vista = vista;
    }

    public void setModeloSQLite(JugadorDAOSQLite modelo) {
        this.jugadorSQLite = modelo;
    }

    public void setModeloPostgreeSQL(JugadorDAOPostgreeSQL modelo) {
        this.jugadorPostgreeSQL = modelo;
    }

    public void setModeloMySQL(JugadorDAOMySQL modelo) {
        this.jugadorMySQL = modelo;
    }

    public List<String> obtenerJugadores(int servidor) {
        List<String> listaJugadores = new LinkedList();

        switch (servidor) {
            case 1:
                //Postgre
                listaJugadores = JugadorDAOPostgreeSQL.obtenerJugadoresString();
                break;
            case 2:
                //MySQL
                listaJugadores = JugadorDAOMySQL.obtenerJugadores();
                break;
            case 3:
                //SQLite
                listaJugadores = jugadorSQLite.obtenerJugadores();
                break;
            default:
                throw new AssertionError();
        }
        return listaJugadores;
    }

    public boolean comprobarJugador(int idJugadorJuego, String nombreJugador, int servidor) {
        switch (servidor) {
            case 1:
                //PostgreSQL
                return jugadorPostgreeSQL.comprobarIDJugador(idJugadorJuego);
            case 2:
                //MySQL
                return jugadorMySQL.comprobarIDJugador(idJugadorJuego);
            case 3:
                //SQLite
                return jugadorSQLite.comprobarJugador(idJugadorJuego, nombreJugador);
            default:
                throw new AssertionError();
        }
    }

    public boolean actualizarDatosJugador(List<Integer> cambiosPartidaJugada, int idJugadorJuego, String nombreJugador, int servidor) {
        switch (servidor) {
            case 1:
                //Postgre
                JugadorDAOPostgreeSQL jugadorPostgreeSQL = new JugadorDAOPostgreeSQL(idJugadorJuego, cambiosPartidaJugada.get(0), cambiosPartidaJugada.get(1), cambiosPartidaJugada.get(2), "");
                JugadorDAOSQLite jugadorDAOSQLiteA = new JugadorDAOSQLite(idJugadorJuego, nombreJugador, cambiosPartidaJugada.get(0), cambiosPartidaJugada.get(1), cambiosPartidaJugada.get(2), 1);
                jugadorDAOSQLiteA.actualizarDatosJugador(jugadorDAOSQLiteA);
                return jugadorPostgreeSQL.actualizarDatosJugador(jugadorPostgreeSQL);
            case 2:
                //MySQL
                JugadorDAOMySQL jugadorDAOMySQL = new JugadorDAOMySQL(idJugadorJuego, cambiosPartidaJugada.get(0), cambiosPartidaJugada.get(1), cambiosPartidaJugada.get(2), "");
                JugadorDAOSQLite jugadorDAOSQLiteB = new JugadorDAOSQLite(idJugadorJuego, nombreJugador, cambiosPartidaJugada.get(0), cambiosPartidaJugada.get(1), cambiosPartidaJugada.get(2), 2);
                jugadorDAOSQLiteB.actualizarDatosJugador(jugadorDAOSQLiteB);
                return jugadorDAOMySQL.actualizarDatosJugador(jugadorDAOMySQL);
            case 3:
                //SQLite
                JugadorDAOSQLite jugadorDAOSQLite = new JugadorDAOSQLite(idJugadorJuego, nombreJugador, cambiosPartidaJugada.get(0), cambiosPartidaJugada.get(1), cambiosPartidaJugada.get(2));
                return jugadorDAOSQLite.actualizarDatosJugador(jugadorDAOSQLite);
            default:
                return false;
        }
    }

    public boolean comprobarJugadorDescargado(int idJugadorJuego, String nombreJugador, int servidorLinea) {
        switch (servidorLinea) {
            case 1:
                //Postgre
                return jugadorPostgreeSQL.comprobarJugadorDescargado(idJugadorJuego, nombreJugador);
            case 2:
                //MySQL
                return jugadorMySQL.comprobarJugadorDescargado(idJugadorJuego, nombreJugador);
            default:
                throw new AssertionError();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

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

    public boolean verificarExistenciaJugador(int idJugador) {
        return jugadorSQLite.existeJugador(idJugador);
    }

    public List<String> obtenerJugadores(int servidor) {
        List<String> listaJugadores = new LinkedList();

        if (servidor == 1) {
            listaJugadores = JugadorDAOPostgreeSQL.obtenerJugadores();
        } else {
            //listaJugadores = MYSQL
        }
        return listaJugadores;
    }

    public boolean comprobarIDJugador(int idJugadorJuego, int servidor) {

        if (servidor == 1) {
            //PostgreSQL
            return jugadorPostgreeSQL.comprobarIDJugador(idJugadorJuego);
        } else {
            //MySQL
            return false;
        }
    }

    public boolean actualizarDatosJugador(List<Integer> cambiosPartidaJugada, int idJugadorJuego, int servidor) {
        switch (servidor) {
            case 1:
                //Postgre
                JugadorDAOPostgreeSQL jugadorPostgreeSQL = new JugadorDAOPostgreeSQL(idJugadorJuego, cambiosPartidaJugada.get(0), cambiosPartidaJugada.get(1), cambiosPartidaJugada.get(2), "");
                return jugadorPostgreeSQL.actualizarDatosJugador(jugadorPostgreeSQL);
            case 2:
                //MySQL
                return false;
            case 3:
                //SQLite
                return false;
            default:
                return false;
        }
    }
}

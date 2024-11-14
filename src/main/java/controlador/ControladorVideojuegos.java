/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.VideojuegoDAOSQLite;
import dao.VideojuegoDAOPostgre;
import java.util.LinkedList;
import java.util.List;
import vista.Vista;

/**
 *
 * @author andon
 */
public class ControladorVideojuegos {

    VideojuegoDAOPostgre videojuegoDAOPostgre = new VideojuegoDAOPostgre();
    VideojuegoDAOSQLite videojuegoDAOSQlite = new VideojuegoDAOSQLite();
    Vista vista;

    public ControladorVideojuegos(Vista vista) {
        this.vista = vista;
    }

    public void setModeloVideojuegoPostgre(VideojuegoDAOPostgre videojuegoDAOPostgre) {
        this.videojuegoDAOPostgre = videojuegoDAOPostgre;
    }
    
    public void setModeloVideojuegoSQLite(VideojuegoDAOSQLite videojuegoDAOSQLite) {
        this.videojuegoDAOSQlite = videojuegoDAOSQLite;
    }

    public List<String> obtenerJuegos(int servidor) {
        List<String> listaJuegos = new LinkedList();

        switch (servidor) {
            case 1:
                //Postgre
                listaJuegos = videojuegoDAOPostgre.obtenerJuegos();
                break;
            case 2:
                //MySQL
                //listaJuegos = videojuegoDAOMySQL.obtenerJuegos();
                break;
            case 3:
                listaJuegos = videojuegoDAOSQlite.obtenerJuegos();
                break;
            default:
                throw new AssertionError();
        }
        return listaJuegos;
    }

    public boolean actualizarVideojuego(String isbnJuego, int servidor, int idJugador) {
        switch (servidor) {
            case 1:
                //Postgre
                return videojuegoDAOPostgre.actualizarVidejuego(isbnJuego, idJugador);
            case 2:
                //MySQL
                return false;
            case 3:
                //SQLite
                return videojuegoDAOSQlite.actualizarVidejuego(isbnJuego, idJugador);
            default:
                throw new AssertionError();
        }
    }

    public boolean comprobarJuego(String isbnJuego, int servidorLinea) {
        switch (servidorLinea) {
            case 1:
                return videojuegoDAOPostgre.comprobarJuego(isbnJuego);
            case 2:
                return false;
            case 3:
                return videojuegoDAOSQlite.comprobarJuego(isbnJuego);
            default:
                throw new AssertionError();
        }
    }

    public boolean comprobarJuegoSinDescargar(String isbnJuego, int servidorLinea) {
        switch (servidorLinea) {
            case 1:
                //Postgre
                return videojuegoDAOPostgre.comprobarJuegoSinDescargar(isbnJuego);
            case 2:
                //MySQL
                return false;
            default:
                throw new AssertionError();
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.VideojuegoDAO;
import java.util.LinkedList;
import java.util.List;
import vista.Vista;

/**
 *
 * @author andon
 */
public class ControladorVideojuegos {

    VideojuegoDAO videojuegoDAO = new VideojuegoDAO();
    Vista vista;

    public ControladorVideojuegos(Vista vista) {
        this.vista = vista;
    }

    public void setModeloVideoJuego(VideojuegoDAO videojuegoDAO) {
        this.videojuegoDAO = videojuegoDAO;
    }

    public List<String> obtenerJuegos(int servidor) {
        List<String> listaJuegos = new LinkedList();

        if (servidor == 1) {
            listaJuegos = videojuegoDAO.obtenerJuegos();
        } else {
            //listaJugadores = MYSQL
        }
        return listaJuegos;
    }

    public boolean actualizarVideojuego(String isbnJuego, int servidor, int idJugador) {
        switch (servidor) {
            case 1:
                //Postgre
                return videojuegoDAO.actualizarVidejuego(isbnJuego, idJugador);
            case 2:
                //MySQL
                return false;
            case 3:
                //SQLite
                return false;
            default:
                throw new AssertionError();
        }
    }

    public boolean comprobarJuego(String isbnJuego, int servidorLinea) {
        switch (servidorLinea) {
            case 1:
                return videojuegoDAO.comprobarJuego(isbnJuego);
            case 2:
                return false;
            case 3:
                return false;
            default:
                throw new AssertionError();
        }
    }

}

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

}

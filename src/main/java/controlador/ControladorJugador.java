/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.JugadorDAOSQLite;
import vista.Vista;

/**
 *
 * @author andon
 */
public class ControladorJugador {

    private JugadorDAOSQLite jugador;
    private Vista vista;

    public ControladorJugador(Vista vista) {
        this.vista = vista;
    }
    
    public void setModelo(JugadorDAOSQLite modelo) {
        this.jugador = modelo;
    }

    public boolean verificarExistenciaJugador(int idJugador) {
        return jugador.existeJugador(idJugador);
    }
}

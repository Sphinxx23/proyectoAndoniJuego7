/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package run;

import controlador.ControladorConfiguracion;
import controlador.ControladorJugador;
import dao.JugadorDAOSQLite;
import vista.Vista;

/**
 *
 * @author andon
 */
public class Run {
    public static void main(String[] args) {
        Vista vista = new Vista();
        JugadorDAOSQLite jugador = new JugadorDAOSQLite();
        ControladorConfiguracion controlConf = new ControladorConfiguracion(vista);
        ControladorJugador controlJugador = new ControladorJugador(vista);
        
        controlConf.setModelo(jugador);
        controlJugador.setModelo(jugador);
        
        vista.setControladorConfig(controlConf);
        vista.setControladorJugador(controlJugador);
        
        vista.vistaInicio();
    }
}

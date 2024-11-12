/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package run;

import controlador.ControladorConfiguracion;
import controlador.ControladorJugador;
import controlador.ControladorVideojuegos;
import dao.JugadorDAOPostgreeSQL;
import dao.JugadorDAOSQLite;
import vista.Vista;

/**
 *
 * @author andon
 */
public class Run {
    public static void main(String[] args) {
        Vista vista = new Vista();
        JugadorDAOSQLite jugadorSQLite = new JugadorDAOSQLite();
        JugadorDAOPostgreeSQL jugadorPost = new JugadorDAOPostgreeSQL();
        ControladorConfiguracion controlConf = new ControladorConfiguracion(vista);
        ControladorJugador controlJugador = new ControladorJugador(vista);
        ControladorVideojuegos controlJuegos = new ControladorVideojuegos(vista);
        
        controlJugador.setModeloSQLite(jugadorSQLite);
        controlJugador.setModeloPostgreeSQL(jugadorPost);
        
        vista.setControladorConfig(controlConf);
        vista.setControladorJugador(controlJugador);
        vista.setControladorJuegos(controlJuegos);
        
        vista.vistaInicio();
    }
}

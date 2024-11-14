/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package run;

import controlador.ControladorConfiguracion;
import controlador.ControladorJugador;
import controlador.ControladorPartida;
import controlador.ControladorVideojuegos;
import dao.JugadorDAOPostgreeSQL;
import dao.JugadorDAOSQLite;
import dao.PartidaDAOPostgre;
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
        PartidaDAOPostgre partidaDAOPost = new PartidaDAOPostgre();
        
        ControladorConfiguracion controlConf = new ControladorConfiguracion(vista);
        ControladorJugador controlJugador = new ControladorJugador(vista);
        ControladorVideojuegos controlJuegos = new ControladorVideojuegos(vista);
        ControladorPartida controlPartida = new ControladorPartida(vista);

        controlJugador.setModeloSQLite(jugadorSQLite);
        controlJugador.setModeloPostgreeSQL(jugadorPost);
        
        controlPartida.setModeloPartidaPostgre(partidaDAOPost);

        vista.setControladorConfig(controlConf);
        vista.setControladorJugador(controlJugador);
        vista.setControladorJuegos(controlJuegos);
        vista.setControladorPartidas(controlPartida);

        vista.vistaInicio();
    }
}
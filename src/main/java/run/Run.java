/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package run;

import controlador.ControladorConfiguracion;
import controlador.ControladorJugador;
import controlador.ControladorPartida;
import controlador.ControladorSincronizacion;
import controlador.ControladorVideojuegos;
import dao.JugadorDAOMySQL;
import dao.PartidaDAOSQLite;
import dao.JugadorDAOPostgreeSQL;
import dao.JugadorDAOSQLite;
import dao.PartidaDAOPostgre;
import dao.SincronizarDAO;
import dao.VideojuegoDAOMySQL;
import dao.VideojuegoDAOPostgre;
import dao.VideojuegoDAOSQLite;
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
        JugadorDAOMySQL jugadorMySQL = new JugadorDAOMySQL();
        PartidaDAOPostgre partidaDAOPost = new PartidaDAOPostgre();
        PartidaDAOSQLite partidaDAOSQLite = new PartidaDAOSQLite();
        VideojuegoDAOPostgre videojuegoDAOPostgre = new VideojuegoDAOPostgre();
        VideojuegoDAOSQLite videojuegoDAOSQLite = new VideojuegoDAOSQLite();
        VideojuegoDAOMySQL videojuegoDAOMySQL = new VideojuegoDAOMySQL();
        SincronizarDAO sincroDAO = new SincronizarDAO();
        
        ControladorConfiguracion controlConf = new ControladorConfiguracion(vista);
        ControladorJugador controlJugador = new ControladorJugador(vista);
        ControladorVideojuegos controlJuegos = new ControladorVideojuegos(vista);
        ControladorPartida controlPartida = new ControladorPartida(vista);
        ControladorSincronizacion controlSinc = new ControladorSincronizacion(vista);

        controlSinc.setModelo(sincroDAO);
        
        controlJugador.setModeloSQLite(jugadorSQLite);
        controlJugador.setModeloPostgreeSQL(jugadorPost);
        controlJugador.setModeloMySQL(jugadorMySQL);
        
        controlPartida.setModeloPartidaPostgre(partidaDAOPost);
        controlPartida.setModeloPartidaSQLite(partidaDAOSQLite);
        
        controlJuegos.setModeloVideojuegoPostgre(videojuegoDAOPostgre);
        controlJuegos.setModeloVideojuegoSQLite(videojuegoDAOSQLite);
        controlJuegos.setModeloVideojuegoMySQL(videojuegoDAOMySQL);

        vista.setControladorConfig(controlConf);
        vista.setControladorJugador(controlJugador);
        vista.setControladorJuegos(controlJuegos);
        vista.setControladorPartidas(controlPartida);
        vista.setControladorSinc(controlSinc);

        vista.vistaInicio();
    }
}

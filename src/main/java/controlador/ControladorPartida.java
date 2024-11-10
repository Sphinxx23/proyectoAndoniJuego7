package controlador;

import dao.IDAOPartidaPostgre;
import modelo.Partida;
import vista.IVistaP;

public class ControladorPartida {

    private IVistaP<Partida> vista;
    private IDAOPartidaPostgre<Partida> dao;

    public ControladorPartida(IVistaP vista) {
        this.vista = vista;
    }

    /**
     * Settea el modelo param el modelo dao
     */
    public void setModelo(IDAOPartidaPostgre<Partida> modelo) {
        this.dao = modelo;
    }

    /**
     * Realiza la operacion elegida en el menu y sale un mensaje si se realiza
     * bien o no param el numero de la operacion deseada
     */
    public void operacion(int op) {
        String mensaje;
        Partida aux;
        switch (op) {
            case 1: //insercion
                aux = vista.getObject();
                dao.simularPartida(aux);
                break;
            default:
                throw new AssertionError();
        }
    }

}

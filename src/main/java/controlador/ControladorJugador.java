package controlador;

import modelo.Jugador;
import vista.IVistaJ;
import dao.IDAOJugadorPostgre;

public class ControladorJugador {

    private IVistaJ<Jugador> vista;

    private IDAOJugadorPostgre<Jugador> dao;

    public ControladorJugador(IVistaJ vista) {
        this.vista = vista;
    }

    /**
     * Settea el modelo param el modelo dao
     */
    public void setModelo(IDAOJugadorPostgre<Jugador> modelo) {
        this.dao = modelo;
    }

    /**
     * Realiza la operacion elegida en el menu y sale un mensaje si se realiza
     * bien o no param el numero de la operacion deseada
     */
    public void operacion(int op) {
        String mensaje;
        Jugador aux;
        switch (op) {
            case 1: //insercion
                aux = vista.getObject();
                dao.alta(aux);
                break;
            case 2: //eliminacion
                aux = vista.getObject();
                dao.baja(aux);
                break;

            case 3: //modificacion
                aux = vista.getObject();
                dao.modificacion(aux);
                break;
            case 4: //consulta
                aux = vista.getObject();
                dao.consulta(aux);
                break;
            case 5: //listar por nombre             
                dao.listaNombre();
                break;
            case 6: //listar por xp             
                dao.listaExperiencia();
                break;
            case 7: // top10             
                dao.top10xp();
                break;
            default:
                throw new AssertionError();
        }
    }
}

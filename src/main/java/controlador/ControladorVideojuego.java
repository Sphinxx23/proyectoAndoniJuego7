package controlador;

import dao.IDAOVideojuegoPostgre;
import modelo.Videojuego;
import vista.IVistaV;

public class ControladorVideojuego {

    private IVistaV<Videojuego> vista;

    private IDAOVideojuegoPostgre<Videojuego> dao;

    public ControladorVideojuego(IVistaV vista) {
        this.vista = vista;
    }

    /**
     * Settea el modelo param el modelo dao
     */
    public void setModelo(IDAOVideojuegoPostgre<Videojuego> modelo) {
        this.dao = modelo;
    }

    /**
     * Realiza la operacion elegida en el menu y sale un mensaje si se realiza
     * bien o no param el numero de la operacion deseada
     */
    public void operacion(int op) {
        String mensaje;
        Videojuego aux;
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
            case 4: //listar
                dao.listar();
                break;
            case 5: //consulta playercount            
                aux = vista.getObject();
                dao.jugadoresRegistrados(aux);
                break;
            case 6: //sesiones totales             
                aux = vista.getObject();
                dao.sesionesTotales(aux);
                break;
            case 7: // ultima sesion             
                aux = vista.getObject();
                dao.ultimaSesion(aux);
                break;
            default:
                throw new AssertionError();
        }
    }
}

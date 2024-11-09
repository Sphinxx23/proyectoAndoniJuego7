/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ConfiguracionDAO;
import dao.JugadorDAOSQLite;
import modelo.Jugador;
import vista.Vista;

/**
 *
 * @author andon
 */
public class ControladorConfiguracion {
    
    ConfiguracionDAO confDAO = new ConfiguracionDAO();
    JugadorDAOSQLite j;
    Vista vista;
    
    public ControladorConfiguracion(Vista vista) {
        this.vista = vista;
    }

    public void setModelo(JugadorDAOSQLite modelo) {
        this.j = modelo;
    }
    
    public void actualizarConfiguracion(int idJugador, boolean configSonido, String confResolucion, String confLenguaje){
        if (confDAO.actualizarConfiguracion(idJugador, configSonido, confResolucion, confLenguaje)) {
            vista.mensaje("Configuración actualizada correctamente.");
        }else{
            vista.mensaje("La configuracion no se ha podido aplicar.");
        }
    }
    
}

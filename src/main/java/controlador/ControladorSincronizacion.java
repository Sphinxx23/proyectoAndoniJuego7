/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dao.ConfiguracionDAO;
import dao.JugadorDAOSQLite;
import dao.SincronizarDAO;
import java.sql.SQLException;
import vista.Vista;

/**
 *
 * @author andon
 */
public class ControladorSincronizacion {
    SincronizarDAO sincDAO = new SincronizarDAO();
    Vista vista;
    
    public ControladorSincronizacion(Vista vista) {
        this.vista = vista;
    }

    public void setModelo(SincronizarDAO modelo) {
        this.sincDAO = modelo;
    }

    public boolean sincronizarBasesNubeALocal() throws SQLException {
        return sincDAO.sincronizarBasesNubeALocal();
    }
    
    public boolean sincronizarLocalANube() throws SQLException{
        return sincDAO.sincronizarLocalANube();
    }
}

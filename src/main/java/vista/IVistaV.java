/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorVideojuego;

/**
 *
 * @author PcBox
 */
public interface IVistaV<T> {
    
    public abstract T getObject();

    public abstract void setObject(T Videojuego);

    public abstract void show();

    public abstract void setControlador(ControladorVideojuego c);

    public abstract ControladorVideojuego getControlador();

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorJugador;

public interface IVistaJ<T> {

    public abstract T getObject();

    public abstract void setObject(T Jugador);

    public abstract void show();

    public abstract void setControlador(ControladorJugador c);

    public abstract ControladorJugador getControlador();

}

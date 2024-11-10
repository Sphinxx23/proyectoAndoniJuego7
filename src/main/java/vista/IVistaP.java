/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorPartida;

public interface IVistaP<T> {

    public abstract T getObject();

    public abstract void setObject(T Partida);

    public abstract void show();

    public abstract void setControlador(ControladorPartida c);

    public abstract ControladorPartida getControlador();
}

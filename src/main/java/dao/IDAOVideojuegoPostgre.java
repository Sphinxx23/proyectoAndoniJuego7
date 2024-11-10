/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author PcBox
 */
public interface IDAOVideojuegoPostgre<T> {

    public abstract void alta(T o);

    public abstract void modificacion(T nuevo);

    public abstract void baja(T viejo);

    public abstract void jugadoresRegistrados( T elegido);

    public abstract void sesionesTotales(T elegido);

    public abstract void ultimaSesion(T elegido);
    
    public abstract void listar();

}

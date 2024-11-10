/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorVideojuego;
import java.time.LocalDateTime;
import java.util.Scanner;
import modelo.Videojuego;

/**
 *
 * @author PcBox
 */
public class VistaVideojuego implements IVistaV<Videojuego> {

    private ControladorVideojuego controlador;
    private Videojuego a;

    public VistaVideojuego() {
        a = new Videojuego();
    }

    @Override
    public Videojuego getObject() {
        return a;
    }

    @Override
    public void setObject(Videojuego videojuego) {
        this.a = videojuego;
    }

    private Videojuego altaVid() {
        Scanner sc = new Scanner(System.in);
        a = new Videojuego();

        boolean b = false;
        int recInt = 0;
        String tamano;

        do {
            System.out.println("ISBN:  (Maximo 8 caracteres)");
            tamano = sc.nextLine();

        } while (tamano.length() > 8);
        a.setIsbn(tamano);

        do {
            System.out.println("Titulo:  (Maximo 20 caracteres)");
            tamano = sc.nextLine();

        } while (tamano.length() > 20);
        a.setTitle(tamano);

        do {
            System.out.println("Total de Jugadores:");
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;

            }

        } while (b);
        a.setPlayer_count(recInt);
        b = true;

        do {
            System.out.println("Sesiones totales:");
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;

            }

        } while (b);
        a.setTotal_sessions(recInt);
        b = true;

        LocalDateTime fechaHoraActual = LocalDateTime.now();
        a.setLast_session(fechaHoraActual);

        return a;
    }

    /**
     * Devuelve el jugador que quieres dar de baja o consultar por ID return
     * jugador deseado
     */
    public Videojuego bajaVid() {
        Scanner sc = new Scanner(System.in);
        a = new Videojuego();
        String tamano;

        do {
            System.out.println("ISBN:  (Maximo 8 caracteres)");
            tamano = sc.nextLine();

        } while (tamano.length() > 8);
        a.setIsbn(tamano);

        return a;
    }

    @Override
    public void show() {

        Scanner sc = new Scanner(System.in);
        int recogerNum = 0;
        boolean b = false;

        do {
            a = new Videojuego();
            System.out.println("\n\nElige que accion quieres realizar:\n 1- Alta\n 2- Baja\n "
                    + "3- Modificar\n 4- Lista los Videojuegos\n 5- Consulta de total de jugadores de x videojuego\n"
                    + " 6- Consulta de total de sesiones de x videojuego\n 7- Ultima sesion de x videojuego \n 8- VOLVER AL MENU GENERAL");

            do {
                try {
                    recogerNum = Integer.parseInt(sc.nextLine());
                    b = false;

                } catch (Exception e) {
                    b = true;
                    System.out.println("Caracter no valido intente de nuevo");
                }

            } while (b);

            switch (recogerNum) {
                case 1:
                    System.out.println("Introduce los datos del videojuego a dar de alta:");
                    a = altaVid();

                    break;
                case 2:
                    System.out.println("Introduce el ISBN del videojuego a dar de baja");
                    a = bajaVid();

                    break;
                case 3:
                    System.out.println("Introduce los datos del videojuego a Modificar:");
                    a = altaVid();
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Introduce el ISBN del videojuego a consultar");
                    a = bajaVid();
                    break;
                case 6:
                    System.out.println("Introduce el ISBN del videojuego a consultar");
                    a = bajaVid();
                    break;
                case 7:
                    System.out.println("Introduce el ISBN del videojuego a consultar");
                    a = bajaVid();
                    break;
                default:
                    System.out.println("Numero no valido, tiene que estar comprendido entre el 1-8");
            }

            if (recogerNum > 0 && recogerNum < 8) {
                controlador.operacion(recogerNum);
            }

        } while (recogerNum != 8);

        for (int i = 0; i < 50; i++) {
            System.out.println(" ");
        }

    }

    @Override
    public void setControlador(ControladorVideojuego c) {
        this.controlador = c;
    }

    @Override
    public ControladorVideojuego getControlador() {
        return controlador;
    }

}

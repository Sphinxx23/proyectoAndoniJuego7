/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorJugador;
import java.time.LocalDateTime;
import java.util.Scanner;
import modelo.Jugador;

/**
 *
 * @author PcBox
 */
public class VistaJugador implements IVistaJ<Jugador> {

    private ControladorJugador controlador;
    private Jugador a;

    public VistaJugador() {
        a = new Jugador();
    }

    @Override
    public Jugador getObject() {
        return a;
    }

    @Override
    public void setObject(Jugador Jugador) {
        this.a = Jugador;
    }
    
     private Jugador altaJug() {
        Scanner sc = new Scanner(System.in);
        a = new Jugador();

        boolean b = false;
        int recInt = 0;
        String tamano;

        do {
            System.out.println("ID:");
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;

            }

        } while (b);
        a.setUser_id(recInt);
        b = true;

        do {
            System.out.println("Nick:  (Maximo 8 caracteres)");
            tamano = sc.nextLine();

        } while (tamano.length() > 8);
        a.setNick_name(tamano);

        do {
            System.out.println("Experiencia:");
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;

            }

        } while (b);
        a.setExperience(recInt);
        b = true;

        do {
            System.out.println("Nivel de vida:");
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;

            }

        } while (b);
        a.setLife_level(recInt);
        b = true;

        do {
            System.out.println("Cantidad de monedas");
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;

            }

        } while (b);
        a.setCoins(recInt);
        b = true;

        do {
            System.out.println("Cantidad de sesiones");
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;

            }

        } while (b);
        a.setSession_count(recInt);
        b = true;

         LocalDateTime fechaHoraActual = LocalDateTime.now();
        a.setLast_login(fechaHoraActual);
        
        return a;
    }

    /**
     * Devuelve el jugador que quieres dar de baja o consultar por ID
     * return jugador deseado 
     */
    public Jugador bajaJug() {
        Scanner sc = new Scanner(System.in);
        a = new Jugador();
        boolean b = false;
        int recInt = 0;

        do {
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;
                System.out.println("ID:");
            }

        } while (b);
        a.setUser_id(recInt);

        return a;
    }
    
    

    @Override
    public void show() {

        Scanner sc = new Scanner(System.in);
        int recogerNum = 0;
        boolean b = false;

        do {
            a = new Jugador();
            System.out.println("\n\nElige que accion quieres realizar:\n 1- ALTA\n 2- BAJA\n "
                    + "3- Modificar\n 4- CONSULTA ID\n 5- LISTAR POR NOMBRE\n 6- LISTAR POR EXPERIENCIA \n 7- TOP 10 \n 8- VOLVER AL MENU GENERAL");

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
                    System.out.println("Introduce los datos del jugador a dar de alta:");
                    a = altaJug();

                    break;
                case 2:
                    System.out.println("Introduce el ID del jugador a dar de baja");
                    a = bajaJug();

                    break;
                case 3:
                    System.out.println("Introduce los datos del jugador a Modificar:");
                    a = altaJug();
                    break;
                case 4:
                    System.out.println("Introduce el ID del jugador a Mostrar");
                    a = bajaJug();
                    break;
                case 5,6,7:
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
    public void setControlador(ControladorJugador c) {
        this.controlador = c;
    }

    @Override
    public ControladorJugador getControlador() {
        return controlador;
    }

}

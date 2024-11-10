/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.ControladorPartida;
import java.time.LocalDateTime;
import java.util.Scanner;
import modelo.Partida;

/**
 *
 * @author PcBox
 */
public class VistaPartida implements IVistaP<Partida>{

   
    private ControladorPartida controlador;
    private Partida a;

    public VistaPartida() {
        a = new Partida();
    }

    @Override
    public Partida getObject() {
        return a;
    }

    @Override
    public void setObject(Partida partida) {
        this.a = partida;
    }

    private Partida altaPart() {
        Scanner sc = new Scanner(System.in);
        a = new Partida();

        boolean b = false;
        int recInt = 0;
        String tamano;

        do {
            System.out.println("ISBN del videojuego:  (Maximo 8 caracteres)");
            tamano = sc.nextLine();

        } while (tamano.length() > 8);
        a.setIsbn(tamano);

        do {
            System.out.println("ID del jugador:");
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
            System.out.println("Experiencia :");
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
            System.out.println("Nivel de Vida:");
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
            System.out.println("Cantidad de monedas:");
            try {
                recInt = Integer.parseInt(sc.nextLine());
                b = false;

            } catch (Exception e) {
                b = true;

            }

        } while (b);
        a.setCoins(recInt);
        b = true;
        

        LocalDateTime fechaHoraActual = LocalDateTime.now();
        a.setSession_date(fechaHoraActual);

        return a;
    }
    
     @Override
    public void show() {

        Scanner sc = new Scanner(System.in);
        int recogerNum = 0;
        boolean b = false;

        do {
            a = new Partida();
            System.out.println("\n\nElige que accion quieres realizar:\n 1- Alta\n 2- VOLVER AL MENU GENERAL\n");

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
                    System.out.println("Introduce los datos de la partida a jugar:");
                    a = altaPart();

                    break;

                default:
                    System.out.println("Numero no valido, tiene que estar comprendido entre el 1-2");
            }

            if (recogerNum > 0 && recogerNum < 2) {
                controlador.operacion(recogerNum);
            }

        } while (recogerNum != 2);

        for (int i = 0; i < 50; i++) {
            System.out.println(" ");
        }

    }

    @Override
    public void setControlador(ControladorPartida c) {
        this.controlador = c;
    }

    @Override
    public ControladorPartida getControlador() {
        return controlador;
    }
    
}

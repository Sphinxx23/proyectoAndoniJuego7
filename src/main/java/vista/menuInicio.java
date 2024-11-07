/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.Scanner;

/**
 *
 * @author Vespertino
 */
public class menuInicio {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("""
                             ____  _                           _     _         _ 
                            |  _ \\(_)                         (_)   | |       | |
                            | |_) |_  ___ _ ____   _____ _ __  _  __| | ___   | |
                            |  _ <| |/ _ \\ '_ \\ \\ / / _ \\ '_ \\| |/ _` |/ _ \\  | |
                            | |_) | |  __/ | | \\ V /  __/ | | | | (_| | (_) | |_|
                            |____/|_|\\___|_| |_|\\_/ \\___|_| |_|_|\\__,_|\\___/  (_)
                                                                                 
                                                                                 """);
        System.out.println("");
        System.out.print("Por favor, escribe tu id de jugador para iniciar sesión: ");
        int idJugador = sc.nextInt();

        if (idJugador == 1) {
            inicioJugador();
        } else {
            System.out.println("Error, ese id no esta registrado, contacte con el administrador.");
        }
    }

    private static void inicioJugador() {
        System.out.println("Elige una opción: ");
        System.out.println("1. Jugar a un juego");
        System.out.println("2. Configuración");
        System.out.println("3. Salir");

        int opJugador = sc.nextInt();

        switch (opJugador) {
            case 1:
                menuJuegos();
                break;
            case 2:
                menuConfiguracion();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                throw new AssertionError();
        }
    }

    private static void menuJuegos() {
        System.out.println("Elige la forma en la que quieres jugar: ");
        System.out.println("1. En linea");
        System.out.println("2. Sin conexión");

        int opJuegos = sc.nextInt();

        switch (opJuegos) {
            case 1:
                menuJuegosEnLinea();
                break;
            case 2:
                menuJuegosSinConexion();
                break;
            default:
                throw new AssertionError();
        }
    }

    private static void menuConfiguracion() {
        
    }

    private static void menuJuegosEnLinea() {

    }

    private static void menuJuegosSinConexion() {

    }

}

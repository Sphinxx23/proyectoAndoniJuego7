package vista;

import controlador.ControladorVideojuegos;
import controlador.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import modelo.Jugador;

public class Vista {

    private static final Scanner sc = new Scanner(System.in);
    private Jugador j;
    private int idJugador, opJuegosConex, servidorLinea;
    private ControladorConfiguracion controlConfig;
    private ControladorJugador controlJugador;
    private ControladorVideojuegos controlJuegos;

    public Vista() {
        j = new Jugador();
    }

    public void setControladorConfig(ControladorConfiguracion c) {
        this.controlConfig = c;
    }

    public void setControladorJugador(ControladorJugador c) {
        this.controlJugador = c;
    }
    
    public void setControladorJuegos(ControladorVideojuegos c) {
        this.controlJuegos = c;
    }

    public void vistaInicio() {
        System.out.println("""
                                    ____  _                           _     _         _
                                   |  _ \\(_)                         (_)   | |       | |
                                   | |_) |_  ___ _ ____   _____ _ __  _  __| | ___   | |
                                   |  _ <| |/ _ \\ '_ \\ \\ / / _ \\ '_ \\| |/ _` |/ _ \\  | |
                                   | |_) | |  __/ | | \\ V /  __/ | | | | (_| | (_) | |_|
                                   |____/|_|\\___|_| |_|\\_/ \\___|_| |_|_|\\__,_|\\___/  (_)
                                                                                                """);
        System.out.println();

        menuJuegos();

        //Consultar si el jugador existe en la BD.
//        if (controlJugador.verificarExistenciaJugador(idJugador)) {
//            vaciarPantalla();
//            inicioJugador();
//        } else {
//            vaciarPantalla();
//            System.out.println("##################################################################");
//            System.out.println("Error, ese id no esta registrado, contacte con el administrador.");
//            System.out.println("##################################################################");
//            System.out.println();
//            vistaInicio();
//        }
    }

    private void inicioJugador() {
        int opJugador;

        do {
            System.out.println("####################");
            System.out.println("Elige una opcion: ");
            System.out.println("1. Jugar a un juego");
            System.out.println("2. Configuracion");
            System.out.println("3. Salir");
            System.out.println("####################");

            System.out.print("Indique una opcion: ");
            opJugador = sc.nextInt();

            if (opJugador < 1 || opJugador > 3) {
                System.out.println("Opcion incorrecta.");
                System.out.println();
            }
        } while (opJugador < 1 || opJugador > 3);

        switch (opJugador) {
            case 1:
                vaciarPantalla();
                eligeJugador();
                break;
            case 2:
                vaciarPantalla();
                menuConfiguracion();
                break;
            case 3:
                vaciarPantalla();
                mensajeSalida();
                System.exit(0);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void menuJuegos() {

        do {
            System.out.println("#########################################");
            System.out.println("Elige la forma en la que quieres jugar: ");
            System.out.println("1. En linea");
            System.out.println("2. Sin conexion");
            System.out.println("#########################################");
            System.out.println();
            System.out.print("Indique la opcion: ");

            opJuegosConex = sc.nextInt();

            if (opJuegosConex < 1 || opJuegosConex > 2) {
                System.out.println("Opcion incorrecta.");
                System.out.println();
            }
        } while (opJuegosConex < 1 || opJuegosConex > 2);

        switch (opJuegosConex) {
            case 1:
                vaciarPantalla();
                eligeServidorLinea();
                vaciarPantalla();
                inicioJugador();
                break;
            case 2:
                vaciarPantalla();
                menuJuegosSinConexion();
                break;
            default:
                throw new AssertionError();
        }
    }

    //Que el menu de configuracion pueda ver las configuraciones que tiene ya el usuario y pueda
    // cambiarlas si selecciona una opcion.
    private void menuConfiguracion() {
        String confSonido, confResolucion, confLenguaje;
        int opConfLenguaje;

        System.out.println("###############################");
        System.out.println("Cambio de configuraciones...");
        System.out.println("###############################");
        System.out.println();
        sc.nextLine();

        System.out.print("¿Desea activar el sonido de los juegos? Si / No: ");
        do {
            confSonido = sc.nextLine();
            if (!confSonido.equals("Si") && !confSonido.equals("No")) {
                System.out.println("Opcion incorrecta.");
                System.out.println();
                System.out.print("¿Desea activar el sonido de los juegos? Si / No: ");
            }
        } while (!confSonido.equals("Si") && !confSonido.equals("No"));

        System.out.print("Indique la resolucion que quiere aplicar: ");
        confResolucion = sc.nextLine();

        do {
            System.out.println("###################");
            System.out.println("Eliga el idioma: ");
            System.out.println("1. Castellano");
            System.out.println("2. Ingles");
            System.out.println("###################");
            System.out.println();

            System.out.print("Indique la opción: ");
            opConfLenguaje = sc.nextInt();

            if (opConfLenguaje < 1 || opConfLenguaje > 2) {
                System.out.println("Opcion incorrecta.");
                System.out.println();
            }

        } while (opConfLenguaje < 1 || opConfLenguaje > 2);

        switch (opConfLenguaje) {
            case 1:
                confLenguaje = "Castellano";
                break;
            case 2:
                confLenguaje = "Ingles";
                break;
            default:
                throw new AssertionError();
        }

        aplicarConfiguraciones(idJugador, confSonido, confResolucion, confLenguaje);
    }

    private static void menuJuegosEnLinea() {
        //Obtener los juegos de la BD en una lista para asi poder listarlos y proximamente
        // dar a elegir al usuario y que se realice una consulta para jugar con ese juego.
    }

    private static void menuJuegosSinConexion() {
        //Obtener los juegos de la BD en una lista para asi poder listarlos y proximamente
        // dar a elegir al usuario y que se realice una consulta para jugar con ese juego.
    }

    private static void vaciarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private void aplicarConfiguraciones(int idJugador, String confSonido, String confResolucion, String confLenguaje) {
        boolean confSonidoBool;

        confSonidoBool = confSonido.equals("Si");

        controlConfig.actualizarConfiguracion(idJugador, confSonidoBool, confResolucion, confLenguaje);
    }

    private static void mensajeSalida() {
        System.out.println("""
                            ___  ___  ________  ________  _________  ________                   
                           |\\  \\|\\  \\|\\   __  \\|\\   ____\\|\\___   ___\\\\   __  \\                  
                           \\ \\  \\\\\\  \\ \\  \\|\\  \\ \\  \\___|\\|___ \\  \\_\\ \\  \\|\\  \\                 
                            \\ \\   __  \\ \\   __  \\ \\_____  \\   \\ \\  \\ \\ \\   __  \\                
                             \\ \\  \\ \\  \\ \\  \\ \\  \\|____|\\  \\   \\ \\  \\ \\ \\  \\ \\  \\               
                              \\ \\__\\ \\__\\ \\__\\ \\__\\____\\_\\  \\   \\ \\__\\ \\ \\__\\ \\__\\              
                               \\|__|\\|__|\\|__|\\|__|\\_________\\   \\|__|  \\|__|\\|__|              
                                                  \\|_________|                                  
                                                                                                
                                                                                                
                            ___       ___  ___  _______   ________  ________          ___       
                           |\\  \\     |\\  \\|\\  \\|\\  ___ \\ |\\   ____\\|\\   __  \\        |\\  \\      
                           \\ \\  \\    \\ \\  \\\\\\  \\ \\   __/|\\ \\  \\___|\\ \\  \\|\\  \\       \\ \\  \\     
                            \\ \\  \\    \\ \\  \\\\\\  \\ \\  \\_|/_\\ \\  \\  __\\ \\  \\\\\\  \\       \\ \\  \\    
                             \\ \\  \\____\\ \\  \\\\\\  \\ \\  \\_|\\ \\ \\  \\|\\  \\ \\  \\\\\\  \\       \\ \\__\\   
                              \\ \\_______\\ \\_______\\ \\_______\\ \\_______\\ \\_______\\       \\|__|   
                               \\|_______|\\|_______|\\|_______|\\|_______|\\|_______|           ___ 
                                                                                           |\\__\\
                                                                                           \\|__|
                                                                                                """);
    }

    public void mensaje(String mensaje) {
        System.out.println(mensaje);
    }

    private void eligeServidorLinea() {

        do {
            System.out.println("####################");
            System.out.println("Elige el servidor:");
            System.out.println("1. PostgreSQL");
            System.out.println("2. MySQL");
            System.out.println("####################");

            servidorLinea = sc.nextInt();

            if (servidorLinea < 1 || servidorLinea > 2) {
                System.out.println("Opcion incorrecta.");
                System.out.println();
            }
        } while (servidorLinea < 1 || servidorLinea > 2);

    }

    private void eligeJugador() {
        Scanner sc = new Scanner(System.in);
        boolean existeNombreJugador;
        List<String> listaJugadores = new LinkedList();

        listaJugadores = controlJugador.obtenerJugadores(servidorLinea);

        if (mostrarJugadores(listaJugadores)) {
            do {
                
                System.out.print("Escribe el nombre del jugador que quieres usar: ");
                String nombreJugador = sc.nextLine();

                existeNombreJugador = comprobarNombreJugador(nombreJugador, servidorLinea);

                // Si existe enseñar los juegos que puede jugar y si no repetir la solicitud del nombre del jugador.
                if (existeNombreJugador) {
                    vaciarPantalla();
                    mostrarJuegosServidor();
                } else {
                    System.out.println("No existe ese nombre de jugador.");
                    vaciarPantalla();
                }
            } while (!existeNombreJugador);
        } else {
            inicioJugador();
        }

    }

    private boolean mostrarJugadores(List<String> listaJugadores) {
        if (listaJugadores == null) {
            vaciarPantalla();
            System.out.println("No hay jugadores.");
            return false;
        } else {
            System.out.println("""
                                  __     __  __     ______     ______     _____     ______     ______     ______     ______    
                                 /\\ \\   /\\ \\/\\ \\   /\\  ___\\   /\\  __ \\   /\\  __-.  /\\  __ \\   /\\  == \\   /\\  ___\\   /\\  ___\\   
                                _\\_\\ \\  \\ \\ \\_\\ \\  \\ \\ \\__ \\  \\ \\  __ \\  \\ \\ \\/\\ \\ \\ \\ \\/\\ \\  \\ \\  __<   \\ \\  __\\   \\ \\___  \\  
                               /\\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\____-  \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\/\\_____\\ 
                               \\/_____/   \\/_____/   \\/_____/   \\/_/\\/_/   \\/____/   \\/_____/   \\/_/ /_/   \\/_____/   \\/_____/ 
                                                                                                                               """);
            System.out.println("#######################################################");
            System.out.println(String.format("%-15s %-12s %-15s %-6s", "Nombre", "Experiencia", "Nivel de vida", "Monedas"));
            for (String jugador : listaJugadores) {
                System.out.println(jugador);
            }
            System.out.println("#######################################################");
            return true;
        }
    }

    private boolean comprobarNombreJugador(String nombreJugador, int servidor) {
        return controlJugador.comprobarNombreJugador(nombreJugador, servidor);
    }

    private boolean mostrarJuegosServidor() {
        List<String> listaJuegos = new LinkedList();
        
        listaJuegos = controlJuegos.obtenerJuegos(servidorLinea);

        if (listaJuegos == null) {
            vaciarPantalla();
            System.out.println("No hay juegos.");
            return false;
        } else {
            System.out.println("""
                                  __     __  __     ______     ______     ______     ______    
                                 /\\ \\   /\\ \\/\\ \\   /\\  ___\\   /\\  ___\\   /\\  __ \\   /\\  ___\\   
                                _\\_\\ \\  \\ \\ \\_\\ \\  \\ \\  __\\   \\ \\ \\__ \\  \\ \\ \\/\\ \\  \\ \\___  \\  
                               /\\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\/\\_____\\ 
                               \\/_____/   \\/_____/   \\/_____/   \\/_____/   \\/_____/   \\/_____/ 
                                                                                               """);
            System.out.println("#######################################################");
            System.out.println(String.format("%-15s %-12s", "ISBN", "Nombre Juego"));
            for (String juego : listaJuegos) {
                System.out.println(juego);
            }
            System.out.println("#######################################################");
            return true;
        }
    }

    

}

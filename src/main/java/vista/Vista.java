package vista;

import controlador.ControladorVideojuegos;
import controlador.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import modelo.Jugador;

public class Vista {

    private static final Scanner sc = new Scanner(System.in);
    private Jugador j;
    private int idJugador, opJuegosConex, servidorLinea;
    private ControladorConfiguracion controlConfig;
    private ControladorJugador controlJugador;
    private ControladorVideojuegos controlJuegos;
    private ControladorPartida controlPartida;
    private ControladorSincronizacion controlSincronizacion;

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

    public void setControladorPartidas(ControladorPartida c) {
        this.controlPartida = c;
    }

    public void setControladorSinc(ControladorSincronizacion c) {
        this.controlSincronizacion = c;
    }

    public void vistaInicio() throws ClassNotFoundException {
        System.out.println("""
                                    ____  _                           _     _         _
                                   |  _ \\(_)                         (_)   | |       | |
                                   | |_) |_  ___ _ ____   _____ _ __  _  __| | ___   | |
                                   |  _ <| |/ _ \\ '_ \\ \\ / / _ \\ '_ \\| |/ _` |/ _ \\  | |
                                   | |_) | |  __/ | | \\ V /  __/ | | | | (_| | (_) | |_|
                                   |____/|_|\\___|_| |_|\\_/ \\___|_| |_|_|\\__,_|\\___/  (_)
                                                                                                """);
        System.out.println();

        if (sincronizacionBasesNubeALocal()) {
            menuServidores();
        } else {
            System.out.println("Los servidores no estan funcionando bien.");
        }

    }

    private void menuEnLinea() {
        int opJugador;

        do {
            System.out.println("Menu en linea: ");
            System.out.println("####################");
            System.out.println("Elige una opcion: ");
            System.out.println("1. Jugar a un juego");
            System.out.println("2. Salir");
            System.out.println("####################");

            System.out.print("Indique una opcion: ");
            opJugador = sc.nextInt();

            if (opJugador < 1 || opJugador > 2) {
                System.out.println("Opcion incorrecta.");
                System.out.println();
            }
        } while (opJugador < 1 || opJugador > 2);

        switch (opJugador) {
            case 1:
                vaciarPantalla();
                eligeJugador();
                break;
            case 2:
                vaciarPantalla();
                menuServidores();
                break;
            default:
                throw new AssertionError();
        }
    }

    private void menuServidores() {

        do {
            System.out.println("#########################################");
            System.out.println("Elige la forma en la que quieres jugar: ");
            System.out.println("1. En linea");
            System.out.println("2. Sin conexion");
            System.out.println("3. Salir de la aplicacion.");
            System.out.println("#########################################");
            System.out.println();
            System.out.print("Indique la opcion: ");

            opJuegosConex = sc.nextInt();

            if (opJuegosConex < 1 || opJuegosConex > 3) {
                System.out.println("Opcion incorrecta.");
                System.out.println();
            }
        } while (opJuegosConex < 1 || opJuegosConex > 3);

        switch (opJuegosConex) {
            case 1:
                vaciarPantalla();
                eligeServidorLinea();
                vaciarPantalla();
                menuEnLinea();
                break;
            case 2:
                servidorLinea = 3;
                vaciarPantalla();
                menuSinConexion();
                break;
            case 3:
                vaciarPantalla();
                guardarCambiosLocalANube();
                mensajeSalida();
                break;
            default:
                throw new AssertionError();
        }
    }

    //Que el menu de configuracion pueda ver las configuraciones que tiene ya el usuario y pueda
    // cambiarlas si selecciona una opcion.
    private void menuConfiguracion() {
        Scanner sc = new Scanner(System.in);
        String respuesta;
        
        controlConfig.mostrarConfiguracion();

        do {
            System.out.println("Quieres cambiar las configuraciones? Escriba SI o NO");
            respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("SI")) {
                cambiarConfiguracion();
                menuSinConexion();
            } else if (respuesta.equalsIgnoreCase("NO")) {
                menuSinConexion();
            } else {
                System.out.println("Opcion incorrecta.");
            }
        } while (!respuesta.equalsIgnoreCase("SI") && !respuesta.equalsIgnoreCase("NO"));

    }

    private void cambiarConfiguracion() throws AssertionError {
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
            System.out.println("1. Espanol");
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
                confLenguaje = "Espanol";
                break;
            case 2:
                confLenguaje = "Ingles";
                break;
            default:
                throw new AssertionError();
        }
        aplicarConfiguraciones(confSonido, confResolucion, confLenguaje);
    }

    private void menuSinConexion() {
        int opJugador;

        do {
            System.out.println("Menu sin conexion:");
            System.out.println("####################");
            System.out.println("Elige una opcion: ");
            System.out.println("1. Jugar a un juego");
            System.out.println("2. Configuracion");
            System.out.println("3. Guardar cambios");
            System.out.println("4. Salir");
            System.out.println("####################");

            System.out.print("Indique una opcion: ");
            opJugador = sc.nextInt();

            if (opJugador < 1 || opJugador > 4) {
                System.out.println("Opcion incorrecta.");
                System.out.println();
            }
        } while (opJugador < 1 || opJugador > 4);

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
                if (guardarCambiosLocalANube()) {
                    System.out.println("Cambios guardados correctamente.");
                } else {
                    System.out.println("No se han podido guardar los cambios.");
                }
                break;
            case 4:
                vaciarPantalla();
                menuServidores();
                break;
            default:
                throw new AssertionError();
        }
    }

    private static void vaciarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private void aplicarConfiguraciones(String confSonido, String confResolucion, String confLenguaje) {
        boolean confSonidoBool;

        confSonidoBool = confSonido.equals("Si");

        controlConfig.actualizarConfiguracion(confSonidoBool, confResolucion, confLenguaje);
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
        boolean existeJugador, jugadorSinDescargar;
        String nombreJugador = "";
        List<String> listaJugadores = new LinkedList();

        listaJugadores = controlJugador.obtenerJugadores(servidorLinea);

        if (mostrarJugadores(listaJugadores)) {
            do {

                System.out.print("Escribe el id del jugador que quieres usar: ");
                int idJugadorJuego = sc.nextInt();

                if (servidorLinea == 3) {
                    System.out.print("Para asegurar, escribe el nombre de usuario: ");
                    sc.nextLine();
                    nombreJugador = sc.nextLine();
                }

                existeJugador = comprobarJugador(idJugadorJuego, nombreJugador);

                if (servidorLinea != 3) {
                    jugadorSinDescargar = comprobarJugadorSinDescargar(idJugadorJuego);

                    if (jugadorSinDescargar) {
                        System.out.println("El jugador ha sido descargado en la base de datos local.");
                    }
                }

                // Si existe enseñar los juegos que puede jugar y si no repetir la solicitud del nombre del jugador.
                if (existeJugador) {
                    vaciarPantalla();
                    if (mostrarJuegosServidor(idJugadorJuego, nombreJugador)) {
                        elegirJuego(idJugadorJuego, nombreJugador);
                    } else {
                        if (servidorLinea == 3) {
                            menuSinConexion();
                        } else {
                            menuEnLinea();
                        }
                    }

                } else {
                    System.out.println("No existe ese nombre de jugador.");
                }
            } while (!existeJugador);
        } else {
            if (servidorLinea == 3) {
                menuSinConexion();
            } else {
                menuEnLinea();
            }
        }

    }

    private boolean mostrarJugadores(List<String> listaJugadores) {
        if (listaJugadores.isEmpty()) {
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
            System.out.println(String.format("%-6s%-15s %-12s %-15s %-6s", "ID", "Nombre", "Experiencia", "Nivel de vida", "Monedas"));
            for (String jugador : listaJugadores) {
                System.out.println(jugador);
            }
            System.out.println("#######################################################");
            return true;
        }
    }

    private boolean comprobarJugador(int idJugadorJuego, String nombreJugador) {
        return controlJugador.comprobarJugador(idJugadorJuego, nombreJugador, servidorLinea);
    }

    private boolean mostrarJuegosServidor(int idJugadorJuego, String nombreJuego) {
        List<String> listaJuegos = new LinkedList();

        listaJuegos = controlJuegos.obtenerJuegos(servidorLinea, idJugadorJuego, nombreJuego);

        if (listaJuegos == null) {
            vaciarPantalla();
            if (servidorLinea == 3) {
                System.out.println("No tienes ningun juego descargado.");
            } else {
                System.out.println("No hay juegos en el servidor.");
            }
            return false;
        } else {
            mostrarJuegos(listaJuegos);
            return true;
        }
    }

    private void mostrarJuegos(List<String> listaJuegos) {
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
    }

    private void elegirJuego(int idJugadorJuego, String nombreJugador) {
        Scanner sc = new Scanner(System.in);
        boolean existeJuego, juegoSinDescargar;
        String isbnJuego, nombreJuego = "";
        //COMPROBAR QUE EL JUEGO EXISTE
        do {
            System.out.print("Escribe el isbn del juego que quieres jugar: ");
            isbnJuego = sc.nextLine();

            if (servidorLinea == 3) {
                System.out.print("Para asegurar, escriba el nombre del juego: ");
                nombreJuego = sc.nextLine();
            }

            existeJuego = comprobarJuego(isbnJuego, nombreJuego);

            if (!existeJuego) {
                System.out.println("El juego indicado no existe.");
            }

            if (servidorLinea != 3) {
                juegoSinDescargar = comprobarJuegoSinDescargar(isbnJuego);

                if (juegoSinDescargar) {
                    System.out.println("El juego ha sido instalado.");
                }
            }

        } while (!existeJuego);

        simularPartida(isbnJuego, nombreJuego, idJugadorJuego, nombreJugador);
    }

    private void simularPartida(String isbnJuego, String nombreJuego, int idJugadorJuego, String nombreJugador) {
        List<Integer> cambiosPartidaJugada = new LinkedList();

        System.out.println("Iniciando el juego...");

        cargarBarraJuego();

        cambiosPartidaJugada = controlPartida.simularPartida();

        mostrarMensajePartidaSimulada(cambiosPartidaJugada);
        mostrarDatosPartidaSimulada(cambiosPartidaJugada);

        if (crearPartidaSimulada(cambiosPartidaJugada, idJugadorJuego, nombreJugador, isbnJuego, nombreJuego)) {
            System.out.println("La partida se ha actualizado correctamente.");
        } else {
            System.out.println("La partida no se ha podido actualizar correctamente.");
        }

        if (actualizarDatosJugadorSimulada(cambiosPartidaJugada, idJugadorJuego, nombreJugador)) {
            System.out.println("Los datos del jugador se han actualizado correctamente.");
        } else {
            System.out.println("Los datos del jugador no se han podido actualizar.");
        }

        if (actualizarVideojuego(isbnJuego, nombreJuego, idJugadorJuego, nombreJugador)) {
            System.out.println("Los datos del juego se ha actualizado.");
        } else {
            System.out.println("Los datos del juego no se ha podido actualizar.");
        }

        if (servidorLinea == 1 || servidorLinea == 2) {
            menuEnLinea();
        } else {
            menuSinConexion();
        }

    }

    public static void cargarBarraJuego() {
        int total = 25; // Total de caracteres en la barra de progreso
        System.out.println("Cargando juego...");

        for (int i = 0; i <= total; i++) {
            vaciarPantalla();

            // Construir la barra de progreso
            StringBuilder barra = new StringBuilder("[");
            for (int j = 0; j < i; j++) {
                barra.append("#"); // Parte cargada
            }
            for (int j = i; j < total; j++) {
                barra.append(" "); // Parte restante
            }
            barra.append("] ").append(i * 4).append("%"); // Porcentaje cargado

            // Imprimir la barra
            System.out.println(barra);

            // Pausa para simular la carga
            try {
                Thread.sleep(30); // Menor tiempo de espera para hacerla más rápida
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Juego cargado con éxito!");
    }

    private void mostrarMensajePartidaSimulada(List<Integer> cambiosPartidaJugada) {
        Random random = new Random();

        String[] mensajesPositivo = new String[]{
            "¡Excelente partida! Sigue asi.",
            "¡Gran desempeño! Te estas superando.",
            "¡Increible! Todo salio perfecto."
        };

        String[] mensajesNegativo = new String[]{
            "No fue tu mejor dia, pero sigue intentandolo.",
            "Esta vez no salio como esperabas, aprende y mejora.",
            "Una partida dificil, pero puedes hacerlo mejor."
        };

        int experience = cambiosPartidaJugada.get(0);
        int life_level = cambiosPartidaJugada.get(1);
        int coins = cambiosPartidaJugada.get(2);

        int mensaje = random.nextInt(3);

        if (experience > 50 && life_level > 50 && coins > 75) {
            System.out.println(mensajesPositivo[mensaje]);
        } else {
            System.out.println(mensajesNegativo[mensaje]);
        }

    }

    private void mostrarDatosPartidaSimulada(List<Integer> cambiosPartidaJugada) {
        System.out.println("\n===== Resultados de la Partida =====");

        String[] etiquetas = {"Experiencia", "Nivel de Vida", "Monedas"};

        for (int i = 0; i < cambiosPartidaJugada.size(); i++) {
            System.out.printf("%-15s: %-5d%n", etiquetas[i], cambiosPartidaJugada.get(i));
        }

        System.out.println("====================================");
    }

    private boolean crearPartidaSimulada(List<Integer> cambiosPartidaJugada, int idJugadorJuego, String nombreJugador, String isbnJuego, String nombreJuego) {
        return controlPartida.crearPartidaSimulada(cambiosPartidaJugada, servidorLinea, idJugadorJuego, nombreJugador, isbnJuego, nombreJuego);
    }

    private boolean actualizarDatosJugadorSimulada(List<Integer> cambiosPartidaJugada, int idJugadorJuego, String nombreJugador) {
        return controlJugador.actualizarDatosJugador(cambiosPartidaJugada, idJugadorJuego, nombreJugador, servidorLinea);
    }

    private boolean actualizarVideojuego(String isbnJuego, String nombreJuego, int idJugador, String nombreJugador) {
        return controlJuegos.actualizarVideojuego(isbnJuego, nombreJuego, servidorLinea, idJugador, nombreJugador);
    }

    private boolean comprobarJuego(String isbnJuego, String nombreJuego) {
        return controlJuegos.comprobarJuego(isbnJuego, nombreJuego, servidorLinea);
    }

    private boolean comprobarJuegoSinDescargar(String isbnJuego) {
        return controlJuegos.comprobarJuegoSinDescargar(isbnJuego, servidorLinea);
    }

    private boolean sincronizacionBasesNubeALocal() throws ClassNotFoundException {
        try {
            return controlSincronizacion.sincronizarBasesNubeALocal();
        } catch (SQLException ex) {
            return false;
        }
    }

    private boolean comprobarJugadorSinDescargar(int idJugadorJuego) {
        return controlJugador.comprobarJugadorDescargado(idJugadorJuego, servidorLinea);
    }

    private boolean guardarCambiosLocalANube() {
        try {
            return controlSincronizacion.sincronizarLocalANube();
        } catch (SQLException ex) {
            return false;
        }
    }
}

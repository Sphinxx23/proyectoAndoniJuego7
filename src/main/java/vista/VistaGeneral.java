package vista;

import controlador.ControladorJugador;
import controlador.ControladorPartida;
import controlador.ControladorVideojuego;
import dao.JugadorDAOpostgre;
import java.util.Scanner;
import dao.IDAOJugadorPostgre;
import dao.IDAOPartidaPostgre;
import dao.IDAOVideojuegoPostgre;
import dao.PartidaDAOpostgre;
import dao.VideojuegoDAOpostgre;

public class VistaGeneral {

    public VistaGeneral() {

    }

    public void show() {
        Scanner sc = new Scanner(System.in);
        int recInt=0;
        boolean b = true;

        do {

            do {
                System.out.println("¿QUE DESEA HACER? \n1-Administrar Jugadores\n2-Administrar Videojuegos\n3-Administrar Partidas\n4-Cerrar Programa");

                try {
                    recInt = Integer.parseInt(sc.nextLine());
                    b = false;
                } catch (Exception e) {
                    b = true;
                }
            } while (b);

            switch (recInt) {
                case 1:
                    IVistaJ vista = new VistaJugador();
                    ControladorJugador controlador = new ControladorJugador(vista);
                    IDAOJugadorPostgre modelo = new JugadorDAOpostgre();
                    controlador.setModelo(modelo);
                    vista.setControlador(controlador);
                    for (int i = 0; i < 50; i++) {
                        System.out.println(" ");
                    }
                    vista.show();
                    break;
                case 2:
                    IVistaV vista2 = new VistaVideojuego();
                    ControladorVideojuego controlador2 = new ControladorVideojuego(vista2);
                    IDAOVideojuegoPostgre modelo2 = new VideojuegoDAOpostgre();
                    controlador2.setModelo(modelo2);
                    vista2.setControlador(controlador2);
                    for (int i = 0; i < 50; i++) {
                        System.out.println(" ");
                    }
                    vista2.show();
                    break;
                case 3:
                    IVistaP vista3 = new VistaPartida();
                    ControladorPartida controlador3 = new ControladorPartida(vista3);
                    IDAOPartidaPostgre modelo3 = new PartidaDAOpostgre();
                    controlador3.setModelo(modelo3);
                    vista3.setControlador(controlador3);
                    for (int i = 0; i < 50; i++) {
                        System.out.println(" ");
                    }
                    vista3.show();
                    break;
                case 4:

                    break;
                default:
                    System.out.println("Entrada invalida");
            }
        } while (recInt != 4);

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package facto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author PcBox
 */
public class Factory {

    private static final String URLPOST = "jdbc:postgresql://ep-broad-union-a29uia00.eu-central-1.aws.neon.tech/proyectoJuego?user=proyectoJuego_owner&password=eb4xsQc0ENkU&sslmode=require";
    private static final String URLMYSQL = " ";

    public static Connection getConnection() throws SQLException {
        Scanner sc = new Scanner(System.in);
        int recInt = 0;
        boolean b = true;

        do {
            do {
                System.out.println("EN QUE BASE DE DATOS QUIERES REALIZAR LA ACCION\n 1-Postgre SQL\n 2-MySql");
                try {
                    recInt = Integer.parseInt(sc.nextLine());
                    b = false;

                } catch (Exception e) {
                    b = true;
                    System.out.println("Caracter no valido");
                }
            } while (b);

            switch (recInt) {
                case 1:
                    return DriverManager.getConnection(URLPOST);

                case 2:
                    return DriverManager.getConnection(URLMYSQL);

                default:
                    System.out.println("Numero introducido no valido");
            }
        } while (recInt != 1 && recInt != 2);
        return null;
    }

}

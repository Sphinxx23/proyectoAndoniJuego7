/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;

public class JugadorDAOSQLite {
    
    public static void main(String[] args) throws SQLException {
        // Driver 
		String driver="org.sqlite.JDBC";
		// Cargar el driver
		try{
			Class.forName(driver);
		}catch(ClassNotFoundException e){
			System.out.println("Controlador JDBC de SQLite no encontrado: "+e.toString());
		}
		// Establecer conexion
		String url = "jdbc:sqlite:G:\\2DAM\\AD\\dataBase\\SQLite\\datosLocales.db";		//
		Connection con = DriverManager.getConnection(url);
		
		Statement stmt = con.createStatement();
		String orden = "INSERT INTO jugador VALUES (1,\"HOLA\", 2,3, 4, 5,\"HOY\");";
		ResultSet rset = stmt.executeQuery(orden);	
		
		while (rset.next())	
			System.out.println(rset.getInt(1)+"\t"+rset.getString(2)+"\t"+rset.getString(3));			
		
		// close the result set, the statement and connect
		rset.close(); stmt.close(); con.close();
    
    
        
    }
    
    
    
    
}

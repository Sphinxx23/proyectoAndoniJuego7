package dao;

import java.sql.*;

public class ConfiguracionDAO {

    // Devuelve un boolean para saber que la configuración se ha actualizado correctamente.
    public boolean actualizarConfiguracion(int idJugador, boolean configSonido, String confResolucion, String confLenguaje) {
        String url = "jdbc:sqlite:G:\\2º Superior\\Acceso a datos\\SQLite\\datosLocales.db";
        String sql = "UPDATE configJugador SET sound_enabled = ?, resolution = ?, language = ? WHERE player_id = ?";

        try (Connection con = DriverManager.getConnection(url); 
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Asigna los valores a los parámetros de la consulta
            pstmt.setBoolean(1, configSonido);
            pstmt.setString(2, confResolucion);
            pstmt.setString(3, confLenguaje);
            pstmt.setInt(4, idJugador);
            

            // Ejecuta la actualización y verifica si afectó alguna fila
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

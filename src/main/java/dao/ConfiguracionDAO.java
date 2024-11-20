package dao;

import conexJSON.ConexJSON;
import java.sql.*;

public class ConfiguracionDAO {

    private final String URL = ConexJSON.consultarJson("sqlite");

    // Devuelve un boolean para saber que la configuración se ha actualizado correctamente.
    public boolean actualizarConfiguracion(boolean configSonido, String confResolucion, String confLenguaje) {
        String sql = "UPDATE configJugador SET sound_enabled = ?, resolution = ?, language = ?";

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement pstmt = con.prepareStatement(sql)) {

            // Asigna los valores a los parámetros de la consulta
            pstmt.setBoolean(1, configSonido);
            pstmt.setString(2, confResolucion);
            pstmt.setString(3, confLenguaje);

            // Ejecuta la actualización y verifica si afectó alguna fila
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void mostrarConfiguracion() {
        String sql = "SELECT * FROM configJugador";

        try (Connection con = DriverManager.getConnection(URL); PreparedStatement statement = con.prepareStatement(sql)) {
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()) {
                String sound_enabled = "" + resultado.getBoolean("sound_enabled");
                String resolucion = resultado.getString("resolution");
                String idioma = resultado.getString("language");

                System.out.println("Sonido - Resolucion - Idioma");
                System.out.println(sound_enabled+" - "+resolucion+" - "+idioma);

            }
        } catch (Exception e) {
        }
    }
}

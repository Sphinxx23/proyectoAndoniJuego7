/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexJSON;

import com.fasterxml.jackson.databind.*;
import java.io.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vespertino
 */
public class ConexJSON {

    public static String consultarJson(String dbType) {
        try {
            String URL;
            ObjectMapper mapper = new ObjectMapper();
            JsonNode config = mapper.readTree(new File("configHosts.json"));
            JsonNode dbConfig = config.get(dbType);

            if (dbConfig == null) {
                throw new IllegalArgumentException("Configuración para " + dbType + " no encontrada.");
            }

            switch (dbType.toLowerCase()) {
                case "mysql": {
                    String host = dbConfig.get("host").asText();
                    int port = dbConfig.get("port").asInt();
                    String user = dbConfig.get("user").asText();
                    String password = dbConfig.get("pass").asText();
                    String database = dbConfig.get("database").asText();

                    URL = "jdbc:mysql://" + host + ":" + port + "/" + database
                            + "?user=" + user
                            + "&password=" + password;
                    break;
                }
                case "postgres": {
                    String host = dbConfig.get("host").asText();
                    int port = dbConfig.get("port").asInt();
                    String user = dbConfig.get("user").asText();
                    String password = dbConfig.get("pass").asText();
                    String database = dbConfig.get("database").asText();

                    URL = "jdbc:postgresql://" + host + ":" + port + "/" + database
                            + "?user=" + user
                            + "&password=" + password
                            + "&sslmode=require";
                    break;
                }
                case "sqlite": {
                    String filePath = dbConfig.get("file").asText();
                    URL = "jdbc:sqlite:" + filePath;
                    break;
                }
                default:
                    throw new UnsupportedOperationException("Base de datos no soportada: " + dbType);
            }

            return URL;
        } catch (IOException ex) {
            Logger.getLogger(ConexJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

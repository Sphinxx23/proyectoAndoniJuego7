/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Vespertino
 */
public class ConfiguracionJugador {
    private String control_settings;
    private boolean sound_enabled;
    private String resolution;
    private String language;

    public ConfiguracionJugador(String control_settings, boolean sound_enabled, String resolution, String language) {
        this.control_settings = control_settings;
        this.sound_enabled = sound_enabled;
        this.resolution = resolution;
        this.language = language;
    }

    public ConfiguracionJugador() {
        this(null, false, null, null);
    }

    public String getControl_settings() {
        return control_settings;
    }

    public void setControl_settings(String control_settings) {
        this.control_settings = control_settings;
    }

    public boolean isSound_enabled() {
        return sound_enabled;
    }

    public void setSound_enabled(boolean sound_enabled) {
        this.sound_enabled = sound_enabled;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
    
    
}

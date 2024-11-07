/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author Vespertino
 */
public class Videojuego {
    private String isbn;
    private String title;
    private int player_count;
    private int total_sessions;
    private LocalDate last_session;

    public Videojuego(int game_id, String isbn, String title, int player_count, int total_sessions, LocalDate last_session) {
        this.isbn = isbn;
        this.title = title;
        this.player_count = player_count;
        this.total_sessions = total_sessions;
        this.last_session = last_session;
    }

    public Videojuego() {
        this(0, null,null, 0, 0, null);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPlayer_count() {
        return player_count;
    }

    public void setPlayer_count(int player_count) {
        this.player_count = player_count;
    }

    public int getTotal_sessions() {
        return total_sessions;
    }

    public void setTotal_sessions(int total_sessions) {
        this.total_sessions = total_sessions;
    }

    public LocalDate getLast_session() {
        return last_session;
    }

    public void setLast_session(LocalDate last_session) {
        this.last_session = last_session;
    }
    
    
    
    
}

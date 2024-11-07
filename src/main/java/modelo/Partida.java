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
public class Partida {
    private String isbn;
    private int player_id;
    private int experience;
    private int life_level;
    private int coins;
    private int session_count;
    private LocalDate session_date;

    public Partida(String isbn, int player_id, int experience, int life_level, int coins, int session,LocalDate session_date) {
        this.isbn = isbn;
        this.player_id = player_id;
        this.experience = experience;
        this.life_level = life_level;
        this.coins = coins;
        this.session_count=session;
        this.session_date = session_date;
    }

    public Partida() {
        this(null,0,0,0,0,0,null);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLife_level() {
        return life_level;
    }

    public void setLife_level(int life_level) {
        this.life_level = life_level;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public LocalDate getSession_date() {
        return session_date;
    }

    public void setSession_date(LocalDate session_date) {
        this.session_date = session_date;
    }
    
  
}

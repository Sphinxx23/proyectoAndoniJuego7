/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Vespertino
 */
public class Partida implements Serializable{
    private String isbn;
    private int user_id;
    private int experience;
    private int life_level;
    private int coins;
    private LocalDateTime session_date;

    public Partida(String isbn, int user_id, int experience, int life_level, int coins,LocalDateTime session_date) {
        this.isbn = isbn;
        this.user_id = user_id;
        this.experience = experience;
        this.life_level = life_level;
        this.coins = coins;
        this.session_date = session_date;
    }

    public Partida() {
        this(null,0,0,0,0,null);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int player_id) {
        this.user_id = player_id;
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

    public LocalDateTime getSession_date() {
        return session_date;
    }

    public void setSession_date(LocalDateTime session_date) {
        this.session_date = session_date;
    }
    
  
}

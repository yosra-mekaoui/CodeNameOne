/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entites;

import java.util.Date;

/**
 *
 * @author HP USER
 */
public class Match {
  private int id;
    private int stade;
    private String nom_arbitre;
    private String nom_match;
    private String tour;   
    private Date date; 
    private String image1; 

    public Match() {
    }

    public Match(int id, int stade, String nom_arbitre, String nom_match, String tour, String image1, Date date) {
        this.id = id;
        this.stade = stade;
        this.nom_arbitre = nom_arbitre;
        this.nom_match = nom_match;
        this.tour = tour;
        this.date = date;
                this.image1 = image1;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStade() {
        return stade;
    }

    public void setStade(int stade) {
        this.stade = stade;
    }

    public String getNom_arbitre() {
        return nom_arbitre;
    }

    public void setNom_arbitre(String nom_arbitre) {
        this.nom_arbitre = nom_arbitre;
    }

    public String getNom_match() {
        return nom_match;
    }

    public void setNom_match(String nom_match) {
        this.nom_match = nom_match;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    @Override
    public String toString() {
        return "Match{" + "id=" + id + ", stade=" + stade + ", nom_arbitre=" + nom_arbitre + ", nom_match=" + nom_match + ", tour=" + tour + ", date=" + date + ", image1=" + image1 + '}';
    }

    
    
   
}

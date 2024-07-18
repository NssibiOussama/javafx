/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author Mahdi
 */
public class Classe {

    private int id;
    private String nom;
    private ArrayList<Seance> seances;

    public Classe(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Classe(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }

}

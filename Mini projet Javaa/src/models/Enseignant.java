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
public class Enseignant {

    private String matricule;
    private String nom;
    private String contact;
    private ArrayList<Seance> seances;

    public Enseignant(String matricule, String nom, String contact) {
        this.matricule = matricule;
        this.nom = nom;
        this.contact = contact;
        this.seances = new ArrayList<Seance>();
    }

    public void setSeances(ArrayList<Seance> seances) {
        this.seances = seances;
    }

    public ArrayList<Seance> getSeances() {
        return seances;
    }

    public Enseignant() {

    }

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getContact() {
        return contact;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return this.getNom();
    }

}

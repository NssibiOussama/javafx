/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Mahdi
 */
public class Etudiant {
    private String matricule;
    private String nom;
    private String contact;
    
    public Etudiant(){
        
    }

    public Etudiant(String matricule, String nom, String contact) {
        this.matricule = matricule;
        this.nom = nom;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Etudiant{" + "matricule=" + matricule + ", nom=" + nom + ", contact=" + contact + '}';
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

    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getContact() {
        return contact;
    }
    
    
}

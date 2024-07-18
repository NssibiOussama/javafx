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
public class Seance {

    private String matiere;
    private String jour;
    private String heure;
    private String enseignant;
    private Classe classe;

    public Seance(Classe classe, String matiere, String jour, String heure, String enseignant) {
        this.classe = classe;
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.enseignant = enseignant;
    }
    public Seance(int classe, String matiere, String jour, String heure, String enseignant) {
        this.classe = new Classe(classe);
        this.matiere = matiere;
        this.jour = jour;
        this.heure = heure;
        this.enseignant = enseignant;
    }

    public Classe getClasse() {
        return classe;
    }

    public String getMatiere() {
        return matiere;
    }

    public String getJour() {
        return jour;
    }

    public String getHeure() {
        return heure;
    }

    public String getEnseignant() {
        return enseignant;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }

}

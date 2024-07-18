/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.seance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Classe;
import models.Seance;
import utils.database;

/**
 *
 * @author Mahdi
 */
public class ServiceSeance implements ISeance<Seance> {
    // Singleton instance

    private static ServiceSeance instance;
    private Connection cnx = database.getInstance().getConnection();
    private ObservableList<Seance> seances = FXCollections.observableArrayList();
    private Seance seance;
    // Private constructor to prevent external instantiation

    private ServiceSeance() {
        // Initialization code here, if needed
    }

    // Public method to get the single instance
    public static ServiceSeance getInstance() {
        if (instance == null) {
            instance = new ServiceSeance();
        }
        return instance;
    }

    @Override
    public boolean save(Seance seance) {
        try {
            String req = "INSERT INTO `seances`(`classe`,`matiere`, `jour`, `heure`,`enseignant`) VALUES (?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, seance.getClasse().getId());
            ps.setString(2, seance.getMatiere());
            ps.setString(3, seance.getJour());
            ps.setString(4, seance.getHeure());
            ps.setString(5, seance.getEnseignant());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            return false;
        }
        return true;

    }

    @Override
    public boolean delete(int id) {
        try {
            String req = "delete from `seances` where id = '" + id + "'";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public ObservableList<Seance> getAll() {
        String req = "SELECT classes.nom as classe, classes.id as classeId , matiere, jour, heure, enseignants.nom as enseignant FROM `seances` JOIN `classes` ON `seances`.`classe` = `classes`.`id` JOIN `enseignants` ON `seances`.`enseignant` = `enseignants`.`matricule` order by jour, heure";
        try {
            seances.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Classe classe = new Classe(rs.getInt("classeId"), rs.getString("classe"));
                Seance s = new Seance(classe, rs.getString("matiere"), rs.getString("jour"), rs.getString("heure"), rs.getString("enseignant"));
                seances.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }
        return seances;

    }

    @Override
    public ObservableList<Seance> search(String input) {
        String req = "SELECT classes.nom as classe, classes.id as classeId, matiere, jour, heure, enseignants.nom as enseignant "
                + "FROM `seances` "
                + "JOIN `classes` ON `seances`.`classe` = `classes`.`id` "
                + "JOIN `enseignants` ON `seances`.`enseignant` = `enseignants`.`matricule` "
                + "WHERE classe LIKE '%" + input + "%' OR matiere LIKE '%" + input + "%' OR jour LIKE '%" + input + "%' OR heure LIKE '%" + input + "%' OR enseignants.nom LIKE '%" + input + "%' "
                + "ORDER BY jour, heure";
        try {
            seances.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Classe classe = new Classe(rs.getInt("classeId"), rs.getString("classe"));
                Seance s = new Seance(classe, rs.getString("matiere"), rs.getString("jour"), rs.getString("heure"), rs.getString("enseignant"));
                seances.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }
        return seances;
    }

    

    public ObservableList<Seance> recherche(int classe, String matiere) {
        String req = "SELECT classes.nom as classe, classes.id as classeId, matiere, jour, heure, enseignants.nom as enseignant "
                + "FROM `seances` "
                + "JOIN `classes` ON `seances`.`classe` = `classes`.`id` "
                + "JOIN `enseignants` ON `seances`.`enseignant` = `enseignants`.`matricule` "
                + "WHERE classes.id=" + classe + " AND matiere like'%" + matiere + "%' "
                + "ORDER BY jour, heure";
        try {
            seances.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Classe classe1 = new Classe(rs.getInt("classeId"), rs.getString("classe"));
                Seance s = new Seance(classe1, rs.getString("matiere"), rs.getString("jour"), rs.getString("heure"), rs.getString("enseignant"));
                seances.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }
        return seances;

    }

    public ObservableList<Seance> recherche2(int classe) {
        String req = "SELECT classes.nom as classe, classes.id as classeId, matiere, jour, heure, enseignants.nom as enseignant "
                + "FROM `seances` "
                + "JOIN `classes` ON `seances`.`classe` = `classes`.`id` "
                + "JOIN `enseignants` ON `seances`.`enseignant` = `enseignants`.`matricule` "
                + "WHERE classes.id=" + classe + " ORDER BY jour, heure";
        try {
            seances.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Classe classe1 = new Classe(rs.getInt("classeId"), rs.getString("classe"));
                Seance s = new Seance(classe1, rs.getString("matiere"), rs.getString("jour"), rs.getString("heure"), rs.getString("enseignant"));
                seances.add(s);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }
        return seances;

    }
}

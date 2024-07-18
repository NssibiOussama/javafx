/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.etudiant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Etudiant;

/**
 *
 * @author Mahdi
 */
public class ServiceEtudiant implements IEtudiant<Etudiant> {
// Singleton instance

    private static ServiceEtudiant instance;
    private Connection cnx = database.getInstance().getConnection();
    private ObservableList<Etudiant> etudiants = FXCollections.observableArrayList();
    private Etudiant etudiant;

    // Private constructor to prevent external instantiation
    private ServiceEtudiant() {
        // Initialization code here, if needed
    }

    // Public method to get the single instance
    public static ServiceEtudiant getInstance() {
        if (instance == null) {
            instance = new ServiceEtudiant();
        }
        return instance;
    }

    @Override
    public ObservableList<Etudiant> getAll() {
        String req = "SELECT * FROM etudiants";
        try {
            etudiants.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Etudiant e = new Etudiant(rs.getString("matricule"), rs.getString("nom"), rs.getString("contact"));
                //  e.setId(rs.getInt("id"));
                etudiants.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }
        return etudiants;
    }

    @Override
    public boolean save(Etudiant etudiant) {
        try {
            String req = "INSERT INTO `etudiants`(`matricule`, `nom`, `contact`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, etudiant.getMatricule());
            ps.setString(2, etudiant.getNom());
            ps.setString(3, etudiant.getContact());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean edit(String id, Etudiant updatedEtudiant) {
        try {
            String req = "update `etudiants` set `matricule`= ?, `nom`= ? , `contact` = ? where `matricule` = ? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, updatedEtudiant.getMatricule());
            ps.setString(2, updatedEtudiant.getNom());
            ps.setString(3, updatedEtudiant.getContact());
            ps.setString(4, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String matricule) {
        try {
            String req = "delete from `etudiants` where matricule = '" + matricule + "'";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public ObservableList<Etudiant> search(String criteria) {
        String req = "SELECT * FROM etudiants where matricule like '%" + criteria + "%' or nom like '%" + criteria + "%' or contact like '%" + criteria + "%'";
        try {
            etudiants.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Etudiant e = new Etudiant(rs.getString("matricule"), rs.getString("nom"), rs.getString("contact"));
                // e.setId(rs.getInt("id"));
                etudiants.add(e);

            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }

        return etudiants;
    }
}

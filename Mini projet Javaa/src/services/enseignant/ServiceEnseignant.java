/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.enseignant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Enseignant;
import utils.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Mahdi
 */
public class ServiceEnseignant implements IEnseignant<Enseignant> {

    private static ServiceEnseignant instance;
    private Connection cnx = database.getInstance().getConnection();
    private ObservableList<Enseignant> enseignants = FXCollections.observableArrayList();
    private Enseignant enseignant;

    private ServiceEnseignant() {
    }

    public static ServiceEnseignant getInstance() {
        if (instance == null) {
            instance = new ServiceEnseignant();
        }
        return instance;
    }

    @Override
    public ObservableList<Enseignant> getAll() {
        String req = "SELECT * FROM enseignants";
        try {
            enseignants.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Enseignant e = new Enseignant(rs.getString("matricule"), rs.getString("nom"), rs.getString("contact"));
                enseignants.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }
        return enseignants;
    }

    @Override
    public boolean save(Enseignant enseignant) {
        try {
            String req = "INSERT INTO `enseignants`(`matricule`, `nom`, `contact`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, enseignant.getMatricule());
            ps.setString(2, enseignant.getNom());
            ps.setString(3, enseignant.getContact());
            ps.executeUpdate();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }

    @Override
    public boolean edit(String id, Enseignant updatedEnseignant) {
        try {
            String req = "update `enseignants` set `matricule`= ?, `nom`= ? , `contact` = ? where `matricule` = ? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, updatedEnseignant.getMatricule());
            ps.setString(2, updatedEnseignant.getNom());
            ps.setString(3, updatedEnseignant.getContact());
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
            String req = "delete from `enseignants` where matricule = '" + matricule + "'";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public ObservableList<Enseignant> search(String criteria) {
        String req = "SELECT * FROM enseignants where matricule like '%" + criteria + "%' or nom like '%" + criteria + "%' or contact like '%" + criteria + "%'";
        try {
            enseignants.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Enseignant e = new Enseignant(rs.getString("matricule"), rs.getString("nom"), rs.getString("contact"));
                enseignants.add(e);

            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }

        return enseignants;
    }

}

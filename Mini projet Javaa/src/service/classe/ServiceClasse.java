/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.classe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Classe;
import utils.database;

/**
 *
 * @author Mahdi
 */
public class ServiceClasse {
    
     // Singleton instance
    private static ServiceClasse instance;
    private Connection cnx = database.getInstance().getConnection();
    private ObservableList<Classe> classes = FXCollections.observableArrayList();
    private Classe classe;

    // Private constructor to prevent external instantiation
    private ServiceClasse() {
        // Initialization code here, if needed
    }

    // Public method to get the single instance
    public static ServiceClasse getInstance() {
        if (instance == null) {
            instance = new ServiceClasse();
        }
        return instance;
    }
    public ObservableList<Classe> getAll() {
        String req = "SELECT * FROM classes";
        try {
            classes.clear();
            PreparedStatement ps = cnx.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Classe c = new Classe(rs.getInt("id"),rs.getString("nom"));
                classes.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur :" + ex.getMessage());
        }
        return classes;
    }
    
}

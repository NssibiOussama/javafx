/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Etudiant;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Etudiant;
import services.etudiant.ServiceEtudiant;
import utils.AlertUtil;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class EditEtudiantController implements Initializable {

    private ServiceEtudiant serviceEtudiant = ServiceEtudiant.getInstance();

    private String matriculeInitial;

    @FXML
    private TextField inputMatricule;
    @FXML
    private TextField inputNom;
    @FXML
    private TextField inputContact;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void save(ActionEvent event) throws IOException {
        String matricule = inputMatricule.getText();
        String nom = inputNom.getText();
        String contact = inputContact.getText();

        if (matricule.isEmpty() || nom.isEmpty() || contact.isEmpty()) {
            AlertUtil.showError("Echec de l'opération", "Veuillez remplir tous les champs");
            return;
        }

        if (!contact.matches("[0-9]*")) {
            AlertUtil.showError("Erreur", "Le numéro de téléphone ne doit contenir que des chiffres");
            return;
        }
        Node source = (Node) event.getSource();
        Stage dialogStage = (Stage) source.getScene().getWindow();
        Etudiant updatedEtudiant = new Etudiant();
        updatedEtudiant.setMatricule(inputMatricule.getText());
        updatedEtudiant.setNom(inputNom.getText());
        updatedEtudiant.setContact(inputContact.getText());
        serviceEtudiant.edit(matriculeInitial, updatedEtudiant);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Etudiant.fxml"));
        loader.load();
        EtudiantController etudiantController = loader.getController();
        etudiantController.start();
        dialogStage.close();
    }

    public void setTextFields(String matricule, String nom, String contact) {
        matriculeInitial = matricule;
        inputMatricule.setText(matricule);
        inputNom.setText(nom);
        inputContact.setText(contact);

    }

}

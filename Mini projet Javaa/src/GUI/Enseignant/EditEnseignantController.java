/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Enseignant;

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
import models.Enseignant;
import services.enseignant.ServiceEnseignant;
import utils.AlertUtil;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class EditEnseignantController implements Initializable {

    private final ServiceEnseignant serviceEnseignant = ServiceEnseignant.getInstance();

    @FXML
    private TextField inputMatricule;
    @FXML
    private TextField inputNom;
    @FXML
    private TextField inputContact;
    private String matriculeInitial;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setTextFields(String matricule, String nom, String contact) {
        matriculeInitial = matricule;
        inputMatricule.setText(matricule);
        inputNom.setText(nom);
        inputContact.setText(contact);

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
        Enseignant updatedEnseignant = new Enseignant();
        updatedEnseignant.setMatricule(inputMatricule.getText());
        updatedEnseignant.setNom(inputNom.getText());
        updatedEnseignant.setContact(inputContact.getText());
        serviceEnseignant.edit(matriculeInitial, updatedEnseignant);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Enseignant.fxml"));
        loader.load();
        EnseignantController EnseignantController = loader.getController();
        EnseignantController.start();
        dialogStage.close();

    }

}

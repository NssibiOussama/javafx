/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojetjava;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Mahdi
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private Button BtnEnseignant;
    @FXML
    private Button BtnSeance;
    @FXML
    private Button BtnEtudiants;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void showEnseignants(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage dialogStage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Enseignant/Enseignant.fxml")));
        dialogStage.setTitle("Enseignants");
        dialogStage.setWidth(source.getScene().getWindow().getWidth());
        dialogStage.setHeight(source.getScene().getWindow().getHeight());
        dialogStage.setScene(scene);
    }

    @FXML
    private void showSeances(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage dialogStage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Seance/Seance.fxml")));
        dialogStage.setTitle("Séances de cours");
        dialogStage.setWidth(source.getScene().getWindow().getWidth());
        dialogStage.setHeight(source.getScene().getWindow().getHeight());
        dialogStage.setScene(scene);

    }

    @FXML
    private void exit(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Quitter?");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment quitter?");
        if (ButtonType.OK == alert.showAndWait().orElse(ButtonType.CANCEL)) {
            Platform.exit();
        } else {
            return;
        }

    }

    @FXML
    private void showEtudiants(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage dialogStage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Etudiant/Etudiant.fxml")));
        dialogStage.setTitle("Etudiants");
        dialogStage.setWidth(source.getScene().getWindow().getWidth());
        dialogStage.setHeight(source.getScene().getWindow().getHeight());
        dialogStage.setScene(scene);
    }

}

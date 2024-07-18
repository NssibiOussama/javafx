/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Seance;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Classe;
import models.Seance;
import service.classe.ServiceClasse;
import services.seance.ServiceSeance;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class AdvancedSearchController implements Initializable {

    private ObservableList<Seance> seance_list;

    @FXML
    private Button btnAdvancedSearch;
    @FXML
    private TableColumn<?, ?> celClasse;
    @FXML
    private TableColumn<?, ?> celMatiere;
    @FXML
    private TableColumn<?, ?> celJour;
    @FXML
    private TableColumn<?, ?> celHeure;
    @FXML
    private TableColumn<?, ?> celEnseignant;
    @FXML
    private TableView<Seance> seanceTable;
    @FXML
    private ComboBox<Classe> comboClasse;
    @FXML
    private ComboBox<Classe> comboClasse2;
    @FXML
    private TextField inputMatiere;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        start();
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
    private void showHome(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage dialogStage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/javafxapplication15/FXMLDocument.fxml")));
        dialogStage.setTitle("Institu supérieure d'informatique ISI");
        dialogStage.setWidth(source.getScene().getWindow().getWidth());
        dialogStage.setHeight(source.getScene().getWindow().getHeight());
        dialogStage.setScene(scene);
    }

    @FXML
    private void showSeance(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage dialogStage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Seance/Seance.fxml")));
        dialogStage.setTitle("Séances de cours");
        dialogStage.setWidth(source.getScene().getWindow().getWidth());
        dialogStage.setHeight(source.getScene().getWindow().getHeight());
        dialogStage.setScene(scene);
    }

    private void start() {
        //seance_list = ServiceSeance.getInstance().getAll();
        seance_list = ServiceSeance.getInstance().getAll();
        celClasse.setCellValueFactory(new PropertyValueFactory<>("Classe"));
        celMatiere.setCellValueFactory(new PropertyValueFactory<>("Matiere"));
        celJour.setCellValueFactory(new PropertyValueFactory<>("Jour"));
        celHeure.setCellValueFactory(new PropertyValueFactory<>("Heure"));
        celEnseignant.setCellValueFactory(new PropertyValueFactory<>("Enseignant"));
        celClasse.prefWidthProperty().bind(seanceTable.widthProperty().divide(5));
        celMatiere.prefWidthProperty().bind(seanceTable.widthProperty().divide(5));
        celJour.prefWidthProperty().bind(seanceTable.widthProperty().divide(5));
        celHeure.prefWidthProperty().bind(seanceTable.widthProperty().divide(5));
        celEnseignant.prefWidthProperty().bind(seanceTable.widthProperty().divide(5));
        comboClasse.setItems(ServiceClasse.getInstance().getAll());
        comboClasse2.setItems(ServiceClasse.getInstance().getAll());
        seanceTable.setItems(seance_list);

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

    @FXML
    private void recherche(ActionEvent event) {
        seance_list.clear();
        seance_list = ServiceSeance.getInstance().recherche(comboClasse.getSelectionModel().getSelectedItem().getId(), inputMatiere.getText());
        seanceTable.setItems(seance_list);
    }

    @FXML
    private void recherche2(ActionEvent event) {
        seance_list.clear();
        seance_list = ServiceSeance.getInstance().recherche2(comboClasse2.getSelectionModel().getSelectedItem().getId());
        seanceTable.setItems(seance_list);
    }

}

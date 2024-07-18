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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.Classe;
import models.Enseignant;
import services.seance.ServiceSeance;
import models.Seance;
import service.classe.ServiceClasse;
import services.enseignant.ServiceEnseignant;
import utils.AlertUtil;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class SeanceController implements Initializable {

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
    private TextField inputClasse;
    @FXML
    private TextField inputMatiere;
    @FXML
    private TextField inputJour;
    @FXML
    private TextField inputHeure;
    private TextField inputEnseignant;
    @FXML
    private ComboBox<Classe> comboClasse;
    @FXML
    private ComboBox<Enseignant> comboEnseignant;
    @FXML
    private TextField inputSearch;

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
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/miniprojetjava/FXMLDocument.fxml")));
        dialogStage.setTitle("Institu supérieure d'informatique ISI");
        dialogStage.setWidth(source.getScene().getWindow().getWidth());
        dialogStage.setHeight(source.getScene().getWindow().getHeight());
        dialogStage.setScene(scene);
    }

    @FXML
    private void showAdvancedSearch(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage dialogStage = (Stage) source.getScene().getWindow();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/GUI/Seance/AdvancedSearch.fxml")));
        dialogStage.setTitle("Requettes");
        dialogStage.setWidth(source.getScene().getWindow().getWidth());
        dialogStage.setHeight(source.getScene().getWindow().getHeight());
        dialogStage.setScene(scene);
    }

    private void start() {
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
        seanceTable.setItems(seance_list);
        comboEnseignant.setItems(ServiceEnseignant.getInstance().getAll());
        comboClasse.setItems(ServiceClasse.getInstance().getAll());

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
    private void save(ActionEvent event) {
        if ( comboEnseignant.getSelectionModel().getSelectedItem() == null) {
            AlertUtil.showError("Echec de l'opération", "Veuillez seulectionner un enseignant");
            return;
        }
        if (comboClasse.getSelectionModel().getSelectedItem() == null) {
            AlertUtil.showError("Echec de l'opération", "Veuillez seulectionner une classe");
            return;
        }

        int classeId = comboClasse.getSelectionModel().getSelectedItem().getId();
        String matiere = inputMatiere.getText().trim();
        String jour = inputJour.getText().trim();
        String heure = inputHeure.getText().trim();
        String enseignant = comboEnseignant.getSelectionModel().getSelectedItem().getMatricule();

        if (matiere.isEmpty() || jour.isEmpty() || heure.isEmpty()) {
            AlertUtil.showError("Echec de l'opération", "Veuillez remplir tous les champs");
            return;
        }

        Classe classe = new Classe(classeId);
        Seance seance = new Seance(classe, matiere, jour, heure, enseignant);
        boolean done = ServiceSeance.getInstance().save(seance);

        if (done) {
            comboClasse.setValue(null);
            comboEnseignant.setValue(null);
            inputMatiere.setText("");
            inputJour.setText("");
            inputHeure.setText("");
            seanceTable.setItems(ServiceSeance.getInstance().getAll());
        } else {
            AlertUtil.showError("Echec de l'opération", "Une erreur s'est produite. Veuillez réessayer.");
        }
    }

    @FXML

    private void searchSeances(KeyEvent event) {
        seance_list.clear();
        seance_list = ServiceSeance.getInstance().search(inputSearch.getText());
        seanceTable.setItems(seance_list);
    }

}

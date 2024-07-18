/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Etudiant;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Etudiant;
import services.etudiant.ServiceEtudiant;
import utils.AlertUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class EtudiantController implements Initializable {

    private Etudiant etudiant = null;
    private ObservableList<Etudiant> etudiant_list;
    private ServiceEtudiant serviceEtudiant = ServiceEtudiant.getInstance();
    @FXML
    private TextField inputMatricule;
    @FXML
    private TextField inputNom;
    @FXML
    private TextField inputContact;
    @FXML
    private Button delBtn;
    @FXML
    private TableView<Etudiant> etudiantTable;
    @FXML
    private TableColumn<?, ?> celMatricule;
    @FXML
    private TableColumn<?, ?> celNom;
    @FXML
    private TableColumn<?, ?> celContact;
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
    private void save(ActionEvent event) {
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

        Etudiant etudiant = new Etudiant(matricule, nom, contact);
        boolean done = serviceEtudiant.save(etudiant);

        if (done) {
            inputMatricule.setText("");
            inputNom.setText("");
            inputContact.setText("");
            etudiantTable.setItems(ServiceEtudiant.getInstance().getAll());
        } else {
            AlertUtil.showError("Echec de l'opération", "Une erreur s'est produite. Veuillez réessayer.");
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        Etudiant selected = etudiantTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            AlertUtil.showError("Echec de l'opération", "Selectionnez un element à supprimer!");
            return;

        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment supprimer " + selected.getNom() + " ?");
        if (ButtonType.OK == alert.showAndWait().orElse(ButtonType.CANCEL)) {
            serviceEtudiant.delete(selected.getMatricule());
            etudiant_list = ServiceEtudiant.getInstance().getAll();
            etudiantTable.setItems(etudiant_list);
        } else {
            return;
        }
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

    protected void start() {
        etudiant_list = ServiceEtudiant.getInstance().getAll();
        celMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        celNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        celContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        celMatricule.prefWidthProperty().bind(etudiantTable.widthProperty().divide(3));
        celNom.prefWidthProperty().bind(etudiantTable.widthProperty().divide(3));
        celContact.prefWidthProperty().bind(etudiantTable.widthProperty().divide(3));
        etudiantTable.setItems(etudiant_list);

    }

    @FXML
    private void searchEtudiants(KeyEvent event) {
        etudiant_list.clear();
        etudiant_list = serviceEtudiant.search(inputSearch.getText());
        etudiantTable.setItems(etudiant_list);
    }

    @FXML
    private void editEtudiant(ActionEvent event) throws IOException {
        Etudiant selected = etudiantTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showError("Echec de l'opération", "Veuillez selectionner un étudiant");
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditEtudiant.fxml"));
        loader.load();
        EditEtudiantController editEtudiantController = loader.getController();
        editEtudiantController.setTextFields(selected.getMatricule(), selected.getNom(), selected.getContact());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Modification de l'étudiant " + selected.getNom());
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.show();
    }

}

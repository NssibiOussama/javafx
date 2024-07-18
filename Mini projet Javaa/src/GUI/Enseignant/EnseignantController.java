/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Enseignant;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Enseignant;
import services.enseignant.ServiceEnseignant;
import utils.AlertUtil;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Mahdi
 */
public class EnseignantController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Enseignant enseignant = null;
    private ObservableList<Enseignant> enseignant_list;
    private ServiceEnseignant serviceEnseignant = ServiceEnseignant.getInstance();
    @FXML
    private TextField inputNom;
    @FXML
    private TextField inputContact;
    @FXML
    private TextField inputMatricule;
    @FXML
    private TableColumn<?, ?> celMatricule;
    @FXML
    private TableColumn<?, ?> celNom;
    @FXML
    private TableColumn<?, ?> celContact;
    @FXML
    private TableView<Enseignant> enseignantTable;
    @FXML
    private Button delBtn;
    @FXML
    private TextField inputSearch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        Enseignant enseignant = new Enseignant(matricule, nom, contact);
        boolean done = serviceEnseignant.save(enseignant);

        if (done) {
            inputMatricule.setText("");
            inputNom.setText("");
            inputContact.setText("");
            enseignantTable.setItems(ServiceEnseignant.getInstance().getAll());
        } else {
            AlertUtil.showError("Echec de l'opération", "Une erreur s'est produite. Veuillez réessayer.");
        }
    }

    protected void start() {
        enseignant_list = ServiceEnseignant.getInstance().getAll();
        celMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        celNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        celContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        celMatricule.prefWidthProperty().bind(enseignantTable.widthProperty().divide(3));
        celNom.prefWidthProperty().bind(enseignantTable.widthProperty().divide(3));
        celContact.prefWidthProperty().bind(enseignantTable.widthProperty().divide(3));
        enseignantTable.setItems(enseignant_list);

    }

    @FXML
    private void delete(ActionEvent event) {
        Enseignant selected = enseignantTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            AlertUtil.showError("Echec de l'opération", "Selectionnez un element à supprimer!");
            return;

        }
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Suppression");
        alert.setHeaderText(null);
        alert.setContentText("Voulez vous vraiment supprimer " + selected.getNom() + " ?");
        if (ButtonType.OK == alert.showAndWait().orElse(ButtonType.CANCEL)) {
            serviceEnseignant.delete(selected.getMatricule());
            enseignant_list = ServiceEnseignant.getInstance().getAll();
            enseignantTable.setItems(enseignant_list);
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

    @FXML
    private void searchEnseignants(KeyEvent event) {
        enseignant_list.clear();
        enseignant_list = serviceEnseignant.search(inputSearch.getText());
        enseignantTable.setItems(enseignant_list);
    }

    @FXML
    private void editEnseignant(ActionEvent event) throws IOException {
        Enseignant selected = enseignantTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            AlertUtil.showError("Echec de l'opération", "Veuillez selectionner un enseignant");
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditEnseignant.fxml"));
        loader.load();
        EditEnseignantController editEnseignantController = loader.getController();
        editEnseignantController.setTextFields(selected.getMatricule(), selected.getNom(), selected.getContact());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Modification de l'enseignant " + selected.getNom());
        stage.setScene(new Scene(parent));
        stage.setResizable(false);
        stage.show();
    }

}

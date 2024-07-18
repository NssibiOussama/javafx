/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import javafx.scene.control.Alert;

/**
 *
 * @author Mahdi
 */
public class AlertUtil {
    public static void showError(String title, String content) {
        showAlert(Alert.AlertType.ERROR, title, content);
    }

    public static void showSuccess(String title, String content) {
        showAlert(Alert.AlertType.INFORMATION, title, content);
    }

    public static void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    
}









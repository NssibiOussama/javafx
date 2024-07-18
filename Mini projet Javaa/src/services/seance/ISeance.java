/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.seance;

import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 *
 * @author Mahdi
 */
public interface ISeance<S> {

    boolean save(S seance);

    boolean delete(int id);

    ObservableList<S> getAll();

    ObservableList<S> search(String input);
    
}

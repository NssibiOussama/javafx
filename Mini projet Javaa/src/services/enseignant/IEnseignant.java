/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.enseignant;

import java.util.List;

/**
 *
 * @author Mahdi
 */
public interface IEnseignant<E> {

    boolean save(E enseignant);

    boolean edit(String id, E updatedEnseignant);

    boolean delete(String matricule);

    List<E> search(String criteria);

    List<E> getAll();
}

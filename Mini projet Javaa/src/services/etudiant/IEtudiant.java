/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.etudiant;

import java.util.List;

/**
 *
 * @author Mahdi
 */
public interface IEtudiant<E> {

    boolean save(E etudiant);

    boolean edit(String id, E updatedEtudiant);

    boolean delete(String matricule);

    List<E> search(String criteria);

    List<E> getAll();
}

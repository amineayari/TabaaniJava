/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.util.List;

/**
 *
 * @author admin
 */
public interface IService<T> {

    void Ajouter (T t) ; 
    List<T> Afficher() ; 
    T TrouverById (int id) ; 
    void Modifier (T t) ; 
    void Supprimer (int id) ; 

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author House_Info
 * @param <A>
 */
public interface Iservice <A> {
  public void ajouter( A t);
  public void supprimer( int t);
  public void modifier( A t);
  public List<A> afficher();
}

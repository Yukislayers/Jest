package Carte;

import java.io.Serializable;
/**
 * La classe carte qui repr�sente les cartes qui seront utilis�es dans le jeu,
 * chaque carte sera compos�e d'un signe de l'�num�ration Signe et d'une valeur de l'�num�ration Valeur
 * @author Shino
 *
 */
public class Carte implements Serializable{
	  private Valeur valeur;
	  private Signe signe;
	  
	 /**
	  * Card constructor
	  * 
	  * @param valeur
	  * @param signe
	  */
	  
	  public Carte(Valeur valeur, Signe signe) {
	    this.valeur = valeur;
	    this.signe = signe;
	  }
	  
	  /**
	   * Un constructeur de carte utilis� pour la m�thode qui distribue les troph�es
	   * @param c
	   */
	  public Carte(Carte c) {
		  this.valeur = c.valeur;
		  this.signe = c.signe;
	  }
	  public Valeur getValeur() {
		  return this.valeur;
	  }
	  
	  /**
	   * Permet de renvoyer le type de l'image pour pouvoir les afficher dans l'interface graphique
	   * @return Le Signe et la Valeur de la carte, tout coll�
	   */
	  public String getTypeCarte() {
		  StringBuffer sb = new StringBuffer();
		  sb.append(this.getValeur());
		  sb.append(this.getSigne());
		  return sb.toString();
	  }
	  
	  /**
	   * R�d�finition de la m�thode toString pour tous les objets de type Carte
	   */
	  public String toString(){
		  StringBuffer sb = new StringBuffer();
		  if( this.signe.ordinal() == 0) {
			  sb.append(this.signe);
		  } else {
			  sb.append(this.valeur + " de " + this.signe);
		  }
		  return sb.toString();
	  }
	  
	  public Signe getSigne() {
			return signe;
	  }
	 
	  /**
	   * M�thode qui permet de savoir si la carte est plus grande que celle entr�e en param�tre
	   * @param c La carte qu'on compare
	   * @return false si la carte est plus petite que celle entr�e en param�tre et false dans le cas contraire
	   */
	  public boolean estPlusGrand(Carte c) {
		  if (this.valeur.ordinal() == c.valeur.ordinal()) {
			  if (this.signe.ordinal() > c.signe.ordinal()) {
				  return true;
			  } else {
				  return false;
			  } 
		  } else if (this.valeur.ordinal() > c.valeur.ordinal()) {
			  return true;
		  } else {
			  return false;
		  }
	  }
	
	}



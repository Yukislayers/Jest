package Carte;

import java.io.Serializable;
/**
 * La classe carte qui représente les cartes qui seront utilisées dans le jeu,
 * chaque carte sera composée d'un signe de l'énumération Signe et d'une valeur de l'énumération Valeur
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
	   * Un constructeur de carte utilisé pour la méthode qui distribue les trophées
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
	   * @return Le Signe et la Valeur de la carte, tout collé
	   */
	  public String getTypeCarte() {
		  StringBuffer sb = new StringBuffer();
		  sb.append(this.getValeur());
		  sb.append(this.getSigne());
		  return sb.toString();
	  }
	  
	  /**
	   * Rédéfinition de la méthode toString pour tous les objets de type Carte
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
	   * Méthode qui permet de savoir si la carte est plus grande que celle entrée en paramètre
	   * @param c La carte qu'on compare
	   * @return false si la carte est plus petite que celle entrée en paramètre et false dans le cas contraire
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



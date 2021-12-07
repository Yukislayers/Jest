package Joueur;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Scanner;

import Carte.Carte;
import Carte.Jest;
import Controleur.Controleur;
/**
 * La classe abstraite Joueur
 * @author Shino
 *
 */
public abstract class Joueur extends Observable implements Comparable<Joueur>, Serializable{
	/**
	 * Les cartes que le joueur reçoit en début de tour et dont il doit choisir laquelle il veut mettre face ouverte
	 */
	private LinkedList<Carte> offre;
	/**
	 * Un objet de la classe Jest associé à chaque Joueur
	 */
	private Jest carteJest;
	/**
	 * Le nom du Joueur
	 */
	private String name;
	/**
	 * Le score du joueur
	 */
	private int score;
	/**
	 * La carte ouverte du joueur
	 */
	protected Carte carteOuverte;
	
/**
 * Constructeur d'un Joueur qui est défini par son nom
 * @param nom
 */
	public Joueur(){
		
		setOffre(new LinkedList<Carte>());
		setCarteJest(new Jest());
		setScore(0);
	}
/**
 * Méthode qui permet d'ajouter un objet de type Carte à l'offre d'un joueur qui est aussi sa main
 * @param c
 */
	public void addCardOffre(Carte c) {
	getOffre().add(c);
	}
	/**
	 * Méthode qui permet d'ajouter la carte qui est entré en paramètre dans le jest d'un joueur
	 * @param c
	 */
	public void addCardJest(Carte c) {
		getCarteJest().getJest().add(c);
		}
	

	public LinkedList<Carte> getJest(){
		return getCarteJest().getJest();
	}
	
	public String getNom() {
		return this.getName();
	}
	
	/**
	 * La méthode abstraite faire une offre
	 * @return 
	 */
	public abstract void faireUneOffre() ;
	
	
	/**
	 * La méthode abstraite prendre une carte
	 * @param joueurs La liste des joueurs de la partie
	 */
	public void prendreUneCarte(ArrayList<Joueur> joueurs) {
		
	}
	
	
	
	
	public Carte getCarteOuverte() {
		return this.carteOuverte;
	}
	
	/**
	 * Redéfinition de la méthode toString
	 * On affiche que le nom du joueur
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(getName());
		return sb.toString();
	}
	
	/**
	 * Permet de comparer les joueurs entre eux pour les classer
	 * Un joueur est supérieur à un autre si sa carte ouverte est supérieure aux cartes ouvertes des autres
	 */
	@Override
	public int compareTo(Joueur j) {
		if(this.carteOuverte.estPlusGrand(j.carteOuverte)) {
			return 1;
		}else {
			return -1;
		}
		
	}
	public LinkedList<Carte> getOffre() {
		return offre;
	}
	public void setOffre(LinkedList<Carte> offre) {
		this.offre = offre;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Jest getCarteJest() {
		return carteJest;
	}
	public void setCarteJest(Jest carteJest) {
		this.carteJest = carteJest;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public abstract int cartePossible(ArrayList<Joueur> joueurs);
	
	public abstract void prendreUneCarte(int choix, ArrayList<Joueur> nbJoueur);
	
	public void faireUneOffre(int reponse) {
		// TODO Auto-generated method stub
		
	}
}
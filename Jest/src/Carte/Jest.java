package Carte;
import java.util.*;

import Jeu.Visitor;

/**
 * La classe jest, chaque joueur aura un Jest unique
 * @author Shino
 *
 */
public class Jest implements JestElement {
	/**
	 * LinkedList pour utiliser les méthodes de la collection
	 */
	private LinkedList<Carte> jest;
	/**
	 * Constructeur de la classe Jest
	 */
	public Jest() {
		this.setJest(new LinkedList<Carte>());
	}
	/**
	 * Méthode utilisée pour le Pattern Visitor et le comptage des points
	 */
	public int accept(Visitor visitor) {
		return visitor.visit(this);
	}

	public LinkedList<Carte> getJest() {
		return jest;
	}

	public void setJest(LinkedList<Carte> jest) {
		this.jest = jest;
	}
}

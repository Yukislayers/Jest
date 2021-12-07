package Carte;
import java.util.*;
/**
 * La classe Deck qui est composé d'objets de la classe Carte
 * @author Shino
 *
 */
public class Deck{
/**
 * On utilise LinkedList pour profiter des méthodes propres à cette collection
 */
    protected LinkedList<Carte> deck;
/**
 * Le constructeur d'un deck qui fait à l'aide de 2 boucle for une association entre les 
 * énumerations Valeur et Signe pour créer des cartes   
 */
public Deck() {
	this.deck = new LinkedList<Carte>();
	for (Valeur v : Valeur.values()) 
	      for (Signe s : Signe.values()) 
	        this.deck.add(new Carte(v, s));
	// On supprime les Joker qu'on a crée en plus
	this.deck.remove(15);
	this.deck.remove(10);
	this.deck.remove(5);
}

/**
 * Constructeur de deck pour créer des decks vides
 * @param nbr Il n'a pas vraiment d'interet, il sert seulement à surcharger la méthode
 */
public Deck(int nbr) {
	this.deck = new LinkedList<Carte>();
}

public LinkedList<Carte> getDeck(){
	return this.deck;
}

/**
 * Une méthode qui permet de mélanger un oobjet de type deck
 * @param deck
 */

public static void melanger(LinkedList<Carte> deck) {
	Collections.shuffle(deck);
}

		

}



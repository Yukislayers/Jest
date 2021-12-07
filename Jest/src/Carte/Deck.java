package Carte;
import java.util.*;
/**
 * La classe Deck qui est compos� d'objets de la classe Carte
 * @author Shino
 *
 */
public class Deck{
/**
 * On utilise LinkedList pour profiter des m�thodes propres � cette collection
 */
    protected LinkedList<Carte> deck;
/**
 * Le constructeur d'un deck qui fait � l'aide de 2 boucle for une association entre les 
 * �numerations Valeur et Signe pour cr�er des cartes   
 */
public Deck() {
	this.deck = new LinkedList<Carte>();
	for (Valeur v : Valeur.values()) 
	      for (Signe s : Signe.values()) 
	        this.deck.add(new Carte(v, s));
	// On supprime les Joker qu'on a cr�e en plus
	this.deck.remove(15);
	this.deck.remove(10);
	this.deck.remove(5);
}

/**
 * Constructeur de deck pour cr�er des decks vides
 * @param nbr Il n'a pas vraiment d'interet, il sert seulement � surcharger la m�thode
 */
public Deck(int nbr) {
	this.deck = new LinkedList<Carte>();
}

public LinkedList<Carte> getDeck(){
	return this.deck;
}

/**
 * Une m�thode qui permet de m�langer un oobjet de type deck
 * @param deck
 */

public static void melanger(LinkedList<Carte> deck) {
	Collections.shuffle(deck);
}

		

}



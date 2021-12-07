package Jeu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

import Carte.Carte;
import Carte.Deck;
import Carte.JestElement;
import Carte.Signe;
import Carte.Valeur;
import Joueur.Humain;
import Joueur.IA;
import Joueur.Joueur;
import Joueur.StrategieAvancee;
import Joueur.StrategieClassique;
import Message.Message;
import Message.MessageType;

/**
 * La classe Partie qui correspond au d�roulement du jeu
 * @author Shino
 *
 */
public class Partie extends Observable implements  Runnable{
	/**
	 * La liste des joueurs de la Partie
	 */
	private ArrayList<Joueur> nbJoueur;
	/**
	 * La liste des troph�es de la partie
	 */
	private ArrayList<Carte> trophee;
	/**
	 * Le nombre d'IA de la partie qui est soit 2, soit 3
	 */
	protected int nbIA;
	/**
	 * C'est le deck de d�part qui est compos� de 17 cartes
	 */
	protected Deck deckDepart;
	/**
	 * C'est le deck qui est constitu� � chaque fin de tour sauf le dernier car on doit prendre 3 cartes du deck de d�part et ajouter les 3 cartes restantes sur le terrain puis les m�langer
	 */
	protected Deck deckIntermediaire;
	/**
	 * Le joueur � qui c'est le tour de jouer
	 */
	private Joueur joueurActif;
	
	public static int tour = 0;

	public int nbHumains;

	private int nbJoueurPartie;

public Partie(int nbJoueurs, int nbHumains) {
	
	this.nbJoueurPartie = nbJoueurs;
	this.nbHumains = nbHumains;
	this.deckDepart = new Deck();
	Deck.melanger(this.deckDepart.getDeck());
	setNbJoueur(new ArrayList<Joueur>());
	setTrophee(new ArrayList<Carte>());
	
	
	
	
}

public void commencerPartie() {
	Thread t = new Thread(this);
	t.start();
}

public void run() {
	
	genererPartie();
	
	this.play();
}

public void genererPartie() {

	for(int i = 0; i < nbHumains; i++) {
		this.setChanged();
		this.notifyObservers(new Message(MessageType.NOM, i));
	}
		
if (this.nbJoueurPartie == 3) {
		
		
		for (int i = 0; i < nbJoueurPartie - nbHumains; i++) {
			IA bot = new IA();
			int r = random(0,2);
			if(r == 0) {
				bot.setStrategie(new StrategieClassique(bot));
				//System.out.println("classique");
			}
			else {
				bot.setStrategie(new StrategieAvancee(bot));
				//System.out.println("avancee");
			}
			getNbJoueur().add(bot);
		}
				
		this.getTrophee().add(this.deckDepart.getDeck().removeFirst());		
		this.getTrophee().add(this.deckDepart.getDeck().removeFirst());

		this.deckIntermediaire = new Deck(6);
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
		
	}
	
	if (nbJoueurPartie == 4) {
		
		
		for (int i = 0; i < nbJoueurPartie - nbHumains; i++) {
			IA bot = new IA();
			int r = random(0,2);
			if(r == 0) {
				bot.setStrategie(new StrategieClassique(bot));
				//System.out.println("classique");
			}
			else {
				bot.setStrategie(new StrategieAvancee(bot));
				//System.out.println("avancee");
			}
			getNbJoueur().add(bot);
		}
		
		this.getTrophee().add(this.deckDepart.getDeck().removeFirst());		
		
		this.deckIntermediaire = new Deck(6);
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().removeFirst());
	}	
		
		
}


/**
 * M�thode pour demander au joueur une r�ponse en int
 * @param str La question qui est pos�
 * @param borneinf Le nombre minimal
 * @param bornesupp Le nombre maximal
 * @return La r�ponse du joueur
 */
private int demander(String str,int borneinf, int bornesupp) { //Methode pour demander a un joueur une r�ponse en int
	int rep=0;
	do {
		if(str != "")	System.out.println(str);
		Scanner sc = new Scanner(System.in);
		try {
			rep = sc.nextInt();
		}catch (Exception e) {
			System.out.println("(ATTENTION : un chiffre est attendu)");
			rep=borneinf-1;
		}
	}while(rep<borneinf || rep>bornesupp);
	return rep;
}

/**
 * M�thode qui permet de renvoyer un nombre al�atoire entier
 * @param min l'entier minimal qui peut �tre renvoy�
 * @param max l'entier maximal qui peut �tre renvoy�
 * @return
 */
protected int random(int min, int max) {
	Random r = new Random();
	
	int valeur;
	
	valeur = min + r.nextInt(max-min);
	return valeur;
}

/**
 * M�thode qui fait piocher chacun � leur tour les diff�rents joueurs de la partie dans le deck
 * @param deck
 */
public void Piocher(Deck deck) {
	for(int j = 0; j < 2; j++ ) {
		for (int i = 0; i < getNbJoueur().size();i++) {
			this.getNbJoueur().get(i).addCardOffre(deck.getDeck().remove());
		}
	}
}

public Joueur getJoueurActif() {
	return this.joueurActif;
}



/**
 * M�thode qui permet d'afficher l'offre de chacun des joueurs, c'est une m�thode de test pour v�rifier que tout fonctionnait
 */


public void afficherMain() {
	for (int i = 0; i < getNbJoueur().size();i++) {
		System.out.println("------------------------------");
		System.out.println(this.getNbJoueur().get(i).getNom());
		for(int j =0;j < this.getNbJoueur().get(i).getOffre().size();j++)
			System.out.println(this.getNbJoueur().get(i).getOffre().get(j));
	}
}

/**
 * M�thode qui compare les scores des joueurs 
 * @return Le joueur qui a le plus de points (le gagnant de la partie)
 */
public Joueur d�terminerGagnant() {
	int index  = 0;
	for (int i = 0; i < this.getNbJoueur().size(); i++) {
		if (this.getNbJoueur().get(index).getScore() < this.getNbJoueur().get(i).getScore() && index != i){
			index = i;
		}
	}
	return this.getNbJoueur().get(index);
}

/**
 * La m�thode qui permet de donner les conditions pour gagner les troph�es
 * @param c Le troph�e
 */
public void donnerTroph�e(Carte c) {
	// Le Joker
	if (c.getValeur().ordinal() == 0 && c.getSigne().ordinal() == 0) {
		System.out.println("La condition pour le troph�e est : le joueur qui a le jest avec le plus de points ");
	}
	// L'as de coeur
	if (c.getValeur().ordinal() == 0 && c.getSigne().ordinal() == 1) {
		System.out.println("La condition pour le troph�e est : Le joueur qui poss�de le joker ");
	}
	// L'as de pique
	if (c.getValeur().ordinal() == 0 && c.getSigne().ordinal() == 4) {
		System.out.println("La condition pour le troph�e est : le joueur qui a la plus grande carte en tr�fle ");
	}
	// L'as de tr�fle
	if (c.getValeur().ordinal() == 0 && c.getSigne().ordinal() == 3) {
		System.out.println("La condition pour le troph�e est : le joueur qui a la plus grande carte en pique ");
	}
	// L'as de carreau
	if (c.getValeur().ordinal() == 0 && c.getSigne().ordinal() == 2) {
		System.out.println("La condition pour le troph�e est : le joueur qui a le plus de 4 ");
	}
	// Le 2 de coeur
	if (c.getValeur().ordinal() == 1 && c.getSigne().ordinal() == 1) {
		System.out.println("La condition pour le troph�e est : le joueur qui poss�de le joker ");
	}
	// Le 2 de pique
	if (c.getValeur().ordinal() == 1 && c.getSigne().ordinal() == 4) {
		System.out.println("La condition pour le troph�e est : le joueur qui a le plus de 3 ");
	}
	// Le 2 de tr�fle
	if (c.getValeur().ordinal() == 1 && c.getSigne().ordinal() == 3) {
		System.out.println("La condition pour le troph�e est : le joueur qui a la plus petite carte en coeur ");
	}
	// Le 2 de carreau
	if (c.getValeur().ordinal() == 1 && c.getSigne().ordinal() == 2) {
		System.out.println("La condition pour le troph�e est : le joueur qui a la plus grande carte en carreau ");
	}
	// Le 3 de coeur
	if (c.getValeur().ordinal() == 2 && c.getSigne().ordinal() == 1) {
		System.out.println("La condition pour le troph�e est : le joueur qui poss�de le joker ");
	}
	//Le 3 de pique
	if (c.getValeur().ordinal() == 2 && c.getSigne().ordinal() == 4) {
		System.out.println("La condition pour le troph�e est : le joueur qui a le plus de 2 ");
	}
	// Le 3 de tr�fle
	if (c.getValeur().ordinal() == 2 && c.getSigne().ordinal() == 3) {
		System.out.println("La condition pour le troph�e est : le joueur qui a la plus grande carte en coeur ");
	}
	// Le 3 de carreau
	if (c.getValeur().ordinal() == 2 && c.getSigne().ordinal() == 2) {
		System.out.println("La condition pour le troph�e est : le joueur qui a la plus petite carte en carreau ");
	}
	// Le 4 de coeur
	if (c.getValeur().ordinal() == 3 && c.getSigne().ordinal() == 1) {
		System.out.println("La condition pour le troph�e est : le joueur qui poss�de le joker ");
	}
	// Le 4 de pique
	if (c.getValeur().ordinal() == 3 && c.getSigne().ordinal() == 4) {
		System.out.println("La condition pour le troph�e est : le joueur qui a la plus petite carte en tr�fle ");
	}
	// Le 4 de tr�fle
	if (c.getValeur().ordinal() == 3 && c.getSigne().ordinal() == 3) {
		System.out.println("La condition pour le troph�e est : le joueur qui a la plus petite carte en pique ");
	}
	// Le 4 de carreau
	if (c.getValeur().ordinal() == 3 && c.getSigne().ordinal() == 2) {
		System.out.println("La condition pour le troph�e est : le joueur qui a le plus de points sans joker ");
	}
	
}
/**
 * La m�thode qui permet de donner les troph�es aux joueurs en fonction des cartes de leur Jest
 * @param c Le troph�e
 */
	public void determinerTrophee(Carte c) {
		// Le Joker
		if (c.getValeur().ordinal() == 0 && c.getSigne().ordinal() == 0) {
			System.out.println("La condition pour le troph�e est : le joueur qui a le jest avec le plus de points ");
			
			for(int i = 0; i < this.getNbJoueur().size(); i++) {
				this.getNbJoueur().get(i).setScore(Partie.calculateScore(this.getNbJoueur().get(i).getCarteJest()));
				System.out.println("Le score de " + this.getNbJoueur().get(i).getNom() + " est " + this.getNbJoueur().get(i).getScore());
			}
			
			this.d�terminerGagnant().addCardJest(c);
			System.out.println(this.d�terminerGagnant().getNom() + " a re�u le troph�e");
		}
		
		// L'as de coeur
		if (c.getValeur().ordinal() == 0 && c.getSigne().ordinal() == 1) {
			System.out.println("La condition pour le troph�e est : Le joueur qui poss�de le joker ");
			
			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if (this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getSigne().ordinal() == 0) {
						this.getNbJoueur().get(i).addCardJest(c);
						System.out.println(this.getNbJoueur().get(i).getNom() + " a re�u le troph�e");
						break;
					}
				}
			}
		}
		
		// L'as de pique
		if (c.getValeur().ordinal() == 0 && c.getSigne().ordinal() == 4) {
			System.out.println("La condition pour le troph�e est : le joueur qui a la plus grande carte en tr�fle ");
			
			Carte carteMax = new Carte(Valeur.AS, Signe.TREFLE);
			
			int indexI = 0;

			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if (this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getSigne().ordinal() == 3 && this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getValeur().ordinal() > carteMax.getValeur().ordinal()) {
						carteMax = this.getNbJoueur().get(i).getCarteJest().getJest().get(j);
						indexI = i;

					}
				}
			}
			
			this.getNbJoueur().get(indexI).addCardJest(c);
			System.out.println(this.getNbJoueur().get(indexI).getNom() + " a re�u le troph�e");
		}
		
		// L'as de tr�fle
		if (c.getValeur().ordinal() == 0 && c.getSigne().ordinal() == 3) {
			System.out.println("La condition pour le troph�e est : le joueur qui a la plus grande carte en pique ");
			
			Carte carteMax = new Carte(Valeur.AS, Signe.PIQUE);
			
			int indexI = 0;

			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if (this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getSigne().ordinal() == 4 && this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getValeur().ordinal() > carteMax.getValeur().ordinal()) {
						carteMax = this.getNbJoueur().get(i).getCarteJest().getJest().get(j);
						indexI = i;

					}
				}
			}
			
			this.getNbJoueur().get(indexI).addCardJest(c);
			System.out.println(this.getNbJoueur().get(indexI).getNom() + " a re�u le troph�e");
		}
		
		// L'as de carreau
		if (c.getValeur().ordinal() == 0 && c.getSigne().ordinal() == 2) {
			System.out.println("La condition pour le troph�e est : le joueur qui a le plus de 4 ");
			
			int nbr4 = 0;
			int max4 = 0;
			int indexI = 0;
			
			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				nbr4 = 0;
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if(this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getValeur().ordinal() == 3) {
						nbr4++;
					}
					if (nbr4 > max4) {
						indexI = i;
						max4 = nbr4;
					}
				}
			}
			
			this.getNbJoueur().get(indexI).addCardJest(c);
			System.out.println(this.getNbJoueur().get(indexI).getNom() + " a re�u le troph�e");
		}
		
		// Le 2 de coeur
		if (c.getValeur().ordinal() == 1 && c.getSigne().ordinal() == 1) {
			System.out.println("La condition pour le troph�e est : le joueur qui poss�de le joker ");
			
			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if (this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getSigne().ordinal() == 0) {
						this.getNbJoueur().get(i).addCardJest(c);
						System.out.println(this.getNbJoueur().get(i).getNom() + " a re�u le troph�e");
						break;
					}
				}
			}
		}
		
		// Le 2 de pique
		if (c.getValeur().ordinal() == 1 && c.getSigne().ordinal() == 4) {
			System.out.println("La condition pour le troph�e est : le joueur qui a le plus de 3 ");
			
			int nbr3 = 0;
			int max3 = 0;
			int indexI = 0;
			
			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				nbr3 = 0;
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if(this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getValeur().ordinal() == 3) {
						nbr3++;
					}
					if (nbr3 > max3) {
						indexI = i;
						max3 = nbr3;
					}
				}
			}
			
			this.getNbJoueur().get(indexI).addCardJest(c);
			System.out.println(this.getNbJoueur().get(indexI).getNom() + " a re�u le troph�e");
		}
		
		// Le 2 de tr�fle
		if (c.getValeur().ordinal() == 1 && c.getSigne().ordinal() == 3) {
			System.out.println("La condition pour le troph�e est : le joueur qui a la plus petite carte en coeur ");
			
			Carte carteMin = new Carte(Valeur.QUATRE, Signe.COEUR);
			
			int indexI = 0;

			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if (this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getSigne().ordinal() == 1 && this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getValeur().ordinal() < carteMin.getValeur().ordinal()) {
						carteMin = this.getNbJoueur().get(i).getCarteJest().getJest().get(j);
						indexI = i;

					}
				}
			}
			
			this.getNbJoueur().get(indexI).addCardJest(c);
			System.out.println(this.getNbJoueur().get(indexI).getNom() + " a re�u le troph�e");
		}
		
		// Le 2 de carreau
		if (c.getValeur().ordinal() == 1 && c.getSigne().ordinal() == 2) {
			System.out.println("La condition pour le troph�e est : le joueur qui a la plus grande carte en carreau ");
			
			Carte carteMax = new Carte(Valeur.AS, Signe.CARREAU);
			
			int indexI = 0;

			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if (this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getSigne().ordinal() == 2 && this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getValeur().ordinal() > carteMax.getValeur().ordinal()) {
						carteMax = this.getNbJoueur().get(i).getCarteJest().getJest().get(j);
						indexI = i;

					}
				}
			}
			
			this.getNbJoueur().get(indexI).addCardJest(c);
			System.out.println(this.getNbJoueur().get(indexI).getNom() + " a re�u le troph�e");
		}
		
		// Le 3 de coeur
		if (c.getValeur().ordinal() == 2 && c.getSigne().ordinal() == 1) {
			System.out.println("La condition pour le troph�e est : le joueur qui poss�de le joker ");
			
			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if (this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getSigne().ordinal() == 0) {
						this.getNbJoueur().get(i).addCardJest(c);
						System.out.println(this.getNbJoueur().get(i).getNom() + " a re�u le troph�e");
						break;
					}
				}
			}
		}
		
		//Le 3 de pique
		if (c.getValeur().ordinal() == 2 && c.getSigne().ordinal() == 4) {
			System.out.println("La condition pour le troph�e est : le joueur qui a le plus de 2 ");
			
			int nbr2 = 0;
			int max2 = 0;
			int indexI = 0;
			
			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				nbr2 = 0;
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if(this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getValeur().ordinal() == 3) {
						nbr2++;
					}
					if (nbr2 > max2) {
						indexI = i;
						max2 = nbr2;
					}
				}
			}
			
			this.getNbJoueur().get(indexI).addCardJest(c);
			System.out.println(this.getNbJoueur().get(indexI).getNom() + " a re�u le troph�e");
		}
		
		// Le 3 de tr�fle
		if (c.getValeur().ordinal() == 2 && c.getSigne().ordinal() == 3) {
			System.out.println("La condition pour le troph�e est : le joueur qui a la plus grande carte en coeur ");
			
			Carte carteMax = new Carte(Valeur.AS, Signe.COEUR);
			
			int indexI = 0;

			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if (this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getSigne().ordinal() == 1 && this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getValeur().ordinal() > carteMax.getValeur().ordinal()) {
						carteMax = this.getNbJoueur().get(i).getCarteJest().getJest().get(j);
						indexI = i;

					}
				}
			}
			
			this.getNbJoueur().get(indexI).addCardJest(c);
			System.out.println(this.getNbJoueur().get(indexI).getNom() + " a re�u le troph�e");
		}
		
		// Le 3 de carreau
		if (c.getValeur().ordinal() == 2 && c.getSigne().ordinal() == 2) {
			System.out.println("La condition pour le troph�e est : le joueur qui a la plus petite carte en carreau ");
			
			Carte carteMin = new Carte(Valeur.QUATRE, Signe.CARREAU);
			
			int indexI = 0;

			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if (this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getSigne().ordinal() == 2 && this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getValeur().ordinal() < carteMin.getValeur().ordinal()) {
						carteMin = this.getNbJoueur().get(i).getCarteJest().getJest().get(j);
						indexI = i;

					}
				}
			}
			
			this.getNbJoueur().get(indexI).addCardJest(c);
			System.out.println(this.getNbJoueur().get(indexI).getNom() + " a re�u le troph�e");
		}
		
		// Le 4 de coeur
		if (c.getValeur().ordinal() == 3 && c.getSigne().ordinal() == 1) {
			System.out.println("La condition pour le troph�e est : le joueur qui poss�de le joker ");
			
			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if (this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getSigne().ordinal() == 0) {
						this.getNbJoueur().get(i).addCardJest(c);
						System.out.println(this.getNbJoueur().get(i).getNom() + " a re�u le troph�e");
						break;
					}
				}
			}
		}
		
		// Le 4 de pique
		if (c.getValeur().ordinal() == 3 && c.getSigne().ordinal() == 4) {
			System.out.println("La condition pour le troph�e est : le joueur qui a la plus petite carte en tr�fle ");
			
			Carte carteMin = new Carte(Valeur.QUATRE, Signe.TREFLE);
			
			int indexI = 0;

			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if (this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getSigne().ordinal() == 3 && this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getValeur().ordinal() < carteMin.getValeur().ordinal()) {
						carteMin = this.getNbJoueur().get(i).getCarteJest().getJest().get(j);
						indexI = i;

					}
				}
			}
			
			this.getNbJoueur().get(indexI).addCardJest(c);
			System.out.println(this.getNbJoueur().get(indexI).getNom() + " a re�u le troph�e");
		}
		
		// Le 4 de tr�fle
		if (c.getValeur().ordinal() == 3 && c.getSigne().ordinal() == 3) {
			System.out.println("La condition pour le troph�e est : le joueur qui a la plus petite carte en pique ");
			
			Carte carteMin = new Carte(Valeur.QUATRE, Signe.PIQUE);
			
			int indexI = 0;

			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				for (int j = 0; j < this.getNbJoueur().get(i).getCarteJest().getJest().size(); j++) {
					if (this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getSigne().ordinal() == 4 && this.getNbJoueur().get(i).getCarteJest().getJest().get(j).getValeur().ordinal() < carteMin.getValeur().ordinal()) {
						carteMin = this.getNbJoueur().get(i).getCarteJest().getJest().get(j);
						indexI = i;

					}
				}
			}
			
			this.getNbJoueur().get(indexI).addCardJest(c);
			System.out.println(this.getNbJoueur().get(indexI).getNom() + " a re�u le troph�e");
		}
		
		// Le 4 de carreau
		if (c.getValeur().ordinal() == 3 && c.getSigne().ordinal() == 2) {
			System.out.println("La condition pour le troph�e est : le joueur qui a le plus de points sans joker ");
			
			for(int i = 0; i < this.getNbJoueur().size(); i++) {
				this.getNbJoueur().get(i).setScore(Partie.calculateScoreSansJoker(this.getNbJoueur().get(i).getCarteJest()));
				System.out.println("Le score de " + this.getNbJoueur().get(i).getNom() + " est " + this.getNbJoueur().get(i).getScore());
			}
			
			this.d�terminerGagnant().addCardJest(c);
			System.out.println(this.d�terminerGagnant().getNom() + " a re�u le troph�e");
		}
	}
	
	/**
	 * La m�thode qui correspond � un tour de jeu
	 * Distribution des cartes aux joueurs
	 * Les joueurs font leur offre
	 * La variable listeJoueurInter permet de trier les joueurs en fonction de l'offre pour connaitre l'ordre de jeu
	 * Les joueurs prennent une carte qu'ils ajoutent dans leur Jest
	 * On cr�e le deckInterm�diaire qui sera utilis� pour le prochain tour en prennant 3 cartes de deck de d�part et les 3 cartes restantes sur le terrain
	 */
	public void jouerUnTour() {
		
		
	
		
		this.Piocher(this.deckIntermediaire);
		
		this.setChanged();
		this.notifyObservers(new Message(MessageType.DEBUT_TOUR));
		
		
		
		for(int i = 0; i < this.getNbJoueur().size();i++) {
			this.setJoueurActif(this.getNbJoueur().get(i));
			
			this.setChanged();
			this.notifyObservers(new Message(MessageType.FAIRE_OFFRE));
			
		}
		
	
		
		this.setChanged();
		this.notifyObservers(new Message(MessageType.OFFRE_FAIT));
		
		ArrayList<Joueur> listeInterJoueur = new ArrayList<Joueur>();
		listeInterJoueur.addAll(getNbJoueur());
		System.out.println(listeInterJoueur);
		
		Collections.sort(listeInterJoueur, Collections.reverseOrder());
		
		System.out.println(listeInterJoueur);
		
		
		
		while(listeInterJoueur.size() != 0) {
			boolean aJoue = false;
			boolean humainJoue = false;
			
			for (int i = 0; i < listeInterJoueur.size(); i++) {
				if (listeInterJoueur.get(i).getOffre().size() != 2) {
					System.out.println(listeInterJoueur.get(i).getNom() + " joue !");
					if(listeInterJoueur.get(i).getClass() == Humain.class) {
						this.setJoueurActif(listeInterJoueur.get(i));
						
						
						this.setChanged();
						this.notifyObservers(new Message(MessageType.PRENDRE_CARTE));
						
						
						listeInterJoueur.remove(i);
						
						this.setChanged();
						this.notifyObservers(new Message(MessageType.CARTE_PRISE));
						
						humainJoue = true;
						aJoue = true;
					}
					if(humainJoue == false && aJoue == false) {
						this.setJoueurActif(listeInterJoueur.get(i));
						
						this.setChanged();
						this.notifyObservers(new Message(MessageType.PRENDRE_CARTE));
						
						listeInterJoueur.remove(i);
					
						
						this.setChanged();
						this.notifyObservers(new Message(MessageType.CARTE_PRISE));
						
						aJoue = true;
					}
				}
			} 
		
			
			if (aJoue == false) {
			System.out.println(listeInterJoueur.get(0).getNom() + " joue !");
				if(listeInterJoueur.get(0).getClass() == Humain.class) {
					this.setJoueurActif(listeInterJoueur.get(0));
				
					
					this.setChanged();
					this.notifyObservers(new Message(MessageType.PRENDRE_CARTE));
					
					
					listeInterJoueur.remove(0);
					
					this.setChanged();
					this.notifyObservers(new Message(MessageType.CARTE_PRISE));
					
					humainJoue = true;
					aJoue = true;
				}
				if(humainJoue == false) {
					this.setJoueurActif(listeInterJoueur.get(0));
					
					this.setChanged();
					this.notifyObservers(new Message(MessageType.PRENDRE_CARTE));
					
					
					listeInterJoueur.remove(0);
					
					this.setChanged();
					this.notifyObservers(new Message(MessageType.CARTE_PRISE));
					
					aJoue = true;
				}
			}
		}
			
		for (int i = 0; i < this.getNbJoueur().size(); i++) {
			this.deckIntermediaire.getDeck().add(getNbJoueur().get(i).getOffre().remove());
		}
		
		for (int i = 0; i < this.getNbJoueur().size(); i++) {
		this.deckIntermediaire.getDeck().add(this.deckDepart.getDeck().remove());
		}
		
		Deck.melanger(this.deckIntermediaire.getDeck());
		
		this.setChanged();
		this.notifyObservers(new Message(MessageType.FIN_TOUR));
		
		
	}
	
	/**
	 * M�me principe que la m�thode precedente sauf qu'il n'y a pas de cr�ation de deck � la fin car ce tour correspond au dernier
	 * @param a Il ne sert qu'� surcharger la m�thode
	 */
	public void jouerUnTour(int a) {

		
		this.Piocher(this.deckIntermediaire);
		
		this.setChanged();
		this.notifyObservers(new Message(MessageType.DEBUT_TOUR));
		
		
		
		for(int i = 0; i < this.getNbJoueur().size();i++) {
			this.setJoueurActif(this.getNbJoueur().get(i));
			
			this.setChanged();
			this.notifyObservers(new Message(MessageType.FAIRE_OFFRE));
			
		
		}
		
	
		
		this.setChanged();
		this.notifyObservers(new Message(MessageType.OFFRE_FAIT));
		
		ArrayList<Joueur> listeInterJoueur = new ArrayList<Joueur>();
		listeInterJoueur.addAll(getNbJoueur());
		System.out.println(listeInterJoueur);
		
		Collections.sort(listeInterJoueur, Collections.reverseOrder());
		
		System.out.println(listeInterJoueur);
		
		
		
		while(listeInterJoueur.size() != 0) {
			boolean aJoue = false;
			boolean humainJoue = false;
			
			for (int i = 0; i < listeInterJoueur.size(); i++) {
				if (listeInterJoueur.get(i).getOffre().size() != 2) {
					System.out.println(listeInterJoueur.get(i).getNom() + " joue !");
					if(listeInterJoueur.get(i).getClass() == Humain.class) {
						this.setJoueurActif(listeInterJoueur.get(i));
						
						
						this.setChanged();
						this.notifyObservers(new Message(MessageType.PRENDRE_CARTE));
						
						
						listeInterJoueur.remove(i);
						
						this.setChanged();
						this.notifyObservers(new Message(MessageType.CARTE_PRISE));
						
						humainJoue = true;
						aJoue = true;
					}
					if(humainJoue == false && aJoue == false) {
						this.setJoueurActif(listeInterJoueur.get(i));
						
						this.setChanged();
						this.notifyObservers(new Message(MessageType.PRENDRE_CARTE));
						
						listeInterJoueur.remove(i);
						
						
						this.setChanged();
						this.notifyObservers(new Message(MessageType.CARTE_PRISE));
						
						aJoue = true;
					}
				}
			} 
		
			
			if (aJoue == false) {
			System.out.println(listeInterJoueur.get(0).getNom() + " joue !");
				if(listeInterJoueur.get(0).getClass() == Humain.class) {
					this.setJoueurActif(listeInterJoueur.get(0));
				
					
					this.setChanged();
					this.notifyObservers(new Message(MessageType.PRENDRE_CARTE));
					
					
					listeInterJoueur.remove(0);
					
					this.setChanged();
					this.notifyObservers(new Message(MessageType.CARTE_PRISE));
					
					humainJoue = true;
					aJoue = true;
				}
				if(humainJoue == false) {
					this.setJoueurActif(listeInterJoueur.get(0));
					
					this.setChanged();
					this.notifyObservers(new Message(MessageType.PRENDRE_CARTE));
					
					listeInterJoueur.remove(0);
					
					this.setChanged();
					this.notifyObservers(new Message(MessageType.CARTE_PRISE));
					
					aJoue = true;
				}
			}
		}
			
		
		
		this.setChanged();
		this.notifyObservers(new Message(MessageType.FIN_TOUR));
		
		
	}
	
	/**
	 * La m�thode qui permet de d�rouler une partie de jeu
	 */
	public void play() {
		
		this.setChanged();
		this.notifyObservers(new Message(MessageType.DEBUT_JEU));
		
	
				
			
			while(this.deckDepart.getDeck().size() != 0) {
				tour++;
				this.jouerUnTour();
			}
			tour++;
			this.jouerUnTour(1);
			
			
			for (int i = 0; i < this.getNbJoueur().size(); i++) {
				this.getNbJoueur().get(i).addCardJest(this.getNbJoueur().get(i).getOffre().remove());
			}
		
	
		
		this.setChanged();
		this.notifyObservers(new Message(MessageType.DONNER_TROPHEE));
		
		

		this.setChanged();
		this.notifyObservers(new Message(MessageType.SCORE));
		
		
		
		this.setChanged();
		this.notifyObservers(new Message(MessageType.FIN_PARTIE));
		
	 
	}
	
	/**
	 * M�thode qui permet de calculer le score des joueurs 
	 * On envoie des visitor dans les Jest des joueurs
	 * Chaque visitor correspond � une r�gle de comptage
	 * @param jest Le jest pour lequel il faut compter les points
	 * @return Le score du jest de Joueur
	 */
	public static int calculateScore(JestElement jest) {
		
		Visitor visitor1 = new RegleCarreau(); 
		Visitor visitor2 = new RegleAs(); 
		Visitor visitor3 = new RegleCoeur(); 
		Visitor visitor4 = new RegleJoker(); 
		Visitor visitor5 = new ReglePaire(); 
		Visitor visitor6 = new RegleSigneNoire(); 
		int somme = 0;
		somme = somme + jest.accept(visitor1) + jest.accept(visitor2) + jest.accept(visitor3) + jest.accept(visitor4) + jest.accept(visitor5) + jest.accept(visitor6);
		return somme;
		
	}
	
	/**
	 * M�me principe que la m�thode precedente sauf qu'il n'y a pas la RegleJoker
	 * Elle ne sert que pour la m�thode qui donne les troph�es aux joueurs
	 * @param jest
	 * @return
	 */
	public static int calculateScoreSansJoker(JestElement jest) {
		
		Visitor visitor1 = new RegleCarreau(); 
		Visitor visitor2 = new RegleAs(); 
		Visitor visitor3 = new RegleCoeur();  
		Visitor visitor5 = new ReglePaire(); 
		Visitor visitor6 = new RegleSigneNoire(); 
		int somme = 0;
		somme = somme + jest.accept(visitor1) + jest.accept(visitor2) + jest.accept(visitor3)  + jest.accept(visitor5) + jest.accept(visitor6);
		return somme;
		
	}
	public ArrayList<Joueur> getNbJoueur() {
		return nbJoueur;
	}
	public void setNbJoueur(ArrayList<Joueur> nbJoueur) {
		this.nbJoueur = nbJoueur;
	}
	public ArrayList<Carte> getTrophee() {
		return trophee;
	}
	public void setTrophee(ArrayList<Carte> trophee) {
		this.trophee = trophee;
	}

	public void setJoueurActif(Joueur joueurActif) {
		this.joueurActif = joueurActif;
	}

	/**
	 * Une m�thode qui permet de donner les r�gles du jeu
	 * @return
	 */
	
	
	public void retour() {
		setChanged();
		notifyObservers(new Message(MessageType.DEBUT_PARTIE));
	}
}
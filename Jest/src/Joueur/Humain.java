package Joueur;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import Carte.Carte;
import Message.Message;
import Message.MessageType;

/**
 * La classe qui correspond aux joueurs Humains
 * @author Shino
 *
 */
public class Humain extends Joueur{
	
	public Humain() {
		
		super();
		
	}
	
	public Humain(String nom) {
		super();
		this.setName(nom);
	}

/**
 * Redefinition de la méthode faire une offre pour un joueur de type humain
 */
public void faireUneOffre() {
	
	System.out.println(getName() + " c'est à vous de jouer !");
	
	System.out.println("Votre carte 0 est " + this.getOffre().get(0));
	System.out.println("Votre carte 4 est " + this.getOffre().get(1));
	System.out.println("Mettez 0 ou 4 pour décider de la carte que vous mettrez face ouverte");
	
	Scanner sc = new Scanner(System.in);
	int choix = sc.nextInt();
	
		
	if (choix == 4) {
		LinkedList<Carte> offreInter = new LinkedList<Carte>();
		offreInter.add(this.getOffre().getLast());
		offreInter.add(this.getOffre().getFirst());
		this.setOffre(offreInter);
		this.carteOuverte=this.getOffre().get(0);
		
	}
		this.carteOuverte = this.getOffre().get(0);
		
	System.out.println("La carte ouverte de " + this.getName() + " est : " + this.getCarteOuverte());
	}

public void faireUneOffre(int choix) {
			
	if (choix == 4) {
		LinkedList<Carte> offreInter = new LinkedList<Carte>();
		offreInter.add(this.getOffre().getLast());
		offreInter.add(this.getOffre().getFirst());
		this.setOffre(offreInter);
		this.carteOuverte=this.getOffre().get(0);
		
	}
		this.carteOuverte = this.getOffre().get(0);
		
		

		System.out.println("La carte ouverte de " + this.getName() + " est : " + this.getCarteOuverte());

	}

/**
 * Méthode qui permet de savoir chez quel joueur on peut prendre une carte;
 */
public int cartePossible(ArrayList<Joueur> joueurs) {
	
	ArrayList<Joueur> potentiel = new ArrayList<Joueur>();
	
	for (int i = 0; i < joueurs.size(); i++) {
		if(joueurs.get(i).getOffre().size() == 2) {
			potentiel.add(joueurs.get(i));
		}	
	}
	
	if(potentiel.size() > 1) {
		for(int i = 0; i < potentiel.size(); i++) {
			if(potentiel.get(i) == this) {
				potentiel.remove(i);
			}				
		}
	} 
	
	for(int i = 0; i < potentiel.size(); i++) {
		for (int j = 0; j < joueurs.size(); j++) {
			if(potentiel.get(i) == joueurs.get(j)) {
				int z = j + 4;
				System.out.println("Mettez "+j+" ou "+z+" pour prendre la carte de "+ joueurs.get(j).getName());
			}
		}
	}
	
	Scanner sc = new Scanner(System.in);
	int reponse = sc.nextInt();
	
	return reponse;
}


/**
 * Méthode qui permet de prendre une carte dans l'offre d'un autre joueur de la partie
 */
public void prendreUneCarte(int choixCarte, ArrayList<Joueur> joueurs) {
	
	
	
	if (choixCarte == 1) {
		this.addCardJest(joueurs.get(1).getOffre().remove(0));
	} else if (choixCarte == 2) {
		this.addCardJest(joueurs.get(2).getOffre().remove(0));
	} else if (choixCarte == 3) {
		this.addCardJest(joueurs.get(3).getOffre().remove(0));
	} else if (choixCarte == 5) {
		this.addCardJest(joueurs.get(1).getOffre().remove(1));
	}else if (choixCarte == 6) {
		this.addCardJest(joueurs.get(2).getOffre().remove(1));
	}else if (choixCarte == 7) {
		this.addCardJest(joueurs.get(3).getOffre().remove(1));
	}
	else if (choixCarte == 0) {
		this.addCardJest(joueurs.get(0).getOffre().remove(0));
	}
	else if (choixCarte == 4) {
		this.addCardJest(joueurs.get(0).getOffre().remove(1));
	}
	
	
}

}
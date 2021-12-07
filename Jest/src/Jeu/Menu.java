package Jeu;

import java.util.Observable;

import Message.Message;
import Message.MessageType;

public class Menu extends Observable{
	
	public Menu() {
		
	}
	public void lancerMenu() {
		setChanged();
		notifyObservers(new Message(MessageType.DEBUT_PARTIE));
	}
	
	public void startPartie() {
		setChanged();
		this.notifyObservers(new Message(MessageType.PARAM_JEU));
	}
	
	public void retour() {
		this.setChanged();
		this.notifyObservers(new Message(MessageType.DEBUT_PARTIE));
	}
	
	public Object afficherRegle() {
		String regle = "REGLE DU JEU\r\n" + 
				"\r\n" + 
				"1. FAIRE UNE OFFRE\r\n" + 
				"Mettez une de vos cartes en face ouverte.\r\n" + 
				"\r\n" + 
				"2. PRENDRE UNE CARTE\r\n" + 
				"\r\n" + 
				"Prenez une carte dans les offres des autres joueurs et ajoutez la à votre Jest\r\n" + 
				"\r\n" + 
				"3. CARTE\r\n" + 
				"\r\n" + 
				"Signe les plus forts dans : PIQUE/TREFLE/CARREAU/COEUR.\r\n" +
				"\r\n" + 
				"4. SCORE\r\n" + 
				"\r\n" + 
				"Les cartes noires rapportent des points en fonction de leur valeur.\r\n" + 
				"Si vous avez une paire de cartes noires vous gagnez +2.\r\n"+
				"Les carreaux font toujours perdre des points. \r\n"+
				"Si vous avez l'AS et aucune autre carte du même signe, celui-ci vaut 5. \r\n" +
				"Le joker vaut 4 si vous avez aucun COEUR. \r\n"+
				"Si vous avez le Joker et tous les COEUR, chaque coeur compte pour sa valeur. \r\n"+
				"";
		return regle;
	}
}

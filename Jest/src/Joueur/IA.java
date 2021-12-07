package Joueur;
import java.util.*;

import Message.Message;
import Message.MessageType;
/**
 * La classe IA
 * @author Shino
 *
 */
public class IA extends Joueur{
	/**
	 * La stratégie que le bot va adopter
	 */
	private Strategie strategie;
	/**
	 * Permet de compter le nombre d'IA
	 */
	private static int compteur = 1;
	
	/**
	 * Le constructeur de la classe IA
	 */
	public IA(Strategie s) {
		
		setName("robot"+compteur+" ");
		compteur++;
		this.strategie = s;
	}
	
	public IA() {
		setName("robot"+compteur+" ");
		compteur++;
	}
	
		public void faireUneOffre() {
			
		
			
			strategie.faireUneOffre();
			
			
		}
			
			
		public void prendreUneCarte(ArrayList<Joueur> joueurs) {
			
			
			
			strategie.prendreUneCarte(joueurs);
			
		
			
		}
		
		public void setStrategie(Strategie s) {
			this.strategie = s;
		}

		@Override
		public int cartePossible(ArrayList<Joueur> joueurs) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void prendreUneCarte(int choix, ArrayList<Joueur> nbJoueur) {
			// TODO Auto-generated method stub
			
		}
}

package Joueur;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

import Carte.Carte;
import Message.Message;
import Message.MessageType;

public class StrategieAvancee implements Strategie{

	private IA joueurVirtuel;
	
	public StrategieAvancee(IA j) {
		joueurVirtuel = j;
	}
	
	@Override
	public void faireUneOffre() {
		// TODO Auto-generated method stub
		//System.out.println("offre avancée");
		
		boolean aJoue = false;
		
		for(int i = 0; i < joueurVirtuel.getOffre().size(); i++) {
			if(joueurVirtuel.getOffre().get(i).getSigne().ordinal() < 3) {
				joueurVirtuel.carteOuverte = joueurVirtuel.getOffre().get(i);
				aJoue = true;
				
				break;
			}
			
		}
		
		if(aJoue == false) {
			int r = random(0,2);
			if(r == 0) {
				joueurVirtuel.carteOuverte = joueurVirtuel.getOffre().get(0);
			}
			else {
				LinkedList<Carte> offreInter = new LinkedList<Carte>();
				offreInter.add(joueurVirtuel.getOffre().getLast());
				offreInter.add(joueurVirtuel.getOffre().getFirst());
				joueurVirtuel.setOffre(offreInter);
				joueurVirtuel.carteOuverte = joueurVirtuel.getOffre().get(0);
			}
			
		}
		
		System.out.println("La carte ouverte de " + joueurVirtuel.getName() + " est : " + joueurVirtuel.getCarteOuverte());

	}


	@Override
	public void prendreUneCarte(ArrayList<Joueur> joueurs) {
		// TODO Auto-generated method stub
		//System.out.println("prendre carte avancée");
		
		ArrayList<Joueur> potentiel = new ArrayList<Joueur>();
		
		for(int i = 0; i < joueurs.size(); i++) {
			if(joueurs.get(i).getOffre().size() == 2) {
				potentiel.add(joueurs.get(i));
			}
		}
	
		if(potentiel.size() > 1) {
			for(int i = 0; i < potentiel.size(); i++) {
				if(potentiel.get(i) == joueurVirtuel) {
					potentiel.remove(i);
				}				
			}
		}
		
		boolean aJoue = false;
		
		for(int i = 0; i < potentiel.size(); i++) {
			//System.out.println("je rentre dans le for de l'avancée");
			if (potentiel.get(i).getCarteOuverte().getSigne().ordinal() >= 3) {
				//System.out.println("je suis dans le if de l'avancee");
				joueurVirtuel.addCardJest(potentiel.get(i).getOffre().remove(0));
				aJoue = true;
				break;
			}
		}
		
		if(aJoue == false) {
			//System.out.println("je rentre dabns le random");
			
			int r = random(0,1);
			
			
			joueurVirtuel.addCardJest(potentiel.get(r).getOffre().remove(1));
			
		}
	}
	
	protected int random(int min, int max) {
		Random r = new Random();
		int valeur;
		
		valeur = min + r.nextInt(max-min);
		return valeur;
	}

}

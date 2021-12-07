package Joueur;

import java.util.ArrayList;

import Message.Message;
import Message.MessageType;

public class StrategieClassique implements Strategie{

	private IA joueurVirtuel;
	
	public StrategieClassique(IA j) {
		joueurVirtuel = j;
	}
	@Override
	public void faireUneOffre() {
		// TODO Auto-generated method stub
		//System.out.println("offre classique");
		
		
		joueurVirtuel.carteOuverte = joueurVirtuel.getOffre().get(0);
		System.out.println("La carte ouverte de " + joueurVirtuel.getName() + " est : " + joueurVirtuel.getCarteOuverte());

	}

	@Override
	public void prendreUneCarte(ArrayList<Joueur> joueurs) {
		// TODO Auto-generated method stub
		//System.out.println("prendre carte classique;");
		for (int i = 0; i < joueurs.size(); i++) {
			if(joueurs.get(i).getOffre().size()==2) {
				joueurVirtuel.addCardJest(joueurs.get(i).getOffre().remove(0));
				break;
			}
		}
	}

}

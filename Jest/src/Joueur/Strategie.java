package Joueur;

import java.util.ArrayList;

/**
 * L'interface Strat�gie pour les IA
 * @author Shino
 *
 */
public interface Strategie {

	public void faireUneOffre();
	


	public void prendreUneCarte(ArrayList<Joueur> joueurs);
}

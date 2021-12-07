package Joueur;

import java.util.ArrayList;

/**
 * L'interface Stratégie pour les IA
 * @author Shino
 *
 */
public interface Strategie {

	public void faireUneOffre();
	


	public void prendreUneCarte(ArrayList<Joueur> joueurs);
}

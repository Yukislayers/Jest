package Jeu;
import java.util.LinkedList;

import Carte.Jest;
/**
 * La classe RegleJoker pour le pattern Visitor
 * @author Shino
 *
 */
public class RegleJoker implements Visitor{

	/**
	 * Redéfinition de la méthode visit
	 */
	public int visit(Jest jest) {
		// TODO Auto-generated method stub
		int score = 0;
		int nbrCoeur = 0;
		for(int i = 0; i < jest.getJest().size(); i++) {
			if(jest.getJest().get(i).getSigne().ordinal() == 1) {
				nbrCoeur = nbrCoeur + 1;
			}
		}
		
		for (int i = 0; i < jest.getJest().size(); i++) {
			if(jest.getJest().get(i).getSigne().ordinal() == 0 && nbrCoeur == 0) {
				score = score + 4;
			}
		}
		return score;
		
	}

		

	
	

}

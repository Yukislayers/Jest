package Jeu;
import java.util.LinkedList;

import Carte.Jest;

/**
 * La classe RegleCoeur pour le pattern Visitor
 * @author Shino
 *
 */
public class RegleCoeur implements Visitor{

	/**
	 * Redéfinition de la méthode visit
	 */
	public int visit(Jest jest) {
		// TODO Auto-generated method stub
		int score = 0;
		int nbrCoeur = 0;
		for (int i = 0; i < jest.getJest().size(); i++) {
			if (jest.getJest().get(i).getSigne().ordinal() == 1) {
				nbrCoeur = nbrCoeur + 1;
			}
		}
		
		for (int i = 0; i < jest.getJest().size(); i++) {
			if (nbrCoeur <= 3) {
				if (jest.getJest().get(i).getSigne().ordinal() == 1 && jest.getJest().get(i).getValeur().ordinal() != 0) {
					score = score - (jest.getJest().get(i).getValeur().ordinal() + 1);
				}
			} else  if (nbrCoeur == 4){
				for (int j = 0; j < jest.getJest().size(); j++) {
					if (jest.getJest().get(j).getSigne().ordinal() == 0) {
						score = score + 9; 
						break;
					}
				}
			}
		}
		return score;
	}

}

package Jeu;
import java.util.LinkedList;

import Carte.Jest;

/**
 * La classe RegleSigneNoire pour le pattern Visitor
 * @author Shino
 *
 */
public class RegleSigneNoire implements Visitor{

	/**
	 * Redéfinition de la méthode visit
	 */
	public int visit(Jest jest) {
		// TODO Auto-generated method stub
		int score = 0;
		for (int i = 0; i < jest.getJest().size(); i++) {
			if(jest.getJest().get(i).getSigne().ordinal() == 3 && jest.getJest().get(i).getValeur().ordinal() != 0 || jest.getJest().get(i).getSigne().ordinal() == 4 && jest.getJest().get(i).getValeur().ordinal() != 0) {
				score = score + (jest.getJest().get(i).getValeur().ordinal() + 1);
			}
		}
		return score;
	}
	
}

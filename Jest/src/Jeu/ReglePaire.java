package Jeu;
import java.util.LinkedList;

import Carte.Jest;

/**
 * La classe ReglePaire pour le pattern Visitor
 * @author Shino
 *
 */
public class ReglePaire implements Visitor{

	/**
	 * Redéfinition de la méthode visit
	 */
	public int visit(Jest jest) {
		// TODO Auto-generated method stub
		int score = 0;
		for (int i = 0; i < jest.getJest().size(); i++) {
			if (jest.getJest().get(i).getSigne().ordinal() == 3 && jest.getJest().get(i).getValeur().ordinal() != 0) {
				for (int j = 0; j < jest.getJest().size(); j++) {
					if (jest.getJest().get(j).getSigne().ordinal() == 4 && jest.getJest().get(i).getValeur().ordinal() == jest.getJest().get(j).getValeur().ordinal()) {
						score = score + 2;
					}
				}
			}
		}
		return score;
	}

}

package Jeu;
import java.util.*;

import Carte.Carte;
import Carte.Jest;

/**
 * La classe RegleAs pour le pattern Visitor
 * @author Shino
 *
 */
public class RegleAs implements Visitor{


	/**
	 * Redéfinition de la méthode visit
	 */
	public int visit(Jest jest) {
		
		int score = 0;
		ArrayList<Carte> nbrAs = new ArrayList<Carte>();
		ArrayList<Integer> index = new ArrayList<Integer>();
		for(int i = 0; i < jest.getJest().size(); i++) {
			if(jest.getJest().get(i).getValeur().ordinal() == 0 && jest.getJest().get(i).getSigne().ordinal() != 0) {
				nbrAs.add(jest.getJest().get(i));
				index.add(i);
			}
		}
		

		if (nbrAs.size() == 0) {
			return score;
		} else {
			while (nbrAs.size() != 0) {
				for(int i = 0; i < jest.getJest().size(); i++) {
				
						if(nbrAs.get(0).getSigne().ordinal() == jest.getJest().get(i).getSigne().ordinal() && index.get(0) != i && nbrAs.get(0).getSigne().ordinal() == 1) {
							score = score - 1;
							break;
						
						}
						else if(nbrAs.get(0).getSigne().ordinal() == jest.getJest().get(i).getSigne().ordinal() && index.get(0) != i && nbrAs.get(0).getSigne().ordinal() == 2) {
							score = score - 1;
							break;
						
						}
						else if(nbrAs.get(0).getSigne().ordinal() == jest.getJest().get(i).getSigne().ordinal() && index.get(0) != i && nbrAs.get(0).getSigne().ordinal() == 3) {
							score = score + 1;
							break;
						
						}
						else if(nbrAs.get(0).getSigne().ordinal() == jest.getJest().get(i).getSigne().ordinal() && index.get(0) != i && nbrAs.get(0).getSigne().ordinal() == 1) {
							score = score + 1;
							break;
						
						}
				}
					if (score == 0 && nbrAs.get(0).getSigne().ordinal() == 1) {
						score = score - 5;
					}
					else if (score == 0 && nbrAs.get(0).getSigne().ordinal() == 2) {
						score = score - 5;
					}
					else if (score == 0 && nbrAs.get(0).getSigne().ordinal() == 3) {
						score = score + 5;
					}
					else if (score == 0 && nbrAs.get(0).getSigne().ordinal() == 4) {
						score = score + 5;
					}
				nbrAs.remove(0);
				index.remove(0);
			} 
		}
		return score;
	}

}

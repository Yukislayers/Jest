package Carte;
import Jeu.Visitor;

/**
 * Interface qui permet le bon fonctionnement du pattern Visitor
 * @author Shino
 *
 */
public interface JestElement {

		public int accept(Visitor visitor);
}

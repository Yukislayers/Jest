package Message;

/**
 * Classe qui repr�sente les messages �mis par le mod�le dans le cas de la communication avec la vue.
 * Permet de venir pr�ciser l'utilisation du patron observer/observable
 * Un Message est compos� d'un type (qui est une �num�ration)
 * @author Shino
 * @see MessageType
 * @see Vue
 */
public class Message {
	
	MessageType contenu;
	Object o;
	
	/**
	 * Le constructeur d'un Message
	 * @param m @see MessageType
	 */
	public Message(MessageType m) {
		this.contenu = m;
	}
	
	/**
	 * Cr�ation d'un message avec un Type et un objet 
	 */
	public Message(MessageType m, Object o) {
		this.contenu = m;
		this.o  = o;
	}

	
	public MessageType getType() {
		return this.contenu;
	}
	
	public Object getObject() {
		return this.o;
	}
}

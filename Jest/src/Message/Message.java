package Message;

/**
 * Classe qui représente les messages émis par le modèle dans le cas de la communication avec la vue.
 * Permet de venir préciser l'utilisation du patron observer/observable
 * Un Message est composé d'un type (qui est une énumération)
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
	 * Création d'un message avec un Type et un objet 
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

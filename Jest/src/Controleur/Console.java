package Controleur;


import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import Jeu.*;
import Joueur.Humain;
import Message.Message;

public class Console implements Controleur,Runnable, Observer{

	private Partie partie;
	private Menu menu;
	
	public void demarrer() {
		Thread t = new Thread(this);
		t.start();
	}
	
	public Console() {
		
	}
	
	private int demander(String str,int borneinf, int bornesupp) { //Methode pour demander a un joueur une réponse en int
		int rep=0;
		do {
			if(str != "")	System.out.println(str);
			Scanner sc = new Scanner(System.in);
			try {
				rep = sc.nextInt();
			}catch (Exception e) {
				System.out.println("(ATTENTION : un chiffre est attendu)");
				rep=borneinf-1;
			}
		}while(rep<borneinf || rep>bornesupp);
		return rep;
	}
	
	private String demander(String str) { //Methode pour demander a un joueur une réponse en char(comme 'o')
		Scanner sc = new Scanner(System.in);
		
		String rep = sc.nextLine();
		System.out.println("Votre nom est :" +rep);
		return rep;
	}

	
	public void run() {
		// TODO Auto-generated method stub
			
			menu = new Menu();
			
		
			menu.addObserver(new Observer() {
				public void update(Observable o, Object arg) {
					if(arg instanceof Message) {
						Message msg = (Message) arg;
						switch (msg.getType()) {
						case DEBUT_PARTIE:
							int rep = demander("Que voulez-vous faire ?\n1-Commencer une nouvelle partie\n2-Consulter les règles\n3-Quitter le jeu", 1, 3);
							if(rep==1) menu.startPartie();
							else if(rep==2) {
								System.out.println(menu.afficherRegle());
								Scanner sc = new Scanner(System.in);
								System.out.println("(Appuyer sur entré)");
								sc.nextLine();
								menu.retour();						
							}else if(rep==4) {
								System.out.println("Aurevoir et à bientot !");
								try {
									Thread.sleep(2000);
								} catch (Exception e) {}
								System.exit(0);
							}
							break;
						case CARTE_PRISE:
							break;
						case FAIRE_OFFRE:
							break;
						case FIN_PARTIE:
							break;
						case FIN_TOUR:
							break;
						case OFFRE_FAIT:
							break;
						case PARAM_JEU:
							int nbJoueurs = demander("Combien de joueurs voulez-vous ? 3 ou 4 ?" , 3 , 4);
							int nbHumains = demander("Combien de joueurs humains êtes vous ? 1 ou " + nbJoueurs + " ?", 1, nbJoueurs);
						
						
							partie = new Partie(nbJoueurs, nbHumains);
							partie.commencerPartie();
							
							break;
						case PRENDRE_CARTE:
							break;							
						case NOM:
							break;
						case SCORE:
							break;
						default:
							break;
						}
					}
				}
			});
			menu.lancerMenu();
			partie.addObserver(this);
	}
	
	@Override
	public void update(Observable arg0, Object arg) {
		if(arg instanceof Message) {
			Message msg = (Message) arg;
			switch(msg.getType()) {
			case DEBUT_PARTIE:
				int rep = demander("Que voulez-vous faire ?\n1-Commencer une nouvelle partie\n2-Consulter les règles\n3-Quitter le jeu", 1, 3);
				if(rep==1) menu.startPartie();
				else if(rep==2) {
					System.out.println(menu.afficherRegle());
					Scanner sc = new Scanner(System.in);
					System.out.println("(Appuyer sur entré)");
					sc.nextLine();
					menu.retour();						
				}else if(rep==4) {
					System.out.println("Aurevoir et à bientot !");
					try {
						Thread.sleep(2000);
					} catch (Exception e) {}
					System.exit(0);
				}
				break;
			case PARAM_JEU:
				int nbJoueurs = demander("Combien de joueurs voulez-vous ? 3 ou 4 ?" , 3 , 4);
				int nbHumains = demander("Combien de joueurs humains êtes vous ? 1 ou " + nbJoueurs + " ?", 1, nbJoueurs);
			
			
				partie = new Partie(nbJoueurs, nbHumains);
				partie.commencerPartie();
				
				break;
			case NOM:
				
					Scanner sc = new Scanner(System.in);
					System.out.println("Joueur "+((int)msg.getObject()+1)+" quel est votre prénom ?");
					String nom = sc.nextLine();
					partie.getNbJoueur().add(new Humain(nom));
				
			
				break;
			case DEBUT_JEU:
				
				System.out.println("Les trophée sont " + partie.getTrophee());	
				
				
				for (int i = 0; i < partie.getTrophee().size(); i++) {
					partie.donnerTrophée(partie.getTrophee().get(i));
				}
				
				break;
			case DEBUT_TOUR:
				System.out.println("------------------------------");
				
				System.out.println("NOUVEAU TOUR");
				break;
			case FAIRE_OFFRE:
				
				partie.getJoueurActif().faireUneOffre();
				
				break;
			case PRENDRE_CARTE:
				
				if (partie.getJoueurActif().getClass() == partie.getNbJoueur().get(0).getClass()) {
					int choix = partie.getNbJoueur().get(0).cartePossible(partie.getNbJoueur());
					partie.getJoueurActif().prendreUneCarte(choix,partie.getNbJoueur());
				} else {
					partie.getJoueurActif().prendreUneCarte(partie.getNbJoueur());

				}
				
				break;
			case DONNER_TROPHEE:
				System.out.println("------------------------------");
				System.out.println("Les jest des joueurs avant avoir donné les trophées");
				for (int i = 0; i < partie.getNbJoueur().size(); i++) {
					System.out.println(partie.getNbJoueur().get(i).getCarteJest().getJest());
				}
				
				System.out.println("------------------------------");
				
				System.out.println("Les trophée sont " + partie.getTrophee());
				
				
				for (int i = 0; i < partie.getTrophee().size(); i++) {		
					partie.determinerTrophee(partie.getTrophee().get(i));
				}
				System.out.println("------------------------------");
				System.out.println("Les jest des joueurs après distribution des trophées");
				for (int i = 0; i < partie.getNbJoueur().size(); i++) {
					System.out.println(partie.getNbJoueur().get(i).getCarteJest().getJest());
				}
				break;
			case FIN_PARTIE:
				System.out.println(partie.déterminerGagnant().getNom() + " a gagné la partie !");
				break;
			case FIN_TOUR:
				for(int i = 0; i < partie.getNbJoueur().size(); i++) {
					if(partie.getNbJoueur().get(i).getClass() == Humain.class) {
						System.out.println("Jest de : "+ partie.getNbJoueur().get(i).getName() + " " + partie.getNbJoueur().get(0).getCarteJest().getJest());
					}
				}
				break;
			case SCORE:
				System.out.println("------------------------------");
				
				for(int i = 0; i < partie.getNbJoueur().size(); i++) {
					partie.getNbJoueur().get(i).setScore(Partie.calculateScore(partie.getNbJoueur().get(i).getCarteJest()));
					System.out.println("Le score de " + partie.getNbJoueur().get(i).getNom() + " est " + partie.getNbJoueur().get(i).getScore());
				}	
				
				break;
			default:
				break;
			}
		}
	}
		
}
	
	


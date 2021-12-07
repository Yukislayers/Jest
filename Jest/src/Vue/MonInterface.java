package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


import Controleur.Controleur;
import Jeu.Menu;
import Jeu.Partie;
import Joueur.Humain;
import Message.Message;

import net.miginfocom.swing.MigLayout;



public class MonInterface extends JFrame implements Controleur, Runnable, Observer{


	private Menu menu;
	private Partie partie;
	private JoueurHumainGraphique panelHumain;
	private JoueurVirtuelGraphique panelIA;
	private JoueurVirtuelGraphique2 panelIA2;
	private JoueurVirtuelGraphique3 panelIA3;
	private Plateau plateau;
	private MenuGraphique menuGraph;
	private JLabel infoPartie;
	private Score tabScore;
	
	


	/**
	 * Create the application.
	 */
	public MonInterface() {
		
		initialize();

	}
	
	public void demarrer() {
		Thread t = new Thread(this);
		t.start();
	}
		
	
	
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 900);
		setTitle("Jest");
		
		setVisible(true);
			
				
		
		
	}
	public void run() {
	
		menu = new Menu();
		menu.addObserver(this);		
		menu.lancerMenu();
	
		
	}
	
	public void update(Observable o, Object arg1) {
		if(arg1 instanceof Message){
			Message msg = (Message) arg1;
			switch(msg.getType()) {
			case DEBUT_PARTIE:
				getContentPane().add(new MenuGraphique(menu));
				getContentPane().revalidate();				
				break;
				
			case PARAM_JEU:					
				int nbHumains;
				int nbJoueurs;
				String var1[] = {"3","4"};
				String var2[] = {"1","2","3","4"};
				
				nbJoueurs = Integer.valueOf((String)JOptionPane.showInputDialog(null,"Combien de joueurs voulez-vous ?","Choix joueurs",JOptionPane.QUESTION_MESSAGE, null,var1,var1[0]));
				nbHumains = Integer.valueOf((String)JOptionPane.showInputDialog(null,"Combien de joueurs humains êtes-vous ?","Choix joueurs",JOptionPane.QUESTION_MESSAGE, null,var2,var2[0]));

				partie = new Partie(nbJoueurs, nbHumains);	
				partie.addObserver(this);
				partie.commencerPartie();
				break;
				
			case NOM:
				
				String nom = JOptionPane.showInputDialog(null, "Joueur"+((int)msg.getObject()+1)+" quel est votre nom ?", "Nom", JOptionPane.QUESTION_MESSAGE);
				partie.getNbJoueur().add(new Humain(nom));
				
				break;
				
			case FAIRE_OFFRE:
				
				infoPartie.setText(partie.getJoueurActif().getNom()+", vous devez faire une offre");
				
				if (partie.getJoueurActif().getClass() == Humain.class) {
					panelHumain.setClickable(true);
					int reponse = -1;
					
					panelHumain.setLastClick(-1);
					while(panelHumain.getLastClick() == -1) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(panelHumain.getLastClick() != -1) {
							reponse = panelHumain.getLastClick();
						}
					}
					
					
					panelHumain.setClickable(false);
					panelHumain.setLastClick(-1);
					
					partie.getJoueurActif().faireUneOffre(reponse);
				} else {
					partie.getJoueurActif().faireUneOffre();
				}
				
				
				
				
				break;
				
			case OFFRE_FAIT:
				panelHumain.refreshOffre();
				panelIA.refreshOffre();
				panelIA2.refreshOffre();
				if(partie.getNbJoueur().size() == 4) {
					panelIA3.refreshOffre();
				}
				break;
				
			case DEBUT_JEU:
				plateau = new Plateau(partie);
				add(plateau);

				break;
				
			case DEBUT_TOUR:
				afficherPartie();
				if(Partie.tour > 1) {
					panelHumain.addCartePanelJest();;
				}
				break;
				
			case FIN_PARTIE:				
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
				
				for(int i = 0; i < partie.getNbJoueur().size(); i++) {
					partie.getNbJoueur().get(i).setScore(Partie.calculateScore(partie.getNbJoueur().get(i).getCarteJest()));
					System.out.println("Le score de " + partie.getNbJoueur().get(i).getNom() + " est " + partie.getNbJoueur().get(i).getScore());
				}
				getContentPane().removeAll();
				tabScore = new Score(partie);
				add(tabScore);
				break;
				
			case FIN_TOUR:				
				break;
				
			case PRENDRE_CARTE:
				
				infoPartie.setText(partie.getJoueurActif().getNom()+",vous devez prendre une carte");
				int reponse2 = -1;
				
				if (partie.getJoueurActif().getClass() == Humain.class) {
					if (partie.getNbJoueur().size() == 3) {
						panelHumain.setClickable(true);
						panelIA.setClickable(true);
						panelIA2.setClickable(true);
						
						
						panelHumain.setLastClick(-1);
						panelIA.setLastClick(-1);
						panelIA2.setLastClick(-1);
						
						
						
						
					while (panelHumain.getLastClick() == -1 && panelIA.getLastClick() == -1 && panelIA2.getLastClick() == -1) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					
						if (panelIA.getLastClick() != -1) {
							reponse2 = panelIA.getLastClick();
						}
						else if (panelIA2.getLastClick() != -1){
							reponse2 = panelIA2.getLastClick();
						}
						else {
							reponse2 = panelHumain.getLastClick();
						}
					}
							
						
						
						panelIA.setClickable(false);
						panelIA2.setClickable(false);
					
						panelHumain.setClickable(false);
						
						panelHumain.setLastClick(-1);
						panelIA.setLastClick(-1);
						panelIA2.setLastClick(-1);
						}
						else {
							panelHumain.setClickable(true);
							panelIA.setClickable(true);
							panelIA2.setClickable(true);
							panelIA3.setClickable(true);
							
							panelHumain.setLastClick(-1);
							panelIA.setLastClick(-1);
							panelIA2.setLastClick(-1);
							panelIA3.setLastClick(-1);
							
							
							
							while(panelHumain.getLastClick() == -1 && panelIA.getLastClick() == -1 && panelIA2.getLastClick() == -1 && panelIA3.getLastClick() == -1) {
								try {
									Thread.sleep(100);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								
								if (panelIA.getLastClick() != -1) {
									reponse2 = panelIA.getLastClick();
								}
								else if (panelIA2.getLastClick() != -1){
									reponse2 = panelIA2.getLastClick();
								}
								else if(panelIA3.getLastClick() != -1){
									reponse2 = panelIA3.getLastClick();
								}
								else {
									reponse2 = panelHumain.getLastClick();
								}
							}
							
							panelIA.setClickable(false);
							panelIA2.setClickable(false);
							panelIA3.setClickable(false);
							panelHumain.setClickable(false);
							
							panelHumain.setLastClick(-1);
							panelIA.setLastClick(-1);
							panelIA2.setLastClick(-1);
							panelIA3.setLastClick(-1);
						}
							System.out.println(reponse2);
				 			partie.getJoueurActif().prendreUneCarte(reponse2, partie.getNbJoueur());
				} else { 
					partie.getJoueurActif().prendreUneCarte(partie.getNbJoueur());

				}
		
				
				break;
			case CARTE_PRISE:
				for(int i = 0; i < partie.getNbJoueur().size(); i++) {
					if(partie.getNbJoueur().get(i).getClass() == Humain.class) {
						System.out.println("Jest de : "+ partie.getNbJoueur().get(i).getName() + " " + partie.getNbJoueur().get(0).getCarteJest().getJest());
					}
				}
				
				panelHumain.refreshCarte();
				panelIA.refreshCarte();
				panelIA2.refreshCarte();
				if(partie.getNbJoueur().size() == 4) {
					panelIA3.refreshCarte();
				}
				break;
			case SCORE:				
				break;
			default:
				break;
				
			}
		}
	}

	
	private void afficherPartie() {
		
		getContentPane().removeAll();
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(new BorderLayout(0,0));
		plateau = new Plateau(partie);
		
		getContentPane().add(plateau, BorderLayout.CENTER);
		
		infoPartie = plateau.getPanelInfo();
		panelHumain = plateau.getPanelJoueurAct();
		panelIA = plateau.getPanelJoueurAdv1();
		panelIA2 = plateau.getPanelJoueurAdv2();
		
		panelHumain.setJoueur(partie.getNbJoueur().get(0));
		panelIA.setJoueur(partie.getNbJoueur().get(1));
		panelIA2.setJoueur(partie.getNbJoueur().get(2));	
		
		if (partie.getNbJoueur().size() == 4) {
			panelIA3 = plateau.getPanelJoueurAdv3();
			panelIA3.setJoueur(partie.getNbJoueur().get(3));
		}
		
		
		getContentPane().revalidate();
	}
		
	
	
	

}

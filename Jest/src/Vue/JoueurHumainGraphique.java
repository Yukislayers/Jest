package Vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Joueur.Joueur;

public class JoueurHumainGraphique extends JPanel{


		private Joueur joueur;
		private JPanel panelJest, panelCarte;
		private JLabel l1,b1,b2, jest;
		private JLabel j1,j2,j3,j4,j5,j6, j7;
		private boolean clickable = false;
		private int lastClick = 0;
		private static int compteur = 0;
		
		
		public JoueurHumainGraphique() {
			
			//this.setPreferredSize(new Dimension(500, 500));
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			
			panelCarte = new JPanel();
			panelCarte.setLayout(new FlowLayout());
			
			
			l1 = new JLabel("");
			l1.setFont(new Font("Ink Free", Font.BOLD, 25));
			
			
			b1 = new JLabel("");
			b1.setIcon(new ImageIcon("images\\retourne.png"));
			//b1.setIcon(new ImageIcon("images\\"+joueur.getOffre().get(1).getTypeCarte()+".png"));
			b1.setPreferredSize(new Dimension(166, 229));
			b1.addMouseListener(new MouseListener() {
				
				public void mouseReleased(MouseEvent e) {
					if(clickable) {
						lastClick = 0;
					}
				}
			
				public void mousePressed(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
			});
			panelCarte.add(b1 );
			b2 = new JLabel("");
			b2.setIcon(new ImageIcon("images\\retourne.png"));
			//b2.setIcon(new ImageIcon("images\\"+joueur.getOffre().get(0).getTypeCarte()+".png"));
			b2.setPreferredSize(new Dimension(166, 229));
			b2.addMouseListener(new MouseListener() {
				
				public void mouseReleased(MouseEvent e) {
					if(clickable) {
						lastClick = 4;
					}
				}
			
				public void mousePressed(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
			});
			panelCarte.add(b2);
			setOpaque(false);
			
			this.add(panelCarte);
			
			
			
			panelJest = new JPanel();
			//panelJest.setLayout(new FlowLayout());
			
			
			
			
	
			
			this.add(panelJest);
		}
	/**
	 * Une méthode qui permet de raffraichir les cartes que possedent le joueur au début de chaque tour après la distribution des cartes
	 */
	public void refresh() {
		
		b1.setIcon(new ImageIcon("Images\\"+joueur.getOffre().get(0).getTypeCarte()+".png"));
		b2.setIcon(new ImageIcon("Images\\"+joueur.getOffre().get(1).getTypeCarte()+".png"));
	}
	/**
	 * Une méthode qui permet de mettre visuellement la carte ouverte du joueur visible et de retourner l'autre
	 */
public void refreshOffre() {
		
		b1.setIcon(new ImageIcon("Images\\"+joueur.getOffre().get(0).getTypeCarte()+".png"));
		b2.setIcon(new ImageIcon("Images\\retourne.png"));
	}
/**
 * Une méthode qui permet de montrer visuellement quelle cartes ont été prises par les joueurs lors du tour de jeu
 */
public void refreshCarte() {
	if (joueur.getOffre().size() == 1 && joueur.getOffre().get(0) == joueur.getCarteOuverte()) {
		b2.setVisible(false);
	} else if (joueur.getOffre().size() == 1 && joueur.getOffre().get(0) != joueur.getCarteOuverte()) {
		b1.setVisible(false);
	}
	
}
	
/**
 * Une méthode qui permet d'ajouter des label dans l'attribut panelJest, chaque label correspond à une carte du jest du joueur
 */
	public void addCartePanelJest() {
		for(int i = 0; i < joueur.getCarteJest().getJest().size(); i++) {
			JLabel carte = new JLabel();
			carte.setIcon(new ImageIcon("Images\\"+joueur.getJest().get(i).getTypeCarte()+"_jest.png"));
			panelJest.add(carte);
		}
		this.validate();

		compteur++;
	}
		
	public void setClickable(boolean t) {
		this.clickable = t;
	}
	
	public int getLastClick() {
		return this.lastClick;
	}
	
	public void setLastClick(int n) {
		this.lastClick = n;
	}

	public void setJoueur(Joueur joueurActif) {
		// TODO Auto-generated method stub
		this.joueur = joueurActif;
		refresh();
	}
}

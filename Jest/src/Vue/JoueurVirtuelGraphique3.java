package Vue;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import Joueur.*;
import net.miginfocom.swing.MigLayout;

public class JoueurVirtuelGraphique3 extends JPanel{


		private Joueur joueur;
		private JLabel l1,b1,b2;
		private boolean clickable = false;
		private int lastClick = 0;
		
		
		public JoueurVirtuelGraphique3() {
			//this.setPreferredSize(new Dimension(300, 300));
			setLayout(new MigLayout("fillx"));
			
			l1 = new JLabel("");
			l1.setFont(new Font("Ink Free", Font.BOLD, 25));
			this.add(l1,"span,center");
			
			b1 = new JLabel("");
			b1.setIcon(new ImageIcon("images\\retourne.png"));
			b1.setPreferredSize(new Dimension(166, 229));
			b1.addMouseListener(new MouseListener() {
				
				public void mouseReleased(MouseEvent e) {
					if(clickable) {
						lastClick = 3;
					}
				}
			
				public void mousePressed(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
			});
			this.add(b1, "skip,split 2, gapright 15px");
			b2 = new JLabel("");
			b2.setIcon(new ImageIcon("images\\retourne.png"));
			b2.setPreferredSize(new Dimension(166, 229));
			b2.addMouseListener(new MouseListener() {
				
				public void mouseReleased(MouseEvent e) {
					if(clickable) {
						lastClick = 7;
					}
				}
			
				public void mousePressed(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
			});
			this.add(b2);
			setOpaque(false);
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
	public void setClickable(boolean t) {
		this.clickable = t;
	}
	
	public int getLastClick() {
		return this.lastClick;
	}
	
	public void setLastClick(int n) {
		this.lastClick = n;
	}

	public void setJoueur(Joueur joueur2) {
		// TODO Auto-generated method stub
		this.joueur = joueur2;
		
	}
}
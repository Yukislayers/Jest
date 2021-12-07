package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Joueur.*;

public class JoueurVirtuelGraphique extends JPanel{

	private Joueur joueur;
	private JLabel l1, b1, b2 ;
	private boolean clickable = false;
	private int lastClick = 0;

	public JoueurVirtuelGraphique() {
		setBackground(new Color(209, 193, 187));
		//this.setPreferredSize(new Dimension(300, 400));
		setLayout(new BorderLayout(0, 0));
		
		l1 = new JLabel("");
		l1.setBackground(new Color(209, 193, 187));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setFont(new Font("Ink Free", Font.BOLD, 25));
		add(l1, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(2, 0, 0, 0));
		
		b1 = new JLabel("");
		b1.setHorizontalAlignment(SwingConstants.CENTER);
		b1.setIcon(new ImageIcon("images\\retourne_retourne1.png"));
		b1.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				if(clickable) {
					lastClick = 1;
				}
			}
		
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		panel.add(b1);
		
		b2 = new JLabel("");
		b2.setHorizontalAlignment(SwingConstants.CENTER);
		b2.setIcon(new ImageIcon("images\\retourne_retourne1.png"));
		b2.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				if(clickable) {
					lastClick = 5;
				}
			}
		
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
		});
		panel.add(b2);
	}
	/**
	 * Une méthode qui permet de raffraichir les cartes que possedent le joueur au début de chaque tour après la distribution des cartes
	 */
	public void refresh() {
		
		b1.setIcon(new ImageIcon("Images\\"+joueur.getOffre().get(0).getTypeCarte()+"_retourne1.png"));
		b2.setIcon(new ImageIcon("Images\\"+joueur.getOffre().get(1).getTypeCarte()+"_retourne1.png"));
	}
	/**
	 * Une méthode qui permet de mettre visuellement la carte ouverte du joueur visible et de retourner l'autre
	 */
public void refreshOffre() {
		
		b1.setIcon(new ImageIcon("Images\\"+joueur.getOffre().get(0).getTypeCarte()+"_retourne1.png"));
		b2.setIcon(new ImageIcon("Images\\retourne_retourne1.png"));
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
		joueur = joueur2;
		
	}
}

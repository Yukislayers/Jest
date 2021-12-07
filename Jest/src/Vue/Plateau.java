package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Jeu.Partie;

public class Plateau extends JPanel{

	private JoueurHumainGraphique joueurGraph;
	private JoueurVirtuelGraphique joueurIA;
	private JoueurVirtuelGraphique2 joueurIA2;
	private JoueurVirtuelGraphique3 joueurIA3;
	private JPanel panelTrophee, panelCentral;
	private JLabel t1, t2, b2;
	private boolean clickable = false;
	private int lastClick = 0;
	private Partie partie;
	
	public Plateau(Partie p) {
		
		partie = p;
		
		if(partie.getNbJoueur().size() == 3) {
		this.setLayout(new BorderLayout(0,0));
		
		joueurGraph = new JoueurHumainGraphique();
		joueurIA = new JoueurVirtuelGraphique();
		joueurIA2 = new JoueurVirtuelGraphique2();
		
		
		this.add(joueurGraph, BorderLayout.SOUTH);
		this.add(joueurIA, BorderLayout.WEST);
		this.add(joueurIA2, BorderLayout.EAST);
		
		
		panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		
		panelTrophee = new JPanel();
		panelTrophee.setLayout(new FlowLayout());
		
		t1 = new JLabel("");
		t1.setHorizontalAlignment(SwingConstants.CENTER);
		t1.setIcon(new ImageIcon("Images\\"+partie.getTrophee().get(0).getTypeCarte()+".png"));
		//t1.setIcon(new ImageIcon("Images\\retourne.png"));
		t1.setPreferredSize(new Dimension(166, 229));
		
		panelTrophee.add(t1);
		
		t2 = new JLabel("");
		t2.setHorizontalAlignment(SwingConstants.CENTER);
		t2.setIcon(new ImageIcon("Images\\"+partie.getTrophee().get(1).getTypeCarte()+".png"));
		//t2.setIcon(new ImageIcon("Images\\retourne.png"));
		t2.setPreferredSize(new Dimension(166, 229));
		
		panelTrophee.add(t2);
		
		panelCentral.add(panelTrophee);
		
		this.add(panelCentral, BorderLayout.CENTER);
		
		b2 = new JLabel(" Vous devez selectionner une carte ");
		b2.setFont(new Font("Calibri", Font.BOLD, 25));
		b2.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED,Color.GRAY,Color.GRAY));
		b2.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(b2, BorderLayout.NORTH);
		setOpaque(false);
		}
		 
		 
		
		else {
			this.setLayout(new BorderLayout(0,0));
			
			joueurGraph = new JoueurHumainGraphique();
			joueurIA = new JoueurVirtuelGraphique();
			joueurIA2 = new JoueurVirtuelGraphique2();
			joueurIA3 = new JoueurVirtuelGraphique3();
			
			this.add(joueurGraph, BorderLayout.SOUTH);
			this.add(joueurIA, BorderLayout.WEST);
			this.add(joueurIA2, BorderLayout.EAST);
			this.add(joueurIA3, BorderLayout.NORTH);
			
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout(0,0));
			
			t1 = new JLabel("");
			t1.setHorizontalAlignment(SwingConstants.CENTER);
			t1.setIcon(new ImageIcon("Images\\"+partie.getTrophee().get(0).getTypeCarte()+".png"));
			//t1.setIcon(new ImageIcon("Images\\retourne.png"));
			t1.setPreferredSize(new Dimension(166, 229));
			
			panel.add(t1, BorderLayout.CENTER);
			
			b2 = new JLabel(" Vous devez selectionner une carte ");
			b2.setFont(new Font("Calibri", Font.BOLD, 25));
			b2.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED,Color.GRAY,Color.GRAY));
			b2.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(b2, BorderLayout.NORTH);
			setOpaque(false);
			
			this.add(panel, BorderLayout.CENTER);
		}
		
		
	}
	
	public JoueurHumainGraphique getPanelJoueurAct() {
		return this.joueurGraph;
	}
	
	public JoueurVirtuelGraphique getPanelJoueurAdv1() {
		return this.joueurIA;
	}
	
	public JoueurVirtuelGraphique2 getPanelJoueurAdv2() {
		return this.joueurIA2;
	}
	
	public JoueurVirtuelGraphique3 getPanelJoueurAdv3() {		
		return this.joueurIA3;
	}
	
	public JLabel getPanelInfo() {
		return this.b2;
	}
	
	public void setClickable(boolean t) {
		this.clickable = t;
	}
	
	public int getLastClick() {
		return this.lastClick;
	}

	public void setLastClick(int i) {
		this.lastClick = i;
	}

	
	
}

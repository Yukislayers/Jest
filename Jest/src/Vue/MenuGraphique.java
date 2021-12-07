package Vue;

import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Jeu.Menu;
import Jeu.Partie;
import net.miginfocom.swing.MigLayout;

public class MenuGraphique extends JPanel {

	private Partie partie;
	private Menu menu;
	
	public MenuGraphique(Menu m) {
		menu = m;
		setLayout(new MigLayout("fillx"));
		
		JButton button = new JButton("1. Jouer une nouvelle partie");
		button.setFont(new Font("Ink Free", Font.BOLD, 18));
		//button.setPreferredSize(sizeButton);
		add(button, "span, center, gapy 40%, wrap 20");
		button.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				menu.startPartie();;			
			}
		});
		
		JButton button_2 = new JButton("2. Quitter le jeu");
		button_2.setFont(new Font("Ink Free", Font.BOLD, 18));
		//button_3.setPreferredSize(sizeButton);
		button_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(button_2, "span, center");
	}
}

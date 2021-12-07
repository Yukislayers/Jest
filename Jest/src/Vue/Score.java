package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Jeu.Partie;





public class Score extends JPanel{
	
	public Score(Partie p) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelDesJest = new JPanel();
		panelDesJest.setLayout(new BoxLayout(panelDesJest, BoxLayout.Y_AXIS));
		
		JPanel panelJest = new JPanel();
		//panelJest.setLayout(new FlowLayout());
		
		for(int i = 0; i < p.getNbJoueur().get(0).getCarteJest().getJest().size(); i++) {
			JLabel j1 = new JLabel();
			j1.setIcon(new ImageIcon("images\\"+p.getNbJoueur().get(0).getCarteJest().getJest().get(i).getTypeCarte()+"_jest.png"));
			j1.setPreferredSize(new Dimension(80, 114));
			panelJest.add(j1);
		}
		
		
		
		panelDesJest.add(panelJest);
		
		JPanel panelJest1 = new JPanel();
		//panelJest1.setLayout(new FlowLayout());
		
		for(int i = 0; i < p.getNbJoueur().get(1).getCarteJest().getJest().size(); i++) {
			JLabel j1 = new JLabel();
			j1.setIcon(new ImageIcon("images\\"+p.getNbJoueur().get(1).getCarteJest().getJest().get(i).getTypeCarte()+"_jest.png"));
			j1.setPreferredSize(new Dimension(80, 114));
			panelJest1.add(j1);
		}
	
		
		panelDesJest.add(panelJest1);
		
		JPanel panelJest2 = new JPanel();
		//panelJest2.setLayout(new FlowLayout());
		
		for(int i = 0; i < p.getNbJoueur().get(2).getCarteJest().getJest().size(); i++) {
			JLabel j1 = new JLabel();
			j1.setIcon(new ImageIcon("images\\"+p.getNbJoueur().get(2).getCarteJest().getJest().get(i).getTypeCarte()+"_jest.png"));
			j1.setPreferredSize(new Dimension(80, 114));
			panelJest2.add(j1);
		}
	
		
		panelDesJest.add(panelJest2);
		
		if(p.getNbJoueur().size() == 4) {
			
			JPanel panelJest3 = new JPanel();
			//panelJest3.setLayout(new FlowLayout());
			
			for(int i = 0; i < p.getNbJoueur().get(3).getCarteJest().getJest().size(); i++) {
				JLabel j1 = new JLabel();
				j1.setIcon(new ImageIcon("images\\"+p.getNbJoueur().get(3).getCarteJest().getJest().get(i).getTypeCarte()+"_jest.png"));
				j1.setPreferredSize(new Dimension(80, 114));
				panelJest3.add(j1);
			}
			
			
			panelDesJest.add(panelJest3);
		}
		
		this.add(panelDesJest, BorderLayout.SOUTH);
			
			
			
			JLabel lblNewLabel = new JLabel();
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Ink Free", Font.BOLD, 28));
			add(lblNewLabel, BorderLayout.NORTH);
			
			JLabel lblNewLabel_1 = new JLabel();
			lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 49));
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			add(lblNewLabel_1, BorderLayout.CENTER);
			
			
				lblNewLabel.setText("Le gagnant est " + p.déterminerGagnant());
				
				if (p.getNbJoueur().size() == 3) {
				lblNewLabel_1.setText("<html>Tableau des scores :<br>"+p.getNbJoueur().get(0).getName()+" "+p.getNbJoueur().get(0).getScore()+"<br>"+p.getNbJoueur().get(1).getName()+" "+p.getNbJoueur().get(1).getScore()+"<br>"+p.getNbJoueur().get(2).getName()+" "+p.getNbJoueur().get(2).getScore()+"</html>");
					}
				else {
					lblNewLabel_1.setText("<html>Tableau des scores :<br>"+p.getNbJoueur().get(0).getName()+" "+p.getNbJoueur().get(0).getScore()+"<br>"+p.getNbJoueur().get(1).getName()+" "+p.getNbJoueur().get(1).getScore()+"<br>"+p.getNbJoueur().get(2).getName()+" "+p.getNbJoueur().get(2).getScore()+"<br>"+p.getNbJoueur().get(3).getName()+" "+p.getNbJoueur().get(3).getScore()+"</html>");

				}
	}
}

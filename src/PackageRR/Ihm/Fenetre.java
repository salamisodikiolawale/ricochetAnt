package PackageRR.Ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import PackageRR.Cible;
import PackageRR.Direction;
import PackageRR.Plateau;
import PackageRR.Position;
import PackageRR.Robot;
import mvc.IEcouteurModel;
import packageAEtoile.AStar;
import packageAEtoile.Noeud;
import packageAEtoile.Solution;
/**
 * 
 * @author MOHAMED BA KOMARA ,SALAMI SODIKI ,MOHAMED CAMARA ,BAMBA ALASSANE
 *
 */
public class Fenetre extends JFrame implements IEcouteurModel {
	private Plateau plateau;
	private AStar aStar;
	private JLabel solution;
	private JLabel jlcible;
	/**
	 * constructeur
	 */
	public Fenetre() {
		plateau=new Plateau();
		plateau.ajoutEcouteur(this);
		aStar=new AStar();
		aStar.ajoutEcouteur(this);
		this.setTitle("Ricochet Robot");
		this.setSize(600,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container cp = this.getContentPane();
		BorderLayout borderLayout = new BorderLayout();
		cp.setLayout(new BorderLayout());
		
		
		//jpanel du centre interface de la grille
		Maquette maquette = new Maquette(plateau);
		maquette.setPreferredSize(new Dimension(600,400));
		
		//jpanel Est Deplacement et controle
		JPanel jpEast = new JPanel();
		jpEast.setLayout(new BorderLayout());
		jpEast.setBackground(Color.WHITE);
				
		//Jpanel Astar
		JPanel jpAstar = new JPanel();
		JButton jbAstar = new JButton("Astar");
		jbAstar.setPreferredSize(new Dimension(300, 50));
		jbAstar.addActionListener(new JbStarListener());
		jpEast.add(jbAstar,BorderLayout.SOUTH);
		
		//Controle
		JPanel jpDeplacement= new JPanel(borderLayout);
		jpDeplacement.setSize(200, 500);
		JButton jbHaut= new JButton("HAUT");
		JButton jbGauche= new JButton("GAUCHE");
		JButton jbDroit= new JButton("DROITE");
		JButton jbBas= new JButton("BAS");
		jpDeplacement.add(jbHaut,BorderLayout.NORTH);
		jbHaut.setPreferredSize(new Dimension(300,50));
		jbHaut.addActionListener(new jbHautListener());
		jbBas.setPreferredSize(new Dimension(300,50));
		jbBas.addActionListener(new jbBasListener());
		jbGauche.setPreferredSize(new Dimension(100,200));
		jbGauche.addActionListener(new jbGaucheListener());
		jbDroit.setPreferredSize(new Dimension(100,200));
		jbDroit.addActionListener(new jbDroitListener());
		jpDeplacement.add(jbBas,BorderLayout.SOUTH);
		jpDeplacement.add(jbDroit,BorderLayout.EAST);
		jpDeplacement.add(jbGauche,BorderLayout.WEST);
		jpEast.add(jpDeplacement,BorderLayout.NORTH);
		
		//LABEL CIBLE
		int cibleCourante=plateau.getCibleCourante();
		jlcible = new JLabel("CIBLE:"+plateau.getCibles()[cibleCourante].getCouleur()+" "+plateau.getCibles()[cibleCourante].getForme());
		jlcible.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		cp.add(jlcible,BorderLayout.NORTH);
		
		//label solution
		solution= new JLabel("solution");
		jpEast.add(solution,borderLayout.CENTER);
		
		cp.add(maquette,BorderLayout.CENTER);
		cp.add(jpEast,BorderLayout.EAST);
		
		cp.setBackground(Color.WHITE);
		
		this.setVisible(true);
		
	}
	
	//Deplacement haut du robot
	class jbHautListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			plateau.gestionDeplacementRobot(Direction.HAUT);
		}
		
	}
	
	//Deplacement bas du robot
	class jbBasListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			plateau.gestionDeplacementRobot(Direction.BAS);
		}
		
	}
	
	//Deplacement gauche du robot
	class jbGaucheListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			plateau.gestionDeplacementRobot(Direction.GAUCHE);
		}
		
	}
	
	//Deplacement droit du robot
	class jbDroitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			plateau.gestionDeplacementRobot(Direction.DROITE);
		}
		
	}
	
	//Utilisation de Astar
	class JbStarListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int cibleCourante=plateau.getCibleCourante();
			Cible cible= plateau.getCibles()[cibleCourante];
			aStar.setPlateau(plateau);
			aStar.setListeRobot(plateau.getListeRobot());
			aStar.setArrivee(cible.getPosition());
			aStar.setCoulRobPrincipal(cible.getCouleur());
			//lancement du solveur
			ArrayList<Noeud> noeuds = aStar.solveur();
			//Affichage de la solution en console
			if(noeuds!=null) {
				System.out.println("**************Solution******************");
				for(Noeud n: noeuds) {
					for(Robot r : n.getListeRobot()) {
						System.out.print(r.getPosition());
					}
					System.out.println();
				}
			}
			
			
		}
		
	}

	@Override
	public void modeleMisAJour(Object source) {
		//Affichage de la solution sur l'interface graphique
		
		if(!aStar.getSolution().isEmpty()) {
			String solString="<html><h1>Solution <br/>";
			int i=1;
			for (Solution s:aStar.getSolution()) {
				solString+=i++ +" "+s.getCoulRob()+"  "+s.getDirection()+"     "+s.getPosition()+"<br/>";
			}
			solString+="</h1></html>";
			solution.setText(solString);
		}
		//actualisation de la cible
		int cibleCourante=plateau.getCibleCourante();
		jlcible.setText("CIBLE:"+plateau.getCibles()[cibleCourante].getCouleur()+" "+plateau.getCibles()[cibleCourante].getForme());
		
	}
}

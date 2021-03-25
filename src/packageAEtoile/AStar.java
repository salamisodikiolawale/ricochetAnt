package packageAEtoile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import PackageRR.Direction;
import PackageRR.Plateau;
import PackageRR.Position;
import PackageRR.Robot;
import mvc.ModelEcoutable;
/**
 * 
 * @author MOHAMED BA KOMARA ,SALAMI SODIKI ,MOHAMED CAMARA ,BAMBA ALASSANE
 *
 */
public class AStar extends ModelEcoutable{
	private ArrayList<Noeud> openList= new ArrayList<Noeud>();
	private ArrayList<Noeud> closedList= new ArrayList<Noeud>();
	private ArrayList<Noeud> chemin = new ArrayList<Noeud>();
	ArrayList<Solution>solution=new ArrayList<Solution>();
	private Plateau plateau;
	private Robot[] listeRobot;
	private String coulRobPrincipal;
	private Position arrivee;
	
	/**
	 * Constructeur
	 * @param plateau plateau
	 * @param listeRobot liste des robots
	 * @param arrivee position d'arrivée
	 * @param coulRobPrincipal couleur du robot principal
	 */
	public AStar(Plateau plateau, Robot[] listeRobot, Position arrivee,String coulRobPrincipal) {
		super();
		this.plateau = plateau;
		this.listeRobot = listeRobot;
		this.arrivee = arrivee;
		this.coulRobPrincipal=coulRobPrincipal;
	}
	/**
	 * constructeur sans argument
	 */
	public AStar() {
		super();
	}
	/**
	 * methode implementant l'algorithme astar
	 * @return Chemin S'il est trouve
	 */
	public ArrayList<Noeud> solveur(){
		//initialisation
		this.chemin.clear();
		this.openList.clear();
		this.closedList.clear();
		this.solution.clear();
		Noeud courant=new Noeud(listeRobot);
		ArrayList<Noeud> listAdjadcents =plateau.getAdjacent(plateau.getListeRobot());
		for (Noeud noeud : listAdjadcents) {
			if(!estDansClosedList(noeud)) {
				addOpenList(noeud,courant);
			}
		}
		//debut des itérations
		while( !(courant.getRobot(coulRobPrincipal).getPosition().equals(arrivee)) && (!openList.isEmpty()) ) {
			courant=meilleurNoeud();
			/*for(Robot r : courant.getListeRobot()) {
				System.out.print(r.getPosition());
			}*/
			//ajout du noeud courant dans la closedList
			closedList.add(courant);
			Plateau plateauCopie=this.plateau.copie(courant);
			//récupération des adjacents du noeud courant
			listAdjadcents =plateauCopie.getAdjacent(courant.getListeRobot());
			for (Noeud noeud : listAdjadcents) {
				//tester s'il n'existe pas la closedList
				if(!estDansClosedList(noeud)) {
					//ajout du noeud dans l'openList
					addOpenList(noeud,courant);
				}
			}
			//suppression du noeud courant dans l'openList
			openList.remove(courant);
		}
		//solution trouvee
		if (courant.getRobot(coulRobPrincipal).getPosition().equals(arrivee)){
			construireChemin(courant);
			return chemin;
	 
	    }
		//pas de solution
		else{
	    	System.out.println("Pas de Solution");
	    	return null;
	    }
		
		
	}
	
	/**
	 * ajouter un noeud dans l'openList
	 * @param noeudAdj noeud adjacent 
	 * @param parent noeud parent
	 */
	public void addOpenList(Noeud noeudAdj, Noeud parent) {
		/*h: DISTANCE(COUT) ENTRE NOEUD CONSIDERE ET NOEUD DE DESTINATION
		 * distance de manhattan
		*/
		//int h=0;
		int h = (Math.abs( noeudAdj.getRobot(coulRobPrincipal).getPosition().getcol() - arrivee.getcol() ) 
				+ Math.abs(noeudAdj.getRobot(coulRobPrincipal).getPosition().getligne() - arrivee.getligne()));
		noeudAdj.setG(noeudAdj.getG()+1);
		noeudAdj.setH(h);
		//CALCUL DE F
		noeudAdj.calculF();
		noeudAdj.setNoeudParent(parent);
		this.openList.add(noeudAdj);
	}
	/**
	 * retourner le meilleur noeud dans l'openList
	 * @return Noeud
	 */
	public Noeud meilleurNoeud() {
		Noeud meilleur=openList.get(0);
		for(Noeud n:openList) {
			if(n.getF()<meilleur.getF()) {
				meilleur=n;
			}
		}
		return meilleur;
	}
	/**
	 * construit le chemin une fois la solution trouvée
	 * @param destination destination finale
	 */
	public void construireChemin(Noeud destination) {
		do {
		this.chemin.add(destination);
		destination=destination.getNoeudParent();
		}while(destination!=null) ;
		//ArrayList<Solution> solution=new ArrayList<Solution>();
		for(int i=chemin.size()-1;i>0;i--) {
			for(int j=0;j<4;j++) {
				Direction dr=null;
				
				if(!chemin.get(i).getListeRobot()[j].getPosition().equals(chemin.get(i-1).getListeRobot()[j].getPosition())) {
					//calcul de la direction à prendre
					if(chemin.get(i-1).getListeRobot()[j].getPosition().getligne()<chemin.get(i).getListeRobot()[j].getPosition().getligne()) {
						dr=Direction.HAUT;
					}
					if(chemin.get(i-1).getListeRobot()[j].getPosition().getligne()>chemin.get(i).getListeRobot()[j].getPosition().getligne()) {
						dr=Direction.BAS;
					}
					if(chemin.get(i-1).getListeRobot()[j].getPosition().getcol()<chemin.get(i).getListeRobot()[j].getPosition().getcol()) {
						dr=Direction.GAUCHE;
					}
					if(chemin.get(i-1).getListeRobot()[j].getPosition().getcol()>chemin.get(i).getListeRobot()[j].getPosition().getcol()) {
						dr=Direction.DROITE;
					}
					solution.add(new Solution(chemin.get(i-1).getListeRobot()[j].getCouleur(), dr, chemin.get(i-1).getListeRobot()[j].getPosition()));
				}
			}
		}
		for (int i=0;i<solution.size();i++) {
			System.out.println(solution.get(i).getCoulRob()+" "+solution.get(i).getDirection()+" "+solution.get(i).getPosition());
		}
		fireChangement();
	}
	/**
	 * permet de savoir si un noeud existe deja ou non dans la closedList
	 * @param noeud noeud
	 * @return Boolean
	 */
	public Boolean estDansClosedList(Noeud noeud) {
		for(Noeud n:closedList) {
			if(n.getListeRobot()[0].getPosition().equals(noeud.getListeRobot()[0].getPosition())
			   && n.getListeRobot()[1].getPosition().equals(noeud.getListeRobot()[1].getPosition())
			   && n.getListeRobot()[2].getPosition().equals(noeud.getListeRobot()[2].getPosition())
			   && n.getListeRobot()[3].getPosition().equals(noeud.getListeRobot()[3].getPosition())
			  ) {
				return true;
			
			}
			
		}
		return false;
	}

	public ArrayList<Noeud> getChemin() {
		return chemin;
	}

	public void setChemin(ArrayList<Noeud> chemin) {
		this.chemin = chemin;
	}

	public ArrayList<Noeud> getOpenList() {
		return openList;
	}

	public void setOpenList(ArrayList<Noeud> openList) {
		this.openList = openList;
	}

	public ArrayList<Noeud> getClosedList() {
		return closedList;
	}

	public void setClosedList(ArrayList<Noeud> closedList) {
		this.closedList = closedList;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public Robot[] getListeRobot() {
		return listeRobot;
	}

	public void setListeRobot(Robot[] listeRobot) {
		this.listeRobot = listeRobot;
	}

	public String getCoulRobPrincipal() {
		return coulRobPrincipal;
	}

	public void setCoulRobPrincipal(String coulRobPrincipal) {
		this.coulRobPrincipal = coulRobPrincipal;
	}

	public Position getArrivee() {
		return arrivee;
	}

	public void setArrivee(Position arrivee) {
		this.arrivee = arrivee;
	}
	public ArrayList<Solution> getSolution() {
		return solution;
	}
	public void setSolution(ArrayList<Solution> solution) {
		this.solution = solution;
	}
	

}

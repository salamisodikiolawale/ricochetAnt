package PackageRR;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import mvc.ModelEcoutable;
import packageAEtoile.Noeud;

/**
 * 
 * @author MOHAMED BA KOMARA ,SALAMI SODIKI ,MOHAMED CAMARA ,BAMBA ALASSANE
 *
 */
public class Plateau extends ModelEcoutable{
	private  final int DIM=16;
	private  final int DIMPLATEAU=8;
	private  final int nbCible=17;
	private  final int nbRobot=4;
	
	
	private final static String[] FORME= {"CERCLE","ETOILE","TRIANGLE","LOSANGE"};
	private final static String[] COULEUR= {"ROUGE","JAUNE","VERT","BLEU"};
	
	private  final int RROUGE=0;
	private  final int RJAUNE=1;
	private  final int RVERT=2;
	private  final int RBLEU=3;
	
	
	private Case [][] plateauFinal;
	/**
	 * 0:mur haut
	 * 1:mur droit
	 * 2:mur bas
	 * 3:mur gauche
	 * 4 et 5:numero cible (17 =absence de cible)
	 */
	private String[][]donnees= {
	        /*0*/ {"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL","010017","000117","NULL"  ,"010017","000117","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"},
	        /*1*/ {"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"010017","001101","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"},
	        /*2*/ {"NULL"  ,"001017","NULL"  ,"NULL"  ,"NULL","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"100017","NULL"  ,"011012","000117","NULL"  ,"NULL"},
	        /*3*/ {"010017","100100","NULL"  ,"NULL"  ,"NULL","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"010017","100111","NULL"  ,"NULL"  ,"NULL"},
	        /*4*/ {"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL","NULL"  ,"011005","000117","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"001017"},
	        /*5*/ {"001017","NULL"  ,"001017","NULL"  ,"NULL","NULL"  ,"100017","010017","001116","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"001017","NULL"  ,"100017"},
	        /*6*/ {"100017","NULL"  ,"110010","001115","NULL","NULL"  ,"NULL"  ,"NULL"  ,"100017","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"110006","000117","NULL"},
	        /*7*/ {"NULL"  ,"NULL"  ,"NULL"  ,"100017","NULL","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"},
	        /*8*/ {"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"},
	        /*9*/ {"NULL"  ,"010017","001104","NULL"  ,"NULL","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"011007","000117","NULL"  ,"NULL"  ,"NULL"  ,"NULL"},
	        /*10*/{"NULL"  ,"NULL"  ,"110009","000117","NULL","NULL"  ,"NULL"  ,"001017","NULL"  ,"NULL"  ,"100017","NULL"  ,"001017","NULL"  ,"NULL"  ,"NULL"},	
	        /*11*/{"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL","NULL"  ,"010017","100114","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"110002","001108","NULL"  ,"NULL"},                      
	        /*12*/{"001017","NULL"  ,"NULL"  ,"NULL"  ,"NULL","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"001017","NULL"  ,"NULL"  ,"NULL"  ,"100017","NULL"  ,"001017"},	
	        /*13*/{"100017","NULL"  ,"NULL"  ,"NULL"  ,"NULL","NULL"  ,"NULL"  ,"NULL"  ,"010017","100113","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"100017"},
	        /*14*/{"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL","011003","000117","NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL"},
	        /*15*/{"NULL"  ,"NULL"  ,"NULL"  ,"NULL"  ,"NULL","100017","010017","000117","NULL"  ,"NULL"  ,"010017","000117","NULL"  ,"NULL"  ,"NULL"  ,"NULL"},
	        };	
			
	
	private Cible[] cibles=new Cible[nbCible];
	private int progresNbelem=nbCible;
	private ArrayList<Integer>progressionJeu=new ArrayList<Integer>();
	private Robot[] listeRobot = new Robot[nbRobot];
	private int robotCourant;
	private int cibleCourante;

	/**
	 * constructeur de la classe
	 */
	public Plateau() {
		
		plateauFinal=new Case[this.DIM][this.DIM];
		//liste nous permettant de stocker les 4 tableau
		ArrayList<Case [][]> ListPlateau= new ArrayList<Case[][]>();
		
		creerCible();
		
		//creation des 4 plateaux
		//creerPlateau(ListPlateau, ligneDebut, colonneDebut, ligneFin, colonneFin);
		creerPlateau(ListPlateau, 0, 0,DIMPLATEAU,DIMPLATEAU);
		creerPlateau(ListPlateau, 0, DIMPLATEAU,DIMPLATEAU,DIM);
		creerPlateau(ListPlateau, DIMPLATEAU, 0,DIM,DIMPLATEAU);
		creerPlateau(ListPlateau, DIMPLATEAU, DIMPLATEAU,DIM,DIM);
		
		ArrayList<Integer>indiceListe= new ArrayList<Integer>();
		for(int i =0;i<4;i++) {
			indiceListe.add(i);
		}
		//Collections.shuffle(ListPlateau,new Random());
		//collerPlateau(plateau, ligneGp, colonneGp, lignePp, colonnePp, MaxLigneGp, MaxColonneGp);
		//ajout des 4 plateaux dans le plateau final
		collerPlateau(ListPlateau.get(0), 0, 0, DIMPLATEAU, DIMPLATEAU);
		collerPlateau(ListPlateau.get(1), 0, DIMPLATEAU,DIMPLATEAU, 16);
		collerPlateau(ListPlateau.get(2), DIMPLATEAU, 0,DIM, DIMPLATEAU);
		collerPlateau(ListPlateau.get(3), DIMPLATEAU, DIMPLATEAU,DIM, DIM);

		//Creation des frontieres
		creerfrontiere();
		
		//creation obstacle central
		creerObstacleCentral();
		
		//positionner les cibles
		positionnerCible();
		
		this.listeRobot[this.RROUGE]=new Robot("ROUGE", false);
		this.listeRobot[this.RJAUNE]=new Robot("JAUNE", false);
		this.listeRobot[this.RVERT]=new Robot("VERT", false);
		this.listeRobot[this.RBLEU]=new Robot("BLEU", false);
		
		//Place les robots aleatoirement
		placerRobot();
		//PlacerRobotAleatoire();
		
		
		//Remplissage de la liste
		for(int i = 0; i<this.progresNbelem; i++) {
			progressionJeu.add(i);
		}
		//activer une cible
		cibleCourante=10;
		//cibleCourante=selectionnerObjectif();
		
		//System.out.println(this.cibles[cibleCourante].getCouleur()+" "+this.cibles[cibleCourante].getForme());
		this.robotCourant = 4;
	}
	
	public void placerRobot() {
		plateauFinal[15][3].setRobot(this.listeRobot[0]);
		this.listeRobot[0].setPosition(new Position(15, 3));
		
		plateauFinal[1][5].setRobot(this.listeRobot[1]);
		this.listeRobot[1].setPosition(new Position(1, 5));
		
		plateauFinal[7][1].setRobot(this.listeRobot[2]);
		this.listeRobot[2].setPosition(new Position(7, 1));
		
		plateauFinal[10][11].setRobot(this.listeRobot[3]);
		this.listeRobot[3].setPosition(new Position(10, 11));
	}
	/**
	 * Crée une copie du plateau de jeu en se servant d'un noeud pour placer les robots
	 * utilisée  par A star pour obtenir les adjacents d'un noeud 
	 * @param noeud noeud A star
	 * @return Plateau 
	 */
	public Plateau copie(Noeud noeud) {
		Plateau platCopie= new Plateau();
		for(int i=0;i<DIM;i++) {
			for(int j=0;j<DIM;j++) {
				Case c=this.plateauFinal[i][j];
				platCopie.getPlateauFinal()[i][j]=new Case(c.getWall(0), c.getWall(1), c.getWall(2), c.getWall(3));
			}
		}
		for (int i=0;i<this.nbRobot;i++) {
			Robot robot=noeud.getListeRobot()[i];
			Position p = robot.getPosition();
			platCopie.getPlateauFinal()[p.getligne()][p.getcol()].
			                 setRobot(new Robot(robot.getCouleur(),new Position(p.getligne(), p.getcol())));
			platCopie.getListeRobot()[i].setPosition(new Position(p.getligne(), p.getcol()));
		}
		return platCopie;
	}
	
 	/**
 	 * crée les cibles du jeu
 	 */
	public void creerCible() {
		for (int couleur = 0; couleur<COULEUR.length; couleur++) {
			for (int forme =0; forme<FORME.length; forme++) {
				this.cibles[couleur*FORME.length+forme]=new Cible(FORME[forme],COULEUR[couleur],false);
			}
		}
		this.cibles[16]=new Cible("ARCENCIEL","VORTEX",false);		
		
	}
	/**
	 * crée les petits plateaux de jeu en se servant du tableau "donnees"
	 * @param ListPlateau liste des 4 plateaux à rogner
	 * @param ligneDebut ligne de debut de la creation
	 * @param colonneDebut colonne de debut de la creation
	 * @param ligneFin ligne de fin de la creation
	 * @param colonneFin colonne de fin de la creation
	 */
	public void creerPlateau(ArrayList<Case [][]> ListPlateau,int ligneDebut,int colonneDebut,int ligneFin,int colonneFin) {
		Case [][] petitPlateau=new Case[this.DIMPLATEAU][this.DIMPLATEAU];
		int lignePetit=0;
		for(int i=ligneDebut;i<ligneFin;i++) {
			int colonnePetit=0;
			for(int j=colonneDebut;j<colonneFin;j++) {
				Case c = new Case(null, null);
				if(!this.donnees[i][j].equals("NULL")) {
					//mur haut
					if(this.donnees[i][j].charAt(0)=='1') {
						c.setWall(0);
					}
					//mur droit
					if(this.donnees[i][j].charAt(1)=='1') {
						c.setWall(1);
					}
					//mur bas
					if(this.donnees[i][j].charAt(2)=='1') {
						c.setWall(2);
					}
					//mur gauche
					if(this.donnees[i][j].charAt(3)=='1') {
						c.setWall(3);
					}
					//reperage de la cible
					StringBuffer sb = new StringBuffer();
					sb=sb.append(this.donnees[i][j].charAt(4)).append(this.donnees[i][j].charAt(5));
					String cible=sb.toString();
					if(!cible.equals("17")) {
						c.setCible(this.cibles[Integer.parseInt(sb.toString())]);
					}
				}
				petitPlateau[lignePetit][colonnePetit]=c;
				colonnePetit++;
			}
			lignePetit++;
		}
		ListPlateau.add(petitPlateau);
	}
	
	/**
	 * Methode permettant d'assembler les 4 sous plateaux crées dans un seul plateau
	 * @param plateau plateau principal du jeu
	 * @param ligneGp ligne de debut
	 * @param colonneGp colonne de debut
	 * @param MaxLigneGp ligne de fin
	 * @param MaxColonneGp colonne de fin
	 */
	public void collerPlateau(Case [][] plateau,int ligneGp,int colonneGp,int MaxLigneGp,int MaxColonneGp) {
		int lignePp=0;int colonnePp=0;
		int initColonneGp=colonneGp;

		while(ligneGp<MaxLigneGp) {
			while(colonneGp<MaxColonneGp) {
				this.plateauFinal[ligneGp][colonneGp]=plateau[lignePp][colonnePp];
				colonnePp++;
				colonneGp++;
			}
			ligneGp++;colonneGp=initColonneGp;
			lignePp++;colonnePp=0;
		}
	}
	/**
	 * creation des frontières du jeu (murs)
	 */
	public void creerfrontiere() {
		
		//Mur pour le haut
		for(int j=0;j<DIM;j++) {
			//boolean[] wall = {true, false, false, false};
			this.plateauFinal[0][j].setWall(0);
		}
		//Mur pour le bas
		for(int j=0;j<DIM;j++) {
			//boolean[] wall = {false, true, false, false};
			this.plateauFinal[DIM-1][j].setWall(2);
		}
		
		//Mur pour le cote gauche
		for(int i=0;i<DIM;i++) {
			//boolean[] wall = {false, false, true, false};
			this.plateauFinal[i][0].setWall(3);
		}
		
		//Mur pour le cote droit
		for(int i=0;i<DIM;i++) {
			//boolean[] wall = {false, false, true, false};
			this.plateauFinal[i][DIM-1].setWall(1);
		}
		
	}
	
	/**
	 * creation des frontières de l'obstacle central
	 */
	public void creerObstacleCentral() {
		
		this.plateauFinal[this.DIMPLATEAU - 1][this.DIMPLATEAU - 1].setWall(0);
		this.plateauFinal[this.DIMPLATEAU - 1][this.DIMPLATEAU - 1].setWall(3);
		
		this.plateauFinal[this.DIMPLATEAU - 1][this.DIMPLATEAU].setWall(0);
		this.plateauFinal[this.DIMPLATEAU - 1][this.DIMPLATEAU].setWall(1);
		
		this.plateauFinal[this.DIMPLATEAU][this.DIMPLATEAU - 1].setWall(3);
		this.plateauFinal[this.DIMPLATEAU][this.DIMPLATEAU - 1].setWall(2);
		
		this.plateauFinal[this.DIMPLATEAU][this.DIMPLATEAU].setWall(1);
		this.plateauFinal[this.DIMPLATEAU][this.DIMPLATEAU].setWall(2);
	}
	/**
	 * Recuperer la position finale de chaque cible en fonction du grand tableau
	 */
	private void positionnerCible() {
		for(int i=0;i<DIM;i++) {
			for(int j=0;j<DIM;j++) {
				if(this.plateauFinal[i][j].getCible()!= null) {
					for(Cible c: this.getCibles()) {
						if(c==this.plateauFinal[i][j].getCible()) {
							c.setPosition(new Position(i,j));
						}
					}
				}
				
			}
		}
		
	}
	/**
	 * Choisir une cible aleatoirement
	 * @return progressionJeu.get(choix)
	 */
	public int selectionnerObjectif() {
		Random random = new Random();
		int choix=random.nextInt(this.progresNbelem-1);
		progressionJeu.remove(choix);
		this.progresNbelem--;
		//choix aleatoire dune valeur
		return progressionJeu.get(choix);
	}
	/**
	 * placer les robots aleatoirement
	 */
	public void PlacerRobotAleatoire() {
		int xRd,yRd;
		Random random = new Random();
		
		for(int i= 0; i<this.nbRobot;i++) {
			do{
				xRd = random.nextInt(DIM);
				yRd = random.nextInt(DIM);
				this.listeRobot[i].setPosition(new Position(xRd, yRd));
				
			}
			while (this.plateauFinal[xRd][yRd].estOccupe() || this.plateauFinal[xRd][yRd].estOccupeParCible() ||
					this.plateauFinal[xRd][yRd].equals(this.plateauFinal[this.DIMPLATEAU-1][this.DIMPLATEAU-1]) ||
					this.plateauFinal[xRd][yRd].equals(this.plateauFinal[this.DIMPLATEAU-1][this.DIMPLATEAU]) ||
					this.plateauFinal[xRd][yRd].equals(this.plateauFinal[this.DIMPLATEAU][this.DIMPLATEAU-1]) ||
					this.plateauFinal[xRd][yRd].equals(this.plateauFinal[this.DIMPLATEAU][this.DIMPLATEAU])

					
				); 
			this.plateauFinal[xRd][yRd].setRobot(this.listeRobot[i]);
				
		}
	}
	
	/**
	 * methode qui permet de selectionner le robot courant
	 * @param p position
	 */
	public void selectionRobot (Position p) {
		System.out.println("li "+p.getligne());
		System.out.println("col "+p.getcol());
		int i = 0;
		for(;i<this.nbRobot;i++){
			if (listeRobot[i].getPosition().equals(p)) {
				this.robotCourant = i;
				fireChangement();
				break;	
			}
		}
	}
	/**
	 * methode qui permet de trouver le robot principal
	 * @return 0
	 */
	public int findRobotPrincipal() {
		for(int i=0;i<this.nbRobot;i++) {
			if(this.listeRobot[i].getCouleur().equals(this.cibles[this.cibleCourante].getCouleur())) {
				return i;
			}
		}
		return 0;
	}
	/**
	 * methode permettant de savoir si un objectif a été atteint
	 * @return boolean
	 */
	public boolean isFinish() {
		if(progressionJeu.isEmpty()) {
			System.exit(0);
		}
		if(this.listeRobot[findRobotPrincipal()].getPosition().equals(this.cibles[this.cibleCourante].getPosition())) {
			System.out.println("la partie est finie");
			cibleCourante=selectionnerObjectif();
			fireChangement();
			return true;
		}
		System.out.println("la partie n'est pas finie");
		return false;
	}
	/**
	 * Methode qui nous permet de deplacer effectivement un robot
	 */
	private void deplacerRobot(int ligne,int colonne,Robot rb) {
		this.plateauFinal[rb.getPosition().getligne()][rb.getPosition().getcol()].setRobot(null);
		this.plateauFinal[ligne][colonne].setRobot(rb);
		rb.setPosition(new Position(ligne, colonne));
		isFinish();
		this.robotCourant = 4;
	}
	
	/**
	 * Methode nous Permettant de calculer le deplacement du Robot
	 * selectionner en fonction de la direction
	 * @param dr direction 
	 * @return null
	 */
	public Position gestionDeplacementRobot( Direction dr) {
		if(this.robotCourant != 4) {
			System.out.println("Robot courant"+this.robotCourant);
			int ligne=listeRobot[robotCourant].getPosition().getligne();
			int colonne=listeRobot[robotCourant].getPosition().getcol();

			if(dr == Direction.HAUT) {
				while(ligne>=0) {
					if(plateauFinal[ligne][colonne].getWall()[0] || plateauFinal[ligne-1][colonne].estOccupe()
							|| plateauFinal[ligne-1][colonne].getWall()[2]) {
						//deplacement du robot sur le tableau final
						
						deplacerRobot(ligne, colonne, listeRobot[robotCourant]);
						fireChangement();
						return new Position(ligne,colonne);
					}
					ligne--;
				}
			}
			
			else if(dr == Direction.DROITE) {
				while(colonne<this.DIM) {
					if(plateauFinal[ligne][colonne].getWall()[1] || plateauFinal[ligne][colonne+1].estOccupe()
							|| plateauFinal[ligne][colonne+1].getWall()[3]) {
						//deplacement du robot sur le tableau final
						deplacerRobot(ligne, colonne,listeRobot[robotCourant]);
						fireChangement();
						return new Position(ligne,colonne);
					}
					colonne++;
				}
			}
			
			else if(dr == Direction.BAS) {
				while(ligne<this.DIM) {
					if(plateauFinal[ligne][colonne].getWall()[2] || plateauFinal[ligne+1][colonne].estOccupe()
							|| plateauFinal[ligne+1][colonne].getWall()[0]) {
						//deplacement du robot sur le tableau final
						deplacerRobot(ligne, colonne, listeRobot[robotCourant]);
						fireChangement();
						return new Position(ligne,colonne);
					}
					ligne++;
				}
				
			}
			//traitement deplacement à gauche
			else{
				while(colonne>=0) {
					if(plateauFinal[ligne][colonne].getWall()[3] || plateauFinal[ligne][colonne-1].estOccupe()
							|| plateauFinal[ligne][colonne-1].getWall()[1]) {
						//deplacement du robot sur le tableau final
						deplacerRobot(ligne, colonne, listeRobot[robotCourant]);
						fireChangement();
						return new Position(ligne,colonne);
					}
					colonne--;
				}
			}
			return listeRobot[robotCourant].getPosition();
		}
		return null;	
		
	}
	
	
	/**
	 * calcule et renvoie dans une liste les différentes positions que peut prendre 
	 * un robot à partir de sa position initiale
	 * @param posiRobot position initiale du robot
	 * @return  Position
	 */
	public ArrayList<Position> gestionDeplacementRobotAStar(Position posiRobot) {
		ArrayList<Position> listArrivees=new ArrayList<Position>();
		int ligne=posiRobot.getligne();
		int colonne=posiRobot.getcol();
		
		//haut
		while(ligne>=0) {
			if(plateauFinal[ligne][colonne].getWall()[0] || plateauFinal[ligne-1][colonne].estOccupe()
					|| plateauFinal[ligne-1][colonne].getWall()[2]) {
				listArrivees.add(new Position(ligne,colonne));
				break;
			}
			ligne--;
		}
		
		ligne=posiRobot.getligne();
		colonne=posiRobot.getcol();
		//droite
		while(colonne<this.DIM) {
			if(plateauFinal[ligne][colonne].getWall()[1] || plateauFinal[ligne][colonne+1].estOccupe()
					|| plateauFinal[ligne][colonne+1].getWall()[3]) {
				listArrivees.add(new Position(ligne,colonne));
				break;
			}
			colonne++;
		}
		
		ligne=posiRobot.getligne();
		colonne=posiRobot.getcol();
		//Bas
			while(ligne<this.DIM) {
				if(plateauFinal[ligne][colonne].getWall()[2] || plateauFinal[ligne+1][colonne].estOccupe()
						|| plateauFinal[ligne+1][colonne].getWall()[0]) {
					listArrivees.add(new Position(ligne,colonne));
					break;
				}
				ligne++;
			}
			
		ligne=posiRobot.getligne();
		colonne=posiRobot.getcol();
		//gauche
		while(colonne>=0) {
			if(plateauFinal[ligne][colonne].getWall()[3] || plateauFinal[ligne][colonne-1].estOccupe()
					|| plateauFinal[ligne][colonne-1].getWall()[1]) {
				listArrivees.add(new Position(ligne,colonne));
				break;
			}
			colonne--;
		}
		
		return listArrivees;
		
	}
	/**
	 * calcule et renvoie tous les adjacents d'un noeud
	 * @param listeRobotNoeuds liste des robots
	 * @return Noeud
	 */
	public ArrayList<Noeud> getAdjacent(Robot[] listeRobotNoeuds){
		ArrayList<Noeud> listAdjacent=new ArrayList<Noeud>();
		for (int i=0;i<this.nbRobot;i++) {
				Position position=listeRobotNoeuds[i].getPosition();
				Robot[] listeRobot = new Robot[nbRobot];
				listeRobot[this.RROUGE]=new Robot("ROUGE", false);
				listeRobot[this.RJAUNE]=new Robot("JAUNE", false);
				listeRobot[this.RVERT]=new Robot("VERT", false);
				listeRobot[this.RBLEU]=new Robot("BLEU", false);
				for(int k=0;k<this.nbRobot;k++) {
					listeRobot[k].setPosition(
							new Position(listeRobotNoeuds[k].getPosition().getligne(),listeRobotNoeuds[k].getPosition().getcol()));
				}
				ArrayList<Position> listArrivees=gestionDeplacementRobotAStar(position);
				for(int j=0;j<listArrivees.size();j++) {
					listeRobot[i].setPosition(new Position(listArrivees.get(j).getligne(),listArrivees.get(j).getcol()));
					 for (Robot r : listeRobot) {
						//System.out.println(r.getPosition());
					}
					listAdjacent.add(new Noeud(listeRobot));
				}
				
		}
		return listAdjacent;
	}
	
	public Case[][] getPlateauFinal() {
		return plateauFinal;
	}

	public void setPlateauFinal(Case[][] plateauFinal) {
		this.plateauFinal = plateauFinal;
	}

	public Cible[] getCibles() {
		return cibles;
	}

	public void setCibles(Cible[] cibles) {
		this.cibles = cibles;
	}

	public Robot[] getListeRobot() {
		return listeRobot;
	}

	public void setListeRobot(Robot[] listeRobot) {
		this.listeRobot = listeRobot;
	}
	
	public int getRROUGE() {
		return RROUGE;
	}

	public int getRJAUNE() {
		return RJAUNE;
	}

	public int getRVERT() {
		return RVERT;
	}

	public int getRBLEU() {
		return RBLEU;
	}
	
	public int getRobotCourant() {
		return robotCourant;
	}

	public void setRobotCourant(int robotCourant) {
		this.robotCourant = robotCourant;
	}
	
	public int getCibleCourante() {
		return cibleCourante;
	}
	public void setCibleCourante(int cibleCourante) {
		this.cibleCourante = cibleCourante;
	}
	/**
	 * permet l'affichage du plateau dans la console
	 */
	public void affichePlateau() {
		for(int i=0;i<DIM;i++) {
			//Mur haut
			for(int j=0;j<DIM;j++) {
				
				if(this.plateauFinal[i][j].getWall()[0]==true) {
					System.out.print("  _");
				}
				else {
					System.out.print("   ");
				}
			}
			System.out.println("");
			//Mur gauche cible robot mur droit
			for(int j=0;j<DIM;j++) {
				//mur gauche
				if(this.plateauFinal[i][j].getWall()[3]==true) {
					if(j==0) {
						System.out.print("||");
					}
					else{
						System.out.print("|");
					}
				}
				else {
					System.out.print(" ");
				}
				//robot ou cible
				if(this.plateauFinal[i][j].getRobot()!=null) {
					if(this.plateauFinal[i][j].getRobot().getCouleur().equals("ROUGE")) {
						System.out.print("R");
					}
					else if(this.plateauFinal[i][j].getRobot().getCouleur().equals("JAUNE")) {
						System.out.print("J");
					}
					else if(this.plateauFinal[i][j].getRobot().getCouleur().equals("VERT")) {
						System.out.print("V");
					}
					else {
						System.out.print("B");
					}
				}
				else if(this.plateauFinal[i][j].getCible()!=null) {
					System.out.print("O");
				}
				else {
					System.out.print(".");
				}
				//mur droit
				if(this.plateauFinal[i][j].getWall()[1]==true) {
					System.out.print("|");
				}
				else {
					System.out.print(" ");
				}
				
			}
			System.out.println("");
			//Mur bas
			for(int j=0;j<DIM;j++) {
				if(this.plateauFinal[i][j].getWall()[2]==true) {
					System.out.print("  _");
				}
				else {
					System.out.print("   ");
				}
			}
			System.out.println("");
		}
		
	}
	public void TrouveRobot(String couleur) {
		for(int i=0;i<this.nbRobot;i++) {
			if(this.listeRobot[i].getCouleur().equals(couleur)) {
				robotCourant=i;
			}
		}
	}
	public void deplacementConsole() {
		boolean erreur;
		do {
			affichePlateau();
			do{
				erreur = false;
				try {
					
					 Scanner s = new Scanner(System.in);
					 System.out.println("Saisir le numero du robot");
					 System.out.println("1:Rouge 2:Jaune 3:Vert 4:Bleu");
					 int numRob = s.nextInt();
					 System.out.println("Saisir la direction");
					 System.out.println("1:Haut 2:Bas 3:Droite 4:Gauche");
					 int direction = s.nextInt();
					 String couleur="";
					 Direction dr=Direction.BAS;
					 if(numRob==1) {
						 couleur="ROUGE";
					 }
					 else if(numRob==2) {
						 couleur="JAUNE";
					 }
					 else if(numRob==3) {
						 couleur="VERT";
					 }
					 else if(numRob==4) {
						 couleur="BLEU";
					 }
					 TrouveRobot(couleur);
					 if(direction==1) {
						 dr=Direction.HAUT;
					 }
					 else if(direction==2) {
						 dr=Direction.BAS;
					 }
					 else if(direction==3) {
						 dr=Direction.DROITE;
					 }
					 else if(direction==4) {
						 dr=Direction.GAUCHE;
					 }
					 gestionDeplacementRobot(dr);
						 
				}
				catch(java.util.InputMismatchException e) {
					erreur = true;
					System.out.println("Erreur de saisir");
				}
				
			}while(erreur);
			
		}while(!isFinish());
			 
		
	}
	
	
	

}

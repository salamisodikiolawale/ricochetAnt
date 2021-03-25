package packageAEtoile;

import PackageRR.Position;
import PackageRR.Robot;
/**
 * 
 * @author MOHAMED BA KOMARA ,SALAMI SODIKI ,MOHAMED CAMARA ,BAMBA ALASSANE
 *
 */
public class Noeud {
	private final int nbRobot=4;
	
	private Robot[] listeRobot = new Robot[nbRobot];
	private int g;
	private int h;
	private int f=0;
	private Noeud noeudParent;
	
	public Noeud(Robot[] listeRobot) {
		for (int i=0;i<this.nbRobot;i++) {
			String k= listeRobot[i].getCouleur();
			Position p= new Position(listeRobot[i].getPosition().getligne(), listeRobot[i].getPosition().getcol());
			Robot r = new Robot(listeRobot[i].getCouleur(),
					new Position(listeRobot[i].getPosition().getligne(), listeRobot[i].getPosition().getcol()));
			this.listeRobot[i]=r;
		}
	}

	public int getG() {
		return g;
	}

	public void calculF() {
		this.f = h + g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getF() {
		return g+h;
	}


	public Noeud getNoeudParent() {
		return noeudParent;
	}

	public void setNoeudParent(Noeud noeudParent) {
		this.noeudParent = noeudParent;
	}
	
	public Robot getRobot(String couleur){
		for (Robot robot : listeRobot) {
			if (robot.getCouleur().equals(couleur)) {
				return robot;
			}
		}
		return null;
	}

	public Robot[] getListeRobot() {
		return listeRobot;
	}

	public void setListeRobot(Robot[] listeRobot) {
		this.listeRobot = listeRobot;
	}

	public int getNbRobot() {
		return nbRobot;
	}
	
	

}


package PackageRR;

/**
 * Classe nous permetant de representer les cases
 * @author MOHAMED BA KOMARA ,SALAMI SODIKI ,MOHAMED CAMARA ,BAMBA ALASSANE
 *
 */
public class Case {
	
	private Robot robot;
	private Cible cible;
	/* haut = 0, droit= 1, bas =2, gauche = 3 */
    boolean[] wall = {false, false, false, false};

	public Case(Robot robot, Cible cible) {
		super();
		this.robot = robot;
		this.cible = cible;
	}
	public Case(boolean murHaut,boolean murDroit,boolean murBas,Boolean murGauche) {
		super();
		this.wall[0] = murHaut;
		this.wall[1] = murDroit;
		this.wall[2] = murBas;
		this.wall[3] = murGauche;
	}

	public Robot getRobot() {
		return robot;
	}

	public void setRobot(Robot robot) {
		this.robot = robot;
	}

	public Cible getCible() {
		return cible;
	}

	public void setCible(Cible cible) {
		this.cible = cible;
	}

	public boolean[] getWall() {
		return wall;
	}
	public boolean getWall(int p) {
		return this.wall[p];
	}
	public void setWall(int p) {
		this.wall[p] = true;
	}

	
	/**
	 * permet de savoir si une case est occupee
	 * @return boolean
	 */
    public boolean estOccupe() {
    	if (this.robot==null)
    		return false;
        return true;
    }
    
    public boolean estOccupeParCible() {
    	if (this.cible==null)
    		return false;
        return true;
	}
	

}

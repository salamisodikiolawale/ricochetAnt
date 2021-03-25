package PackageRR.Ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import PackageRR.Cible;
import PackageRR.Plateau;
import PackageRR.Position;
import PackageRR.Robot;
import mvc.IEcouteurModel;
/**
 * 
 * @author MOHAMED BA KOMARA ,SALAMI SODIKI ,MOHAMED CAMARA ,BAMBA ALASSANE
 *
 */
public class Maquette extends JComponent implements IEcouteurModel ,MouseListener{
	private Plateau plateau;
	private  final int DIMPLATEAU=16;
	private final int DIM_CASE=50;
	private final static String[] COULEUR= {"ROUGE","JAUNE","VERT","BLEU","VORTEX"};
	private final static String[] FORME= {"CERCLE","ETOILE","TRIANGLE","LOSANGE","ARCENCIEL"};
	
	private ImageIcon imgCarre, imgCbTobi,
					  imgRB, imgRJ, imgRR, imgRV,
					  imgCbCercleB, imgCbCercleJ, imgCbCercleR, imgCbCercleV, 
					  imgCbEtoileB, imgCbEtoileJ, imgCbEtoileR, imgCbEtoileV,
					  imgCbLosangeB, imgCbLosangeJ, imgCbLosangeR, imgCbLosangeV,
					  imgCbTriangleB, imgCbTriangleJ, imgCbTriangleR, imgCbTriangleV;
 
	
	
	public Maquette(Plateau plateau){
		this.plateau=plateau;
		plateau.ajoutEcouteur(this);
		chargementImages();
		this.addMouseListener(this);
		
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;

		//Dessiner les  cases du jeu
		for(int i=0;i<DIMPLATEAU;i++) {
			for(int j=0;j<DIMPLATEAU;j++) {
				
				g2D.fill(new Rectangle((j+1)*DIM_CASE, (i+1)*DIM_CASE, DIM_CASE, DIM_CASE));
				imgCarre.paintIcon(null, g2D, (j+1)*DIM_CASE, (i+1)*DIM_CASE);
				
				if( this.plateau.getPlateauFinal()[i][j].getWall()[0]) {
					
					g2D.fill(new Rectangle((j+1)*DIM_CASE, (i+1)*DIM_CASE, DIM_CASE,5));
				}
				if( this.plateau.getPlateauFinal()[i][j].getWall()[1]) {
					
					g2D.fill(new Rectangle((j+1+1)*DIM_CASE-5, (i+1)*DIM_CASE, 5,DIM_CASE));
				}
				if( this.plateau.getPlateauFinal()[i][j].getWall()[2]) {
					
					g2D.fill(new Rectangle((j+1)*DIM_CASE, (i+1+1)*DIM_CASE-5,DIM_CASE,5));
				}
				if( this.plateau.getPlateauFinal()[i][j].getWall()[3]) {
					
					g2D.fill(new Rectangle((j+1)*DIM_CASE, (i+1)*DIM_CASE, 5,DIM_CASE));
				}
				
			}
		}
		//affichage des numeros de cases
		for(int i=0;i<DIMPLATEAU;i++) {
			g2D.drawString(String.valueOf(i), 2.0f/3*DIM_CASE, (i+1.5f)*DIM_CASE+6);
			g2D.drawString(String.valueOf(i), (i+1.5f)*DIM_CASE-5, 2.0f/3*DIM_CASE+6);
		}
		//placer les cibles
		for(Cible c:plateau.getCibles()) {
			positionnementImageC(c, g2D);
		}
				
		//placer les robots
		for(Robot r:plateau.getListeRobot()) {
			positionnementImageR(r, g2D);
		}
		//illuminer le robot selectionne
		if(this.plateau.getRobotCourant()!=4) {
			g2D.setPaint(Color.RED);
			Position posRobot =plateau.getListeRobot()[plateau.getRobotCourant()].getPosition();
			g2D.setStroke(new BasicStroke(3));
			g2D.drawRect((posRobot.getcol()+1)*DIM_CASE, (posRobot.getligne()+1)*DIM_CASE, DIM_CASE, DIM_CASE);
		}
		
		g2D.dispose();
	}

	/**
	 * Permet de charger les differentes images du jeu
	 */
	private void chargementImages() {
		//Chargement du carre
				this.imgCarre=new ImageIcon(getClass().getResource("/images/carre.png"));
						
				//Chargement des images des robot
				this.imgRB=new ImageIcon(getClass().getResource("/images/robot-blue.png"));
				this.imgRJ=new ImageIcon(getClass().getResource("/images/robot-yellow.png"));
				this.imgRR=new ImageIcon(getClass().getResource("/images/robot-red.png"));
				this.imgRV=new ImageIcon(getClass().getResource("/images/robot-green.png"));
				
				//Chargement des images des cibles
				this.imgCbTobi=new ImageIcon(getClass().getResource("/images/cbTobi.png"));
				this.imgCbCercleB=new ImageIcon(getClass().getResource("/images/cbCercleB.png"));
				this.imgCbCercleJ=new ImageIcon(getClass().getResource("/images/cbCercleJ.png"));
				this.imgCbCercleR=new ImageIcon(getClass().getResource("/images/cbCercleR.png"));
				this.imgCbCercleV=new ImageIcon(getClass().getResource("/images/cbCercleV.png"));
				
				//Chargement des images des cibles
				this.imgCbEtoileB=new ImageIcon(getClass().getResource("/images/cbEtoileB.png"));
				this.imgCbEtoileJ=new ImageIcon(getClass().getResource("/images/cbEtoileJ.png"));
				this.imgCbEtoileR=new ImageIcon(getClass().getResource("/images/cbEtoileR.png"));
				this.imgCbEtoileV=new ImageIcon(getClass().getResource("/images/cbEtoileV.png"));
				
				//Chargement des images des cibles
				this.imgCbLosangeB=new ImageIcon(getClass().getResource("/images/cbLosangeB.png"));
				this.imgCbLosangeJ=new ImageIcon(getClass().getResource("/images/cbLosangeJ.png"));
				this.imgCbLosangeR=new ImageIcon(getClass().getResource("/images/cbLosangeR.png"));
				this.imgCbLosangeV=new ImageIcon(getClass().getResource("/images/cbLosangeV.png"));
				
				//Chargement des images des cibles
				this.imgCbTriangleB=new ImageIcon(getClass().getResource("/images/cbTriangleB.png"));
				this.imgCbTriangleJ=new ImageIcon(getClass().getResource("/images/cbTriangleJ.png"));
				this.imgCbTriangleR=new ImageIcon(getClass().getResource("/images/cbTriangleR.png"));
				this.imgCbTriangleV=new ImageIcon(getClass().getResource("/images/cbTriangleV.png"));
	}
	
	/**
	 * Dispose des images des robot en fonction des positions des Robots
	 * @param r Robot
	 * @param g2D graphic
	 */
	public void positionnementImageR(Robot r, Graphics2D g2D) {
		if( r.getCouleur() == this.COULEUR[0])
			imgRR.paintIcon(null, g2D,(r.getPosition().getcol()+1)*DIM_CASE,(r.getPosition().getligne()+1)*DIM_CASE);
		if( r.getCouleur() == this.COULEUR[1])
			imgRJ.paintIcon(null, g2D,(r.getPosition().getcol()+1)*DIM_CASE,(r.getPosition().getligne()+1)*DIM_CASE);
		if( r.getCouleur() == this.COULEUR[2])
			imgRV.paintIcon(null, g2D,(r.getPosition().getcol()+1)*DIM_CASE,(r.getPosition().getligne()+1)*DIM_CASE);
		if( r.getCouleur() == this.COULEUR[3])
			imgRB.paintIcon(null, g2D,(r.getPosition().getcol()+1)*DIM_CASE,(r.getPosition().getligne()+1)*DIM_CASE);
	}
	
	/**
	 * Dispose des images des Cibles en fonction des positions des Cibles
	 * @param c Cible
	 * @param g2D graphic
	 */
	public void positionnementImageC(Cible c, Graphics2D g2D) {
		//COULEUR= {"ROUGE","JAUNE","VERT","BLEU","VORTEX"};
		//FORME= {"CERCLE","ETOILE","TRIANGLE","LOSANGE","ARCENCIEL"};
		
		//Cible Cercle ARCENCIEL
		if( c.getForme() == this.FORME[4] && c.getCouleur() == this.COULEUR[4])
			this.imgCbTobi.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
		
		//Cibles rouge
		if( c.getCouleur() == this.COULEUR[0]) {
			
			if( c.getForme() == this.FORME[0])
				this.imgCbCercleR.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
			if( c.getForme() == this.FORME[1])
				this.imgCbEtoileR.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
			if( c.getForme() == this.FORME[2])
				this.imgCbTriangleR.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
			if( c.getForme() == this.FORME[3])
				this.imgCbLosangeR.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
		}
		//Cibles Jaune
		if( c.getCouleur() == this.COULEUR[1]) {
			
			if( c.getForme() == this.FORME[0])
				this.imgCbCercleJ.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
			if( c.getForme() == this.FORME[1])
				this.imgCbEtoileJ.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
			if( c.getForme() == this.FORME[2])
				this.imgCbTriangleJ.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
			if( c.getForme() == this.FORME[3])
				this.imgCbLosangeJ.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
		}
		//Cibles Vert
		if( c.getCouleur() == this.COULEUR[2]) {
			
			if( c.getForme() == this.FORME[0])
				this.imgCbCercleV.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
			if( c.getForme() == this.FORME[1])
				this.imgCbEtoileV.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
			if( c.getForme() == this.FORME[2])
				this.imgCbTriangleV.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
			if( c.getForme() == this.FORME[3])
				this.imgCbLosangeV.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
		}
		//Cibles Bleu
		if( c.getCouleur() == this.COULEUR[3]) {
			
			if( c.getForme() == this.FORME[0])
				this.imgCbCercleB.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
			if( c.getForme() == this.FORME[1])
				this.imgCbEtoileB.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
			if( c.getForme() == this.FORME[2])
				this.imgCbTriangleB.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
			if( c.getForme() == this.FORME[3])
				this.imgCbLosangeB.paintIcon(null, g2D,(c.getPosition().getcol()+1)*DIM_CASE,(c.getPosition().getligne()+1)*DIM_CASE);
		}
	}
	@Override
	public void modeleMisAJour(Object source) {
		repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		int ligne=event.getY()/this.DIM_CASE;
		int colonne=event.getX()/this.DIM_CASE;
		/*System.out.println("ligne "+ligne);
		System.out.println("col "+colonne);
		System.out.println();*/
		Position p=new Position(ligne-1, colonne-1);
		this.plateau.selectionRobot(p);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

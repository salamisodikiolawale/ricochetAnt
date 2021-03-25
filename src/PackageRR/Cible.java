package PackageRR;

/**
 * 
 * @author MOHAMED BA KOMARA ,SALAMI SODIKI ,MOHAMED CAMARA ,BAMBA ALASSANE
 *
 */
public class Cible {
	private final int ROUGE=1;
	private final int JAUNE=2;
	private final int VERT=3;
	private final int BLEU=4;
	
	private String forme;
	private String couleur;
	private Position position;
	
	
	public Cible(String forme, String couleur, boolean active) {
		super();
		this.forme = forme;
		this.couleur = couleur;
		this.position=new Position();
	}
	public String getForme() {
		return forme;
	}
	public void setForme(String forme) {
		this.forme = forme;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	
	
}

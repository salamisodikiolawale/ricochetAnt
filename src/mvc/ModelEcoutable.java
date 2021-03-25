package mvc;


import java.util.ArrayList;
import java.util.List;

public abstract class ModelEcoutable {
	private List<IEcouteurModel> listEcouteur = new ArrayList<IEcouteurModel>();
	
	public void ajoutEcouteur(IEcouteurModel e) {
		listEcouteur.add(e);
		
	}
	public void retraitEcouteur (IEcouteurModel e) {
		listEcouteur.remove(e);
	}
	public void fireChangement() {
		for(IEcouteurModel e: listEcouteur) {
			e.modeleMisAJour(this);
		}
		
	}
}

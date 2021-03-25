package PackageRR;

public class Position {
	private int ligne;
	private int col;
	
	public Position(int ligne, int col) {
		this.ligne = ligne;
		this.col = col;
	}
	public Position() {
		super();
	}
	public int getligne() {
		return ligne;
	}
	public void setligne(int ligne) {
		this.ligne = ligne;
	}
	public int getcol() {
		return col;
	}
	public void setcol(int col) {
		this.col = col;
	}
	@Override
	public String toString() {
		return "Position [ligne=" + ligne + ", col=" + col + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (col != other.col)
			return false;
		if (ligne != other.ligne)
			return false;
		return true;
	}
	

}

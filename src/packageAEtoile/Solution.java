package packageAEtoile;

import PackageRR.Direction;
import PackageRR.Position;

public class Solution {
	private String coulRob;
	private Direction direction;
	private Position position;
	
	public Solution(String coulRob, Direction direction, Position position) {
		super();
		this.coulRob = coulRob;
		this.direction = direction;
		this.position = position;
	}

	public String getCoulRob() {
		return coulRob;
	}

	public void setCoulRob(String coulRob) {
		this.coulRob = coulRob;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	

}

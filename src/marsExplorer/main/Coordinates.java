package marsExplorer.main;


/**
 * Represents a square on the planet's "grid" (assuming that the spherical surface of the planet is represented by
 * a two-dimensional grid with connected edges).
 * The point (0,0) is on the bottom left corner.
 * @author Alessandro
 *
 */
public class Coordinates {
	
	private Position x, y;
	
	public Coordinates(Position x, Position y) {
		setX(x);
		setY(y);
	}

	public Position getX() {
		return x;
	}

	public void setX(Position x) {
		this.x = x;
	}

	public Position getY() {
		return y;
	}

	public void setY(Position y) {
		this.y = y;
	}

	/**
	 * Returns a new instance of Coordinates representing the 'should be' coordinates after moving in the specified direction.
	 * @param direction
	 * @return
	 */
	public Coordinates getNextCoordinates(Direction direction) {
		Position nextX = new Position(x.getPosition(), x.getMaxPosition());
		Position nextY = new Position(y.getPosition(), y.getMaxPosition());
		switch(direction) {
		case EAST: nextX.stepForward();
			break;
		case NORTH: nextY.stepForward();
			break;
		case SOUTH: nextY.stepBackward();
			break;
		case WEST: nextX.stepBackward();
			break;
		}
		return new Coordinates(nextX, nextY);
	}
	
	@Override
	public boolean equals(Object obj) {
		Coordinates c = (Coordinates) obj;
		return (c.getX().equals(x) && c.getY().equals(y));
	}

	
}

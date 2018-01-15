package marsExplorer.main;

public enum Direction {
	NORTH(0),
	WEST(1),
	SOUTH(2),
	EAST(3);

	private int index;
	
	private Direction(int index) {
		this.index = index;
	}
	
	public Direction getLeftDirection() {
		return changeDirection(+1);
	}
	
	public Direction getRightDirection() {
		return changeDirection(-1);
	}
	

	/**
	 * 
	 * @param offset +1 = anti-clockwise rotation, -1 = clockwise rotation.
	 * @return
	 */
	private Direction changeDirection(int offset) {
		//sommo values().length per evitare che il valore tra parentesi risulti negativo
		int nextIndex = (index + offset + values().length) % values().length;
		return Direction.values()[nextIndex];
	}
	
	public Direction getBackwardDirection() {
		int backwardIndex = (index + 2) % 4;
		return values()[backwardIndex];
	}
	
	/*
	public static Direction getBackwardDirection(Direction d) {
		switch(d) {
		case EST: return WEST;
		case NORTH: return SOUTH;
		case SOUTH: return NORTH;
		case WEST: return EST;
		default: return null;
		}
	}*/
	
}

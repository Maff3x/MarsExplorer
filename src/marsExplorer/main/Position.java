package marsExplorer.main;

/**
 * Represents the position on one single axis.
 * @author Alessandro
 *
 */
public class Position {
	
	/**
	 * The position is discrete.
	 */
	private int position, maxPosition;

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getMaxPosition() {
		return maxPosition;
	}

	public void setMaxPosition(int maxPosition) {
		this.maxPosition = maxPosition;
	}

	public Position(int position, int maxPosition) {
		setPosition(position);
		setMaxPosition(maxPosition);
	}
	
	/**
	 * Increases the value (moving right or up)
	 */
	public void stepForward() {
		position = forwardPosition();
	}
	
	/**
	 * Decreases the value (moving left or down)
	 */
	public void stepBackward() {
		position = backwardPosition();
	}
	
	/**
	 * if the current position is maxPosition returns 0
	 * @return
	 */
	private int forwardPosition() {
		return (position + 1) % (maxPosition + 1);
	}
	
	/**
	 * if the current position is 0 returns maxPosition
	 * @return
	 */
	private int backwardPosition() {
		if(getPosition() > 0) {
			return getPosition() - 1;
		} else {
			return getMaxPosition();
		}
	}
	
	public Position getForwardPosition() {
		return new Position(forwardPosition(), maxPosition);
	}
	
	public Position getBackwardPosition() {
		return new Position(backwardPosition(), maxPosition);
	}
	
	@Override
	public boolean equals(Object obj) {
		Position p = (Position) obj;
		return p.getPosition() == position;
	}
}

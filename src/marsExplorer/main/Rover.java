package marsExplorer.main;

import java.util.List;

/**
 * Represents a rover with a position and the direction it is facing. <br />
 * A list of obstacles is also provided
 * @author Alessandro
 *
 */
public class Rover {

	private Coordinates coordinates;
	private Direction direction;
	private List<Coordinates> obstacles;
	
	public Rover(Coordinates coordinates, Direction direction, List<Coordinates> obstacles) {
		setCoordinates(coordinates);
		setDirection(direction);
		setObstacles(obstacles);
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public List<Coordinates> getObstacles() {
		return obstacles;
	}

	public void setObstacles(List<Coordinates> obstacles) {
		this.obstacles = obstacles;
	}

	/**
	 * Executes a string of commands. <br />
	 * The possible commands are: <br />
	 * f: forward
	 * b: backward (does not change the direction the rover is facing to)
	 * r: rotate right
	 * l: rotate left
	 * The commands are not case sensitive.
	 * @param commands a string of single (character) commands.
	 * @throws Exception the command is not valid.
	 */
	public boolean runCommands(String commands) throws Exception {
		System.out.println("Starting command sequence: " + commands);
		System.out.println(coordinatesString());
		for(char command : commands.toCharArray()) {
			boolean success = runCommand(command);
			System.out.println(coordinatesString());
			if(!success) {
				return false;
			}
		}
		return true;
	}
	

	/**
	 * Runs a single command
	 * @param command
	 * @return
	 * @throws Exception if the command is not valid
	 */
	private boolean runCommand(char command) throws Exception {
		switch(Character.toUpperCase(command)) {
		case 'F': return move(direction);
		case 'B': return move(direction.getBackwardDirection());
		case 'R': rotateRight(); return true;
		case 'L': rotateLeft(); return true;
		default: throw new Exception("Command " + command + "is not valid");
			
		}
	}
	
	/**
	 * Gets the expected next coordinates and carries out the move if they are free.
	 * @param direction
	 * @return true if it is possible to carry out the move, false otherwise
	 */
	private boolean move(Direction direction) {
		Coordinates preview = coordinates.getNextCoordinates(direction);
		if(!obstacles.contains(preview)) {
			coordinates = preview;
			return true;
		} else {
			System.out.println("OBSTACLE FOUND!");
			return false;
		}
	}

	private void rotateRight() {
		setDirection(direction.getRightDirection());
	}
	
	private void rotateLeft() {
		setDirection(direction.getLeftDirection());
	}
	
	private String coordinatesString() {
		return "(" + coordinates.getX().getPosition() + ";" + coordinates.getY().getPosition() + ")" + direction;
	}
}

package marsExplorer.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.BeforeEach;

import marsExplorer.main.Coordinates;
import marsExplorer.main.Direction;
import marsExplorer.main.Position;
import marsExplorer.main.Rover;

class RoverTest {

	private Rover rover;
	private Coordinates coordinates;
	private final Direction direction = Direction.EAST;
	private Position x;
	private Position y;
	private List<Coordinates> obstacles;

	@BeforeEach
	public void beforeRoverTest() {
		x = new Position(1, 100);
		y = new Position(5, 200);
		obstacles = new ArrayList<Coordinates>();
		coordinates = new Coordinates(x, y);
		rover = new Rover(coordinates, direction, obstacles);
	}

	@Test
	public void forwardCommandTest() throws Exception {
		int expected = x.getPosition() + 1;
		assertTrue(rover.runCommands("F"));
		assertEquals(expected, rover.getCoordinates().getX().getPosition());
	}

	@Test
	public void backwardCommandTest() throws Exception {
		int expected = x.getPosition() - 1;
		assertTrue(rover.runCommands("B"));
		assertEquals(expected, rover.getCoordinates().getX().getPosition());
	}

	@Test
	public void rightCommandTest() throws Exception {
		assertTrue(rover.runCommands("R"));
		assertEquals(Direction.SOUTH, rover.getDirection());
	}

	@Test
	public void leftCommandTest() throws Exception {
		assertTrue(rover.runCommands("L"));
		assertEquals(Direction.NORTH, rover.getDirection());
	}

	@Test
	public void receiveSingleCommandShouldThrowExceptionWhenCommandIsUnknown() throws Exception {
		Executable executable = () -> { rover.runCommands("A");};
		assertThrows(Exception.class, executable);
	}

	@Test
	public void commandCaseTest() throws Exception {
		int expected = x.getPosition() + 1;
		assertTrue(rover.runCommands("f"));
		assertEquals(expected, rover.getCoordinates().getX().getPosition());
	}

	@Test
	public void multipleCommandsTest() throws Exception {
		int expectedX = x.getPosition() + 1;
		int expectedY = y.getPosition() + 1;
		assertTrue(rover.runCommands("FFLFRB"));
		assertEquals(expectedX, rover.getCoordinates().getX().getPosition());
		assertEquals(expectedY, rover.getCoordinates().getY().getPosition());
		assertEquals(Direction.EAST, rover.getDirection());
	}

	
	@Test
	public void acrossGridCommandTest() throws Exception {
		int expectedX = x.getMaxPosition() + 1 + x.getPosition() - 2;
		int expectedY = y.getMaxPosition() + 1 + y.getPosition() - 7;
		assertTrue(rover.runCommands("LLFFRBBBBBBB"));
		assertEquals(expectedX, rover.getCoordinates().getX().getPosition());
		assertEquals(expectedY, rover.getCoordinates().getY().getPosition());
		assertEquals(Direction.NORTH, rover.getDirection());
	}
	
	@Test
	public void obstacleTest() throws Exception {

		Position obstacleX = new Position(x.getPosition() + 2, x.getMaxPosition());
		Position obstacleY = new Position(y.getPosition(), y.getMaxPosition());
		Coordinates obstacle = new Coordinates(obstacleX, obstacleY);
		rover.getObstacles().add(obstacle);

		int expectedX = x.getPosition() + 1;
		
		assertFalse(rover.runCommands("FFFFFFFR"));

		// the rover should stop on the left of the obstacle
		assertEquals(expectedX, rover.getCoordinates().getX().getPosition());
		// direction shouldn't have changed
		assertEquals(Direction.EAST, rover.getDirection());
		
	}

}

package marsExplorer.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import marsExplorer.main.Coordinates;
import marsExplorer.main.Direction;
import marsExplorer.main.Position;

class CoordinatesTest {

	private Position x;
	private Position y;
	private final int xPosition = 10;
	private final int yPosition = 20;
	private final int maxPosition = 100;
	private Coordinates c;
	
	@BeforeEach
	public void beforeTests() {
		x = new Position(xPosition, maxPosition);
		y = new Position(yPosition, maxPosition);
		c = new Coordinates(x, y);
	}
	
	@Test
	public void testNextCoordinatesNorth() {
		Position expectedX = new Position(x.getPosition(), x.getMaxPosition());
		Position expectedY = new Position(y.getForwardPosition().getPosition(), y.getMaxPosition());
		Coordinates expectedCoordinates = new Coordinates(expectedX, expectedY);
		assertEquals(expectedCoordinates, c.getNextCoordinates(Direction.NORTH));
	}
	
	@Test
	public void testNextCoordinatesSouth() {
		Position expectedX = new Position(x.getPosition(), x.getMaxPosition());
		Position expectedY = new Position(y.getBackwardPosition().getPosition(), y.getMaxPosition());
		Coordinates expectedCoordinates = new Coordinates(expectedX, expectedY);
		assertEquals(expectedCoordinates, c.getNextCoordinates(Direction.SOUTH));
	}
	

	@Test
	public void testNextCoordinatesEast() {
		Position expectedX = new Position(x.getForwardPosition().getPosition(), x.getMaxPosition());
		Position expectedY = new Position(y.getPosition(), y.getMaxPosition());
		Coordinates expectedCoordinates = new Coordinates(expectedX, expectedY);
		assertEquals(expectedCoordinates, c.getNextCoordinates(Direction.EAST));
	}
	

	@Test
	public void testNextCoordinatesWest() {
		Position expectedX = new Position(x.getBackwardPosition().getPosition(), x.getMaxPosition());
		Position expectedY = new Position(y.getPosition(), y.getMaxPosition());
		Coordinates expectedCoordinates = new Coordinates(expectedX, expectedY);
		assertEquals(expectedCoordinates, c.getNextCoordinates(Direction.WEST));
	}
	
}

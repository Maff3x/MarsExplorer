package marsExplorer.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import marsExplorer.main.Position;

class PositionTest {
	
	private Position p;
	private int position = 1;
	private int maxPosition = 10;
	
	@BeforeEach
	public void beforeTests() {
		p = new Position(position, maxPosition);
	}
	
	@Test
	public void testStepForward() {
		int expected = p.getPosition() + 1;
		p.stepForward();
		assertEquals(expected, p.getPosition());
	}
	
	@Test
	public void testStepBackward() {
		int expected = p.getPosition()-1;
		p.stepBackward();
		assertEquals(expected, p.getPosition());
	}
	
	@Test
	public void testWrappingForward() {
		p.setPosition(maxPosition);
		int expected = 0;
		p.stepForward();
		assertEquals(expected, p.getPosition());
	}
	
	@Test
	public void testWrappingBackward() {
		p.setPosition(0);
		int expected = maxPosition;
		p.stepBackward();
		assertEquals(expected, p.getPosition());
	}
}

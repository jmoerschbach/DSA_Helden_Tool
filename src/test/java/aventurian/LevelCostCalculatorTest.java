package aventurian;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LevelCostCalculatorTest {
	private LevelCostCalculator toTest;

	@Before
	public void setUp() throws Exception {
		toTest = new LevelCostCalculator();
	}

	@Test
	public void testGetCostValid() {
		assertEquals(357, toTest.getCost(10, 25, 0));
		assertEquals(720, toTest.getCost(0, 31, 1));
		assertEquals(0, toTest.getCost(0, 0, 2));
		assertEquals(0, toTest.getCost(1, 1, 3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetCostToTooHigh() {
		toTest.getCost(0, 32, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetCostFromTooLow() {
		toTest.getCost(-1, 10, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetCostRowTooHigh() {
		toTest.getCost(0, 31, 9);
	}

	@Test
	public void testGetCostFromHigherThanTo() {
		fail("Sollverhalten?");
		// is it possible to call getCost with parameters switched -> negative
		// cost = refund??
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetCostRowTooLow() {
		toTest.getCost(0, 31, -1);
	}
}

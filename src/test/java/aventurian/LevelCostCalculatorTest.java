package aventurian;

import static org.junit.Assert.*;

import static aventurian.LevelCostCalculator.COLUMN.*;

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
		assertEquals(357, toTest.getCost(10, 25, ASTERN));
		assertEquals(720, toTest.getCost(0, 31, A));
		assertEquals(0, toTest.getCost(0, 0, B));
		assertEquals(0, toTest.getCost(1, 1, C));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetCostToTooHigh() {
		toTest.getCost(0, 32, ASTERN);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetCostFromTooLow() {
		toTest.getCost(-1, 10, ASTERN);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testGetCostFromHigherThanTo() {
		toTest.getCost(12, 8, C);
	}
	
	@Test
	public void testGetRefundValid() {
		assertEquals(357, toTest.getRefund(25, 10, ASTERN));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetRefundToTooLow() {
		toTest.getRefund(18, -1, B);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testGetRefundFromTooHigh() {
		toTest.getRefund(33, 18, D);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testGetRefundFromLowerThanTo() {
		toTest.getRefund(8, 12, C);
	}
}

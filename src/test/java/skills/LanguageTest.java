package skills;

import static org.junit.Assert.*;

import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;

import aventurian.Aventurian;

public class LanguageTest {
	private Language toTest;
	private Predicate<Aventurian> requirement = (Aventurian a) -> {
		return true;
	};

	@Before
	public void setUp() throws Exception {
		this.toTest = new Language("testLanguage", "description", requirement,
				5, 50);
	}

	@Test
	public void testIncrease() {
		assertEquals(1, toTest.getLevel());
		toTest.increase();
		assertEquals(2, toTest.getLevel());
	}

	@Test(expected = IllegalStateException.class)
	public void testIncreaseExceedMaximum() {
		while (toTest.isIncreasable())
			toTest.increase();
		toTest.increase();
	}

	@Test
	public void testDecrease() {
		// first increase to be able to decrease
		toTest.increase();
		assertEquals(2, toTest.getLevel());
		toTest.decrease();
		assertEquals(1, toTest.getLevel());
	}

	@Test(expected = IllegalStateException.class)
	public void testDecreaseExceedMinimum() {
		toTest.decrease();
	}

	@Test
	public void testGetLevel() {
		assertEquals(1, toTest.getLevel());
	}

	@Test
	public void testGetUpgradeCost() {
		assertEquals(1, toTest.getLevel());
		assertEquals(100, toTest.getUpgradeCost());

		toTest.increase();
		assertEquals(2, toTest.getLevel());
		assertEquals(150, toTest.getUpgradeCost());

		toTest.increase();
		assertEquals(3, toTest.getLevel());
		assertEquals(200, toTest.getUpgradeCost());

		toTest.increase();
		assertEquals(4, toTest.getLevel());
		assertEquals(250, toTest.getUpgradeCost());

	}
	
	@Test
	public void testGetTotalCost() {
		assertEquals(1, toTest.getLevel());
		assertEquals(50, toTest.getTotalCost());

		toTest.increase();
		assertEquals(2, toTest.getLevel());
		assertEquals(150, toTest.getTotalCost());

		toTest.increase();
		assertEquals(3, toTest.getLevel());
		assertEquals(300, toTest.getTotalCost());

		toTest.increase();
		assertEquals(4, toTest.getLevel());
		assertEquals(500, toTest.getTotalCost());
		
		toTest.increase();
		assertEquals(5, toTest.getLevel());
		assertEquals(750, toTest.getTotalCost());

	}


	@Test
	public void testGetDowngradeCost() {
		while (toTest.isIncreasable())
			toTest.increase();

		assertEquals(5, toTest.getLevel());
		assertEquals(250, toTest.getDowngradeCost());

		toTest.decrease();
		assertEquals(4, toTest.getLevel());
		assertEquals(200, toTest.getDowngradeCost());

		toTest.decrease();
		assertEquals(3, toTest.getLevel());
		assertEquals(150, toTest.getDowngradeCost());

		toTest.decrease();
		assertEquals(2, toTest.getLevel());
		assertEquals(100, toTest.getDowngradeCost());

		fail("method name??");
		// should be getDowngradeRefund(), right?
	}

	@Test
	public void testGetLearningCost() {
		assertEquals(50, toTest.getLearningCost());
	}

	@Test
	public void testSetNativeTongue() {
		toTest.setNativeTongue(false);
		assertFalse(toTest.isNativeTongue());

		toTest.setNativeTongue(true);
		assertTrue(toTest.isNativeTongue());
	}

	@Test
	public void testIsIncreasable() {
		assertTrue(toTest.isIncreasable());

		for (int i = 1; i < 5; i++)
			toTest.increase();

		assertFalse(toTest.isIncreasable());
	}

	@Test
	public void testIsDecreasable() {
		assertFalse(toTest.isDecreasable());

		toTest.increase();

		assertTrue(toTest.isDecreasable());
	}

}

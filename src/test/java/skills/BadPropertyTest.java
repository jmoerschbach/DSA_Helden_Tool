package skills;

import static org.junit.Assert.*;

import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;

import aventurian.Aventurian;

public class BadPropertyTest {
	private BadProperty toTest;
	private static Predicate<Aventurian> requirement = (Aventurian a) -> {
		return true;
	};

	@Before
	public void setUp() throws Exception {
		toTest = new BadProperty("testBadProperty", "testDescription", 100,
				requirement);
	}

	@Test
	public void testIncrease() {
		assertEquals(5, toTest.getLevel());
		toTest.increase();
		assertEquals(6, toTest.getLevel());
	}

	@Test(expected = IllegalStateException.class)
	public void testIncreaseExceedMaximum() {
		while (toTest.isIncreasable())
			toTest.increase();
		toTest.increase();
	}

	@Test
	public void testDecrease() {
		toTest.increase();
		assertEquals(6, toTest.getLevel());

		toTest.decrease();
		assertEquals(5, toTest.getLevel());
	}

	@Test(expected = IllegalStateException.class)
	public void testDecreaseExceedMinimum() {
		toTest.decrease();
	}

	@Test
	public void testGetLevel() {
		assertEquals(5, toTest.getLevel());
	}

	@Test
	public void testIsIncreasable() {
		assertTrue(toTest.isIncreasable());
		for(int i = 5; i < 12; i++)
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

package skills;

import static org.junit.Assert.*;

import java.util.function.Consumer;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;

import aventurian.Aventurian;

public class PropertyTest {
	private Property toTest;
	private static final Consumer<Aventurian> EMPTY = (Aventurian a) -> {
	};
	private static Predicate<Aventurian> requirement = (Aventurian a) -> {
		return true;
	};

	@Before
	public void setUp() throws Exception {
		toTest = new Property("testProperty", "testDescription", 100, EMPTY,
				EMPTY, requirement);
	}

	@Test
	public void testIsAdvantage() {
		assertTrue(toTest.isAdvantage());
		assertFalse(toTest.isDisadvantage());
	}

	@Test
	public void testIsDisadvantage() {
		toTest = new Property("testProperty", "testDescription", -100, EMPTY,
				EMPTY, requirement);
		assertTrue(toTest.isDisadvantage());
		assertFalse(toTest.isAdvantage());
	}

	@Test
	public void testGetCost() {
		assertEquals(100, toTest.getCost());
	}

}

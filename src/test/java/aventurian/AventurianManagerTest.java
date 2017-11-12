package aventurian;

import org.junit.Before;
import org.junit.Test;

import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.*;
import static org.mockito.Mockito.*;
import skills.BadProperty;
import skills.Language;
import skills.Property;
import static org.junit.Assert.*;


class AventurianManagerTest {
	
	AventurianManager toTest;

	@Before
	void setUp() throws Exception {
		this.toTest = new AventurianManager(new Aventurian());
	}

	@Test
	void testIncreasePrimaryAttribute() {
		fail("Not yet implemented");
	}

	@Test
	void testDecreasePrimaryAttribut() {
		fail("Not yet implemented");
	}

	@Test
	void testAddProperty() {
		fail("Not yet implemented");
	}

	@Test
	void testAddBadProperty() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveBadProperty() {
		fail("Not yet implemented");
	}

	@Test
	void testIncreaseBadProperty() {
		fail("Not yet implemented");
	}

	@Test
	void testDecreaseBadProperty() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveProperty() {
		fail("Not yet implemented");
	}

	@Test
	void testIncreaseLanguage() {
		fail("Not yet implemented");
	}

	@Test
	void testDecreaseLanguage() {
		fail("Not yet implemented");
	}

	@Test
	void testAddLanguage() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveLanguage() {
		fail("Not yet implemented");
	}

}

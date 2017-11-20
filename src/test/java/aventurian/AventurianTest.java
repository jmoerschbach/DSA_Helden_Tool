package aventurian;

import org.junit.Before;
import org.junit.Test;

import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.*;
import static org.mockito.Mockito.*;
import skills.BadProperty;
import skills.Language;
import skills.Property;
import static org.junit.Assert.*;

public class AventurianTest {

	public static final int AP = 16500;
	private Aventurian toTest;

	@Before
	public void setUp() throws Exception {
		toTest = new Aventurian("test", AP);
	}

	@Test
	public void payValid() throws Exception {
		toTest.pay(1000);
		int expected = AP - 1000;
		int actual = toTest.getAdventurePoints();
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void payToMuch() throws Exception {
		toTest.pay(20000);
	}

	@Test(expected = IllegalArgumentException.class)
	public void payNegative() throws Exception {
		toTest.pay(-20);
	}

	@Test
	public void refundValid() throws Exception {
		toTest.refund(500);
		int expected = AP + 500;
		int actual = toTest.getAdventurePoints();
		assertEquals(expected, actual);
	}

	@Test
	public void refundMuch() throws Exception {
		toTest.refund(20000);
		int expected = AP + 20000;
		int actual = toTest.getAdventurePoints();
		assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void refundNegative() throws Exception {
		toTest.refund(-20);
	}

	@Test
	public void canPay() throws Exception {
		assertFalse(toTest.canPay(20000));
	}

	@Test
	public void addProperty() throws Exception {
		Property testProp = mock(Property.class);
		when(testProp.getName()).thenReturn("test");
		toTest.add(testProp);
		assertTrue(toTest.hasSkill(testProp));
	}

	@Test
	public void removeProperty() throws Exception {
		Property testProp = mock(Property.class);
		when(testProp.getName()).thenReturn("test");
		toTest.add(testProp);
		assertTrue(toTest.hasSkill(testProp));
		toTest.remove(testProp);
		assertFalse(toTest.hasSkill(testProp));
	}

	@Test
	public void addBadProperty() throws Exception {
		BadProperty testProp = mock(BadProperty.class);
		when(testProp.getName()).thenReturn("test");
		toTest.add(testProp);
		assertTrue(toTest.hasSkill(testProp));
	}

	@Test
	public void removeBadProperty() throws Exception {
		BadProperty testProp = mock(BadProperty.class);
		when(testProp.getName()).thenReturn("test");
		toTest.add(testProp);
		assertTrue(toTest.hasSkill(testProp));
		toTest.remove(testProp);
		assertFalse(toTest.hasSkill(testProp));
	}

	@Test
	public void getBadPropertySum() throws Exception {
		assertEquals(0, toTest.getBadPropertySum());
		BadProperty bP1 = mock(BadProperty.class);
		when(bP1.getLevel()).thenReturn(7);
		toTest.add(bP1);
		assertEquals(7, toTest.getBadPropertySum());
		
		BadProperty bP2 = mock(BadProperty.class);
		when(bP2.getLevel()).thenReturn(5);
		toTest.add(bP2);
		assertEquals(7+5, toTest.getBadPropertySum());
	}

	@Test
	public void addLanguage() throws Exception {
		Language testLanguage = mock(Language.class);
		when(testLanguage.getName()).thenReturn("test");
		toTest.add(testLanguage);
		assertTrue(toTest.hasSkill(testLanguage));
	}

	@Test
	public void removeLanguage() throws Exception {
		Language testLanguage = mock(Language.class);
		when(testLanguage.getName()).thenReturn("test");
		toTest.add(testLanguage);
		assertTrue(toTest.hasSkill(testLanguage));
		toTest.remove(testLanguage);
		assertFalse(toTest.hasSkill(testLanguage));
	}

	@Test
	public void hasSkill() throws Exception {
		Property p = mock(Property.class);		
		assertFalse(toTest.hasSkill(p));
		toTest.add(p);
		assertTrue(toTest.hasSkill(p));
		
		BadProperty bP = mock(BadProperty.class);		
		assertFalse(toTest.hasSkill(bP));
		toTest.add(bP);
		assertTrue(toTest.hasSkill(bP));
		
		Language l = mock(Language.class);		
		assertFalse(toTest.hasSkill(l));
		toTest.add(l);
		assertTrue(toTest.hasSkill(l));
	}


	@Test
	public void getSumOfPrimaryAttributes() throws Exception {
		assertEquals(64, toTest.getSumOfPrimaryAttributes());
		toTest.increasePrimaryAttribute(COURAGE);
		toTest.increasePrimaryAttribute(COURAGE);
		toTest.increasePrimaryAttribute(COURAGE);
		assertEquals(67, toTest.getSumOfPrimaryAttributes());
	}

	@Test
	public void getPrimaryAttribute() throws Exception {
		assertEquals(8, toTest.getPrimaryAttribute(COURAGE));
	}

	@Test
	public void getMaxOfPrimaryAttribute() throws Exception {
		assertEquals(14, toTest.getMaxOfPrimaryAttribute(COURAGE));
	}

	@Test
	public void increasePrimaryAttribute() throws Exception {
		PrimaryAttributes pri = mock(PrimaryAttributes.class);
		SecondaryAttributes second = mock(SecondaryAttributes.class);
		toTest = new Aventurian("", 100, pri, second);

		toTest.increasePrimaryAttribute(COURAGE);
		verify(pri).increase(COURAGE);
		verify(second).updateValues(pri);
	}

	@Test
	public void decrasePrimaryAttribute() throws Exception {
		PrimaryAttributes pri = mock(PrimaryAttributes.class);
		SecondaryAttributes second = mock(SecondaryAttributes.class);
		toTest = new Aventurian("", 100, pri, second);

		toTest.decrasePrimaryAttribute(COURAGE);
		verify(pri).decrease(COURAGE);
		verify(second).updateValues(pri);
	}
	
	@Test
	public void increaseMaximumOfPrimaryAttribute() throws Exception {
		PrimaryAttributes pri = mock(PrimaryAttributes.class);
		toTest = new Aventurian("", 100, pri, null);
		
		toTest.increaseMaximumOfPrimaryAttribute(COURAGE);
		verify(pri).increaseMaximum(COURAGE);
	}
	
	@Test
	public void decreaseMaximumOfPrimaryAttribute() throws Exception {
		PrimaryAttributes pri = mock(PrimaryAttributes.class);
		toTest = new Aventurian("", 100, pri, null);
		
		toTest.decreaseMaximumOfPrimaryAttribute(COURAGE);
		verify(pri).decreaseMaximum(COURAGE);
	}
	
	@Test
	public void testGetPointsInAdvantagesValid() throws Exception {
		assertEquals(0, toTest.getPointsInAdvantages());
		Property p = mock(Property.class);
		when(p.getCost()).thenReturn(200);
		when(p.isAdvantage()).thenReturn(true);
		toTest.add(p);
		assertEquals(200, toTest.getPointsInAdvantages());
		Property p2 = mock(Property.class);
		when(p2.getCost()).thenReturn(300);
		when(p2.isAdvantage()).thenReturn(true);
		toTest.add(p2);
		assertEquals(500, toTest.getPointsInAdvantages());
	}
	
	@Test
	public void testGetPointsOutDisadvantagesValig() throws Exception {
		assertEquals(0, toTest.getPointsOutDisadvantages());
		Property p = mock(Property.class);
		when(p.getCost()).thenReturn(200);
		when(p.isDisadvantage()).thenReturn(true);
		toTest.add(p);
		assertEquals(200, toTest.getPointsOutDisadvantages());
	}

}
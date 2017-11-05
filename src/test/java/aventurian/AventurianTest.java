package aventurian;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

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
        toTest.addProperty(testProp);
        assertTrue(toTest.hasSkill(testProp));
    }

    @Test
    public void removeProperty() throws Exception {
        Property testProp = mock(Property.class);
        when(testProp.getName()).thenReturn("test");
        toTest.addProperty(testProp);
        assertTrue(toTest.hasSkill(testProp));
        toTest.removeProperty(testProp);
        assertFalse(toTest.hasSkill(testProp));
    }

    @Test
    public void addBadProperty() throws Exception {
    }

    @Test
    public void removeBadProperty() throws Exception {
    }

    @Test
    public void getBadPropertySum() throws Exception {
    }

    @Test
    public void addLanguage() throws Exception {
    }

    @Test
    public void removeLanguage() throws Exception {
    }

    @Test
    public void hasSkill() throws Exception {
    }

    @Test
    public void hasProperty() throws Exception {
    }

    @Test
    public void hasLanguage() throws Exception {
    }

    @Test
    public void getSumOfPrimaryAttributes() throws Exception {
    }

    @Test
    public void getPrimaryAttribute() throws Exception {
    }

    @Test
    public void getMaxOfPrimaryAttribute() throws Exception {
    }

    @Test
    public void increasePrimaryAttribute() throws Exception {
    }

    @Test
    public void decrasePrimaryAttribute() throws Exception {
    }

}
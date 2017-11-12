package aventurian;

import org.junit.Before;
import org.junit.Test;

import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.*;
import static org.junit.Assert.*;

public class PrimaryAttributesTest {

    private PrimaryAttributes toTest;

    @Before
    public void setUp() throws Exception {
        this.toTest = new PrimaryAttributes();
    }

    @Test
    public void testGetSumDefault() throws Exception {
        int expected = 8 * 8;
        int actual = toTest.getSum();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetSum() throws Exception {
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        int expected = 8 * 8 + 1;
        int actual = toTest.getSum();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetPrimaryAttribute() throws Exception {
        int expected = 8;
        int actual = toTest.getPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        assertEquals(expected, actual);
    }

    @Test
    public void testIncrease() throws Exception {
        int expected = toTest.getPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE) + 1;
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        int actual = toTest.getPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void testIncreaseWithNull() throws Exception {
        toTest.increase(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testIncreaseWithExcedingMaximum() throws Exception {
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
    }

    @Test
    public void testDecrease() throws Exception {
        // Attributes start on minimum. -> increase first in order to be able to decrease again.
        int expected = toTest.getPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.decrease(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        int actual = toTest.getPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void testDecreaseWhileAlreadyOnMinimum() throws Exception {
        toTest.decrease(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
    }

    @Test
    public void testIncreaseMaximum() throws Exception {
        int expected = toTest.getMaximumOfPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE) + 1;
        toTest.increaseMaximum(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        int actual = toTest.getMaximumOfPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void testDecreaseMaximum() throws Exception {
        toTest.decreaseMaximum(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.decreaseMaximum(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.decreaseMaximum(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.decreaseMaximum(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.decreaseMaximum(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.decreaseMaximum(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.decreaseMaximum(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.decreaseMaximum(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.decreaseMaximum(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
    }

    @Test
    public void testGetMaximumOfPrimaryAttribute() throws Exception {
        int expected = 14;
        int actual = toTest.getMaximumOfPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        assertEquals(expected, actual);
    }
    
    @Test
    public void testIsIncreasable() throws Exception {
    	assertTrue(toTest.isIncreasable(COURAGE));
    	toTest.increase(COURAGE);
    	toTest.increase(COURAGE);
    	toTest.increase(COURAGE);
    	toTest.increase(COURAGE);
    	toTest.increase(COURAGE);
    	toTest.increase(COURAGE);
    	assertFalse(toTest.isIncreasable(COURAGE));
    }
    
    @Test
    public void testIsDecreasable() throws Exception{
    	assertFalse(toTest.isDecreasable(COURAGE));
    	toTest.increase(COURAGE);
    	assertTrue(toTest.isDecreasable(COURAGE));
    }

}
package aventurian;

import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PrimaryAttributesTest {

    private PrimaryAttributes toTest;

    @Before
    public void setUp() throws Exception {
        this.toTest = new PrimaryAttributes();
    }

    @Test
    public void testGetSumDefault() throws Exception {
        final int expected = 8 * 8;
        final int actual = toTest.getSum();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetSum() throws Exception {
        toTest.increase(COURAGE);
        final int expected = 8 * 8 + 1;
        final int actual = toTest.getSum();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetPrimaryAttribute() throws Exception {
        final int expected = 8;
        final int actual = toTest.getPrimaryAttribute(COURAGE);
        assertEquals(expected, actual);
    }

    @Test
    public void testIncrease() throws Exception {
        final int expected = toTest.getPrimaryAttribute(COURAGE) + 1;
        toTest.increase(COURAGE);
        final int actual = toTest.getPrimaryAttribute(COURAGE);
        assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void testIncreaseWithNull() throws Exception {
        toTest.increase(null);
    }

    @Test(expected = IllegalStateException.class)
    public void testIncreaseWithExcedingMaximum() throws Exception {
        toTest.increase(COURAGE);
        toTest.increase(COURAGE);
        toTest.increase(COURAGE);
        toTest.increase(COURAGE);
        toTest.increase(COURAGE);
        toTest.increase(COURAGE);
        toTest.increase(COURAGE);
        toTest.increase(COURAGE);
        toTest.increase(COURAGE);
        toTest.increase(COURAGE);
    }

    @Test
    public void testDecrease() throws Exception {
        // Attributes start on minimum. -> increase first in order to be able to decrease again.
        final int expected = toTest.getPrimaryAttribute(COURAGE);
        toTest.increase(COURAGE);
        toTest.decrease(COURAGE);
        final int actual = toTest.getPrimaryAttribute(COURAGE);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void testDecreaseWhileAlreadyOnMinimum() throws Exception {
        toTest.decrease(COURAGE);
    }

    @Test
    public void testIncreaseMaximum() throws Exception {
        final int expected = toTest.getMaximumOfPrimaryAttribute(COURAGE) + 1;
        toTest.increaseMaximum(COURAGE);
        final int actual = toTest.getMaximumOfPrimaryAttribute(COURAGE);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void testDecreaseMaximum() throws Exception {
        toTest.decreaseMaximum(COURAGE);
        toTest.decreaseMaximum(COURAGE);
        toTest.decreaseMaximum(COURAGE);
        toTest.decreaseMaximum(COURAGE);
        toTest.decreaseMaximum(COURAGE);
        toTest.decreaseMaximum(COURAGE);
        toTest.decreaseMaximum(COURAGE);
        toTest.decreaseMaximum(COURAGE);
        toTest.decreaseMaximum(COURAGE);
    }

    @Test
    public void testGetMaximumOfPrimaryAttribute() throws Exception {
        final int expected = 14;
        final int actual = toTest.getMaximumOfPrimaryAttribute(COURAGE);
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
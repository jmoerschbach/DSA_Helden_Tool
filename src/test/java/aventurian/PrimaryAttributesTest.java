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
        toTest.increase(COURAGE);
        int expected = 8 * 8 + 1;
        int actual = toTest.getSum();
        assertEquals(actual, expected);
    }

    @Test
    public void testGetPrimaryAttribute() throws Exception {
        int expected = 8;
        int actual = toTest.getPrimaryAttribute(COURAGE);
        assertEquals(expected, actual);
    }

    @Test
    public void testIncrease() throws Exception {
        int expected = toTest.getPrimaryAttribute(COURAGE) + 1;
        toTest.increase(COURAGE);
        int actual = toTest.getPrimaryAttribute(COURAGE);
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
        int expected = toTest.getPrimaryAttribute(COURAGE);
        toTest.increase(COURAGE);
        toTest.decrease(COURAGE);
        int actual = toTest.getPrimaryAttribute(COURAGE);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void testDecreaseWhileAlreadyOnMinimum() throws Exception {
        toTest.decrease(COURAGE);
    }

    @Test
    public void testIncreaseMaximum() throws Exception {
        int expected = toTest.getMaximumOfPrimaryAttribute(COURAGE) + 1;
        toTest.increaseMaximum(COURAGE);
        int actual = toTest.getMaximumOfPrimaryAttribute(COURAGE);
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
        int expected = 14;
        int actual = toTest.getMaximumOfPrimaryAttribute(COURAGE);
        assertEquals(expected, actual);
    }
    
//    @Test
//    public void testIsIncreasable() throws Exception {
//    	assertTrue(toTest.isIncreasable(COURAGE));
//    	toTest.increase(COURAGE);
//    	toTest.increase(COURAGE);
//    	toTest.increase(COURAGE);
//    	toTest.increase(COURAGE);
//    	toTest.increase(COURAGE);
//    	toTest.increase(COURAGE);
//    	assertFalse(toTest.isIncreasable(COURAGE));
//    }
//    
//    @Test
//    public void testIsDecreasable() throws Exception{
//    	assertFalse(toTest.isDecreasable(COURAGE));
//    	toTest.increase(COURAGE);
//    	assertTrue(toTest.isDecreasable(COURAGE));
//    }

}
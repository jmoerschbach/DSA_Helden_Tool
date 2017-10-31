package aventurian;

import org.junit.Before;
import org.junit.Test;
import sun.security.pkcs11.wrapper.CK_SSL3_KEY_MAT_OUT;

import static org.junit.Assert.*;

public class PrimaryAttributesTest {

    private PrimaryAttributes toTest;

    @Before
    public void setUp() throws Exception {
        this.toTest = new PrimaryAttributes();
    }

    @Test
    public void getSumDefault() throws Exception {
        int expected = 8 * 8;
        int actual = toTest.getSum();
        assertEquals(actual, expected);
    }

    @Test
    public void getSum() throws Exception {
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        int expected = 8 * 8 + 1;
        int actual = toTest.getSum();
        assertEquals(actual, expected);
    }

    @Test
    public void getPrimaryAttribute() throws Exception {
        int expected = 8;
        int actual = toTest.getPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        assertEquals(expected, actual);
    }

    @Test
    public void increase() throws Exception {
        int expected = toTest.getPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE) + 1;
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        int actual = toTest.getPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void increaseWithNull() throws Exception {
        toTest.increase(null);
    }

    @Test(expected = IllegalStateException.class)
    public void increaseWithExcedingMaximum() throws Exception {
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
    public void decrease() throws Exception {
        // Attributes start on minimum. -> increase first in order to be able to decrease again.
        int expected = toTest.getPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.increase(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        toTest.decrease(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        int actual = toTest.getPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void decreaseWhileAlreadyOnMinimum() throws Exception {
        toTest.decrease(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
    }

    @Test
    public void increaseMaximum() throws Exception {
        int expected = toTest.getMaximumOfPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE) + 1;
        toTest.increaseMaximum(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        int actual = toTest.getMaximumOfPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        assertEquals(expected, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void decreaseMaximum() throws Exception {
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
    public void getMaximumOfPrimaryAttribute() throws Exception {
        int expected = 14;
        int actual = toTest.getMaximumOfPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE);
        assertEquals(expected, actual);
    }

}
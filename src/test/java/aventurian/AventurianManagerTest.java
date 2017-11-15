package aventurian;

import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class AventurianManagerTest {
	AventurianManager toTest;
	Aventurian a;

	@Before
	public void setUp() throws Exception {
		a = mock(Aventurian.class);
		toTest = new AventurianManager(a);
	}

	@Test
	public void testIncreasePrimaryAttributeAllConditionsMet() {
		setUpMock(true, true, true);
		toTest.increasePrimaryAttribute(COURAGE);
		verify(a).increasePrimaryAttribute(COURAGE);
		verify(a).pay(anyInt());
	}

	private void setUpMock(boolean canPay, boolean currentSmallerThanMax,
			boolean sumSmallerThanMax) {
		when(a.canPay(anyInt())).thenReturn(canPay);
		when(a.getSumOfPrimaryAttributes()).thenReturn(
				sumSmallerThanMax ? AventurianManager.MAX_ATTRIBUTES_SUM - 1
						: AventurianManager.MAX_ATTRIBUTES_SUM);
		when(a.getMaxOfPrimaryAttribute(COURAGE)).thenReturn(
				PrimaryAttributes.MAX);
		when(a.getPrimaryAttribute(COURAGE)).thenReturn(
				currentSmallerThanMax ? PrimaryAttributes.DEFAULT
						: PrimaryAttributes.MAX);
	}

	@Test
	public void testIncreasePrimaryAttributeTooExpensive() {
		setUpMock(false, true, true);
		toTest.increasePrimaryAttribute(COURAGE);
		verify(a, never()).increasePrimaryAttribute(COURAGE);
		verify(a, never()).pay(anyInt());
	}

	@Test
	public void testIncreasePrimaryAttributeCurrentAtMaximum() {
		setUpMock(true, false, true);
		toTest.increasePrimaryAttribute(COURAGE);
		verify(a, never()).increasePrimaryAttribute(COURAGE);
		verify(a, never()).pay(anyInt());
	}

	@Test
	public void testIncreasePrimaryAttributeSumTooBig() {
		setUpMock(true, true, false);
		toTest.increasePrimaryAttribute(COURAGE);
		verify(a, never()).increasePrimaryAttribute(COURAGE);
		verify(a, never()).pay(anyInt());
	}
}

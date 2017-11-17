package aventurian;

import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import skills.Property;

public class AventurianManagerTest {
	AventurianManager toTest;
	Aventurian a;

	@Before
	public void setUp() throws Exception {
		a = mock(Aventurian.class);
		when(a.canPay(anyInt())).thenReturn(true);
		toTest = new AventurianManager(a);
	}

	@Test
	public void testIncreasePrimaryAttributeAllConditionsMet() {
		setUpMockForPrimaryAttributesTest(true, true, true);
		toTest.increasePrimaryAttribute(COURAGE);
		verify(a).increasePrimaryAttribute(COURAGE);
		verify(a).pay(anyInt());
	}

	private void setUpMockForPrimaryAttributesTest(boolean canPay,
			boolean currentSmallerThanMax, boolean sumSmallerThanMax) {
		when(a.canPay(anyInt())).thenReturn(canPay);
		when(a.getSumOfPrimaryAttributes()).thenReturn(
				sumSmallerThanMax ? AventurianManager.MAX_ATTRIBUTES_SUM - 1
						: AventurianManager.MAX_ATTRIBUTES_SUM);
		when(a.getMaxOfPrimaryAttribute(COURAGE)).thenReturn(
				PrimaryAttributes.MAX);
		when(a.getPrimaryAttribute(COURAGE)).thenReturn(
				currentSmallerThanMax ? PrimaryAttributes.MIN
						: PrimaryAttributes.MAX);
	}

	@Test
	public void testIncreasePrimaryAttributeTooExpensive() {
		setUpMockForPrimaryAttributesTest(false, true, true);
		toTest.increasePrimaryAttribute(COURAGE);
		verify(a, never()).increasePrimaryAttribute(COURAGE);
		verify(a, never()).pay(anyInt());
	}

	@Test
	public void testIncreasePrimaryAttributeCurrentAtMaximum() {
		setUpMockForPrimaryAttributesTest(true, false, true);
		toTest.increasePrimaryAttribute(COURAGE);
		verify(a, never()).increasePrimaryAttribute(COURAGE);
		verify(a, never()).pay(anyInt());
	}

	@Test
	public void testIncreasePrimaryAttributeSumTooBig() {
		setUpMockForPrimaryAttributesTest(true, true, false);
		toTest.increasePrimaryAttribute(COURAGE);
		verify(a, never()).increasePrimaryAttribute(COURAGE);
		verify(a, never()).pay(anyInt());
	}

	@Test
	public void testDecreasePrimaryAttributAlreadyAtMinimum() {
		when(a.getPrimaryAttribute(COURAGE)).thenReturn(PrimaryAttributes.MIN);
		toTest.decreasePrimaryAttribut(COURAGE);
		verify(a, never()).decrasePrimaryAttribute(COURAGE);

	}

	@Test
	public void testDecreasePrimaryAttributAllConditionsMet() {
		when(a.getPrimaryAttribute(COURAGE)).thenReturn(
				PrimaryAttributes.MIN + 1);
		toTest.decreasePrimaryAttribut(COURAGE);
		verify(a).decrasePrimaryAttribute(COURAGE);
		verify(a).refund(anyInt());

	}

	@Test
	public void testAddPropertyAdvantageAllConditionsMet() {
		Property p = createPropertyMock(true, true);

		toTest.addProperty(p);

		verify(a).add(p);
		verify(a).pay(anyInt());
		verify(p).gain(a);

	}

	@Test
	public void testAddPropertyAdvantageTooExpensive() {
		Property p = createPropertyMock(true, true);
		when(a.canPay(anyInt())).thenReturn(false);

		toTest.addProperty(p);

		verify(a, never()).add(p);
		verify(a, never()).pay(anyInt());
		verify(p, never()).gain(a);
	}

	private Property createPropertyMock(boolean isAllowed, boolean isAdvantage) {
		Property p = mock(Property.class);
		when(p.isAllowed(a)).thenReturn(isAllowed);
		when(p.isAdvantage()).thenReturn(isAdvantage);
		when(p.isDisadvantage()).thenReturn(!isAdvantage);
		return p;
	}

	@Test
	public void testAddPropertyAdvantageNotAllowed() {
		Property p = createPropertyMock(false, true);

		toTest.addProperty(p);

		verify(a, never()).add(p);
		verify(a, never()).pay(anyInt());
		verify(p, never()).gain(a);

	}

	@Test
	public void testAddPropertyDisdvantageAllConditionsMet() {
		Property p = createPropertyMock(true, false);

		toTest.addProperty(p);

		verify(a).add(p);
		verify(a).pay(anyInt());
		verify(p).gain(a);

	}
}

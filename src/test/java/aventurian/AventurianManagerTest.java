package aventurian;

import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import skills.BadProperty;
import skills.Language;
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

	private void setUpMockForPrimaryAttributesTest(boolean canPay, boolean currentSmallerThanMax,
			boolean sumSmallerThanMax) {
		when(a.canPay(anyInt())).thenReturn(canPay);
		when(a.getSumOfPrimaryAttributes()).thenReturn(
				sumSmallerThanMax ? AventurianManager.MAX_ATTRIBUTES_SUM - 1 : AventurianManager.MAX_ATTRIBUTES_SUM);
		when(a.getMaxOfPrimaryAttribute(COURAGE)).thenReturn(PrimaryAttributes.MAX);
		when(a.getPrimaryAttribute(COURAGE))
				.thenReturn(currentSmallerThanMax ? PrimaryAttributes.MIN : PrimaryAttributes.MAX);
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
		when(a.getPrimaryAttribute(COURAGE)).thenReturn(PrimaryAttributes.MIN + 1);
		toTest.decreasePrimaryAttribut(COURAGE);
		verify(a).decrasePrimaryAttribute(COURAGE);
		verify(a).refund(anyInt());

	}

	@Test
	public void testAddPropertyAdvantageAllConditionsMet() {
		final Property p = createPropertyMock(true, true);

		toTest.addProperty(p);

		verify(a).add(p);
		verify(a).pay(anyInt());
		verify(p).gain(a);
	}

	@Test
	public void testAddPropertyAdvantageTooExpensive() {
		final Property p = createPropertyMock(true, true);
		when(a.canPay(anyInt())).thenReturn(false);

		toTest.addProperty(p);

		verify(a, never()).add(p);
		verify(a, never()).pay(anyInt());
		verify(p, never()).gain(a);
	}

	@Test
	public void testAddPropertyAdvantageExceedsMaxPointsInAdvantages() {
		final Property p = createPropertyMock(true, true);
		when(a.canPay(anyInt())).thenReturn(true);
		when(a.getPointsInAdvantages()).thenReturn(AventurianManager.MAX_POINTS_IN_ADVANTAGES);

		toTest.addProperty(p);

		verify(a, never()).add(p);
		verify(a, never()).pay(anyInt());
		verify(p, never()).gain(a);
	}

	private Property createPropertyMock(boolean isAllowed, boolean isAdvantage) {
		final Property p = mock(Property.class);
		when(p.isAllowed(a)).thenReturn(isAllowed);
		when(p.isAdvantage()).thenReturn(isAdvantage);
		when(p.isDisadvantage()).thenReturn(!isAdvantage);
		when(p.getName()).thenReturn("testProperty");
		when(p.getCost()).thenReturn(200);
		return p;
	}

	@Test
	public void testAddPropertyAdvantageNotAllowed() {
		final Property p = createPropertyMock(false, true);

		toTest.addProperty(p);

		verify(a, never()).add(p);
		verify(a, never()).pay(anyInt());
		verify(p, never()).gain(a);

	}

	@Test
	public void testAddPropertyDisdvantageAllConditionsMet() {
		final Property p = createPropertyMock(true, false);

		toTest.addProperty(p);

		verify(a).add(p);
		verify(a).refund(anyInt());
		verify(p).gain(a);

	}

	@Test
	public void testAddPropertyDisadvantageExceedMaxPointsOutDisadvantages() {
		final Property p = createPropertyMock(true, false);
		when(a.getPointsOutDisadvantages()).thenReturn(AventurianManager.MAX_POINTS_OUT_DISADVANTAGES);

		toTest.addProperty(p);

		verify(a, never()).add(p);
		verify(a, never()).refund(anyInt());
		verify(p, never()).gain(a);
	}

	@Test(expected = IllegalStateException.class)
	public void testAddPropertyAlreadyHasSkill() {
		final Property p = createPropertyMock(true, true);
		when(a.hasSkill(p)).thenReturn(true);
		toTest.addProperty(p);

	}

	@Test
	public void testAddBadPropertyAllConditionsMet() {
		final BadProperty p = createBadPropertyMock(true);
		when(a.getBadPropertySum()).thenReturn(5);

		toTest.addBadProperty(p);

		verify(p).gain(a);
		verify(a).refund(anyInt());
		verify(a).add(p);
	}

	@Test
	public void testAddBadPropertyExceedingMaxPointsOutDisadvantages() {
		final BadProperty p = createBadPropertyMock(true);
		when(a.getPointsOutDisadvantages()).thenReturn(AventurianManager.MAX_POINTS_OUT_DISADVANTAGES);

		toTest.addBadProperty(p);

		verify(p, never()).gain(a);
		verify(a, never()).refund(anyInt());
		verify(a, never()).add(p);
	}

	private BadProperty createBadPropertyMock(boolean isAllowed) {
		final BadProperty p = mock(BadProperty.class);
		when(p.isAllowed(a)).thenReturn(isAllowed);
		when(p.getLevel()).thenReturn(5);
		when(p.getCost()).thenReturn(50);
		when(p.getName()).thenReturn("testBadProperty");
		return p;
	}

	@Test
	public void testAddBadPropertyNotAllowed() {
		final BadProperty p = createBadPropertyMock(false);
		when(a.getBadPropertySum()).thenReturn(5);

		toTest.addBadProperty(p);

		verify(p, never()).gain(a);
		verify(a, never()).pay(anyInt());
		verify(a, never()).add(p);
	}

	@Test
	public void testAddBadPropertyBadPropertySumTooHigh() {
		final BadProperty p = createBadPropertyMock(false);
		when(a.getBadPropertySum()).thenReturn(AventurianManager.MAX_BAD_PROPERTIES_SUM);

		toTest.addBadProperty(p);

		verify(p, never()).gain(a);
		verify(a, never()).pay(anyInt());
		verify(a, never()).add(p);
	}

	@Test(expected = IllegalStateException.class)
	public void testAddBadPropertyAlreadyHasSkill() {
		final BadProperty p = createBadPropertyMock(true);
		when(a.hasSkill(p)).thenReturn(true);
		toTest.addBadProperty(p);
	}

	@Test(expected = IllegalStateException.class)
	public void testRemoveBadPropertyDoesNotHaveSkill() {
		final BadProperty p = createBadPropertyMock(true);
		when(a.hasSkill(p)).thenReturn(false);

		toTest.removeBadProperty(p);
	}

	@Test
	public void testLanguageAllConditionsMet() {
		final Language l = createLanguageMock(true, true);
		when(a.canPay(anyInt())).thenReturn(true);
		toTest.addLanguage(l);

		verify(l).gain(a);
		verify(a).pay(anyInt());
		verify(a).add(l);
	}

	@Test(expected = IllegalStateException.class)
	public void testAddLanguageAlreadyHasSkill() {
		final Language l = createLanguageMock(true, true);
		when(a.hasSkill(l)).thenReturn(true);
		toTest.addLanguage(l);
	}

	@Test
	public void testAddLanguageTooExpensive() {
		final Language l = createLanguageMock(true, true);
		when(a.canPay(anyInt())).thenReturn(false);

		toTest.addLanguage(l);

		verify(a, never()).add(l);
		verify(a, never()).pay(anyInt());
		verify(l, never()).gain(a);
	}

	@Test
	public void testAddLanguageNotAllowed() {
		final Language l = createLanguageMock(false, true);
		when(a.canPay(anyInt())).thenReturn(true);

		toTest.addLanguage(l);

		verify(a, never()).add(l);
		verify(a, never()).pay(anyInt());
		verify(l, never()).gain(a);
	}

	@Test
	public void testRemoveBadProperty() {

		final BadProperty p = createBadPropertyMock(true);
		when(a.hasSkill(p)).thenReturn(true);

		toTest.removeBadProperty(p);

		verify(a).remove(p);
		verify(a).refund(anyInt());
		verify(p).lose(a);
	}

	@Test
	public void testRemoveIncreasedBadProperty() {

		final BadProperty p = createBadPropertyMock(true);
		when(p.isDecreasable()).thenReturn(true).thenReturn(true).thenReturn(false);
		when(a.hasSkill(p)).thenReturn(true);

		toTest.removeBadProperty(p);
		verify(p).decrease();
		verify(a).remove(p);
		verify(a, times(2)).refund(anyInt());
		verify(p).lose(a);
	}

	@Test(expected = IllegalStateException.class)
	public void testRemoveLanguageDoesNotHaveSkill() {
		final Language l = createLanguageMock(true, true);
		when(a.hasSkill(l)).thenReturn(false);

		toTest.removeLanguage(l);
	}

	private Language createLanguageMock(boolean isAllowed, boolean isIncreasable) {
		final Language l = mock(Language.class);
		when(l.getName()).thenReturn("testLanguage");
		when(l.isAllowed(a)).thenReturn(isAllowed);
		when(l.getLevel()).thenReturn(5);
		when(l.getLearningCost()).thenReturn(50);
		when(l.isIncreasable()).thenReturn(isIncreasable);
		when(l.isDecreasable()).thenReturn(false);

		return l;
	}

	@Test
	public void testRemoveLanguage() {

		final Language l = createLanguageMock(true, true);
		when(a.hasSkill(l)).thenReturn(true);

		toTest.removeLanguage(l);

		verify(a).remove(l);
		verify(a).refund(anyInt());
		verify(l).lose(a);
	}

	@Test
	public void testDecreaseLanguage() {
		final Language l = createLanguageMock(true, true);
		when(a.hasSkill(l)).thenReturn(true);
		when(l.isDecreasable()).thenReturn(true).thenReturn(true).thenReturn(false);

		toTest.decreaseLanguage(l);

		verify(l).decrease();
		verify(a).refund(anyInt());
		verify(a, never()).remove(l);
	}

	@Test(expected = IllegalStateException.class)
	public void testDecreaseLanguageNotOwned() {
		final Language l = createLanguageMock(true, true);
		when(l.isDecreasable()).thenReturn(true);
		when(a.hasSkill(l)).thenReturn(false);
		toTest.decreaseLanguage(l);
	}

	@Test(expected = IllegalStateException.class)
	public void testDecreaseLanguageNotDecreasable() {
		final Language l = createLanguageMock(true, true);
		when(a.hasSkill(l)).thenReturn(true);

		toTest.decreaseLanguage(l);
	}

	@Test
	public void testRemoveIncreasedLanguage() {

		final Language l = createLanguageMock(true, true);
		when(a.hasSkill(l)).thenReturn(true);
		when(l.isDecreasable()).thenReturn(true).thenReturn(true).thenReturn(false);

		toTest.removeLanguage(l);
		verify(l).decrease();
		verify(a).remove(l);
		verify(a, times(2)).refund(anyInt());
		verify(l).lose(a);
	}

	@Test
	public void testIncreaseLanguageAllConditionsMet() {
		final Language l = createLanguageMock(true, true);
		when(a.hasSkill(l)).thenReturn(true);
		when(a.canPay(anyInt())).thenReturn(true);

		toTest.increaseLanguage(l);

		verify(a).pay(anyInt());
		verify(l).increase();
	}

	@Test
	public void testIncreaseLanguageNotAllowed() {
		final Language l = createLanguageMock(false, true);
		when(a.hasSkill(l)).thenReturn(true);
		when(a.canPay(anyInt())).thenReturn(true);

		toTest.increaseLanguage(l);

		verify(a, never()).pay(anyInt());
		verify(l, never()).increase();
	}

	@Test
	public void testIncreaseLanguageNotIncreasable() {
		final Language l = createLanguageMock(true, false);
		when(a.hasSkill(l)).thenReturn(true);
		when(a.canPay(anyInt())).thenReturn(true);

		toTest.increaseLanguage(l);

		verify(a, never()).pay(anyInt());
		verify(l, never()).increase();
	}

	@Test
	public void testIncreaseLanguageTooExpensive() {
		final Language l = createLanguageMock(true, true);
		when(a.hasSkill(l)).thenReturn(true);
		when(a.canPay(anyInt())).thenReturn(false);

		toTest.increaseLanguage(l);

		verify(a, never()).pay(anyInt());
		verify(l, never()).increase();
	}

	@Test(expected = IllegalStateException.class)
	public void testIncreaseLanguageDoesNotHaveSkill() {
		final Language l = createLanguageMock(true, true);
		when(a.hasSkill(l)).thenReturn(false);
		when(a.canPay(anyInt())).thenReturn(true);

		toTest.increaseLanguage(l);

	}

	@Test
	public void testDecreaseBadProperty() {
		final BadProperty bp = createBadPropertyMock(true);
		when(a.hasSkill(bp)).thenReturn(true);
		when(bp.isDecreasable()).thenReturn(true).thenReturn(true).thenReturn(false);

		toTest.decreaseBadProperty(bp);

		verify(bp).decrease();
		verify(a).refund(anyInt());
		verify(a, never()).remove(bp);
	}

	@Test(expected = IllegalStateException.class)
	public void testDecreaseBadPropertyNotOwned() {
		final BadProperty bp = createBadPropertyMock(true);
		when(bp.isDecreasable()).thenReturn(true);
		when(a.hasSkill(bp)).thenReturn(false);
		toTest.decreaseBadProperty(bp);
	}

	@Test(expected = IllegalStateException.class)
	public void testDecreaseBadPropertyNotDecreasable() {
		final BadProperty bp = createBadPropertyMock(true);
		when(a.hasSkill(bp)).thenReturn(true);

		toTest.decreaseBadProperty(bp);
	}

}

package ui;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import ui.LeftController.PAGES;

@RunWith(MockitoJUnitRunner.class)
public class AttributePaneTest extends BaseGuiTest {

	@Override
	@Before
	public void setUp() {
		final Hyperlink attributes = find("#hyperlinkAttributes");
		clickOn(attributes);
		verifyThat("#paneAttributes", isVisible());
	}

	@Test
	public void testIncreaseCourage() {
		final Button incCourage = find("#btnIncreaseCourage");
		clickOn(incCourage);
		verify(mockedAventurianManager).increasePrimaryAttribute(PRIMARY_ATTRIBUTE.COURAGE);
	}

	@Test
	public void testDecreaseCourage() {
		final Button decCourage = find("#btnDecreaseCourage");
		clickOn(decCourage);
		verify(mockedAventurianManager).decreasePrimaryAttribute(PRIMARY_ATTRIBUTE.COURAGE);
	}

	
	@Test
	public void testUpdate() {
		// Platform.runLater(() -> {
		Mockito.when(mockedAventurian.getPrimaryAttribute(PRIMARY_ATTRIBUTE.COURAGE)).thenReturn(10);
		getControllerOfPage(PAGES.ATTRIBUTES).update(mockedAventurian);
		final Label lblCourage = find("#labelCourage");
		assertEquals(String.valueOf(10), lblCourage.getText());
		// });
	}

}

package ui;

import static org.mockito.Mockito.verify;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import org.junit.Before;
import org.junit.Test;

import aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class AttributePaneTest extends BaseGuiTest {

	public AttributePaneTest() {
		// TODO Auto-generated constructor stub
	}

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

}

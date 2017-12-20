package ui;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class LanguagePaneTest extends BaseGuiTest {
	
	
	@Override
	@Before
	public void setUp() {
		final Hyperlink languages = find("#hyperlinkLanguages");
		clickOn(languages);
		verifyThat("#paneLanguages", isVisible());
	}

	@Test
	public void testAssignLanguage() {
		final Button assignLanguage = find("#btnAssignLanguage");
		clickOn(assignLanguage);
		verify(mockedAventurianManager).addLanguage(Mockito.anyObject());
	}

}

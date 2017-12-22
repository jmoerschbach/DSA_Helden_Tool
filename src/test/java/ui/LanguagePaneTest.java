package ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.testfx.matcher.base.NodeMatchers;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import skills.Language;

@RunWith(MockitoJUnitRunner.class)
public class LanguagePaneTest extends BaseGuiTest {

	@Before
	public void setUp() {
		final Hyperlink languages = find("#hyperlinkLanguages");
		clickOn(languages);
		verifyThat("#paneLanguages", isVisible());
	}

	@Test
	public void testAssignLanguage() {
		verifyThat("Garethi", NodeMatchers.isNotNull());
		clickOn("Garethi");
		final Button assignLanguage = find("#btnAssignLanguage");
		clickOn(assignLanguage);
		verify(mockedAventurianManager).addLanguage(Mockito.any(Language.class));
	}

	@Test
	public void testAssignLanguageButtonIsDisabled() {
		final ListView<Language> allLanguages = find("#allLanguages");
		assertTrue(allLanguages.getSelectionModel().isEmpty());
		final Button assignLanguage = find("#btnAssignLanguage");
		assertTrue(assignLanguage.isDisable());
	}

	@Ignore
	@Test
	public void testToggleAssignButtonEnabledDisabled() {
		testAssignLanguageButtonIsDisabled();
		testAssignLanguageButtonIsEnabled();
		press(KeyCode.CONTROL);
		clickOn("Garethi");
		release(KeyCode.CONTROL);
		final Button assignLanguage = find("#btnAssignLanguage");
		assertTrue(assignLanguage.isDisable());
	}

	@Test
	public void testAssignLanguageButtonIsEnabled() {
		verifyThat("Garethi", NodeMatchers.isNotNull());
		clickOn("Garethi");
		final ListView<Language> allLanguages = find("#allLanguages");
		assertFalse(allLanguages.getSelectionModel().isEmpty());
		final Button assignLanguage = find("#btnAssignLanguage");
		assertFalse(assignLanguage.isDisable());
	}

	@Test
	public void testUpdate() {
		final ListView<Language> lv = find("#assignedLanguages");
		assertEquals(3, lv.getItems().size());
	}

	@Override
	void setUpMocks() {
		// Database
		final Language l1 = mock(Language.class);
		when(l1.toString()).thenReturn("Garethi");
		final Language l2 = mock(Language.class);
		when(l2.toString()).thenReturn("blablub");
		when(mockedDatabase.getLanguages()).thenReturn(Arrays.asList(l1, l2));

		// Aventurian
		final List<Language> l = Arrays.asList(mock(Language.class), mock(Language.class), mock(Language.class));
		when(mockedAventurian.getLanguages()).thenReturn(l);
	}

}

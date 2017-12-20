package ui;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import skills.Language;
import ui.LeftController.PAGES;

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
	
	@Test
	public void testUpdate() {
		// Platform.runLater(() -> {
		final List<Language> l = new ArrayList<>();
		l.add(Mockito.mock(Language.class));
		l.add(Mockito.mock(Language.class));
		l.add(Mockito.mock(Language.class));
		Mockito.when(mockedAventurian.getLanguages()).thenReturn(l);
		getControllerOfPage(PAGES.LANGUAGES).update(mockedAventurian);
		final ListView lv = find("#assignedLanguages");
		assertEquals(3, lv.getItems().size());
		// });
	}

}

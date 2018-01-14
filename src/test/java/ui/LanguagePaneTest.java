package ui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import java.util.Arrays;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.testfx.matcher.base.NodeMatchers;

import aventurian.Aventurian;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import skills.Language;

@RunWith(MockitoJUnitRunner.class)
public class LanguagePaneTest extends BaseGuiTest {

	private static final String ID_BTN_ASSIGN_LANGUAGE = "#btnAssignLanguage";
	private static final String ID_BTN_UN_ASSIGN_LANGUAGE = "#btnUnAssignLanguage";
	private static final String ID_BTN_NATIVE_TONGUE = "#btnNativeTongue";
	private static final String ID_LV_ASSIGNED_LANGUAGES = "#lvAssignedLanguages";
	private static final String ID_LV_UN_ASSIGNED_LANGUAGES = "#lvUnAssignedLanguages";

	private static final String LANGUAGE_NAME_ASSIGNED = "assigned language";
	private static final String LANGUAGE_NAME_BLABLUB = "blablub";
	private static final String LANGUAGE_NAME_GARETHI = "Garethi";
	private static final String LANGUAGE_NAME_NATIVETONGUE = "Native Tongue";

	@Before
	public void setUp() {
		clickOn("#hyperlinkLanguages");
		verifyThat("#paneLanguages", isVisible());
	}

	@Test
	public void testAssignLanguage() {
		verifyThat(LANGUAGE_NAME_GARETHI, NodeMatchers.isNotNull());
		clickOn(LANGUAGE_NAME_GARETHI).clickOn(ID_BTN_ASSIGN_LANGUAGE);
		verify(mockedAventurianManager).addLanguage(any(Language.class));
	}

	@Test
	public void testAssignLanguageAsNativeTongue() {
		verifyThat(LANGUAGE_NAME_GARETHI, NodeMatchers.isNotNull());
		final Button btn = find(ID_BTN_NATIVE_TONGUE);
		assertFalse(btn.isDisable());
		clickOn(btn);
		verify(mockedAventurianManager).addLanguageAsNativeTongue(any(Language.class));

	}

	@Test
	public void testNativeTongueButtonsAreDisabled() throws InterruptedException {
		final Set<Button> nativeTongueButtons = findAll(ID_BTN_NATIVE_TONGUE);
		nativeTongueButtons.forEach(b -> assertFalse(b.isDisable()));
		when(mockedAventurian.hasNativeTongue()).thenReturn(true);
		updateGui();
		nativeTongueButtons.forEach(b -> assertTrue(b.isDisable()));
	}

	@Test
	public void testAssignLanguageViaDoubleClick() {
		verifyThat(LANGUAGE_NAME_GARETHI, NodeMatchers.isNotNull());
		doubleClickOn(LANGUAGE_NAME_GARETHI, MouseButton.PRIMARY);
		verify(mockedAventurianManager).addLanguage(any(Language.class));
	}

	@Test
	public void testUnAssignLanguage() {
		when(mockedAventurian.getLanguages()).thenReturn(Arrays.asList(createLanguage(LANGUAGE_NAME_ASSIGNED)));
		updateGui();

		verifyThat(LANGUAGE_NAME_ASSIGNED, NodeMatchers.isNotNull());
		clickOn(LANGUAGE_NAME_ASSIGNED).clickOn(ID_BTN_UN_ASSIGN_LANGUAGE);
		verify(mockedAventurianManager).removeLanguage(any(Language.class));
	}

	@Test
	public void testUnAssignLanguageViaDoubleClick() {
		when(mockedAventurian.getLanguages()).thenReturn(Arrays.asList(createLanguage(LANGUAGE_NAME_ASSIGNED)));
		updateGui();

		verifyThat(LANGUAGE_NAME_ASSIGNED, NodeMatchers.isNotNull());
		doubleClickOn(LANGUAGE_NAME_ASSIGNED);
		verify(mockedAventurianManager).removeLanguage(any(Language.class));
	}

	@Test
	public void testAssignLanguageButtonIsDisabled() {
		final ListView<Language> allLanguages = find(ID_LV_UN_ASSIGNED_LANGUAGES);
		assertTrue(allLanguages.getSelectionModel().isEmpty());
		verifyThat(ID_BTN_ASSIGN_LANGUAGE, (Button b) -> b.isDisable());
	}

	@Test
	public void testUnAssignLanguageButtonIsDisabled() {
		final ListView<Language> allLanguages = find(ID_LV_ASSIGNED_LANGUAGES);
		assertTrue(allLanguages.getSelectionModel().isEmpty());
		verifyThat(ID_BTN_UN_ASSIGN_LANGUAGE, (Button b) -> b.isDisable());
	}

	@Test
	public void testUnAssignLanguageButtonIsEnabled() {
		when(mockedAventurian.getLanguages()).thenReturn(Arrays.asList(createLanguage(LANGUAGE_NAME_ASSIGNED)));
		updateGui();

		verifyThat(LANGUAGE_NAME_ASSIGNED, NodeMatchers.isNotNull());
		clickOn(LANGUAGE_NAME_ASSIGNED);
		final ListView<Language> assignedLanguages = find(ID_LV_ASSIGNED_LANGUAGES);
		assertFalse(assignedLanguages.getSelectionModel().isEmpty());
		verifyThat(ID_BTN_UN_ASSIGN_LANGUAGE, (Button b) -> !b.isDisable());
	}

	@Category(CannotRunHeadless.class)
	@Test
	public void testToggleAssignButtonEnabledDisabled() {
		testAssignLanguageButtonIsDisabled();
		testAssignLanguageButtonIsEnabled();
		press(KeyCode.CONTROL).clickOn(LANGUAGE_NAME_GARETHI).release(KeyCode.CONTROL);
		verifyThat(ID_BTN_ASSIGN_LANGUAGE, (Button b) -> b.isDisable());
	}

	@Category(CannotRunHeadless.class)
	@Test
	public void testToggleUnAssignButtonEnabledDisabled() {
		testUnAssignLanguageButtonIsDisabled();
		testUnAssignLanguageButtonIsEnabled();
		press(KeyCode.CONTROL).clickOn(LANGUAGE_NAME_ASSIGNED).release(KeyCode.CONTROL);
		verifyThat(ID_BTN_UN_ASSIGN_LANGUAGE, (Button b) -> b.isDisable());
	}

	@Test
	public void testAssignLanguageButtonIsEnabled() {
		verifyThat(LANGUAGE_NAME_GARETHI, NodeMatchers.isNotNull());
		clickOn(LANGUAGE_NAME_GARETHI);
		final ListView<Language> allLanguages = find(ID_LV_UN_ASSIGNED_LANGUAGES);
		assertFalse(allLanguages.getSelectionModel().isEmpty());
		verifyThat(ID_BTN_ASSIGN_LANGUAGE, (Button b) -> !b.isDisable());
	}

	@Test
	public void testIncreaseLanguageLevel() {
		when(mockedAventurian.getLanguages()).thenReturn(Arrays.asList(createLanguage(LANGUAGE_NAME_ASSIGNED)));
		updateGui();

		verifyThat(LANGUAGE_NAME_ASSIGNED, NodeMatchers.isNotNull());
		final Button btn = find("+");
		assertFalse(btn.isDisable());
		clickOn(btn);
		verify(mockedAventurianManager).increaseLanguage(any(Language.class));
	}

	@Test
	public void testListAreDisjunct() {
		when(mockedAventurian.getLanguages()).thenReturn(Arrays.asList(createLanguage(LANGUAGE_NAME_ASSIGNED)));
		updateGui();

		final ListView<Language> lvAssigned = find(ID_LV_ASSIGNED_LANGUAGES);
		final ListView<Language> lvUnAssigned = find(ID_LV_UN_ASSIGNED_LANGUAGES);
		final ObservableList<Language> assignedLanguages = lvAssigned.getItems();
		final ObservableList<Language> unAssignedLanguages = lvUnAssigned.getItems();

		assertTrue(assignedLanguages.stream().noneMatch(l -> unAssignedLanguages.contains(l)));
		assertTrue(unAssignedLanguages.stream().noneMatch(l -> assignedLanguages.contains(l)));

	}

	@Override
	void setUpMocks() {
		// Database
		final Language l1 = createLanguage(LANGUAGE_NAME_GARETHI);
		final Language l2 = createLanguage(LANGUAGE_NAME_BLABLUB);
		final Language l3 = createLanguage(LANGUAGE_NAME_ASSIGNED);
		when(mockedDatabase.getLanguages()).thenReturn(Arrays.asList(l1, l2, l3));
	}

	private static Language createLanguage(String name) {
		// mocking of languages not possible, because for some tests, we need real
		// implementation of "equals()" which cannot be mocked
		return new Language(name, "", (Aventurian a) -> true, 5, 100);
	}

}
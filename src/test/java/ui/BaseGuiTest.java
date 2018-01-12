package ui;

import static ui.NavigationPaneController.PAGES.ATTRIBUTES;
import static ui.NavigationPaneController.PAGES.LANGUAGES;

import java.io.IOException;

import org.mockito.Mock;
import org.testfx.framework.junit.ApplicationTest;

import aventurian.Aventurian;
import aventurian.AventurianManager;
import database.Database;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.NavigationPaneController.PAGES;

/**
 * This is the base class for UI Unit Tests. Every testcase must extend it. This
 * class starts up the actual GUI and provides mocks that can be configured and
 * used by actual ui tests. <br>
 * Have a look at {@link LanguagePaneTest} or {@link AttributePaneTest}.
 * 
 * @author Jonas
 *
 */
public abstract class BaseGuiTest extends ApplicationTest {

	protected MainController mainController;
	@Mock
	protected AventurianManager mockedAventurianManager;

	@Mock
	protected Aventurian mockedAventurian;

	@Mock
	protected Database mockedDatabase;

	@Override
	public void init() {
		setUpMocks();
	}

	@Override
	public void start(Stage stage) throws Exception {
		final Parent root = loadMainPane();

		loadPage(LANGUAGES, "/languages.fxml");
		loadPage(ATTRIBUTES, "/attributes.fxml");

		mainController.init(mockedAventurianManager, mockedDatabase);
		mockedAventurian.addObserver(mainController);
		mainController.update(mockedAventurian, null);

		stage.setScene(new Scene(root));
		stage.show();
		/*
		 * Do not forget to put the GUI in front of windows. Otherwise, the robots may
		 * interact with another window, the one in front of all the windows...
		 */
		stage.toFront();
	}

	private Parent loadMainPane() throws IOException {
		final FXMLLoader loader = new FXMLLoader(ui.Main.class.getResource("/main.fxml"));
		final Parent root = loader.load();
		mainController = loader.getController();
		return root;
	}

	private void loadPage(PAGES p, String fxml) throws IOException {
		final FXMLLoader l = new FXMLLoader(ui.MainController.class.getResource(fxml));
		final Parent bla = l.load();
		final PaneController blub = l.getController();
		mainController.addLoadedPage(p, blub, bla);
	}

	public <T extends Node> T find(final String query) {

		// TestFX provides many operations to retrieve elements from the loaded GUI.
		return lookup(query).query();
	}

	/**
	 * Implement to configure the behaviour of the mocks the actual testcase relies
	 * on. Typically, you would setup {@link #mockedDatabase} and
	 * {@link #mockedAventurian} and verify on {@link #mockedAventurianManager}.
	 * <br>
	 * Have a look at {@link LanguagePaneTest}.
	 */
	abstract void setUpMocks();
}

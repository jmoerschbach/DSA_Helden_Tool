package ui;

import static ui.LeftController.PAGES.ATTRIBUTES;
import static ui.LeftController.PAGES.LANGUAGES;

import java.io.IOException;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.testfx.framework.junit.ApplicationTest;

import aventurian.Aventurian;
import aventurian.AventurianManager;
import database.Database;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.LeftController.PAGES;

@RunWith(MockitoJUnitRunner.class)
public class BaseGuiTest extends ApplicationTest {

	private MainController mainController;
	@Mock
	protected AventurianManager mockedAventurianManager;

	@Mock
	protected Aventurian mockedAventurian;

	/* This operation comes from ApplicationTest and loads the GUI to test. */
	@Override
	public void start(Stage stage) throws Exception {
		Database.getInstance().initialize();
		// mockedAventurianManager = mock(AventurianManager.class);
		// mockedAventurian = mock(Aventurian.class);

		final Parent root = loadMainPane();

		loadPage(LANGUAGES, "/languages.fxml");
		loadPage(ATTRIBUTES, "/attributes.fxml");

		mainController.init(mockedAventurianManager);
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
		final XController blub = l.getController();
		mainController.addLoadedPage(p, blub, bla);
	}

	XController getControllerOfPage(PAGES p) {
		return mainController.getControllerOfPage(p);
	}

	@Before
	public void setUp() {
		/* Just retrieving the tested widgets from the GUI. */
	}

	/* Just a shortcut to retrieve widgets in the GUI. */
	public <T extends Node> T find(final String query) {
		/**
		 * TestFX provides many operations to retrieve elements from the loaded GUI.
		 */
		return lookup(query).query();
	}
}

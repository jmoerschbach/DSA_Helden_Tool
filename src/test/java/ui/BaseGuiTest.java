package ui;

import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.testfx.framework.junit.ApplicationTest;

import aventurian.AventurianManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BaseGuiTest extends ApplicationTest {

	private Parent mainNode;

	private MainController mainController;

	protected AventurianManager mockedAventurianManager;


	/* This operation comes from ApplicationTest and loads the GUI to test. */
	@Override
	public void start(Stage stage) throws Exception {
		mockedAventurianManager = mock(AventurianManager.class);
		final FXMLLoader l = new FXMLLoader(MainController.class.getResource("/main.fxml"));
		mainNode = l.load();
		mainController = l.getController();
		mainController.init(mockedAventurianManager);
		stage.setScene(new Scene(mainNode));
		stage.show();
		/*
		 * Do not forget to put the GUI in front of windows. Otherwise, the robots may
		 * interact with another window, the one in front of all the windows...
		 */
		stage.toFront();
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

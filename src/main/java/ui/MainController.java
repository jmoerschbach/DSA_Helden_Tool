package ui;

import static ui.LeftController.PAGES.ATTRIBUTES;
import static ui.LeftController.PAGES.LANGUAGES;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import aventurian.Aventurian;
import aventurian.AventurianManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import ui.LeftController.PAGES;

public class MainController implements Observer {

	private final List<XController> centerControllers;
	private final Map<PAGES, Parent> centerPages;

	public MainController() {
		centerControllers = new ArrayList<>();
		centerPages = new HashMap<>();
	}

	@FXML
	Parent top;
	@FXML
	TopController topController;

	@FXML
	Parent left;
	@FXML
	LeftController leftController;

	@FXML
	Pane centerPane;


	public void init(AventurianManager manager) {
		try {
			loadPage(LANGUAGES, "/languages.fxml");
			loadPage(ATTRIBUTES, "/attributes.fxml");

			leftController.init(manager, this);
			topController.init(manager);
			centerControllers.forEach(c -> c.init(manager));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private void loadPage(PAGES p, String fxml) throws IOException {
		final FXMLLoader l = new FXMLLoader(ui.MainController.class.getResource(fxml));
		centerPages.put(p, l.load());
		centerControllers.add(l.getController());
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Aventurian) {
			System.out.println("updating...");
			final Aventurian updatedAventurian = (Aventurian) o;
			leftController.update(updatedAventurian);
			topController.update(updatedAventurian);
			centerControllers.forEach(c -> c.update(updatedAventurian));
		}

	}

	void changeTo(PAGES page) {
		centerPane.getChildren().clear();
		centerPane.getChildren().add(centerPages.get(page));

	}

}

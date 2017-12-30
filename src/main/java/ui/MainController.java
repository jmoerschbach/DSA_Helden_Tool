package ui;

import static ui.LeftController.PAGES.ATTRIBUTES;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import aventurian.Aventurian;
import aventurian.AventurianManager;
import database.Database;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import ui.LeftController.PAGES;

public class MainController extends XController implements Observer {

	private final Map<PAGES, XController> centerControllers;
	private final Map<PAGES, Parent> centerPages;

	public MainController() {
		centerControllers = new HashMap<>();
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
	RightController rightController;

	@FXML
	Pane centerPane;

	@Override
	public void init(AventurianManager manager, Database db) {
		this.m = manager;
		leftController.init(this);
		topController.init(manager, db);
		rightController.init(manager, db);
		centerControllers.values().forEach(c -> c.init(manager, db));

		changeTo(ATTRIBUTES);
	}

	void addLoadedPage(PAGES p, XController c, Parent page) {
		centerPages.put(p, page);
		centerControllers.put(p, c);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Aventurian) {
			update((Aventurian) o);
		}

	}

	void changeTo(PAGES page) {
		centerPane.getChildren().clear();
		centerPane.getChildren().add(centerPages.get(page));

	}

	XController getControllerOfPage(PAGES p) {
		return centerControllers.get(p);
	}

	@Override
	void update(Aventurian updatedAventurian) {
		leftController.update(updatedAventurian);
		topController.update(updatedAventurian);
		rightController.update(updatedAventurian);
		centerControllers.values().forEach(c -> c.update(updatedAventurian));

	}

	@Override
	void initControllerSpecificStuff() {
		// TODO Auto-generated method stub

	}

}

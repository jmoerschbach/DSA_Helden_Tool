package ui;

import static ui.LeftController.PAGES.ATTRIBUTES;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBException;

import aventurian.Aventurian;
import aventurian.AventurianManager;
import database.Database;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
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
		m.registerObserver(this);

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

	public void open() {
		final FileChooser fileChooser = new FileChooser();

		final FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);

		final File file = fileChooser.showOpenDialog(top.getScene().getWindow());

		if (file != null) {
			m.loadAventurian(file);
		}
	}

	public void save() {
		if (getFilePath().isPresent())
			try {
				m.saveAventurian(getFilePath().map(File::new).get());
			} catch (final JAXBException e) {
				e.printStackTrace();
			}
		else
			saveAs();
	}

	public void saveAs() {
		try {
			final FileChooser fileChooser = new FileChooser();

			final FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
			fileChooser.getExtensionFilters().add(extFilter);

			File file = fileChooser.showSaveDialog(top.getScene().getWindow());

			if (file != null) {
				if (!file.getPath().endsWith(".xml")) {
					file = new File(file.getPath() + ".xml");
				}
				m.saveAventurian(file);
				saveFilePath(file);
			}
		} catch (final JAXBException e) {
			e.printStackTrace();
		}
	}

	private Optional<String> getFilePath() {
		final Preferences prefs = Preferences.userNodeForPackage(MainController.class);
		return Optional.ofNullable(prefs.get("filePath", null));
	}

	private void saveFilePath(File file) {
		final Preferences prefs = Preferences.userNodeForPackage(MainController.class);
		prefs.put("filePath", file.getPath());

	}
}

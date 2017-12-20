package ui;

import static ui.LeftController.PAGES.ATTRIBUTES;
import static ui.LeftController.PAGES.LANGUAGES;

import java.io.IOException;

import aventurian.Aventurian;
import aventurian.AventurianManager;
import database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.LeftController.PAGES;

public class Starter extends Application {
	private MainController mC;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		Database.getInstance().initialize();
		final Parent root = loadMainPane();

		loadPage(LANGUAGES, "/languages.fxml");
		loadPage(ATTRIBUTES, "/attributes.fxml");

		final Aventurian aventurian = new Aventurian("testAventurian", 16500);
		mC.init(new AventurianManager(aventurian));
		aventurian.addObserver(mC);
		mC.update(aventurian, null);

		final Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

	private Parent loadMainPane() throws IOException {
		final FXMLLoader loader = new FXMLLoader(ui.Main.class.getResource("/main.fxml"));
		final Parent root = loader.load();
		mC = loader.getController();
		return root;
	}

	private void loadPage(PAGES p, String fxml) throws IOException {
		final FXMLLoader l = new FXMLLoader(ui.MainController.class.getResource(fxml));
		final Parent bla = l.load();
		final XController blub = l.getController();
		mC.addLoadedPage(p, blub, bla);
	}

}

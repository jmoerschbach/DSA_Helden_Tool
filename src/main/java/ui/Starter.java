package ui;

import aventurian.Aventurian;
import aventurian.AventurianManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		final FXMLLoader loader = new FXMLLoader(ui.Main.class.getResource("/main.fxml"));
		final Parent root = loader.load();

		final MainController mC = loader.getController();
		final Aventurian aventurian = new Aventurian("testAventurian", 16500);
		mC.init(new AventurianManager(aventurian));
		aventurian.addObserver(mC);
		mC.update(aventurian, null);

		final Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

	}

}

package ui;

import aventurian.Aventurian;
import aventurian.AventurianManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	final FXMLLoader loader = new FXMLLoader(ui.Main.class.getResource("/UserInterface.fxml"));
        final Parent root = loader.load();
        final Controller controller = loader.getController();
        final Aventurian aventurian = new Aventurian("test", 16500);
        controller.initialize(new AventurianManager());
        aventurian.addObserver(controller);
        primaryStage.setTitle("DSA Helden Tool");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

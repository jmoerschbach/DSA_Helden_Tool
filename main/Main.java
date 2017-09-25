package main;

import UI.UIMain;
import UI.UserInterface.Controller;
import character.Character;
import javafx.application.Application;
import javafx.stage.Stage;

import static steigerrechner.Steigerrechner.initializeKostenTabelle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        initializeKostenTabelle();
        UIMain.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

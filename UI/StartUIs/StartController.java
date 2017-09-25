package UI.StartUIs;

import UI.UIMain;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by Hauke on 07.09.2017.
 */
public class StartController {

    public Stage stage;

    public Button neu;
    public Button laden;

    public void neu() throws Exception {
        stage.close();
        UIMain.neu();
    }

    public void laden() throws Exception {
        UIMain.laden();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

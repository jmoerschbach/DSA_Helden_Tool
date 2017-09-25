package UI.StartUIs;

import UI.UIMain;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Hauke on 07.09.2017.
 */
public class APAuswahlController {

    public Stage stage;

    public Button start;
    public TextField nameField;
    public TextField apField;


    public void start() throws Exception {
        try {
            String name = nameField.getText();
            int ap = Integer.parseInt(apField.getText());
            UIMain.startNewCharakter(name, ap);
            stage.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextField getApField() {
        return apField;
    }
}

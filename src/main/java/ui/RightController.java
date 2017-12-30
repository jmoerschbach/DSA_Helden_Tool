package ui;

import aventurian.Aventurian;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RightController extends XController {
	@FXML
	public Label labelRemainingActionPoints;

	@Override
	void update(Aventurian updatedAventurian) {
		labelRemainingActionPoints.setText(String.valueOf(updatedAventurian.getAdventurePoints()));

	}

	@Override
	void initControllerSpecificStuff() {
		// TODO Auto-generated method stub
	}

}

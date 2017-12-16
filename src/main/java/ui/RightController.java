package ui;

import aventurian.Aventurian;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RightController extends XController {
	@FXML
	public Label labelRemainingActionPoints;

	public RightController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	void update(Aventurian updatedAventurian) {
		labelRemainingActionPoints.setText(String.valueOf(updatedAventurian.getAdventurePoints()));

	}

}

package ui;

import aventurian.Aventurian;
import aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AttributesController extends XController {

	@FXML
	Label labelCourage;

	public AttributesController() {
		// TODO Auto-generated constructor stub
	}

	public void increaseCourage() {
		m.increasePrimaryAttribute(PRIMARY_ATTRIBUTE.COURAGE);
	}

	@Override
	public void update(Aventurian updatedAventurian) {
		labelCourage.setText(updatedAventurian.getPrimaryAttribute(PRIMARY_ATTRIBUTE.COURAGE) + "");

	}
}

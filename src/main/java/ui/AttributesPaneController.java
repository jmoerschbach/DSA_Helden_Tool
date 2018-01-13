package ui;

import aventurian.Aventurian;
import aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AttributesPaneController extends PaneController {

	@FXML
	Label labelCourage;
	@FXML
	Label labelIntelligence;
	@FXML
	Label labelIntuition;
	@FXML
	Label labelCharisma;
	@FXML
	Label labelDexterity;
	@FXML
	Label labelAgility;
	@FXML
	Label labelConstitution;
	@FXML
	Label labelStrength;

	public void increaseCourage() {
		m.increasePrimaryAttribute(PRIMARY_ATTRIBUTE.COURAGE);
	}

	public void decreaseCourage() {
		m.decreasePrimaryAttribute(PRIMARY_ATTRIBUTE.COURAGE);
	}
	
	public void increaseIntelligence() {
		m.increasePrimaryAttribute(PRIMARY_ATTRIBUTE.INTELLIGENCE);
	}
	
	public void decreaseIntelligence() {
		m.decreasePrimaryAttribute(PRIMARY_ATTRIBUTE.INTELLIGENCE);
	}
	
	public void increaseIntuition() {
		m.increasePrimaryAttribute(PRIMARY_ATTRIBUTE.INTUITION);
	}
	
	public void decreaseIntuition() {
		m.decreasePrimaryAttribute(PRIMARY_ATTRIBUTE.INTUITION);
	}
	
	public void increaseCharisma() {
		m.increasePrimaryAttribute(PRIMARY_ATTRIBUTE.CHARISMA);
	}
	
	public void decreaseCharisma() {
		m.decreasePrimaryAttribute(PRIMARY_ATTRIBUTE.CHARISMA);
	}
	
	public void increaseDexterity() {
		m.increasePrimaryAttribute(PRIMARY_ATTRIBUTE.DEXTERITY);
	}
	
	public void decreaseDexterity() {
		m.decreasePrimaryAttribute(PRIMARY_ATTRIBUTE.DEXTERITY);
	}
	
	public void increaseAgility() {
		m.increasePrimaryAttribute(PRIMARY_ATTRIBUTE.AGILITY);
	}
	
	public void decreaseAgility() {
		m.decreasePrimaryAttribute(PRIMARY_ATTRIBUTE.AGILITY);
	}
	
	public void increaseConstitution() {
		m.increasePrimaryAttribute(PRIMARY_ATTRIBUTE.CONSTITUTION);
	}
	
	public void decreaseConstitution() {
		m.decreasePrimaryAttribute(PRIMARY_ATTRIBUTE.CONSTITUTION);
	}
	
	public void increaseStrength() {
		m.increasePrimaryAttribute(PRIMARY_ATTRIBUTE.STRENGTH);
	}
	
	public void decreaseStrength() {
		m.decreasePrimaryAttribute(PRIMARY_ATTRIBUTE.STRENGTH);
	}

	@Override
	public void update(Aventurian updatedAventurian) {
		labelCourage.setText(String.valueOf(updatedAventurian.getPrimaryAttribute(PRIMARY_ATTRIBUTE.COURAGE)));
		labelIntelligence.setText(String.valueOf(updatedAventurian.getPrimaryAttribute(PRIMARY_ATTRIBUTE.INTELLIGENCE)));
		labelIntuition.setText(String.valueOf(updatedAventurian.getPrimaryAttribute(PRIMARY_ATTRIBUTE.INTUITION)));
		labelCharisma.setText(String.valueOf(updatedAventurian.getPrimaryAttribute(PRIMARY_ATTRIBUTE.CHARISMA)));
		labelDexterity.setText(String.valueOf(updatedAventurian.getPrimaryAttribute(PRIMARY_ATTRIBUTE.DEXTERITY)));
		labelAgility.setText(String.valueOf(updatedAventurian.getPrimaryAttribute(PRIMARY_ATTRIBUTE.AGILITY)));
		labelConstitution.setText(String.valueOf(updatedAventurian.getPrimaryAttribute(PRIMARY_ATTRIBUTE.CONSTITUTION)));
		labelStrength.setText(String.valueOf(updatedAventurian.getPrimaryAttribute(PRIMARY_ATTRIBUTE.STRENGTH)));
	}

	@Override
	void initControllerSpecificStuff() {
		// TODO nothing to do here!?
	}
}

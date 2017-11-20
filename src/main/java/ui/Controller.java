package ui;

import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE;

import java.util.Observable;
import java.util.Observer;

import aventurian.Aventurian;
import aventurian.AventurianManager;
import javafx.scene.control.Label;

public class Controller implements Observer {
	
	private AventurianManager manager;
	
	public Label courageLabel;
	
	void initialize(AventurianManager manager) {
		this.manager = manager;
	}
	
	public void setCourage(String c) {
		courageLabel.setText(c);
	}
	
	public void increaseCourage() {
		manager.increasePrimaryAttribute(COURAGE);
	}
	
	public void decreaseCourage() {
		manager.decreasePrimaryAttribut(COURAGE);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		courageLabel.setText(Integer.toString(((Aventurian)arg0).getPrimaryAttribute(COURAGE)));
		
	}
}

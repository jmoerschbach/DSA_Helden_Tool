package ui;

import aventurian.Aventurian;
import aventurian.AventurianManager;

public class LeftController {

	enum PAGES {
		ATTRIBUTES, LANGUAGES
	};

	private MainController mainController;

	public void showLanguages() {
		mainController.changeTo(PAGES.LANGUAGES);
	}

	public void showAttributes() {
		mainController.changeTo(PAGES.ATTRIBUTES);
	}

	public void init(AventurianManager manager, MainController mainController) {
		this.mainController = mainController;
	}

	public void update(Aventurian updatedAventurian) {
		// nothing to do here since we do not display any info about aventurian

	}
}

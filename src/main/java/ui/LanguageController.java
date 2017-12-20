package ui;

import aventurian.Aventurian;
import aventurian.AventurianManager;
import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import skills.Language;

public class LanguageController extends XController {

	@FXML
	public ListView<Language> allLanguages;

	@FXML
	public Button btnAssignLanguage;

	@FXML
	public ListView<Language> assignedLanguages;

	@Override
	public void init(AventurianManager manager) {
		super.init(manager);
		final Database database = Database.getInstance();
		final ObservableList<Language> l = FXCollections.observableArrayList(database.getLanguages());
		allLanguages.setItems(l);

		allLanguages.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			this.setButtonDisabled(newValue == null);
		});
	}

	public void assignLanguage() {
		final Language language = allLanguages.getSelectionModel().getSelectedItem();
		m.addLanguage(language);
	}

	public void unassignLanguage() {
		System.out.println("<- pressed");
	}

	private void setButtonDisabled(boolean b) {
		btnAssignLanguage.setDisable(b);
	}

	@Override
	void update(Aventurian updatedAventurian) {
		final ObservableList<Language> l = FXCollections.observableArrayList(updatedAventurian.getLanguages());
		assignedLanguages.setItems(l);
	}

}

package ui;

import aventurian.Aventurian;
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
	public Button btnUnAssignLanguage;
	@FXML
	public ListView<Language> assignedLanguages;

	public void assignLanguage() {
		final Language language = allLanguages.getSelectionModel().getSelectedItem();
		m.addLanguage(language);
	}

	public void unassignLanguage() {
		System.out.println("<- pressed");
	}

	@Override
	void update(Aventurian updatedAventurian) {
		final ObservableList<Language> l = FXCollections.observableArrayList(updatedAventurian.getLanguages());
		assignedLanguages.setItems(l);
	}

	@Override
	void initControllerSpecificStuff() {
		final ObservableList<Language> l = FXCollections.observableArrayList(db.getLanguages());
		allLanguages.setItems(l);

		allLanguages.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			btnAssignLanguage.setDisable(newValue == null);
		});
		assignedLanguages.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			btnUnAssignLanguage.setDisable(newValue == null);
		});
	}

}

package ui;

import aventurian.Aventurian;
import aventurian.AventurianManager;
import database.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import skills.Language;

public class LanguageController extends XController {

	@FXML
	public ListView<Language> allLanguages;

	@FXML
	public ListView<Language> assignedLanguages;
	
	public void init(AventurianManager manager) {
		super.init(manager);
		Database database = Database.getInstance();
		ObservableList<Language> l = FXCollections.observableArrayList(database.getLanguages());
		allLanguages.setItems(l);
	}

	public void assignLanguage() {
		Language language = allLanguages.getSelectionModel().getSelectedItem();
		m.addLanguage(language);
	}

	public void unassignLanguage() {
		System.out.println("<- pressed");
	}

	@Override
	void update(Aventurian updatedAventurian) {
		System.out.println("Treeview must be updated");
		
	}

}

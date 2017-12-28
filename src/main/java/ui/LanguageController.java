package ui;

import static java.util.stream.Collectors.toList;

import java.util.List;

import aventurian.Aventurian;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import skills.Language;

public class LanguageController extends XController {

	@FXML
	public ListView<Language> lvUnAssignedLanguages;

	@FXML
	public Button btnAssignLanguage;
	@FXML
	public Button btnUnAssignLanguage;
	@FXML
	public ListView<Language> lvAssignedLanguages;

	public void assignLanguage() {
		final Language language = lvUnAssignedLanguages.getSelectionModel().getSelectedItem();
		m.addLanguage(language);
	}

	public void unassignLanguage() {
		final Language language = lvAssignedLanguages.getSelectionModel().getSelectedItem();
		m.removeLanguage(language);
	}

	@Override
	void update(Aventurian updatedAventurian) {
		final List<Language> assignedLanguages = updatedAventurian.getLanguages();
		lvAssignedLanguages.setItems(FXCollections.observableArrayList(assignedLanguages));

		final List<Language> unassignedLanguages = db.getLanguages().stream()
				.filter(l -> !assignedLanguages.contains(l)).collect(toList());
		lvUnAssignedLanguages.setItems(FXCollections.observableArrayList(unassignedLanguages));
	}

	@Override
	void initControllerSpecificStuff() {
		final ObservableList<Language> l = FXCollections.observableArrayList(db.getLanguages());
		lvUnAssignedLanguages.setItems(l);

		lvUnAssignedLanguages.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					btnAssignLanguage.setDisable(newValue == null);
				});
		lvAssignedLanguages.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			btnUnAssignLanguage.setDisable(newValue == null);
		});

		lvUnAssignedLanguages.setOnMouseClicked((MouseEvent click) -> {
			if (click.getClickCount() == 2 && !lvUnAssignedLanguages.getSelectionModel().isEmpty()) {
				final Language language = lvUnAssignedLanguages.getSelectionModel().getSelectedItem();
				m.addLanguage(language);
			}
		});

		lvAssignedLanguages.setOnMouseClicked((MouseEvent click) -> {
			if (click.getClickCount() == 2 && !lvAssignedLanguages.getSelectionModel().isEmpty()) {
				final Language language = lvAssignedLanguages.getSelectionModel().getSelectedItem();
				m.removeLanguage(language);
			}
		});

	}

}

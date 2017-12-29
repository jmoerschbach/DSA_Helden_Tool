package ui;

import static java.util.stream.Collectors.toList;

import java.util.List;

import aventurian.Aventurian;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
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
		lvAssignedLanguages.setItems(null); // forces listview to re-render all cells
		lvAssignedLanguages.setItems(FXCollections.observableArrayList(assignedLanguages));

		final List<Language> unassignedLanguages = db.getLanguages().stream()
				.filter(l -> !assignedLanguages.contains(l)).collect(toList());
		lvUnAssignedLanguages.setItems(null); // forces listview to re-render all cells
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
		lvAssignedLanguages.setCellFactory((ListView<Language> list) -> new XCell());

	}

	private class XCell extends ListCell<Language> {
		HBox hbox = new HBox();
		Label nameLabel = new Label("(empty)");
		Label levelLabel = new Label("1");
		Pane pane = new Pane();
		Button increaseButton = new Button("+");
		Button decreaseButton = new Button("-");

		public XCell() {
			hbox.getChildren().addAll(nameLabel, pane, decreaseButton, levelLabel, increaseButton);
			hbox.setSpacing(5);
			hbox.setAlignment(Pos.CENTER);
			HBox.setHgrow(pane, Priority.ALWAYS);
			decreaseButton.setOnAction((ActionEvent e) -> m.decreaseLanguage(getItem()));
			increaseButton.setOnAction((ActionEvent e) -> m.increaseLanguage(getItem()));
		}

		@Override
		protected void updateItem(Language item, boolean empty) {
			super.updateItem(item, empty);
			setText(null); // No text in label of super class
			if (empty) {
				setGraphic(null);
			} else {
				nameLabel.setText(item != null ? item.getName() : "<null>");
				levelLabel.setText(item != null ? item.getLevel() + "" : "<null>");
				setGraphic(hbox);
			}
		}
	}

}

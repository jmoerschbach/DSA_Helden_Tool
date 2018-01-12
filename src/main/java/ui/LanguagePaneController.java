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
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import skills.Language;
import skills.Skill;

public class LanguagePaneController extends PaneController {

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
		prepareUnAssignedListView();
		prepareAssignedListView();

	}

	private void prepareUnAssignedListView() {
		final ObservableList<Language> l = FXCollections.observableArrayList(db.getLanguages());
		lvUnAssignedLanguages.setItems(l);

		lvUnAssignedLanguages.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> {
					btnAssignLanguage.setDisable(newValue == null);
				});

		lvUnAssignedLanguages.setOnMouseClicked((MouseEvent click) -> {
			if (click.getClickCount() == 2 && !lvUnAssignedLanguages.getSelectionModel().isEmpty()) {
				final Language language = lvUnAssignedLanguages.getSelectionModel().getSelectedItem();
				m.addLanguage(language);
			}
		});
		lvUnAssignedLanguages.setCellFactory((ListView<Language> list) -> new ToolTipCell<>());
	}

	// TODO: as soon as tool tips are required in other lists (meaning for other
	// skills like property and so on) make this class a normal/outer class
	private static class ToolTipCell<T extends Skill> extends ListCell<T> {
		@Override
		protected void updateItem(T item, boolean empty) {
			super.updateItem(item, empty);
			if (empty || item == null) {
				setGraphic(null);
				setText(null);
			} else {
				setTooltip(new Tooltip(item.getDescription()));
				setText(item.getName());
			}
		}
	}

	private void prepareAssignedListView() {
		lvAssignedLanguages.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			btnUnAssignLanguage.setDisable(newValue == null);
		});
		lvAssignedLanguages.setOnMouseClicked((MouseEvent click) -> {
			if (click.getClickCount() == 2 && !lvAssignedLanguages.getSelectionModel().isEmpty()) {
				final Language language = lvAssignedLanguages.getSelectionModel().getSelectedItem();
				m.removeLanguage(language);
			}
		});
		lvAssignedLanguages.setCellFactory((ListView<Language> list) -> new AssignedLanguageCell());
	}

	private class AssignedLanguageCell extends ListCell<Language> {
		HBox hbox = new HBox();
		Label nameLabel = new Label("(empty)");
		Label levelLabel = new Label("1");
		Pane pane = new Pane();
		Button increaseButton = new Button("+");
		Button decreaseButton = new Button("-");

		public AssignedLanguageCell() {
			hbox.getChildren().addAll(nameLabel, pane, decreaseButton, levelLabel, increaseButton);
			hbox.setSpacing(5);
			hbox.setAlignment(Pos.CENTER);
			HBox.setHgrow(pane, Priority.ALWAYS);
			nameLabel.setTextFill(Color.web("#0000FF"));
			decreaseButton.setPrefWidth(25);
			increaseButton.setPrefWidth(25);
			decreaseButton.setOnAction((ActionEvent e) -> m.decreaseLanguage(getItem()));
			increaseButton.setOnAction((ActionEvent e) -> m.increaseLanguage(getItem()));
		}

		@Override
		protected void updateItem(Language item, boolean empty) {
			super.updateItem(item, empty);
			setText(null); // No text in label of super class
			if (empty || item == null) {
				setGraphic(null);
			} else {
				nameLabel.setText(item.getName());
				levelLabel.setText(String.valueOf(item.getLevel()));
				increaseButton.setDisable(!item.isIncreasable());
				decreaseButton.setDisable(!item.isDecreasable());
				setTooltip(new Tooltip(item.getDescription()));
				setGraphic(hbox);
			}
		}
	}

}

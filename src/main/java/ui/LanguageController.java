package ui;

import aventurian.Aventurian;
import javafx.fxml.FXML;
import javafx.scene.control.TreeView;

public class LanguageController extends XController {

	@FXML
	public TreeView allLanguages;

	@FXML
	public TreeView assignedLanguages;

	public void assignLanguage() {
		System.out.println("-> pressed");
	}

	public void unassignLanguage() {
		System.out.println("<- pressed");
	}

	@Override
	void update(Aventurian updatedAventurian) {
		System.out.println("Treeview must be updated");

	}

}

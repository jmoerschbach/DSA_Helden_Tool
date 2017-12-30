package ui;

import aventurian.Aventurian;
import aventurian.AventurianManager;
import database.Database;

public abstract class XController {

	protected AventurianManager m;
	protected Database db;

	public XController() {
		// TODO Auto-generated constructor stub
	}

	void init(AventurianManager manager, Database database) {
		m = manager;
		db = database;
		initControllerSpecificStuff();
	}

	abstract void initControllerSpecificStuff();

	abstract void update(Aventurian updatedAventurian);
}

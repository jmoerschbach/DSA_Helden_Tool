package ui;

import aventurian.Aventurian;
import aventurian.AventurianManager;

public abstract class XController {

	protected AventurianManager m;

	public XController() {
		// TODO Auto-generated constructor stub
	}

	void init(AventurianManager manager) {
		m = manager;
	}

	abstract void update(Aventurian updatedAventurian);
}

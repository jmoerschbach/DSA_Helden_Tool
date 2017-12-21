package database;

import java.util.ArrayList;
import java.util.List;

import aventurian.Aventurian;
import aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE;
import skills.Language;
import skills.Property;

public class Database {

	private static Database instance;
	private List<Property> properties;
	private List<Language> languages;

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
			instance.initialize();
		}
		return instance;
	}

	public List<Language> getLanguages() {
		return languages;
	}

	private void initialize() {
		properties = new ArrayList<>();
		languages = new ArrayList<>();

		// properties.add(new Property("bla", "blub", 350, Optional.of((Aventurian a)->
		// a.setName("bla")), Optional.of((Aventurian a) -> a.setName("blub"))));

		languages.add(new Language("Garethi", "wichtigste Sprache", (Aventurian a) -> {
			return a.getPrimaryAttribute(PRIMARY_ATTRIBUTE.COURAGE) >= 13;
		}, 5, 50));

	}
}

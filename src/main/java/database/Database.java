package database;

import java.util.ArrayList;
import java.util.List;

import aventurian.Aventurian;
import aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE;
import skills.Language;
import skills.Property;

public class Database {

	private List<Property> properties;
	private List<Language> languages;

	public Database() {
		initialize();
	}

	public List<Language> getLanguages() {
		return languages;
	}

	private void initialize() {
		properties = new ArrayList<>();
		languages = new ArrayList<>();

		// properties.add(new Property("bla", "blub", 350, Optional.of((Aventurian a)->
		// a.setName("bla")), Optional.of((Aventurian a) -> a.setName("blub"))));

		languages.add(new Language("Garethi", "wichtigste Sprache",
				(Aventurian a) -> a.getPrimaryAttribute(PRIMARY_ATTRIBUTE.COURAGE) >= 13, 5, 50));
		languages.add(new Language("BlaBla", "sinnlose Sprache", (Aventurian a) -> true, 5, 50));

	}
}

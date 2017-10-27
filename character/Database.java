package character;

import skills.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Database {

    private List<Property> properties;


    public void initialize() {
        properties = new ArrayList<>();

        properties.add(new Property("bla", "blub", 350, Optional.of((Aventurian a)-> a.setName("bla")), Optional.of((Aventurian a) -> a.setName("blub"))));
    }
}

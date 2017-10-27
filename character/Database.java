package character;

import skills.Disadvantage;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Disadvantage> disadvantages;


    public void initialize() {
        disadvantages = new ArrayList<>();

        disadvantages.add(new Disadvantage("bla", "blub", 350, (Aventurian a) -> {
            a.setName("tschacka");
        }, (Aventurian a) -> {
            a.setName("undo");
        }, (Aventurian a) -> {
            return true;
        }));
    }
}

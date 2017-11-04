package skills;

import java.util.function.Consumer;
import java.util.function.Predicate;

import aventurian.Aventurian;

public class BadProperty extends Property {
	private static final Consumer<Aventurian> EMPTY = (Aventurian a) -> {
    };
    private int level;

    public BadProperty(String name, String description, int cost, int level, Predicate<Aventurian> requirement) {
        super(name, description, cost, EMPTY, EMPTY, requirement);
        this.level = level;
    }
}

package skills;

import aventurian.Aventurian;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Property extends Skill {

    private final int cost;

    public Property(String name, String description, int cost, Consumer<Aventurian> effectOnGain, Consumer<Aventurian> effectOnLose, Predicate<Aventurian> requirement) {
        super(name, description, effectOnGain, effectOnLose, requirement);
        this.cost = cost;
    }

    public boolean isAdvantage() {
        return cost > 0;
    }

    public boolean isDisadvantage() {
        return !isAdvantage();
    }

    public int getCost() {
        return cost;
    }
}

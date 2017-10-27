package skills;

import character.Aventurian;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Property extends Skill {

    private final int cost;
    private final Optional<Consumer<Aventurian>> effectOnGain;
    private final Optional<Consumer<Aventurian>> effectOnLose;
    private final Optional<Predicate<Aventurian>> requirement;

    public Property(String name, String description, int cost, Optional<Consumer<Aventurian>> effectOnGain, Optional<Consumer<Aventurian>> effectOnLose, Optional<Predicate<Aventurian>> requirement) {
        super(name, description);
        this.cost = cost;
        this.effectOnGain = effectOnGain;
        this.effectOnLose = effectOnLose;
        this.requirement = requirement;
    }

    public Property(String name, String description, int cost, Optional<Consumer<Aventurian>> effectOnLose, Optional<Consumer<Aventurian>> effectOnGain) {
        this(name, description, cost, effectOnGain, effectOnLose, Optional.empty());
    }

    public Property(String name, String description, int cost, Optional<Predicate<Aventurian>> requirement) {
        this(name, description, cost, Optional.empty(), Optional.empty(), requirement);
    }

    public Property(String name, String description, int cost) {
        this(name, description, cost, Optional.empty(), Optional.empty(), Optional.empty());
    }

    public void gain(Aventurian t) {
        effectOnGain.ifPresent(e->e.accept(t));
    }

    public void lose(Aventurian t) {
        effectOnLose.ifPresent(e->e.accept(t));
    }

    public boolean isAllowed(Aventurian t) {
        return !requirement.isPresent() || requirement.get().test(t);
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

package skills;

import character.Aventurian;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Advantage extends Skill {

    private final int cost;
    private final Consumer<Aventurian> effectOnLose;
    private final Consumer<Aventurian> effectOnGain;
    private final Predicate<Aventurian> requirement;

    public Advantage(String name, String description, int cost, Consumer<Aventurian> effectOnGain, Consumer<Aventurian> effectOnLose, Predicate<Aventurian> requirement) {
        super(name, description);
        this.cost = cost;
        this.effectOnGain = effectOnGain;
        this.effectOnLose = effectOnLose;
        this.requirement = requirement;
    }

    public void gain(Aventurian t) {
        effectOnGain.accept(t);
    }

    public void lose(Aventurian t) {
        effectOnLose.accept(t);
    }

    public boolean isAllowed(Aventurian t) {
        return requirement.test(t);
    }


}

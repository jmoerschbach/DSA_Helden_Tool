package skills;

import character.Aventurian;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Disadvantage extends Skill {

    private final int refund;
    private final Optional<Consumer<Aventurian>> effectOnGain;
    private final Optional<Consumer<Aventurian>> effectOnLose;
    private final Optional<Predicate<Aventurian>> requirement;

    public Disadvantage(String name, String description, int refund, Optional<Consumer<Aventurian>> effectOnGain, Optional<Consumer<Aventurian>> effectOnLose, Optional<Predicate<Aventurian>> requirement) {
        super(name, description);
        this.refund = refund;
        this.effectOnGain = effectOnGain;
        this.effectOnLose = effectOnLose;
        this.requirement = requirement;
    }

    public Disadvantage(String name, String description, int refund, Optional<Consumer<Aventurian>> effectOnLose, Optional<Consumer<Aventurian>> effectOnGain) {
        this(name, description, refund, effectOnGain, effectOnLose, Optional.empty());
    }

    public Disadvantage(String name, String description, int refund, Optional<Predicate<Aventurian>> requirement) {
        this(name, description, refund, Optional.empty(), Optional.empty(), requirement);
    }

    public Disadvantage(String name, String description, int refund) {
        this(name, description, refund, Optional.empty(), Optional.empty(), Optional.empty());
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
}

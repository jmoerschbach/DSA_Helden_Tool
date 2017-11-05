
package skills;

import aventurian.Aventurian;

import java.util.function.Predicate;

public class Language extends Skill {

    private int level;
    private final int maxLevel;
    private final int cost;
    private boolean isNativeTongue;

    public Language(String name, String description, Predicate<Aventurian> requirement, int maxLevel, int cost) {
        super(name, description, EMPTY, EMPTY, requirement);
        this.level = 1;
        this.maxLevel = maxLevel;
        this.cost = cost;
        this.isNativeTongue = false;
    }

    public void increase() {
        if (level >= maxLevel) throw new IllegalStateException("Language cannot be over max level!");
        level++;
    }

    public void decrease() {
        if (level <= 1) throw new IllegalStateException("Language level cannot be less than 1!");
        level--;
    }

    public int getLevel() {
        return level;
    }

    public int getUpgradeCost() {
        return (level + 1) * cost;
    }

    public int getDowngradeCost() {
        return level * cost;
    }

    public int getLearningCost() {
        return cost;
    }

    public int getTotalCOst() {
        return (level * (level + 1) / 2) * cost;
    }

    public void setNativeTongue(boolean nativeTongue) {
        isNativeTongue = nativeTongue;
    }

    public boolean isNativeTongue() {
        return isNativeTongue;
    }

    public boolean isIncreasable() {
        return level < maxLevel;
    }

    public boolean isDecreasable() {
        return level > 1;
    }
}

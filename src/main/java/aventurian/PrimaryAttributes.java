package aventurian;

import java.util.HashMap;
import java.util.Map;

import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.*;

class PrimaryAttributes {

    private Map<PRIMARY_ATTRIBUTE, Integer> currentAttributes;
    private Map<PRIMARY_ATTRIBUTE, Integer> maxAttributes;
    private Map<PRIMARY_ATTRIBUTE, Integer> minAttributes;

    public enum PRIMARY_ATTRIBUTE {
        COURAGE,
        INTELLIGENCE,
        INTUITION,
        CHARISMA,
        DEXTERITY,
        FINESSE,
        CONSTITUTION,
        STRENGTH
    }

    PrimaryAttributes() {
        this.currentAttributes = new HashMap<>();
        currentAttributes.put(COURAGE, 8);
        currentAttributes.put(INTELLIGENCE, 8);
        currentAttributes.put(INTUITION, 8);
        currentAttributes.put(CHARISMA, 8);
        currentAttributes.put(DEXTERITY, 8);
        currentAttributes.put(FINESSE, 8);
        currentAttributes.put(CONSTITUTION, 8);
        currentAttributes.put(STRENGTH, 8);
        this.maxAttributes = new HashMap<>();
        maxAttributes.put(COURAGE, 14);
        maxAttributes.put(INTELLIGENCE, 14);
        maxAttributes.put(INTUITION, 14);
        maxAttributes.put(CHARISMA, 14);
        maxAttributes.put(DEXTERITY, 14);
        maxAttributes.put(FINESSE, 14);
        maxAttributes.put(CONSTITUTION, 14);
        maxAttributes.put(STRENGTH, 14);
        this.minAttributes = new HashMap<>();
        minAttributes.put(COURAGE, 8);
        minAttributes.put(INTELLIGENCE, 8);
        minAttributes.put(INTUITION, 8);
        minAttributes.put(CHARISMA, 8);
        minAttributes.put(DEXTERITY, 8);
        minAttributes.put(FINESSE, 8);
        minAttributes.put(CONSTITUTION, 8);
        minAttributes.put(STRENGTH, 8);
    }

    int getSum() {
        return getPrimaryAttribute(COURAGE) + getPrimaryAttribute(INTELLIGENCE) + getPrimaryAttribute(INTUITION) + getPrimaryAttribute(CHARISMA) + getPrimaryAttribute(DEXTERITY) + getPrimaryAttribute(FINESSE) + getPrimaryAttribute(CONSTITUTION) + getPrimaryAttribute(STRENGTH);
    }

    int getPrimaryAttribute(PRIMARY_ATTRIBUTE a) {
        return currentAttributes.get(a);
    }

    void increase(PRIMARY_ATTRIBUTE a) {
        if (currentAttributes.get(a) >= maxAttributes.get(a))
            throw new IllegalStateException("Maximum already reached!");
        currentAttributes.put(a, currentAttributes.get(a)+1);
    }

    void decrease(PRIMARY_ATTRIBUTE a) {
        if (currentAttributes.get(a) <= minAttributes.get(a))
            throw new IllegalStateException("Minimum already reached!");
        currentAttributes.put(a, currentAttributes.get(a)-1);
    }

    void increaseMaximum(PRIMARY_ATTRIBUTE a) {
        maxAttributes.put(a, maxAttributes.get(a)+1);
    }

    void decreaseMaximum(PRIMARY_ATTRIBUTE a) {
        if (maxAttributes.get(a) <= minAttributes.get(a))
            throw new IllegalStateException("Maximum cannot be less than Minimum!");
        maxAttributes.put(a, maxAttributes.get(a)-1);
    }

    int getMaximumOfPrimaryAttribute(PRIMARY_ATTRIBUTE a) {
        return maxAttributes.get(a);
    }
}

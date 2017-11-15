package aventurian;

import java.util.HashMap;
import java.util.Map;

import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.*;

public class PrimaryAttributes {

    static final int MAX = 14;
	static final int DEFAULT = 8;
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
        currentAttributes.put(COURAGE, DEFAULT);
        currentAttributes.put(INTELLIGENCE, DEFAULT);
        currentAttributes.put(INTUITION, DEFAULT);
        currentAttributes.put(CHARISMA, DEFAULT);
        currentAttributes.put(DEXTERITY, DEFAULT);
        currentAttributes.put(FINESSE, DEFAULT);
        currentAttributes.put(CONSTITUTION, DEFAULT);
        currentAttributes.put(STRENGTH, DEFAULT);
        this.maxAttributes = new HashMap<>();
        maxAttributes.put(COURAGE, MAX);
		maxAttributes.put(INTELLIGENCE, MAX);
        maxAttributes.put(INTUITION, MAX);
        maxAttributes.put(CHARISMA, MAX);
        maxAttributes.put(DEXTERITY, MAX);
        maxAttributes.put(FINESSE, MAX);
        maxAttributes.put(CONSTITUTION, MAX);
        maxAttributes.put(STRENGTH, MAX);
        this.minAttributes = new HashMap<>();
        minAttributes.put(COURAGE, DEFAULT);
        minAttributes.put(INTELLIGENCE, DEFAULT);
        minAttributes.put(INTUITION, DEFAULT);
        minAttributes.put(CHARISMA, DEFAULT);
        minAttributes.put(DEXTERITY, DEFAULT);
        minAttributes.put(FINESSE, DEFAULT);
        minAttributes.put(CONSTITUTION, DEFAULT);
        minAttributes.put(STRENGTH, DEFAULT);
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
    
    boolean isIncreasable(PRIMARY_ATTRIBUTE a) {
    	return currentAttributes.get(a) < maxAttributes.get(a);
    }
    
    boolean isDecreasable(PRIMARY_ATTRIBUTE a) {
    	return currentAttributes.get(a) > minAttributes.get(a);
    }
}

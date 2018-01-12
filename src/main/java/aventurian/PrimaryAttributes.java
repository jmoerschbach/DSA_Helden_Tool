package aventurian;

import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.CHARISMA;
import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.CONSTITUTION;
import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.COURAGE;
import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.DEXTERITY;
import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.FINESSE;
import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.INTELLIGENCE;
import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.INTUITION;
import static aventurian.PrimaryAttributes.PRIMARY_ATTRIBUTE.STRENGTH;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PrimaryAttributes {

    static final int MAX = 14;
	static final int MIN = 8;
	private final Map<PRIMARY_ATTRIBUTE, Integer> currentAttributes;
    private final Map<PRIMARY_ATTRIBUTE, Integer> maxAttributes;
    private final Map<PRIMARY_ATTRIBUTE, Integer> minAttributes;

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
        currentAttributes.put(COURAGE, MIN);
        currentAttributes.put(INTELLIGENCE, MIN);
        currentAttributes.put(INTUITION, MIN);
        currentAttributes.put(CHARISMA, MIN);
        currentAttributes.put(DEXTERITY, MIN);
        currentAttributes.put(FINESSE, MIN);
        currentAttributes.put(CONSTITUTION, MIN);
        currentAttributes.put(STRENGTH, MIN);
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
        minAttributes.put(COURAGE, MIN);
        minAttributes.put(INTELLIGENCE, MIN);
        minAttributes.put(INTUITION, MIN);
        minAttributes.put(CHARISMA, MIN);
        minAttributes.put(DEXTERITY, MIN);
        minAttributes.put(FINESSE, MIN);
        minAttributes.put(CONSTITUTION, MIN);
        minAttributes.put(STRENGTH, MIN);
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

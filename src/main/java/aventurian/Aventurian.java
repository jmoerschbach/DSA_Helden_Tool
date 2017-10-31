package aventurian;

import skills.Property;

import java.util.ArrayList;
import java.util.List;

public class Aventurian {

    private String name;
    private PrimaryAttributes primaryAttributes;
    private SecondaryAttributes secondaryAttributes;
    private int adventurePoints;


    private final List<Property> properties;

    Aventurian(String name, int ap) {
        this.name = name;
        this.primaryAttributes = new PrimaryAttributes();
        this.secondaryAttributes = new SecondaryAttributes();
        this.adventurePoints = ap;
        this.properties = new ArrayList<>();
    }

    Aventurian(int ap) {
        this("", ap);
    }

    void pay(int cost) {
        if (canPay(cost)) adventurePoints -= cost;
        else throw new IllegalArgumentException("Cannot pay: " + cost);
    }

    void refund(int refund) {
        adventurePoints += refund;
    }

    boolean canPay(int cost) {
        return cost <= adventurePoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    void addProperty(Property p) {
        properties.add(p);
    }

    void removeProperty(Property p) {
        properties.remove(p);
    }

    boolean has(String nameOfSkill) {
        for (Property p : properties) {
            if (p.getName().equals(nameOfSkill)) return true;
        }
        return false;
    }

    boolean hasProperty(Property p) {
        return properties.contains(p);
    }

    int getSumOfPrimaryAttributes() {
        return primaryAttributes.getSum();
    }

    int getPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE a) {
        return primaryAttributes.getPrimaryAttribute(a);
    }

    int getMaxOfPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE a) {
        return primaryAttributes.getMaximumOfPrimaryAttribute(a);
    }

    void increasePrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE attribute) {
        primaryAttributes.increase(attribute);
        secondaryAttributes.updateValues(primaryAttributes);
    }

    void decrasePrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE attribute) {
        primaryAttributes.decrease(attribute);
        secondaryAttributes.updateValues(primaryAttributes);
    }
}

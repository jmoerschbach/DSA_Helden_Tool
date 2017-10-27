package character;

import skills.Property;

import java.util.ArrayList;
import java.util.List;

public class Aventurian {

    private String name;
    private PrimaryAttributes primaryAttributes;
    private SecondaryAttributes secondaryAttributes;
    private int adventurePoints;

    private final List<Property> properties;

    public Aventurian(String name, int ap) {
        this.name = name;
        this.primaryAttributes = new PrimaryAttributes();
        this.secondaryAttributes = new SecondaryAttributes();
        this.adventurePoints = ap;
        this.properties = new ArrayList<>();
    }

    public Aventurian(int ap) {
        this("", ap);
    }

    public void pay(int cost) {
        if (canPay(cost)) adventurePoints -= cost;
        else throw new IllegalArgumentException("Cannot pay: " + cost);
    }

    public void refund(int refund) {
        adventurePoints += refund;
    }

    public boolean canPay(int cost) {
        return cost <= adventurePoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProperty(Property p) {
        properties.add(p);
    }

    public void removeProperty(Property p) {
        properties.remove(p);
    }

    public boolean hasProperty(Property p) {
        return properties.contains(p);
    }
}

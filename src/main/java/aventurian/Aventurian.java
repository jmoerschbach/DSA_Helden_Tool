package aventurian;

import skills.*;

import java.util.ArrayList;
import java.util.List;

public class Aventurian {

    private String name;
    private PrimaryAttributes primaryAttributes;
    private SecondaryAttributes secondaryAttributes;
    private int adventurePoints;


    private final List<Property> properties;
    private final List<BadProperty> badProperties;
    private final List<Language> languages;

    Aventurian(String name, int ap) {
        this.name = name;
        this.primaryAttributes = new PrimaryAttributes();
        this.secondaryAttributes = new SecondaryAttributes();
        this.adventurePoints = ap;
        this.properties = new ArrayList<>();
        this.badProperties = new ArrayList<>();
        this.languages = new ArrayList<>();
    }

    Aventurian(int ap) {
        this("", ap);
    }

    public int getAdventurePoints() {
        return adventurePoints;
    }

    void pay(int cost) {
        if (canPay(cost) && cost >= 0) adventurePoints -= cost;
        else throw new IllegalArgumentException("Cannot pay: " + cost);
    }

    void refund(int refund) {
        if (refund >= 0) adventurePoints += refund;
        else throw new IllegalArgumentException("Cannot refund negative amound: " + refund);
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

    void addBadProperty(BadProperty p) {
        badProperties.add(p);
    }

    void removeBadProperty(BadProperty p) {
        badProperties.remove(p);
    }

    int getBadPropertySum() {
        return badProperties.stream().mapToInt(BadProperty::getLevel).sum();
    }

    void addLanguage(Language l) {
        languages.add(l);
    }

    void removeLanguage(Language l) {
        languages.remove(l);
    }

    boolean hasSkill(Skill skill) {
        for (Property p : properties) {
            if (p.equals(skill)) return true;
        }
        for (Language l : languages) {
            if (l.equals(skill)) return true;
        }
        return false;
    }

    boolean hasProperty(Property p) {
        return properties.contains(p);
    }

    boolean hasLanguage(Language l) {
        return languages.contains(l);
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

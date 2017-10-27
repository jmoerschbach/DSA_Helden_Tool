package character;

import static character.PrimaryAttributes.PRIMARY_ATTRIBUTE.*;
import static character.PrimaryAttributes.PRIMARY_ATTRIBUTE.CONSTITUTION;

public class SecondaryAttributes {

    private SecondaryAttributeHelper hitPoints;
    private SecondaryAttributeHelper astralPoints;
    private SecondaryAttributeHelper karmalPoints;
    private SecondaryAttributeHelper magicResistance;
    private SecondaryAttributeHelper exhaustionThreshhold;
    private SecondaryAttributeHelper woundThreshhold;

    private SecondaryAttributeHelper initiativeValue;
    private SecondaryAttributeHelper attackValue;
    private SecondaryAttributeHelper defendValue;
    private SecondaryAttributeHelper rangedValue;

    public SecondaryAttributes() {
        hitPoints = new SecondaryAttributeHelper();
        astralPoints = new SecondaryAttributeHelper();
        karmalPoints = new SecondaryAttributeHelper();
        magicResistance = new SecondaryAttributeHelper();
        exhaustionThreshhold = new SecondaryAttributeHelper();
        woundThreshhold = new SecondaryAttributeHelper();
        initiativeValue = new SecondaryAttributeHelper();
        attackValue = new SecondaryAttributeHelper();
        defendValue = new SecondaryAttributeHelper();
        rangedValue = new SecondaryAttributeHelper();
    }

    public void updateValues(PrimaryAttributes attributes) {
        calculateHitPoints(attributes);
        calculateAstralPoints(attributes);
        calculateKarmalPoints(attributes);
        calculateMagicResistance(attributes);
        calculateExhaustionThreshhold(attributes);
        calculateWoundThreshhold(attributes);
        calculateInitiativeValue(attributes);
        calculateAttackValue(attributes);
        calculateDefendValue(attributes);
        calculateRangedValue(attributes);
    }

    private void calculateRangedValue(PrimaryAttributes a) {
        int intuition = a.getPrimaryAttribute(INTUITION);
        int dexterity = a.getPrimaryAttribute(DEXTERITY);
        int strength = a.getPrimaryAttribute(STRENGTH);
        rangedValue.setBasis(round((intuition + dexterity + strength) / 5.0));
    }

    private void calculateDefendValue(PrimaryAttributes a) {
        int intuition = a.getPrimaryAttribute(INTUITION);
        int finesse = a.getPrimaryAttribute(FINESSE);
        int strength = a.getPrimaryAttribute(STRENGTH);
        defendValue.setBasis(round((intuition + finesse + strength) / 5.0));
    }

    private void calculateAttackValue(PrimaryAttributes a) {
        int courage = a.getPrimaryAttribute(COURAGE);
        int finesse = a.getPrimaryAttribute(FINESSE);
        int strength = a.getPrimaryAttribute(STRENGTH);
        attackValue.setBasis(round((courage + finesse + strength) / 5.0));
    }

    private void calculateInitiativeValue(PrimaryAttributes a) {
        int courage = a.getPrimaryAttribute(COURAGE);
        int intuition = a.getPrimaryAttribute(INTUITION);
        int finesse = a.getPrimaryAttribute(FINESSE);
        initiativeValue.setBasis(round((courage * 2 + intuition + finesse) / 5.0));
    }

    private void calculateWoundThreshhold(PrimaryAttributes a) {
        int constitution = a.getPrimaryAttribute(CONSTITUTION);
        woundThreshhold.setBasis(round(constitution / 2.0));
    }

    private void calculateExhaustionThreshhold(PrimaryAttributes a) {
        int constitution = a.getPrimaryAttribute(CONSTITUTION);
        exhaustionThreshhold.setBasis(round(constitution / 2.0));
    }

    private void calculateMagicResistance(PrimaryAttributes a) {
        int courage = a.getPrimaryAttribute(COURAGE);
        int intelligence = a.getPrimaryAttribute(INTELLIGENCE);
        int constitution = a.getPrimaryAttribute(CONSTITUTION);
        magicResistance.setBasis(round((courage + intelligence + constitution) / 5.0));
    }

    private void calculateHitPoints(PrimaryAttributes a) {
        int constitution = a.getPrimaryAttribute(CONSTITUTION);
        int strength = a.getPrimaryAttribute(STRENGTH);
        hitPoints.setBasis(round((constitution * 2 + strength) / 2.0));
    }

    private void calculateAstralPoints(PrimaryAttributes a) {
        int courage = a.getPrimaryAttribute(COURAGE);
        int intuition = a.getPrimaryAttribute(INTUITION);
        int charisma = a.getPrimaryAttribute(CHARISMA);
        astralPoints.setBasis(round((courage + intuition + charisma) / 2.0));
    }

    private void calculateKarmalPoints(PrimaryAttributes a) {
        karmalPoints.setBasis(24);
    }

    private int round(double d) {
        return (int) Math.round(d);
    }
}

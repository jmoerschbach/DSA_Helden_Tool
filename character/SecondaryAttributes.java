package character;

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
        int intuition = a.getIntuition();
        int dexterity = a.getDexterity();
        int strength = a.getStrength();
        rangedValue.setBasis(round((intuition + dexterity + strength) / 5.0));
    }

    private void calculateDefendValue(PrimaryAttributes a) {
        int intuition = a.getIntuition();
        int finesse = a.getFinesse();
        int strength = a.getStrength();
        defendValue.setBasis(round((intuition + finesse + strength) / 5.0));
    }

    private void calculateAttackValue(PrimaryAttributes a) {
        int courage = a.getCourage();
        int finesse = a.getFinesse();
        int strength = a.getStrength();
        attackValue.setBasis(round((courage + finesse + strength) / 5.0));
    }

    private void calculateInitiativeValue(PrimaryAttributes a) {
        int courage = a.getCourage();
        int intuition = a.getIntuition();
        int finesse = a.getFinesse();
        initiativeValue.setBasis(round((courage * 2 + intuition + finesse) / 5.0));
    }

    private void calculateWoundThreshhold(PrimaryAttributes a) {
        int constitution = a.getConstitution();
        exhaustionThreshhold.setBasis(round(constitution / 2.0));
    }

    private void calculateExhaustionThreshhold(PrimaryAttributes a) {
        int constitution = a.getConstitution();
        exhaustionThreshhold.setBasis(round(constitution / 2.0));
    }

    private void calculateMagicResistance(PrimaryAttributes a) {
        int courage = a.getCourage();
        int intelligence = a.getIntelligence();
        int constitution = a.getConstitution();
        magicResistance.setBasis(round((courage + intelligence + constitution) / 5.0));
    }

    private void calculateHitPoints(PrimaryAttributes a) {
        int constitution = a.getConstitution();
        int strength = a.getStrength();
        hitPoints.setBasis(round((constitution * 2 + strength) / 2.0));
    }

    private void calculateAstralPoints(PrimaryAttributes a) {
        int courage = a.getCourage();
        int intuition = a.getIntuition();
        int charisma = a.getCharisma();
        karmalPoints.setBasis(round((courage + intuition + charisma) / 2.0));
    }

    private void calculateKarmalPoints(PrimaryAttributes a) {
        karmalPoints.setBasis(24);
    }

    private int round(double d) {
        return (int) Math.round(d);
    }
}

package character;

class PrimaryAttributes {

    private int courage;
    private int intelligence;
    private int intuition;
    private int charisma;
    private int dexterity;
    private int finesse;
    private int constitution;
    private int strength;

    PrimaryAttributes() {
        courage = 8;
        intelligence = 8;
        intuition = 8;
        charisma = 8;
        dexterity = 8;
        finesse = 8;
        constitution = 8;
        strength = 8;
    }

    public int getSum() {
        return courage + intelligence + intuition + charisma + dexterity + finesse + constitution + strength;
    }

    public int getCourage() {
        return courage;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getIntuition() {
        return intuition;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getFinesse() {
        return finesse;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getStrength() {
        return strength;
    }
}

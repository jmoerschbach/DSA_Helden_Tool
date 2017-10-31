package skills;

public class BadProperty extends Property {

    private int level;

    public BadProperty(String name, String description, int cost, int level) {
        super(name, description, cost);
        this.level = level;
    }
}

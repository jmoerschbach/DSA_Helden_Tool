package skills;

public abstract class Skill {

    private final String name;
    private final String description;

    public Skill(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}

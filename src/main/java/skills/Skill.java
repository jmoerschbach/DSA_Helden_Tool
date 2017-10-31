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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill)) return false;

        Skill skill = (Skill) o;

        return name.equals(skill.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

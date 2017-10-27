package character;

import skills.Advantage;
import skills.Disadvantage;

import java.util.ArrayList;
import java.util.List;

public class Aventurian {

    private String name;
    private Attributes attributes;
    private int adventurePoints;

    private final List<Advantage> advantages;
    private final List<Disadvantage> disadvantages;

    public Aventurian(String name, int ap) {
        this.name = name;
        this.attributes = new Attributes();
        this.adventurePoints = ap;
        this.advantages = new ArrayList<>();
        this.disadvantages = new ArrayList<>();
    }

    public Aventurian(int ap) {
        this("", ap);
    }

    public boolean canPay(int cost) {
        return cost <= adventurePoints;
    }

    public void setName(String name) {
        this.name = name;
    }
}

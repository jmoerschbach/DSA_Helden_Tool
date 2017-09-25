package character.sonderfertigkeiten;

import character.Voraussetzungen.IVoraussetzung;
import character.Voraussetzungen.Voraussetzung;

/**
 * Created by Hauke on 24.08.2017.
 */
public abstract class ASonderfertigkeit implements Comparable<ASonderfertigkeit> {

    public String name;
    public int cost;
    public String text;
    public IVoraussetzung vor;

    public ASonderfertigkeit(String name, int cost, String text) {
        this.name = name;
        this.cost = cost;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public int getCost() {
        return cost;
    }

    public IVoraussetzung getVor() {
        return vor;
    }

    @Override
    public int compareTo(ASonderfertigkeit sf) {
        try {
            if (sf == null) return 1;
            return this.getName().compareTo(sf.getName());
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred in ASonderfertigkeit.compareTo()");
        }
        return 0;
    }

    @Override
    public boolean equals(Object sf) {
        if (sf == null) return false;
        if (getClass() != sf.getClass()) return false;
        return ((ASonderfertigkeit) sf).getName().equals(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

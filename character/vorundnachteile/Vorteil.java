package character.vorundnachteile;

import character.Voraussetzungen.IVoraussetzung;

/**
 * Created by Hauke on 24.08.2017.
 */
public class Vorteil implements Comparable<Vorteil> {

    public String name;
    public String text;
    private int kosten;
    private IVoraussetzung vor;

    public Vorteil(String name, int kosten, String text) {
        this.name = name;
        this.text = text;
        this.kosten = kosten;
    }

    public String getText() {
        return text;
    }

    public int getKosten() {
        return kosten;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " (" + kosten + ")";
    }

    @Override
    public int compareTo (Vorteil v) {
        if (v == null) return 1;
        return this.getName().compareTo(v.getName());
    }

    @Override
    public boolean equals(Object v) {
        if (v == null) return false;
        if (v.getClass() != getClass()) return false;
        return ((Vorteil) v).getName().equals(name);
    }
}

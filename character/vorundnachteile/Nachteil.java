package character.vorundnachteile;

/**
 * Created by Hauke on 24.08.2017.
 */
public class Nachteil implements Comparable<Nachteil> {

    public String name;
    public String text;
    public int bonus;
    public boolean schlechteEigenschaft;
    public int stufe;

    public Nachteil(String name, int bonus, String text, boolean se, int stufe) {
        this.name = name;
        this.text = text;
        this.bonus = bonus;
        this.schlechteEigenschaft = se;
        this.stufe = stufe;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public int getBonus() {
        return bonus;
    }

    public boolean isSchlechteEigenschaft() {
        return schlechteEigenschaft;
    }

    public int getStufe() {
        return stufe;
    }

    public void setStufe(int stufe) {
        this.stufe = stufe;
    }

    public void increaseStufe() { stufe++; }

    public void decreaseStufe() {stufe --; }

    @Override
    public String toString() {
        if (schlechteEigenschaft) {
            return name + " " + stufe + " (-" + bonus + " pro Punkt)";
        }
        return name + " (-" + bonus + ")";
    }

    @Override
    public int compareTo (Nachteil n) {
        if (n == null) {
            return 1;
        }
        return this.getName().compareTo(n.getName());
    }

    @Override
    public boolean equals(Object v) {
        if (v == null) return false;
        if (getClass() != v.getClass()) return false;
        return ((Nachteil) v).getName().equals(name);
    }
}

package character.sprachen;

/**
 * Created by Hauke on 11.09.2017.
 */
public class Schrift implements Comparable<Schrift> {

    public String name;
    public int cost;
    public String text;

    public Schrift(String name, int cost, String text) {
        this.name = name;
        this.cost = cost;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getText() {
        return text;
    }

    @Override
    public int compareTo (Schrift s) {
        try {
            if (s == null) {
                return 1;
            }
            return this.getName().compareTo(s.getName());

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred in Schrift.compareTo().");
        }
        return 0;
    }

    @Override
    public String toString() {
        return name + " (" + cost + ")";
    }
}

package character.sprachen;

/**
 * Created by Hauke on 09.09.2017.
 */
public class Sprache implements Comparable<Sprache> {

    public String name;
    public int cost;
    public int stufe;
    public int maxStufe;
    public String text;
    public boolean sprachenkunde;

    public Sprache(String name, int cost, int maxStufe, String text, boolean sprachenkunde) {
        this.name = name;
        this.cost = cost;
        this.stufe = 1;
        this.maxStufe = maxStufe;
        this.text = text;
        this.sprachenkunde = sprachenkunde;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getStufe() {
        return stufe;
    }

    public void decreaseStufe() {
        if (!(stufe <= 1)) {
            stufe--;
        }
    }

    public void increaseStufe() {
        stufe++;
    }

    public int getMaxStufe() {
        return maxStufe;
    }

    public String getText() {
        return text;
    }

    public boolean isSprachenkunde() {
        return sprachenkunde;
    }

    @Override
    public int compareTo (Sprache s) {
        try {
            if (s == null) {
                return 1;
            }
            return this.getName().compareTo(s.getName());

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred in Sprache.compareTo().");
        }
        return 0;
    }

    @Override
    public String toString() {
        String römischeStufe;
        switch(stufe) {
            case 1: römischeStufe = "I";
                    break;
            case 2: römischeStufe = "II";
                    break;
            case 3: römischeStufe = "III";
                    break;
            case 4: römischeStufe = "IV";
                    break;
            case 5: römischeStufe = "V";
                    break;
            default:römischeStufe = "II";
        }
        return name + " " + römischeStufe + " (" + cost + ")";

    }
}

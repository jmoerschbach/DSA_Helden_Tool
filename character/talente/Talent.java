package character.talente;

import character.Character;

/**
 * Created by Hauke on 24.08.2017.
 */
public class Talent extends ATalent {

    public String[] probe;
    public String tag;

    public Talent(String name, int talentwert, String spalte, String[] probe, String basis, String tag) {
        super(name, talentwert, spalte, basis);
        this.probe = probe;
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public boolean is(String tag) {
        return this.tag.equals(tag);
    }

    public int[] getProbeToIntArray() {
        int[] proben = new int[3];
        for (int i = 0; i < 3; i++) {
            switch(probe[i]) {
                case "MU": proben[i] = 0;
                    break;
                case "KL": proben[i] = 1;
                    break;
                case "IN": proben[i] = 2;
                    break;
                case "CH": proben[i] = 3;
                    break;
                case "FF": proben[i] = 4;
                    break;
                case "GE": proben[i] = 5;
                    break;
                case "KO": proben[i] = 6;
                    break;
                default  : proben[i] = 7;
                    break;
            }
        }
        return proben;
    }

    public boolean fullfillVoraussetzung(Character c) {
        int[] eig = getProbeToIntArray();
        return talentwert.get()+1 <= (Math.max(Math.max(c.getEigenschaft(eig[0]), c.getEigenschaft(eig[1])), c.getEigenschaft(eig[2]))+3);
    }

    @Override
    public String toString() {
        return name.get();
    }
}

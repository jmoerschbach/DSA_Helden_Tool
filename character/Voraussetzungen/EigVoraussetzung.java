package character.Voraussetzungen;

import character.Character;

/**
 * Created by Hauke on 13.09.2017.
 */
public class EigVoraussetzung implements IVoraussetzung{

    public String eigenschaft;
    public int eigenschaftsWert;
    public String sonderfertigkeit;
    public String oderSonderfertigkeit;
    public String nichtSonderfertigkeit;

    public EigVoraussetzung(String eigenschaft, int eigenschaftsWert, String sonderfertigkeit, String oderSonderfertigkeit, String nichtSonderfertigkeit) {
        this.eigenschaft = eigenschaft;
        this.eigenschaftsWert = eigenschaftsWert;
        this.sonderfertigkeit = sonderfertigkeit;
        this.oderSonderfertigkeit = oderSonderfertigkeit;
        this.nichtSonderfertigkeit = nichtSonderfertigkeit;
    }

    private int getEigenschaftInt() {
        int i;
        switch (eigenschaft) {
            case "MU": i = 0;
                break;
            case "KL": i = 1;
                break;
            case "IN": i = 2;
                break;
            case "CH": i = 3;
                break;
            case "FF": i = 4;
                break;
            case "GE": i = 5;
                break;
            case "KO": i = 6;
                break;
            default  : i = 7;
                break;
        }
        return i;
    }

    public boolean fullfillVoraussetzung(Character c) {
        if (c.getEigenschaft(getEigenschaftInt()) < eigenschaftsWert) return false;
        if (!sonderfertigkeit.equals("") && oderSonderfertigkeit.equals("")) {
            if (!c.hasSonderfertigkeit(sonderfertigkeit)) return false;
        } else if (!sonderfertigkeit.equals("") && !oderSonderfertigkeit.equals("")) {
            if (!c.hasSonderfertigkeit(sonderfertigkeit) && !c.hasSonderfertigkeit(oderSonderfertigkeit)) return false;
        }
        if (!nichtSonderfertigkeit.equals("")) {
            if (c.hasSonderfertigkeit(nichtSonderfertigkeit)) return false;
        }
        return true;
    }
}

package character.sonderfertigkeiten;

import character.Voraussetzungen.EigVoraussetzung;

/**
 * Created by Hauke on 13.09.2017.
 */
public class EigenschaftsSF extends ASonderfertigkeit {

    private EigVoraussetzung vor;

    public EigenschaftsSF(String name,int cost, String text, EigVoraussetzung vor) {
        super(name, cost, text);
        this.vor = vor;
    }

    public EigVoraussetzung getVor() {
        return vor;
    }
}

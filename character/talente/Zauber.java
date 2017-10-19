package character.talente;

/**
 * Created by Hauke on 05.09.2017.
 */
public abstract class Zauber extends ATalent {

    public Zauber(String name, int talentwert, String spalte, String basis) {
        super(name, talentwert, spalte, basis);
    }


    public boolean fullfillVoraussetzung(Character c) {
        //ToDo
        return false;
    }
}

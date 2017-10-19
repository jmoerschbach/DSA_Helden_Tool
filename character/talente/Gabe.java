package character.talente;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Hauke on 11.09.2017.
 */
public abstract class Gabe extends ATalent{

    public Gabe(String name) {
        super(name);
        this.talentwert = new SimpleIntegerProperty(3);
        this.spalte = new SimpleStringProperty("F");
        this.basis = "spezial";
    }


    public boolean fullfillVoraussetzung(Character c) {
        //ToDo
        return false;
    }
}

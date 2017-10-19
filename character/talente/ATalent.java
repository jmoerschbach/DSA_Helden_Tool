package character.talente;

import character.Character;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by Hauke on 24.08.2017.
 */
public abstract class ATalent implements Comparable<ATalent> {

    public SimpleStringProperty name;
    public SimpleIntegerProperty talentwert;
    public SimpleStringProperty spalte;
    public String basis;

    public ATalent(String name, int talentwert, String spalte, String basis) {
        this.name = new SimpleStringProperty(name);
        this.talentwert = new SimpleIntegerProperty(talentwert);
        this.spalte = new SimpleStringProperty(spalte);
        this.basis = basis;
    }

    public ATalent(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getName() {
        return name.get();
    }

    public int getTalentwert() {
        return talentwert.get();
    }

    public void increaseTalentwert() {
        talentwert.set(talentwert.get()+1);
    }

    public void decreaseTalentwert() { talentwert.set(talentwert.get()-1); }

    public String getSpalte() {
        return spalte.get();
    }

    public abstract boolean fullfillVoraussetzung(Character c);

    @Override
    public int compareTo(ATalent t) {
        if (t == null) return 1;
        return (-1 * (t.getName().compareTo(name.get())));
    }
}

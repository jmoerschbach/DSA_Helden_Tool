package character;

import static steigerrechner.Steigerrechner.getCost;

import character.sprachen.*;
import character.talente.ATalent;
import character.talente.Kampftalent;
import character.talente.Talent;
import character.vorundnachteile.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Observable;

/**
 * Created by Hauke on 17.10.2017.
 */
public class CharacterManager extends Observable{

    private Character character;

    public CharacterManager(Character character) {
        this.character = character;
    }

    //*******************************************************************************************
    //Eigenschaften
    //*******************************************************************************************

    public void levelUpEigenschaft(int i) {
        int cost = getCost(character.getEigenschaft(i), character.getEigenschaft(i+1), 8);
        if (character.getEigenschaft(i) < character.getMaxEigenschaft(i) && canPayAP(cost) && character.getSummeEigenschaften() < 101) {
            payAP(cost);
            character.levelUpEigenschaft(i);
            String args = "levelEigenschaft" + i;
            character.incrAPinEigenschaften(cost);
            setChanged();
            notifyObservers(args);
        }
    }

    public void levelDownEigenschaft(int i) {
        if (character.getEigenschaft(i) > 8) {
            int cost = getCost(character.getEigenschaft(i-1), character.getEigenschaft(i), 8);
            gainAP(cost);
            character.levelDownEigenschaft(i);
            String args = "levelEigenschaft" + i;
            character.decrAPinEigenschaften(cost);
            setChanged();
            notifyObservers(args);
        }
    }

    public void levelUpLeP() {
        if (character.getLebenspunkte() < character.getKO()/2 && canPayAP(50)) {
            payAP(50);
            character.levelUpLeP();
            setChanged();
            notifyObservers("LeP");
        }
    }

    public void levelDownLeP() {
        gainAP(50);
        character.levelDownLeP();
        setChanged();
        notifyObservers("LeP");
    }

    public void levelUpAsP() {
        //ToDo: AsP
    }

    public void levelDownAsP() {
        //ToDo: AsP
    }

    public void levelUpMR() {
        if (character.getMagieresistenz() < character.getMU() / 2 && canPayAP(100)) {
            character.levelUpMR();
            payAP(100);
            setChanged();
            notifyObservers("MR");
        }
    }

    public void levelDownMR() {
        if (character.getMagieresistenz() > 0) {
            gainAP(100);
            character.levelDownMR();
            setChanged();
            notifyObservers("MR");
        }
    }

    //*******************************************************************************************
    //Vor- und Nachteile
    //*******************************************************************************************

    public boolean addVorteil(Vorteil v) {
        int cost = v.getKosten();
        if (canPayAP(cost) && character.getAPinVorteilen() <= 2500 - cost) {
            payAP(cost);
            character.addVorteil(v);
            character.incrAPinVorteilen(cost);
            setChanged();
            notifyObservers("Vorteil");
            return true;
        }
        return false;
    }

    public boolean removeVorteil(Vorteil v) {
        if (character.has(v)) {
            gainAP(v.getKosten());
            character.removeVorteil(v);
            character.decrAPinVorteilen(v.getKosten());
            setChanged();
            notifyObservers("Vorteil");
            return true;
        }
        return false;
    }

    public boolean addNachteil(Nachteil n) {
        int cost = n.getBonus();
        if (n.isSchlechteEigenschaft()) {
            if (character.getPunkteInSchlechtenEigenschaften() <= 15 && character.getAPausNachteilen() >= -2500 + cost * 5) {
                gainAP(cost * 5);
                character.incrAPausNachteilen(-(cost*5));
                character.incrPunkteInSchlechtenEigenschaften(5);
                character.addNachteil(n);
                setChanged();
                notifyObservers("Nachteil");
                return true;
            }
            return false;
        } else {
            if (character.getAPausNachteilen() >= -2500 + cost) {
                gainAP(cost);
                character.incrAPausNachteilen(-cost);
                character.addNachteil(n);
                setChanged();
                notifyObservers("Nachteil");
                return true;
            }
            return false;
        }
    }

    public boolean removeNachteil(Nachteil n) {
        if (character.has(n)) {
            int cost;
            if (n.isSchlechteEigenschaft()) {
                cost = n.getStufe() * n.getBonus();
                character.decrPunkteInSchlechtenEigenschaften(n.getStufe());
                n.setStufe(5);
            } else {
                cost = n.getBonus();
            }
            payAP(cost);
            character.decrAPausNachteilen(cost);
            character.removeNachteil(n);
            setChanged();
            notifyObservers("Nachteil");
            return true;
        }
        return false;
    }

    public boolean increaseSchlechteEigenschaft(Nachteil n) {
        if (n.isSchlechteEigenschaft() && character.getPunkteInSchlechtenEigenschaften() < 20 && n.getStufe() < 12) {
            gainAP(n.getBonus());
            character.incrAPausNachteilen(n.getBonus());
            character.incrPunkteInSchlechtenEigenschaften(1);
            n.increaseStufe();
            setChanged();
            notifyObservers("Nachteil");
            return true;
        }
        return false;
    }

    public boolean decreaseSchlechteEigenschaft (Nachteil n) {
        if (n.isSchlechteEigenschaft() && n.getStufe() > 5) {
            payAP(n.getBonus());
            character.decrAPausNachteilen(n.getBonus());
            character.decrPunkteInSchlechtenEigenschaften(1);
            n.decreaseStufe();
            setChanged();
            notifyObservers("Nachteil");
            return true;
        }
        return false;
    }

    //*******************************************************************************************
    //Schriften und Sprachen
    //*******************************************************************************************

    public boolean addSprache(Sprache s) {
        int cost = s.getCost();
        if (character.getSummeSprachen() < character.getMaxSprachen() && canPayAP(cost)) {
            character.addSprache(s);
            payAP(cost);
            character.incrAPinSprachen(cost);
            character.incrSummeSprachen();
            setChanged();
            notifyObservers("Sprache");
            return true;
        }
        return false;
    }

    public boolean removeSprache(Sprache s) {
        if (character.has(s)) {
            character.removeSprache(s);
            for (int i = s.getStufe(); i >= 1; i--) {
                int stufe = s.getStufe();
                int cost = stufe * s.getCost();
                gainAP(cost);
                character.decrAPinSprachen(cost);
                s.decreaseStufe();
                character.decrSummeSprachen();
            }
            setChanged();
            notifyObservers("Sprache");
            return true;
        }
        return false;
    }

    public boolean increaseSprache(Sprache s) {
        int finalCost = s.getCost() * (s.getStufe()+1);
        if (canPayAP(finalCost) && s.getStufe() < s.getMaxStufe() && character.getSummeSprachen() < character.getMaxSprachen()) {
            if (s.getStufe() == 4 && character.getKL() < 13) return false;
            s.increaseStufe();
            int cost = s.getCost() * s.getStufe();
            payAP(cost);
            character.incrAPinSprachen(cost);
            character.incrSummeSprachen();
            setChanged();
            notifyObservers("Sprache");
            return true;
        }
        return false;
    }

    public boolean decreaseSprache(Sprache s) {
        if (s.getStufe() > 1) {
            int cost = s.getCost() * s.getStufe();
            gainAP(cost);
            character.decrAPinSprachen(cost);
            character.decrSummeSprachen();
            s.decreaseStufe();
            setChanged();
            notifyObservers("Sprache");
            return true;
        }
        return false;
    }

    public boolean learnLesen() {
        if (character.getKL() >= 10) {
            payAP(150);
            character.incrAPinSprachen(150);
            character.learnLesen();
            setChanged();
            notifyObservers("Sprache");
            return true;
        }
        return false;
    }

    public void forgetLesen(ObservableList<Schrift> mögSch) {
        ObservableList<Schrift> ol = character.getSchriften();
        if (!ol.isEmpty()) {
            for (Schrift s : ol) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        character.removeSchrift(s);
                        mögSch.add(s);
                        FXCollections.sort(mögSch);
                    }
                });
            }
        }
        character.forgetLesen();
        gainAP(150);
        character.decrAPinSprachen(150);
        character.forgetLesen();
        setChanged();
        notifyObservers("Sprache");
    }

    public boolean addSchrift(Schrift s) {
        int cost = s.getCost();
        if (canPayAP(cost) && character.getSummeSchriften() < character.getMaxSchriften()) {
            payAP(cost);
            character.incrAPinSprachen(cost);
            character.incrSummeSchriften();
            character.addSchrift(s);
            setChanged();
            notifyObservers("Sprache");
            return true;
        }
        return false;
    }

    public boolean removeSchrift(Schrift s) {
        int cost = s.getCost();
        if (character.has(s)) {
            character.removeSchrift(s);
            gainAP(cost);
            character.decrAPinSprachen(cost);
            character.decrSummeSchriften();
            setChanged();
            notifyObservers("Sprache");
            return true;
        }
        return false;
    }


    //*******************************************************************************************
    //Utility and abbreviation
    //*******************************************************************************************

    public int increaseTalent(ATalent t) {
        if (t == null) return 0;
        int cost = getCost(t.getTalentwert(), t.getTalentwert()+1, t.getSpalte());
        if (canPayAP(cost) && t.fullfillVoraussetzung(character)) {
            character.increaseTalent(t);
            payAP(cost);
            character.incrAPinTalenten(cost);
            setChanged();
            notifyObservers("Talent");
            return cost;
        }
        return 0;
    }

    public int decreaseTalent(ATalent t) {
        if (t == null) return 0;
        int TaW = t.getTalentwert();
        if (TaW > 0) {
            int cost = getCost(TaW-1, TaW, t.getSpalte());
            character.decreaseTalent(t);
            gainAP(cost);
            character.decrAPinTalenten(cost);
            setChanged();
            notifyObservers("Talent");
            return cost;
        }
        return 0;
    }

    //*******************************************************************************************
    //Utility and abbreviation
    //*******************************************************************************************

    private boolean canPayAP(int cost) { return character.canPayAP(cost); }

    private void payAP(int cost) { character.payAP(cost); }

    private void gainAP(int cost) { character.gainAP(cost); }
}

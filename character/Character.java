package character;

import character.rasseundkultur.Kultur;
import character.rasseundkultur.Rasse;
import character.sonderfertigkeiten.ASonderfertigkeit;
import character.sonderfertigkeiten.EigenschaftsSF;
import character.sprachen.Schrift;
import character.sprachen.Sprache;
import character.talente.*;
import character.vorundnachteile.Nachteil;
import character.vorundnachteile.Vorteil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Observable;

import static steigerrechner.Steigerrechner.getCost;

/**
 * Created by Hauke on 24.08.2017.
 */
public class Character extends Observable{

    public String name;
    public int AP;

    public Rasse rasse;
    public Kultur kultur;

    public int[] eigenschaften;
    public int[] maxEigenschaften;

    public int lebenspunkte;
    public int limitlessLeP;
    public int astralenergie;
    public int magieresistenz;
    public int limitessMR;

    public ObservableList<Vorteil> vorteile;
    public ObservableList<Nachteil> nachteile;

    public ObservableList<Sprache> sprachen;
    public ObservableList<Schrift> schriften;
    private int summeSprachen;
    private int summeSchriften;
    private boolean lesen;

    public ObservableList<EigenschaftsSF> eigenschaftsSFs;
    public ObservableList<ASonderfertigkeit> profaneSFs;

    public ObservableList<Kampftalent> kampftalente;
    public ObservableList<Talent> körperTalente;
    public ObservableList<Talent> interaktionsTalente;
    public ObservableList<Talent> naturTalente;
    public ObservableList<Talent> stadtTalente;
    public ObservableList<Talent> wissensTalente;
    public ObservableList<Talent> fertigkeitsTalente;
    public ObservableList<Talent> handwerksTalente;
    public ObservableList<Gabe> gaben;
    public List<Gabe> möglicheGaben;

    public List<Zauber> zauber;

//    public List<Liturgie> liturgien;

//    public List<Ritual> rituale;

    private int APinEigenschaften;
    private int APinVorteilen;
    private int APausNachteilen;
    private int PunkteInSchlechtenEigenschaften;
    private int APinSprachen;
    private int APinTalenten;
    private int APinSonderfertigkeiten;
    private int APinZauber;
    private int APinLiturgien;
    private int APinRituale;

    public Character(String name, int ap) {
        this.name = name;
        this.AP = ap;

        int[] attribute = {8, 8, 8, 8, 8, 8, 8, 8};
        this.eigenschaften = attribute;
        int[] maxAttribute = {14, 14, 14, 14, 14, 14, 14, 14};
        this.maxEigenschaften = maxAttribute;

        this.vorteile = FXCollections.observableArrayList();
        this.nachteile = FXCollections.observableArrayList();

        this.sprachen = FXCollections.observableArrayList();
        this.schriften = FXCollections.observableArrayList();
        this.summeSprachen = 0;
        this.summeSchriften = 0;
        this.lesen = false;

        this.eigenschaftsSFs = FXCollections.observableArrayList();
        this.profaneSFs = FXCollections.observableArrayList();

        this.kampftalente = FXCollections.observableArrayList();
        this.körperTalente = FXCollections.observableArrayList();
        this.interaktionsTalente = FXCollections.observableArrayList();
        this.naturTalente = FXCollections.observableArrayList();
        this.stadtTalente = FXCollections.observableArrayList();
        this.wissensTalente = FXCollections.observableArrayList();
        this.fertigkeitsTalente = FXCollections.observableArrayList();
        this.handwerksTalente = FXCollections.observableArrayList();
//        this.möglicheGaben = new ArrayList<Gabe>();

//        this.zauber = new ArrayList<Zauber>();
//        this.liturgien = new ArrayList<Liturgie>();
//        this.rituale = new ArrayList<Ritual>();
    }

    public int getAP() {
        return AP;
    }

    public boolean isRasse(Rasse r) {
        return rasse.equals(r);
    }

    public boolean isKultur(Kultur k) {
        return kultur.equals(k);
    }

    public boolean has(Vorteil v) {
        for (Vorteil vorteil : vorteile) {
            if (vorteil.equals(v)) return true;
        }
        return false;
    }

    public boolean has(Nachteil n) {
        for (Nachteil nachteil : nachteile) {
            if (nachteil.equals(n)) return true;
        }
        return false;
    }

    public boolean has(ASonderfertigkeit sf) {
        //ToDo: implementieren!
        return false;
    }

    public boolean isRasse(String s) {
        return rasse.getName().equals(s);
    }

    public boolean isKultur(String s) {
        return kultur.getName().equals(s);
    }

    public boolean hasVorteil(String s) {
        for (Vorteil v : vorteile) {
            if (v.getName().equals(s)) return true;
        }
        return false;
    }

    public boolean hasNachteil(String s) {
        for (Nachteil n : nachteile) {
            if (n.getName().equals(s)) return true;
        }
        return false;
    }

    public boolean hasSonderfertigkeit(String s) {
        return false;
    }

    public boolean hasTalent(String talent) {
        for (ATalent t : kampftalente) {
            if (t.getName().equals(talent)) return true;
        }
        for (ATalent t : körperTalente) {
            if (t.getName().equals(talent)) return true;
        }
        for (ATalent t : interaktionsTalente) {
            if (t.getName().equals(talent)) return true;
        }
        for (ATalent t : naturTalente) {
            if (t.getName().equals(talent)) return true;
        }
        for (ATalent t : stadtTalente) {
            if (t.getName().equals(talent)) return true;
        }
        for (ATalent t : wissensTalente) {
            if (t.getName().equals(talent)) return true;
        }
        for (ATalent t : fertigkeitsTalente) {
            if (t.getName().equals(talent)) return true;
        }
        for (ATalent t : handwerksTalente) {
            if (t.getName().equals(talent)) return true;
        }
        for (ATalent t : gaben) {
            if (t.getName().equals(talent)) return true;
        }
        return false;
    }

    public boolean has(ATalent talent) {
        for (ATalent t : kampftalente) {
            if (t.equals(talent)) return true;
        }
        for (ATalent t : körperTalente) {
            if (t.equals(talent)) return true;
        }
        for (ATalent t : interaktionsTalente) {
            if (t.equals(talent)) return true;
        }
        for (ATalent t : naturTalente) {
            if (t.equals(talent)) return true;
        }
        for (ATalent t : stadtTalente) {
            if (t.equals(talent)) return true;
        }
        for (ATalent t : wissensTalente) {
            if (t.equals(talent)) return true;
        }
        for (ATalent t : fertigkeitsTalente) {
            if (t.equals(talent)) return true;
        }
        for (ATalent t : handwerksTalente) {
            if (t.equals(talent)) return true;
        }
        for (ATalent t : gaben) {
            if (t.equals(talent)) return true;
        }
        return false;
    }

    public ATalent getTalent(String talent) {
        for (ATalent t : kampftalente) {
            if (t.getName().equals(talent)) return t;
        }
        for (ATalent t : körperTalente) {
            if (t.getName().equals(talent)) return t;
        }
        for (ATalent t : interaktionsTalente) {
            if (t.getName().equals(talent)) return t;
        }
        for (ATalent t : naturTalente) {
            if (t.getName().equals(talent)) return t;
        }
        for (ATalent t : stadtTalente) {
            if (t.getName().equals(talent)) return t;
        }
        for (ATalent t : wissensTalente) {
            if (t.getName().equals(talent)) return t;
        }
        for (ATalent t : fertigkeitsTalente) {
            if (t.getName().equals(talent)) return t;
        }
        for (ATalent t : handwerksTalente) {
            if (t.getName().equals(talent)) return t;
        }
        for (ATalent t : gaben) {
            if (t.getName().equals(talent)) return t;
        }
        return null;
    }

    //*******************************************************************************************
    //Rasse & Kultur
    //*******************************************************************************************

    public void setRasse(Rasse rasse) {
        this.rasse = rasse;
    }

    public void setKultur(Kultur kultur) {
        this.kultur = kultur;
    }

    public Rasse getRasse() {
        return rasse;
    }

    public Kultur getKultur() {
        return kultur;
    }

    //*******************************************************************************************
    //Eigenschaften
    //*******************************************************************************************

    public void setMaxEigenschaften(int[] maxEigenschaften) {
        this.maxEigenschaften = maxEigenschaften;
    }

    public void levelUpEigenschaft(int i){
        int cost = getCost(eigenschaften[i], eigenschaften[i]+1, 8);
        if (eigenschaften[i] < maxEigenschaften[i] && AP > cost && getSummeEigenschaften() < 101) {
            AP = AP - cost;
            eigenschaften[i] = eigenschaften[i] + 1;
            String args = "levelEigenschaft" + i;
            APinEigenschaften += cost;
            setChanged();
            notifyObservers(args);
        }
    }

    public void levelDownEigenschaft(int i){
        if (eigenschaften[i] > 8) {
            int cost = getCost(eigenschaften[i] - 1, eigenschaften[i], 8);
            AP = AP + cost;
            eigenschaften[i] = eigenschaften[i] - 1;
            String args = "levelEigenschaft" + i;
            APinEigenschaften -= cost;
            setChanged();
            notifyObservers(args);
        }
    }

    public int getSummeEigenschaften() {
        int sum = 0;
        for (int i : eigenschaften) {
            sum += i;
        }
        return sum;
    }

    public void levelUpLeP() {
        if (lebenspunkte < eigenschaften[6]/2 && AP >= 50) {
            AP -= 50;
            lebenspunkte++;
            setChanged();
            notifyObservers("LeP");
        }
    }

    public void levelDownLeP() {
        lebenspunkte--;
        AP += 50;
        setChanged();
        notifyObservers("LeP");
    }

    public void levelUpAsP() {
        if (astralenergie < eigenschaften[3]/2) {
            levelUpAsPLimitless();
        }
    }

    public void levelUpAsPLimitless() {
        if (AP > 50) {
            AP -= 50;
            astralenergie++;
            setChanged();
            notifyObservers("AsP");
        }
    }

    public void levelDownAsP() {
        AP += 50;
        astralenergie--;
        setChanged();
        notifyObservers("AsP");
    }

    public void levelUpMR() {
        if (magieresistenz < eigenschaften[0]/2 && AP >= 100) {
            AP -= 100;
            magieresistenz++;
            setChanged();
            notifyObservers("MR");
        }
    }

    public void levelDownMR() {
        AP += 100;
        magieresistenz--;
        setChanged();
        notifyObservers("MR");
    }

    public int getMU() {
        return getEigenschaft(0);
    }

    public int getKL() {
        return getEigenschaft(1);
    }

    public int getIN() {
        return getEigenschaft(2);
    }

    public int getCH() {
        return getEigenschaft(3);
    }

    public int getFF() {
        return getEigenschaft(4);
    }

    public int getGE() {
        return getEigenschaft(5);
    }

    public int getKO() {
        return getEigenschaft(6);
    }

    public int getKK() {
        return getEigenschaft(7);
    }

    public int getEigenschaft(int i) {
        return eigenschaften[i];
    }

    public int getLebenspunkte() {
        return lebenspunkte;
    }

    public int getAstralenergie() {
        return astralenergie;
    }

    public int getMagieresistenz() {
        return magieresistenz;
    }

    public int getAPinEigenschaften() {
        return APinEigenschaften;
    }

    public int getAPinVorteilen() {
        return APinVorteilen;
    }

    public int getAPausNachteilen() {
        return APausNachteilen;
    }

    public int getAPinSprachen() {
        return APinSprachen;
    }

    public int getAPinTalenten() {
        return APinTalenten;
    }

    public int getAPinSonderfertigkeiten() {
        return APinSonderfertigkeiten;
    }

    public int getAPinZauber() {
        return APinZauber;
    }

    public int getAPinLiturgien() {
        return APinLiturgien;
    }

    public int getAPinRituale() {
        return APinRituale;
    }

    public int getPunkteInSchlechtenEigenschaften() {
        return PunkteInSchlechtenEigenschaften;
    }


    //*******************************************************************************************
    //Vor- und Nachteile
    //*******************************************************************************************

    public ObservableList<Vorteil> getVorteile() {
        return vorteile;
    }

    public ObservableList<Nachteil> getNachteile() {
        return nachteile;
    }

    /***
     * Fügt Vorteil v hinzu.
     * @param v
     */
    public void addVorteil (Vorteil v) {
        vorteile.add(v);
        FXCollections.sort(vorteile);
        int cost = v.getKosten();
        AP -= cost;
        APinVorteilen += cost;
        setChanged();
        notifyObservers("Vorteil");
    }

    /***
     * Entfernt Vorteil v.
     * @param v
     */
    public void loseVorteil (Vorteil v) {
        try {
            vorteile.remove(v);
            int cost = v.getKosten();
            AP += cost;
            APinVorteilen -= cost;
            setChanged();
            notifyObservers("Vorteil");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred in removeVorteil().");
        }
    }

    /***
     * Fügt Nachteil n hinzu.
     * @param n
     */
    public void addNachteil (Nachteil n) {
        if (n.isSchlechteEigenschaft()) {
            nachteile.add(n);
            int cost = n.getBonus();
            AP += cost * 5;
            APausNachteilen -= cost * 5;
            PunkteInSchlechtenEigenschaften += 5;
            setChanged();
            notifyObservers("Nachteil");
        } else {
            nachteile.add(n);
            int cost = n.getBonus();
            AP += cost;
            APausNachteilen -= cost;
            setChanged();
            notifyObservers("Nachteil");
        }
        FXCollections.sort(nachteile);
    }

    /***
     * Entfernt Nachteil n.
     * @param n
     */
    public void loseNachteil (Nachteil n) {
        try {
            nachteile.remove(n);
            PunkteInSchlechtenEigenschaften -= n.getStufe();
            int cost;
            if (n.isSchlechteEigenschaft()) {
                cost = n.getStufe() * n.getBonus();
                n.setStufe(5);
            } else {
                cost = n.getBonus();
            }
            AP -= cost;
            APausNachteilen += cost;
            setChanged();
            notifyObservers("Nachteil");
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred in removeNachteil().");
        }
    }

    /***
     * Erhöht die Schlechte Eigenschaft n um 1.
     * @param n
     */
    public void increaseSchlechteEigenschaft(Nachteil n) {
        AP += n.getBonus();
        APausNachteilen -= n.getBonus();
        PunkteInSchlechtenEigenschaften++;
        n.increaseStufe();
        setChanged();
        notifyObservers("Nachteil");
    }

    /***
     * Verringert die Schlechte Eigenschaft n um 1.
     * @param n
     */
    public void decreaseSchlechteEigenschaft (Nachteil n) {
        AP -= n.getBonus();
        APausNachteilen += n.getBonus();
        PunkteInSchlechtenEigenschaften--;
        n.decreaseStufe();
        setChanged();
        notifyObservers("Nachteil");
    }


    //*******************************************************************************************
    //Schriften und Sprachen
    //*******************************************************************************************

    public void addSprache(Sprache s) {
        sprachen.add(s);
        FXCollections.sort(sprachen);
        int cost = s.getCost();
        AP -= cost;
        APinSprachen += cost;
        summeSprachen++;
        setChanged();
        notifyObservers("Sprache");
    }

    public void removeSprache(Sprache s) {
        sprachen.remove(s);
        summeSprachen--;
        for (int i = s.getStufe(); i >= 1; i--) {
            int stufe = s.getStufe();
            int cost = stufe * s.getCost();
            AP += cost;
            APinSprachen -= cost;
            s.decreaseStufe();
        }
        setChanged();
        notifyObservers("Sprache");
    }

    public void increaseSprache(Sprache s) {
        s.increaseStufe();
        int cost = s.getStufe() * s.getCost();
        AP -= cost;
        APinSprachen += cost;
        summeSprachen++;
        setChanged();
        notifyObservers("Sprache");

    }

    public void decreaseSprache(Sprache s) {
        int cost = s.getStufe() * s.getCost();
        AP += cost;
        APinSprachen -= cost;
        summeSprachen--;
        s.decreaseStufe();
        setChanged();
        notifyObservers("Sprache");
    }

    public void learnLesen() {
        AP -= 150;
        APinSprachen += 150;
        lesen = true;
        setChanged();
        notifyObservers("Sprache");
    }

    public void forgetLesen() {
        AP += 150;
        APinSprachen -= 150;
        lesen = false;
        setChanged();
        notifyObservers("Sprache");
    }

    public void addSchrift(Schrift s) {
        schriften.add(s);
        FXCollections.sort(schriften);
        int cost = s.getCost();
        AP -= cost;
        APinSprachen += cost;
        summeSchriften++;
        setChanged();
        notifyObservers("Sprache");
    }

    public void loseSchrift(Schrift s) {
        schriften.remove(s);
        int cost = s.getCost();
        AP += cost;
        APinSprachen -= cost;
        summeSchriften--;
        setChanged();
        notifyObservers("Sprache");
    }

    public ObservableList<Sprache> getSprachen() {
        return sprachen;
    }

    public ObservableList<Schrift> getSchriften() {
        return schriften;
    }

    public int getSummeSprachen() {
        return summeSprachen;
    }

    public int getSummeSchriften() {
        return summeSchriften;
    }

    public int getMaxSprachen() {
        int maxSprachen = eigenschaften[1];
        //ToDo: ASonderfertigkeit Sprachenkunde!
        return maxSprachen;
    }

    public boolean canRead() {
        return lesen;
    }

    //*******************************************************************************************
    //Sonderfertigkeiten
    //*******************************************************************************************

    public ObservableList<EigenschaftsSF> getEigenschaftsSFs() {
        return eigenschaftsSFs;
    }

    public ObservableList<ASonderfertigkeit> getProfaneSFs() {
        return profaneSFs;
    }

    public void addEigSF(EigenschaftsSF sf) {
        int cost = sf.getCost();
        AP -= cost;
        APinSonderfertigkeiten += cost;
        eigenschaftsSFs.add(sf);
        FXCollections.sort(eigenschaftsSFs);
        setChanged();
        notifyObservers("SF");
    }

    public void removeEigSF(EigenschaftsSF sf) {
        int cost = sf.getCost();
        AP += cost;
        APinSonderfertigkeiten -= cost;
        eigenschaftsSFs.remove(sf);
        setChanged();
        notifyObservers("SF");
    }


    //*******************************************************************************************
    //Talente
    //*******************************************************************************************

    public ATalent getTalent(ATalent talent) {
        for (ATalent t : kampftalente) {
            if (t.equals(talent)) return t;
        }
        for (ATalent t : körperTalente) {
            if (t.equals(talent)) return t;
        }
        for (ATalent t : interaktionsTalente) {
            if (t.equals(talent)) return t;
        }
        for (ATalent t : naturTalente) {
            if (t.equals(talent)) return t;
        }
        for (ATalent t : stadtTalente) {
            if (t.equals(talent)) return t;
        }
        for (ATalent t : wissensTalente) {
            if (t.equals(talent)) return t;
        }
        for (ATalent t : fertigkeitsTalente) {
            if (t.equals(talent)) return t;
        }
        for (ATalent t : handwerksTalente) {
            if (t.equals(talent)) return t;
        }
        for (ATalent t : gaben) {
            if (t.equals(talent)) return t;
        }
        return null;
    }

    public ObservableList<Kampftalent> getKampftalente() {
        return kampftalente;
    }

    public void increaseTalent(ATalent t, int cost) {
        AP -= cost;
        APinTalenten += cost;
        t.increaseTalentwert();
        setChanged();
        notifyObservers("Talent");
    }

    public void decreaseTalent(ATalent t, int cost) {
        AP += cost;
        APinTalenten -= cost;
        t.decreaseTalentwert();
        setChanged();
        notifyObservers("Talent");
    }

    public ObservableList<Talent> getKörperTalente() {
        return körperTalente;
    }

    public ObservableList<Talent> getInteraktionsTalente() {
        return interaktionsTalente;
    }

    public ObservableList<Talent> getNaturTalente() {
        return naturTalente;
    }

    public ObservableList<Talent> getStadtTalente() {
        return stadtTalente;
    }

    public ObservableList<Talent> getWissensTalente() {
        return wissensTalente;
    }

    public ObservableList<Talent> getFertigkeitsTalente() {
        return fertigkeitsTalente;
    }

    public ObservableList<Talent> getHandwerksTalente() {
        return handwerksTalente;
    }

    public ObservableList<Gabe> getGaben() {
        return gaben;
    }
}

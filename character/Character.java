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
public class Character {

    public String name;
    private int AP;

    private Rasse rasse;
    private Kultur kultur;

    private int[] eigenschaften;
    private int[] maxEigenschaften;
    public enum eigenschaftenAbkürzung { MU, KL, IN, CH, FF, GE, KO, KK }

    private int lebenspunkte;
    private int limitlessLeP;
    private int astralenergie;
    private int magieresistenz;
    public int limitessMR;

    private ObservableList<Vorteil> vorteile;
    private ObservableList<Nachteil> nachteile;

    private ObservableList<Sprache> sprachen;
    private ObservableList<Schrift> schriften;
    private int summeSprachen;
    private int summeSchriften;
    private boolean lesen;

    private ObservableList<EigenschaftsSF> eigenschaftsSFs;
    private ObservableList<ASonderfertigkeit> profaneSFs;

    private ObservableList<ATalent> kampftalente;
    private ObservableList<ATalent> körperTalente;
    private ObservableList<ATalent> interaktionsTalente;
    private ObservableList<ATalent> naturTalente;
    private ObservableList<ATalent> stadtTalente;
    private ObservableList<ATalent> wissensTalente;
    private ObservableList<ATalent> fertigkeitsTalente;
    private ObservableList<ATalent> handwerksTalente;
    private ObservableList<Gabe> gaben;
    private List<Gabe> möglicheGaben;

    private List<Zauber> zauber;

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

    //******************************************************************************************
    // Utility Functions
    //******************************************************************************************

    public int getAP() {
        return AP;
    }

    public void payAP(int i) { AP -= i; }

    public void gainAP(int i) { AP += i; }

    public boolean canPayAP(int cost) { return AP >= cost; }

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

    public boolean has(Sprache s) {
        for (Sprache sprache : sprachen) {
            if (sprache.equals(s)) return true;
        }
        return false;
    }

    public boolean has(Schrift s) {
        for (Schrift schrift : schriften) {
            if (schrift.equals(s)) return true;
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

    //******************************************************************************************
    // UI
    //******************************************************************************************



    public int getAPinEigenschaften() {
        return APinEigenschaften;
    }

    public void incrAPinEigenschaften(int i) { APinEigenschaften += i; }

    public void decrAPinEigenschaften(int i) { APinEigenschaften -= i; }

    public int getAPinVorteilen() {
        return APinVorteilen;
    }

    public void incrAPinVorteilen(int i) { APinVorteilen += i; }

    public void decrAPinVorteilen(int i) { APinVorteilen -= 1;}

    public int getAPausNachteilen() {
        return APausNachteilen;
    }

    public void incrAPausNachteilen(int i) { APausNachteilen += i; }

    public void decrAPausNachteilen(int i) { APausNachteilen -= i; }

    public int getAPinSprachen() {
        return APinSprachen;
    }

    public void incrAPinSprachen(int i) { APinSprachen += i; }

    public void decrAPinSprachen(int i) { APinSprachen -= i; }

    public void incrSummeSprachen() { summeSprachen++; }

    public void decrSummeSprachen() { summeSprachen--; }

    public void incrSummeSchriften() { summeSchriften++; }

    public void decrSummeSchriften() { summeSchriften--; }

    public int getAPinTalenten() {
        return APinTalenten;
    }

    public void incrAPinTalenten(int i) { APinTalenten += i; }

    public void decrAPinTalenten(int i) { APinTalenten -= i; }

    public int getAPinSonderfertigkeiten() {
        return APinSonderfertigkeiten;
    }

    public void incrAPinSonderfertigkeiten(int i) { APinSonderfertigkeiten += i; }

    public void decrAPinSonderfertigkeiten(int i) { APinSonderfertigkeiten -= i; }

    public int getAPinZauber() {
        return APinZauber;
    }

    public void incrAPinZauber(int i) { APinZauber += i; }

    public void decrAPinZauber(int i) { APinZauber -= i; }

    public int getAPinLiturgien() {
        return APinLiturgien;
    }

    public void incrAPinLiturgien(int i) { APinLiturgien += i; }

    public void decrAPinLiturgien(int i) { APinLiturgien -= i; }

    public int getAPinRituale() {
        return APinRituale;
    }

    public void incrAPinRituale(int i) { APinRituale += i; }

    public void decrAPinRituale(int i) { APinRituale -= i; }

    public int getPunkteInSchlechtenEigenschaften() {
        return PunkteInSchlechtenEigenschaften;
    }


    public void incrPunkteInSchlechtenEigenschaften(int i) { PunkteInSchlechtenEigenschaften += i; }

    public void decrPunkteInSchlechtenEigenschaften(int i) { PunkteInSchlechtenEigenschaften -= i; }

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
        eigenschaften[i]++;
    }

    public void levelDownEigenschaft(int i){
        eigenschaften[i]--;
    }

    public int getSummeEigenschaften() {
        int sum = 0;
        for (int i : eigenschaften) {
            sum += i;
        }
        return sum;
    }

    public void levelUpLeP() {
        lebenspunkte++;
    }

    public void levelDownLeP() {
        lebenspunkte--;
    }

    public void levelUpAsP() {
    }

    public void levelUpAsPLimitless() {
        if (AP > 50) {
            AP -= 50;
            astralenergie++;
        }
    }

    public void levelDownAsP() {
        AP += 50;
        astralenergie--;
    }

    public void levelUpMR() {
        magieresistenz++;
    }

    public void levelDownMR() {
        magieresistenz--;
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

    public int getMaxEigenschaft(int i) { return maxEigenschaften[i]; }

    public int getLebenspunkte() {
        return lebenspunkte;
    }

    public int getAstralenergie() {
        return astralenergie;
    }

    public int getMagieresistenz() {
        return magieresistenz;
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
    }

    /***
     * Entfernt Vorteil v.
     * @param v
     */
    public void removeVorteil (Vorteil v) {
        vorteile.remove(v);
    }

    /***
     * Fügt Nachteil n hinzu.
     * @param n
     */
    public void addNachteil (Nachteil n) {
        nachteile.add(n);
        FXCollections.sort(nachteile);
    }

    /***
     * Entfernt Nachteil n.
     * @param n
     */
    public void removeNachteil (Nachteil n) {
        nachteile.remove(n);
    }


    //*******************************************************************************************
    //Schriften und Sprachen
    //*******************************************************************************************

    public void addSprache(Sprache s) {
        sprachen.add(s);
        FXCollections.sort(sprachen);
    }

    public void removeSprache(Sprache s) {
        sprachen.remove(s);
    }

    public void learnLesen() {
        lesen = true;
    }

    public void forgetLesen() {
        lesen = false;
    }

    public void addSchrift(Schrift s) {
        schriften.add(s);
        FXCollections.sort(schriften);
    }

    public void removeSchrift(Schrift s) {
        schriften.remove(s);
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

    public int getMaxSchriften() {
        int maxSchriften = 999;
        //ToDo: Einrichten
        return maxSchriften;
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
//        int cost = sf.getCost();
//        AP -= cost;
//        APinSonderfertigkeiten += cost;
//        eigenschaftsSFs.add(sf);
//        FXCollections.sort(eigenschaftsSFs);
//        setChanged();
//        notifyObservers("SF");
    }

    public void removeEigSF(EigenschaftsSF sf) {
//        int cost = sf.getCost();
//        AP += cost;
//        APinSonderfertigkeiten -= cost;
//        eigenschaftsSFs.remove(sf);
//        setChanged();
//        notifyObservers("SF");
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

    public ObservableList<ATalent> getKampftalente() {
        return kampftalente;
    }

    public void increaseTalent(ATalent t) {
        t.increaseTalentwert();
    }

    public void decreaseTalent(ATalent t) {
        t.decreaseTalentwert();
    }

    public ObservableList<ATalent> getKörperTalente() {
        return körperTalente;
    }

    public ObservableList<ATalent> getInteraktionsTalente() {
        return interaktionsTalente;
    }

    public ObservableList<ATalent> getNaturTalente() {
        return naturTalente;
    }

    public ObservableList<ATalent> getStadtTalente() {
        return stadtTalente;
    }

    public ObservableList<ATalent> getWissensTalente() {
        return wissensTalente;
    }

    public ObservableList<ATalent> getFertigkeitsTalente() {
        return fertigkeitsTalente;
    }

    public ObservableList<ATalent> getHandwerksTalente() {
        return handwerksTalente;
    }

    public ObservableList<Gabe> getGaben() {
        return gaben;
    }
}

package UI.UserInterface;

import character.Character;
import character.sonderfertigkeiten.ASonderfertigkeit;
import character.sonderfertigkeiten.EigenschaftsSF;
import character.sprachen.Schrift;
import character.sprachen.Sprache;
import character.talente.Gabe;
import character.talente.Kampftalent;
import character.talente.Talent;
import character.vorundnachteile.Nachteil;
import character.vorundnachteile.Vorteil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.util.Observable;
import java.util.Observer;

import static steigerrechner.Steigerrechner.getCost;

public class Controller implements Observer {

    private Character character;
    public SplitPane splitpane;

    //Rasse & Kultur
    public Button weiterButton;
    public TabPane tabPane;

    //Eigenschaften
    public Label Mut;
    public Label Klugheit;
    public Label Intuition;
    public Label Charisma;
    public Label Fingerfertigkeit;
    public Label Gewandheit;
    public Label Konstitution;
    public Label Körperkraft;

    public Label summeEigenschaften;


    public Label Lebensenergie;
    public Label Astralenergie;
    public Label Karmaenergie;
    public Label Magieresistenz;
    public Label Erschöpfungsschwelle;
    public Label Wundschwelle;
    public Label INIBasis;
    public Label ATBasis;
    public Label PABasis;
    public Label FKBasis;


    //Vorteile & Nachteile

    public Label APinVorteile;
    public Label APausNachteile;
    public Label summeSchlechteEigenschaften;

    public ListView<Vorteil> möglicheVorteile;
    public ListView<Vorteil> gekaufteVorteile;
    public ListView<Nachteil> möglicheNachteile;
    public ListView<Nachteil> gekaufteNachteile;


    //Sprachen
    public ListView<Sprache> möglicheSprachen;
    public ListView<Sprache> gekaufteSprachen;
    public ListView<Schrift> möglicheSchriften;
    public ListView<Schrift> gekaufteSchriften;
    public CheckBox lesenUndSchreiben;

    //Talente
    public TableView<Kampftalent> kampftalente;
    public ChoiceBox<Kampftalent> kampftalenteSpezial;
    public Label steigerungskostenKampf;

    public TableView<Talent> körpertalente;
    public ChoiceBox<Talent> körpertalenteSpezial;
    public Label steigerungskostenKörper;

    public TableView<Talent> interaktionstalente;
    public ChoiceBox<Talent> interaktionstalenteSpezial;
    public Label steigerungskostenInteraktion;

    public TableView<Talent> naturtalente;
    public ChoiceBox<Talent> naturtalenteSpezial;
    public Label steigerungskostenNatur;

    public TableView<Talent> stadttalente;
    public ChoiceBox<Talent> stadttalenteSpezial;
    public Label steigerungskostenStadt;

    public TableView<Talent> wissenstalente;
    public ChoiceBox<Talent> wissenstalenteSpezial;
    public Label steigerungskostenWissen;

    public TableView<Talent> fertigkeitstalente;
    public ChoiceBox<Talent> fertigkeitstalenteSpezial;
    public Label steigerungskostenFertigkeit;

    public TableView<Talent> handwerkstalente;
    public ChoiceBox<Talent> handwerkstalenteSpezial;
    public Label steigerungskostenHandwerk;

    public TableView<Gabe> gaben;
    public Label steigerungskostenGaben;


    //Sonderfertigkeiten

    public ListView<EigenschaftsSF> möglicheEigSFs;
    public ListView<EigenschaftsSF> gekaufteEigSFs;

    public ListView<ASonderfertigkeit> möglicheProfSFs;
    public ListView<ASonderfertigkeit> gekaufteProfSFs;


    //Zauber


    //Liturgien


    //Rituale


    //Rechte Seite
    public TextArea infoLabel;

    public Label aktuelleAP;
    public Label maximaleAP;
    public Label APinEigenschaften;
    public Label summeEigenschaftenRechts;
    public Label APinVorteileRechts;
    public Label APausNachteileRechts;
    public Label summeSchlechteEigenschaftenRechts;
    public Label APinSprachen;
    public Label APinTalenten;
    public Label APinSonderfertigkeiten;
    public Label APinZauber;
    public Label APinLiturgien;
    public Label APinRituale;



    @Override
    public void update(Observable obs, Object args) {
        if (args.equals("levelEigenschaft0")) {
            Mut.setText(Integer.toString(character.getMU()));
            updateSummeEigenschaften();
            updateMR();
            updateINIBasis();
            updateATBasis();
        } else if (args.equals("levelEigenschaft1")) {
            Klugheit.setText(Integer.toString(character.getKL()));
            updateSummeEigenschaften();
            updateMR();
        } else if (args.equals("levelEigenschaft2")) {
            Intuition.setText(Integer.toString(character.getIN()));
            updateSummeEigenschaften();
            updateINIBasis();
            updatePABasis();
            updateFKBasis();
        } else if (args.equals("levelEigenschaft3")) {
            Charisma.setText(Integer.toString(character.getCH()));
            updateSummeEigenschaften();
        } else if (args.equals("levelEigenschaft4")) {
            Fingerfertigkeit.setText(Integer.toString(character.getFF()));
            updateSummeEigenschaften();
            updateFKBasis();
        } else if (args.equals("levelEigenschaft5")) {
            Gewandheit.setText(Integer.toString(character.getGE()));
            updateSummeEigenschaften();
            updateINIBasis();
            updateATBasis();
            updatePABasis();
        } else if (args.equals("levelEigenschaft6")) {
            Konstitution.setText(Integer.toString(character.getKO()));
            updateSummeEigenschaften();
            updateLeP();
            updateMR();
            updateWS();
            updateES();
        } else if (args.equals("levelEigenschaft7")) {
            Körperkraft.setText(Integer.toString(character.getKK()));
            updateSummeEigenschaften();
            updateLeP();
            updateATBasis();
            updatePABasis();
            updateFKBasis();
        }

        else if (args.equals("LeP")){
            updateLeP();
        } else if (args.equals("AsP")) {

        } else if (args.equals("MR")) {
            updateMR();
        }

        // Vor- und Nachteile
        else if (args.equals("Vorteil")) {
            updateVorteile();
        } else if (args.equals("Nachteil")) {
            updateNachteile();
        }

        //Sprachen
        else if (args.equals("Sprache")) {
            APinSprachen.setText(Integer.toString(character.getAPinSprachen()));
        }

        //Talente
        else if (args.equals("Talent")) {
            APinTalenten.setText(Integer.toString(character.getAPinTalenten()));
        }

        //Sonderfertigkeiten
        else if (args.equals("SF")) {
            APinSonderfertigkeiten.setText(Integer.toString(character.getAPinSonderfertigkeiten()));
        }





        updateAP();
    }

    private void updateAP() {
        aktuelleAP.setText(Integer.toString(character.getAP()));
        APinZauber.setText(Integer.toString(character.getAPinZauber()));
        APinLiturgien.setText(Integer.toString(character.getAPinLiturgien()));
        APinRituale.setText(Integer.toString(character.getAPinRituale()));
    }

    private void updateSummeEigenschaften() {
        APinEigenschaften.setText(Integer.toString(character.getAPinEigenschaften()));
        summeEigenschaften.setText(Integer.toString(character.getSummeEigenschaften()));
        summeEigenschaftenRechts.setText(Integer.toString(character.getSummeEigenschaften()));
    }

    private void updateLeP() {
        double sum = character.getKO() * 2 + character.getKK();
        sum /= 2;
        sum = Math.round(sum);
        int i = character.getLebenspunkte() + (int) sum;
        Lebensenergie.setText(Integer.toString(i));
    }

    private void updateAsP() {
        //ToDo!
    }

    private void updateMR() {
        double sum = character.getMU() + character.getKL() + character.getKO();
        sum /= 5;
        sum = Math.round(sum);
        int i = character.getMagieresistenz() + (int) sum;
        Magieresistenz.setText(Integer.toString(i));
    }

    private void updateES() {
        int i = (character.getKO()+1) / 2;
        //ToDo: SFs implementieren!
        Erschöpfungsschwelle.setText(Integer.toString(i));
    }

    private void updateWS() {
        int i = (character.getKO()+1) / 2;
        //ToDo: SFs implementieren!
        Wundschwelle.setText(Integer.toString(i));
    }

    private void updateINIBasis() {
        double sum = character.getMU() * 2 + character.getIN() + character.getGE();
        sum /= 5;
        sum = Math.round(sum);
        int i = (int) sum;
        INIBasis.setText(Integer.toString(i));

    }

    private void updateATBasis() {
        double sum = character.getMU() + character.getGE() + character.getKK();
        sum /= 5;
        sum = Math.round(sum);
        int i = (int) sum;
        ATBasis.setText(Integer.toString(i));

    }

    private void updatePABasis() {
        double sum = character.getIN() + character.getGE() + character.getKK();
        sum /= 5;
        sum = Math.round(sum);
        int i = (int) sum;
        PABasis.setText(Integer.toString(i));

    }

    private void updateFKBasis() {
        double sum = character.getIN() + character.getFF() + character.getKK();
        sum /= 5;
        sum = Math.round(sum);
        int i = (int) sum;
        FKBasis.setText(Integer.toString(i));

    }

    private void updateVorteile() {
        APinVorteile.setText(Integer.toString(character.getAPinVorteilen()));
        APinVorteileRechts.setText(Integer.toString(character.getAPinVorteilen()));
    }

    private void updateNachteile() {
        APausNachteile.setText(Integer.toString(character.getAPausNachteilen()));
        APausNachteileRechts.setText(Integer.toString(character.getAPausNachteilen()));
        summeSchlechteEigenschaften.setText(Integer.toString(character.getPunkteInSchlechtenEigenschaften()));
        summeSchlechteEigenschaftenRechts.setText(Integer.toString(character.getPunkteInSchlechtenEigenschaften()));
    }

    /**
     * Geht zum nächsten Tab.
     */
    public void nextTab() {
        tabPane.getSelectionModel().selectNext();
    }

    /**
     * Setzt den Character.
     *
     * @param character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }

    /**
     * Gibt den Character zurück.
     *
     * @return Character
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Gibt das SplitPane zurück.
     *
     * @return splitpane
     */
    public SplitPane getSplitpane() {
        return splitpane;
    }


    //*******************************************************************************************
    //Eigenschaften
    //*******************************************************************************************

    public void levelUpMU() {
        character.levelUpEigenschaft(0);
    }

    public void levelUpKL() {
        character.levelUpEigenschaft(1);
    }

    public void levelUpIN() {
        character.levelUpEigenschaft(2);
    }

    public void levelUpCH() {
        character.levelUpEigenschaft(3);
    }

    public void levelUpFF() {
        character.levelUpEigenschaft(4);
    }

    public void levelUpGE() {
        character.levelUpEigenschaft(5);
    }

    public void levelUpKO() {
        character.levelUpEigenschaft(6);
    }

    public void levelUpKK() {
        character.levelUpEigenschaft(7);
    }

    public void levelDownMU() {
        character.levelDownEigenschaft(0);
    }

    public void levelDownKL() {
        character.levelDownEigenschaft(1);
        if (isMaxNotSprachenInCap()) keepMaxSprachenCapped();
        if (character.getKL() < 10 && character.canRead()) unselectLesenUndSchreiben();
    }

    public void levelDownIN() {
        character.levelDownEigenschaft(2);
    }

    public void levelDownCH() {
        character.levelDownEigenschaft(3);
    }

    public void levelDownFF() {
        character.levelDownEigenschaft(4);
    }

    public void levelDownGE() {
        character.levelDownEigenschaft(5);
    }

    public void levelDownKO() {
        character.levelDownEigenschaft(6);
    }

    public void levelDownKK() {
        character.levelDownEigenschaft(7);
    }

    public void levelUpLeP() {
        character.levelUpLeP();
    }

    public void levelDownLeP() {
        character.levelDownLeP();
    }

    public void levelUpMR() {
        character.levelUpMR();
    }

    public void levelDownMR() {
        character.levelDownMR();
    }

    //*******************************************************************************************
    //Vor- und Nachteile
    //*******************************************************************************************

    /***
     * Setzt die Liste mit den bereits gekauften Vor- und Nachteilen.
     * @param vorteile
     * @param nachteile
     */
    public void setListViewsGainedVorundNachteile(ObservableList<Vorteil> vorteile, ObservableList<Nachteil> nachteile) {
        gekaufteVorteile.setItems(vorteile);
        gekaufteNachteile.setItems(nachteile);
    }

    /***
     * Setzt die Liste mit den möglichen Vorteilen.
     * @param vorteile
     */
    public void setListViewVorteile (ObservableList<Vorteil> vorteile) {
        möglicheVorteile.setItems(vorteile);
    }


    /***
     * Setzt die Liste mit den möglichen Nachteilen.
     * @param nachteile
     */
    public void setListViewNachteile (ObservableList<Nachteil> nachteile) {
        möglicheNachteile.setItems(nachteile);
    }

    /***
     * Erhält den ausgewählten Vorteil.
     */
    public void addVorteil() {
        if (!möglicheVorteile.getSelectionModel().isEmpty()) {
            Vorteil v = möglicheVorteile.getSelectionModel().getSelectedItem();
            if (character.getAP() >= v.getKosten() && character.getAPinVorteilen() <= 2500 - v.getKosten()) {
                character.addVorteil(v);
                möglicheVorteile.getItems().remove(v);
            }
        }
    }

    /***
     * Verliert den ausgewählten Vorteil.
     */
    public void removeVorteil() {
        if (!gekaufteVorteile.getSelectionModel().isEmpty()) {
            Vorteil v = gekaufteVorteile.getSelectionModel().getSelectedItem();
            ObservableList<Vorteil> ol = möglicheVorteile.getItems();
            ol.add(v);
            FXCollections.sort(ol);
            character.loseVorteil(v);
        }
    }

    /***
     * Erhält den ausgewählten Nachteil.
     */
    public void addNachteil() {
        if (!möglicheNachteile.getSelectionModel().isEmpty()) {
            Nachteil n = möglicheNachteile.getSelectionModel().getSelectedItem();
            if ((character.getPunkteInSchlechtenEigenschaften() <= 15 || !n.isSchlechteEigenschaft()) && character.getAPausNachteilen() >= -2500 + n.getBonus()) {
                character.addNachteil(n);
                möglicheNachteile.getItems().remove(n);
            }
        }
    }

    /***
     * Verliert den ausgewählten Nachteil.
     */
    public void removeNachteil() {
        if (!gekaufteNachteile.getSelectionModel().isEmpty()) {
            Nachteil n = gekaufteNachteile.getSelectionModel().getSelectedItem();
            ObservableList<Nachteil> ol = möglicheNachteile.getItems();
            ol.add(n);
            FXCollections.sort(ol);
            character.loseNachteil(n);
        }
    }

    /***
     * Erhöht die ausgewählte schlechte Eigenschaft um 1.
     */
    public void increaseSchlechteEigenschaft() {
        if (!gekaufteNachteile.getItems().isEmpty()) {
            Nachteil n = gekaufteNachteile.getSelectionModel().getSelectedItem();
            if (n.isSchlechteEigenschaft() && character.getPunkteInSchlechtenEigenschaften() < 20 && n.getStufe() < 12) {
                character.increaseSchlechteEigenschaft(n);
                gekaufteNachteile.refresh();
            }
        }
    }

    /****
     * Verringert die ausgewählte schlechte Eigenschaft um 1.
     */
    public void decreaseSchlechteEigenschaft() {
        if (!gekaufteNachteile.getItems().isEmpty()) {
            Nachteil n = gekaufteNachteile.getSelectionModel().getSelectedItem();
            if (n.isSchlechteEigenschaft() && n.getStufe() > 5) {
                character.decreaseSchlechteEigenschaft(n);
                gekaufteNachteile.refresh();
            }
        }
    }

    //******************************************************************************************
    //Sprachen & Schriften
    //******************************************************************************************

    /***
     * Setzt die Liste mit den möglichen Sprachen.
     * @param sprachen
     */
    public void setListViewSprachen(ObservableList<Sprache> sprachen) {
        this.möglicheSprachen.setItems(sprachen);
    }

    /***
     * Setzt die Liste mit den möglichen Sprachen.
     * @param schriften
     */
    public void setListViewSchriften(ObservableList<Schrift> schriften) {
        this.möglicheSchriften.setItems(schriften);
    }

    /***
     * Setzt die Liste mit den bereits gekauften Sprachen und Schriften.
     * @param sprachen
     * @param schriften
     */
    public void setListViewsGainedSprachenUndSchriften(ObservableList<Sprache> sprachen, ObservableList<Schrift> schriften) {
        gekaufteSprachen.setItems(sprachen);
        gekaufteSchriften.setItems(schriften);
    }

    /***
     * Erhält die ausgewählte Sprache.
     */
    public void addSprache() {
        if (!möglicheSprachen.getSelectionModel().isEmpty()) {
            Sprache s = möglicheSprachen.getSelectionModel().getSelectedItem();
            if (character.getSummeSprachen() < character.getMaxSprachen() && s.getCost() <= character.getAP()) {
                character.addSprache(s);
                möglicheSprachen.getItems().remove(s);
            }
        }
    }

    /***
     * Verliert die ausgewählte Sprache.
     */
    public void removeSprache() {
        if(!gekaufteSprachen.getSelectionModel().isEmpty()) {
            Sprache s = gekaufteSprachen.getSelectionModel().getSelectedItem();
            ObservableList<Sprache> ol = möglicheSprachen.getItems();
            ol.add(s);
            FXCollections.sort(ol);
            character.removeSprache(s);
        }
    }

    /***
     * Erhöht die Stufe der ausgewählten Sprache um 1.
     */
    public void increaseSprache() {
        if (!gekaufteSprachen.getItems().isEmpty()) {
            Sprache s = gekaufteSprachen.getSelectionModel().getSelectedItem();
            int finalCost = s.getCost() * (s.getStufe()+1);
            if (character.getSummeSprachen() < character.getMaxSprachen() && finalCost <= character.getAP() && s.getStufe() < s.getMaxStufe()) {
                if (s.getStufe() == 4 && character.getKL() < 13) return;
                character.increaseSprache(s);
                gekaufteSprachen.refresh();
            }
        }
    }

    /***
     * Verringert die Stufe der ausgewählten Sprache um 1.
     */
    public void decreaseSprache() {
        if (!gekaufteSprachen.getItems().isEmpty()) {
            Sprache s = gekaufteSprachen.getSelectionModel().getSelectedItem();
            if (s.getStufe() > 1) {
                character.decreaseSprache(s);
                gekaufteSprachen.refresh();
            }
        }
    }

    /***
     * Erhält die ausgewählte Schrift.
     */
    public void addSchrift() {
        if (!möglicheSchriften.getSelectionModel().isEmpty() && lesenUndSchreiben.isSelected()) {
            Schrift s = möglicheSchriften.getSelectionModel().getSelectedItem();
            if (s.getCost() <= character.getAP()) {//ToDo: maximale Anzahl an Schriften!
                character.addSchrift(s);
                möglicheSchriften.getItems().remove(s);
            }
        }
    }

    /***
     * Verliert die ausgewählte Schrift.
     */
    public void removeSchrift() {
        if(!gekaufteSchriften.getSelectionModel().isEmpty()) {
            Schrift s = gekaufteSchriften.getSelectionModel().getSelectedItem();
            ObservableList<Schrift> ol = möglicheSchriften.getItems();
            ol.add(s);
            FXCollections.sort(ol);
            character.loseSchrift(s);
        }
    }

    /***
     * Erhält Lesen&Schreiben oder verliert es wieder.
     */
    public void lesenUndSchreiben() {
        if (!lesenUndSchreiben.isSelected()) {
            unselectLesenUndSchreiben();
        } else if(character.getKL() >= 10) {
            character.learnLesen();
        } else {
            lesenUndSchreiben.setSelected(false);
        }
    }

    /***
     * Entfernt die Fähigkeit zu lesen.
     */
    private void unselectLesenUndSchreiben() {
        ObservableList<Schrift> ol = character.getSchriften();
        if (!ol.isEmpty()) {
            ObservableList<Schrift> mögSch = möglicheSchriften.getItems();
            for (Schrift s : ol) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        character.loseSchrift(s);
                        mögSch.add(s);
                        FXCollections.sort(mögSch);
                    }
                });
            }
        }
        character.forgetLesen();
        lesenUndSchreiben.setSelected(false);
    }

    /***
     * Sorgt dafür, dass die Sprachen im Cap bleiben, auch wenn KL verringert wird.
     */
    private void keepMaxSprachenCapped() {
        ObservableList<Sprache> sprachen = character.getSprachen();
        while (isMaxNotSprachenInCap()) {
            Sprache s = sprachen.get(0);
            character.decreaseSprache(s);
            gekaufteSprachen.refresh();
        }
    }

    /***
     * Fragt ab ob die Sprachen im Cap sind.
     * @return
     */
    private boolean isMaxNotSprachenInCap() {
        return character.getSummeSprachen() > character.getMaxSprachen();
    }


    //*******************************************************************************************
    //Talente
    //*******************************************************************************************

    public void setTalentViewTable(TableView tv, ObservableList ol) {
        try {
            tv.setItems(ol);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred setting the Talent-TableViews!");
        }
    }

    public void setTalentChoiceBox(ChoiceBox tv, ObservableList ol) {
        try {
            tv.setItems(ol);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred setting the Talent-ChoiceBoxes!");
        }
    }

    public TableView<Kampftalent> getKampftalente() {
        return kampftalente;
    }

    public ChoiceBox<Kampftalent> getKampftalenteSpezial() {
        return kampftalenteSpezial;
    }

    public void increaseKampftalent() {
        Kampftalent kt = kampftalente.getSelectionModel().getSelectedItem();
        if (kt == null) return;
        int cost = getCost(kt.getTalentwert(), kt.getTalentwert()+1, kt.getSpalte());
        if (character.getAP() >= cost && kt.fullfillVoraussetzung(character)) {
            character.increaseTalent(kt, cost);
            steigerungskostenKampf.setText(Integer.toString(cost));
            kampftalente.refresh();
        }
    }

    public void decreaseKampftalent() {
        Kampftalent kt = kampftalente.getSelectionModel().getSelectedItem();
        if (kt == null) return;
        int TaW = kt.getTalentwert();
        if (TaW > 0) {
            int cost = getCost(TaW-1, TaW, kt.getSpalte());
            character.decreaseTalent(kt, cost);
            steigerungskostenKampf.setText(Integer.toString(-cost));
            kampftalente.refresh();
        }
    }

    public void addKampftalent() {
        Kampftalent t = kampftalenteSpezial.getSelectionModel().getSelectedItem();
        if (t == null || kampftalente.getItems().contains(t)) return;
        kampftalenteSpezial.getItems().remove(t);
        kampftalente.getItems().add(t);
        FXCollections.sort(kampftalente.getItems());
        kampftalente.refresh();
    }

    public void increaseKörpertalent() {
        int cost = increaseTalent(körpertalente);
        if (cost != 0) {
            steigerungskostenKörper.setText(Integer.toString(cost));
        }
    }

    public void decreaseKörpertalent() {
        int cost = decreaseTalent(körpertalente);
        if (cost != 0) {
            steigerungskostenKörper.setText(Integer.toString(-cost));
        }
    }

    public void addKörpertalent() {
        addTalent(körpertalenteSpezial, körpertalente);
    }

    public void increaseInteraktionstalent() {
        int cost = increaseTalent(interaktionstalente);
        if (cost != 0) {
            steigerungskostenInteraktion.setText(Integer.toString(cost));
        }
    }

    public void decreaseInteraktionstalent() {
        int cost = decreaseTalent(interaktionstalente);
        if (cost != 0) {
            steigerungskostenInteraktion.setText(Integer.toString(-cost));
        }
    }

    public void addInteraktionstalent() {
        addTalent(interaktionstalenteSpezial, interaktionstalente);
    }

    public void increaseNaturtalent() {
        int cost = increaseTalent(naturtalente);
        if (cost != 0) {
            steigerungskostenNatur.setText(Integer.toString(cost));
        }
    }

    public void decreaseNaturtalent() {
        int cost = decreaseTalent(naturtalente);
        if (cost != 0) {
            steigerungskostenNatur.setText(Integer.toString(-cost));
        }
    }

    public void addNaturtalent() {
        addTalent(naturtalenteSpezial, naturtalente);
    }

    public void increaseStadttalent() {
        int cost = increaseTalent(stadttalente);
        if (cost != 0) {
            steigerungskostenStadt.setText(Integer.toString(cost));
        }
    }

    public void decreaseStadttalent() {
        int cost = decreaseTalent(stadttalente);
        if (cost != 0) {
            steigerungskostenStadt.setText(Integer.toString(-cost));
        }
    }

    public void addStadttalent() {
        addTalent(stadttalenteSpezial, stadttalente);
    }

    public void increaseWissenstalent() {
        int cost = increaseTalent(wissenstalente);
        if (cost != 0) {
            steigerungskostenWissen.setText(Integer.toString(cost));
        }
    }

    public void decreaseWissenstalent() {
        int cost = decreaseTalent(wissenstalente);
        if (cost != 0) {
            steigerungskostenWissen.setText(Integer.toString(-cost));
        }
    }

    public void addWissenstalent() {
        addTalent(wissenstalenteSpezial, wissenstalente);
    }

    public void increaseFertigkeitstalent() {
        int cost = increaseTalent(fertigkeitstalente);
        if (cost != 0) {
            steigerungskostenFertigkeit.setText(Integer.toString(cost));
        }
    }

    public void decreaseFertigkeitstalent() {
        int cost = decreaseTalent(fertigkeitstalente);
        if (cost != 0) {
            steigerungskostenFertigkeit.setText(Integer.toString(-cost));
        }
    }

    public void addFertigkeitstalent() {
        addTalent(fertigkeitstalenteSpezial, fertigkeitstalente);
    }

    public void increaseHandwerstalent() {
        int cost = increaseTalent(handwerkstalente);
        if (cost != 0) {
            steigerungskostenHandwerk.setText(Integer.toString(cost));
        }
    }

    public void decreaseHandwerkstalent() {
        int cost = decreaseTalent(handwerkstalente);
        if (cost != 0) {
            steigerungskostenHandwerk.setText(Integer.toString(-cost));
        }
    }

    public void addHandwerkstalent() {
        addTalent(handwerkstalenteSpezial, handwerkstalente);
    }

    public int increaseTalent(TableView<Talent> tv) {
        Talent t = tv.getSelectionModel().getSelectedItem();
        if (t == null) return 0;
        int cost = getCost(t.getTalentwert(), t.getTalentwert()+1, t.getSpalte());
        if (character.getAP() >= cost && t.fullfillVoraussetzung(character)) {
            character.increaseTalent(t, cost);
            tv.refresh();
            return cost;
        }
        return 0;
    }

    public int decreaseTalent(TableView<Talent> tv) {
        Talent t = tv.getSelectionModel().getSelectedItem();
        if (t == null) return 0;
        int cost = getCost(t.getTalentwert()-1, t.getTalentwert(), t.getSpalte());
        if (t.getTalentwert() > 0) {
            character.decreaseTalent(t, cost);
            tv.refresh();
            return cost;
        }
        return 0;
    }

    public void addTalent(ChoiceBox<Talent> box, TableView<Talent> tv) {
        Talent t = box.getSelectionModel().getSelectedItem();
        if (t == null || !(box.getItems().contains(t))) return;
        box.getItems().remove(t);
        tv.getItems().add(t);
        FXCollections.sort(tv.getItems());
        tv.refresh();
    }

    public TableView<Talent> getKörpertalente() {
        return körpertalente;
    }

    public ChoiceBox<Talent> getKörpertalenteSpezial() {
        return körpertalenteSpezial;
    }

    public TableView<Talent> getInteraktionstalente() {
        return interaktionstalente;
    }

    public ChoiceBox<Talent> getInteraktionstalenteSpezial() {
        return interaktionstalenteSpezial;
    }

    public TableView<Talent> getNaturtalente() {
        return naturtalente;
    }

    public ChoiceBox<Talent> getNaturtalenteSpezial() {
        return naturtalenteSpezial;
    }

    public TableView<Talent> getStadttalente() {
        return stadttalente;
    }

    public ChoiceBox<Talent> getStadttalenteSpezial() {
        return stadttalenteSpezial;
    }

    public TableView<Talent> getWissenstalente() {
        return wissenstalente;
    }

    public ChoiceBox<Talent> getWissenstalenteSpezial() {
        return wissenstalenteSpezial;
    }

    public TableView<Talent> getFertigkeitstalente() {
        return fertigkeitstalente;
    }

    public ChoiceBox<Talent> getFertigkeitstalenteSpezial() {
        return fertigkeitstalenteSpezial;
    }

    public TableView<Talent> getHandwerkstalente() {
        return handwerkstalente;
    }

    public ChoiceBox<Talent> getHandwerkstalenteSpezial() {
        return handwerkstalenteSpezial;
    }

//    public ChoiceBox<Gabe> getGabenSpezial() {
//        return gabenSpezial;
//    }


    //*******************************************************************************************
    //Sonderfertigkeiten
    //*******************************************************************************************

    //Eigenschaftssonderfertigkeiten
    public void setListViewEigSFs(ListView<EigenschaftsSF> lv, ObservableList<EigenschaftsSF> ol) {
        lv.setItems(ol);
    }

    public ListView<EigenschaftsSF> getMöglicheEigSFs() {
        return möglicheEigSFs;
    }

    public ListView<EigenschaftsSF> getGekaufteEigSFs() {
        return gekaufteEigSFs;
    }

    public ListView<ASonderfertigkeit> getMöglicheProfSFs() {
        return möglicheProfSFs;
    }

    public ListView<ASonderfertigkeit> getGekaufteProfSFs() {
        return gekaufteProfSFs;
    }

    public void addEigSF() {
        EigenschaftsSF sf = möglicheEigSFs.getSelectionModel().getSelectedItem();
        if (sf == null) return;
        if (character.getAP() >= sf.getCost() && sf.getVor().fullfillVoraussetzung(character)) {
            character.addEigSF(sf);
            möglicheEigSFs.getItems().remove(sf);
        }
    }

    public void removeEigSF() {
        EigenschaftsSF sf = gekaufteEigSFs.getSelectionModel().getSelectedItem();
        if (sf == null) return;
        character.removeEigSF(sf);
        möglicheEigSFs.getItems().add(sf);
    }

    //*******************************************************************************************
    //Rechte Seite
    //*******************************************************************************************

    public Label getAktuelleAP() {
        return aktuelleAP;
    }

    public Label getMaximaleAP() {
        return maximaleAP;
    }

    private void displayInfo(String s) {
        infoLabel.setText(s);
    }

    public void initialize() {
        //Lesen&Schreiben
        lesenUndSchreiben.setAllowIndeterminate(false);
        lesenUndSchreiben.setSelected(false);

        //InfoLabel
        infoLabel.setWrapText(true);

        //Vorteile
        möglicheVorteile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Vorteil v = möglicheVorteile.getSelectionModel().getSelectedItem();
                if (v != null) displayInfo(v.getText());
            }
        });
        gekaufteVorteile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Vorteil v = gekaufteVorteile.getSelectionModel().getSelectedItem();
                if (v != null) displayInfo(v.getText());
            }
        });
        möglicheNachteile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Nachteil n = möglicheNachteile.getSelectionModel().getSelectedItem();
                if (n != null) displayInfo(n.getText());
            }
        });
        gekaufteNachteile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Nachteil n = gekaufteNachteile.getSelectionModel().getSelectedItem();
                if (n != null) displayInfo(n.getText());
            }
        });

        //Sprachen
        möglicheSprachen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Sprache s = möglicheSprachen.getSelectionModel().getSelectedItem();
                if (s != null) displayInfo(s.getText());
            }
        });
        gekaufteSprachen.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Sprache s = gekaufteSprachen.getSelectionModel().getSelectedItem();
                if (s != null) displayInfo(s.getText());
            }
        });
        möglicheSchriften.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Schrift s = möglicheSchriften.getSelectionModel().getSelectedItem();
                if (s != null) displayInfo(s.getText());
            }
        });
        gekaufteSchriften.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Schrift s = gekaufteSchriften.getSelectionModel().getSelectedItem();
                if (s != null) displayInfo(s.getText());
            }
        });

        //Sonderfertigkeiten
        möglicheEigSFs.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ASonderfertigkeit SF = möglicheEigSFs.getSelectionModel().getSelectedItem();
                if (SF != null) displayInfo(SF.getText());
            }
        });
        gekaufteEigSFs.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ASonderfertigkeit SF = gekaufteEigSFs.getSelectionModel().getSelectedItem();
                if (SF != null) displayInfo(SF.getText());
            }
        });
        möglicheProfSFs.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ASonderfertigkeit SF = möglicheProfSFs.getSelectionModel().getSelectedItem();
                if (SF != null) displayInfo(SF.getText());
            }
        });
        gekaufteProfSFs.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ASonderfertigkeit SF = gekaufteProfSFs.getSelectionModel().getSelectedItem();
                if (SF != null) displayInfo(SF.getText());
            }
        });

        //Talente
        gaben.setItems(FXCollections.observableArrayList());


        int i = 0;
        for (TableColumn tc : kampftalente.getColumns()) {
            if (i == 0) tc.setCellValueFactory(new PropertyValueFactory<Kampftalent, String>("name"));
            if (i == 1) tc.setCellValueFactory(new PropertyValueFactory<Kampftalent, String>("spalte"));
            if (i == 2) tc.setCellValueFactory(new PropertyValueFactory<Kampftalent, String>("talentwert"));
            i++;
        }
        i = 0;
        for (TableColumn tc : körpertalente.getColumns()) {
            if (i == 0) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("name"));
            if (i == 1) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("spalte"));
            if (i == 2) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("talentwert"));
            i++;
        }
        i = 0;
        for (TableColumn tc : interaktionstalente.getColumns()) {
            if (i == 0) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("name"));
            if (i == 1) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("spalte"));
            if (i == 2) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("talentwert"));
            i++;
        }
        i = 0;
        for (TableColumn tc : naturtalente.getColumns()) {
            if (i == 0) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("name"));
            if (i == 1) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("spalte"));
            if (i == 2) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("talentwert"));
            i++;
        }
        i = 0;
        for (TableColumn tc : stadttalente.getColumns()) {
            if (i == 0) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("name"));
            if (i == 1) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("spalte"));
            if (i == 2) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("talentwert"));
            i++;
        }
        i = 0;
        for (TableColumn tc : wissenstalente.getColumns()) {
            if (i == 0) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("name"));
            if (i == 1) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("spalte"));
            if (i == 2) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("talentwert"));
            i++;
        }
        i = 0;
        for (TableColumn tc : fertigkeitstalente.getColumns()) {
            if (i == 0) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("name"));
            if (i == 1) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("spalte"));
            if (i == 2) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("talentwert"));
            i++;
        }
        i = 0;
        for (TableColumn tc : handwerkstalente.getColumns()) {
            if (i == 0) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("name"));
            if (i == 1) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("spalte"));
            if (i == 2) tc.setCellValueFactory(new PropertyValueFactory<Talent, String>("talentwert"));
            i++;
        }
    }

//    public void setCellsForKampftalente(ObservableList<TableColumn> columns) {
//        columns.get(0).setCellValueFactory(new PropertyValueFactory<Kampftalent, String>("name"));
//        columns.get(1).setCellValueFactory(new PropertyValueFactory<Kampftalent, String>("spalte"));
//        columns.get(2).setCellValueFactory(new PropertyValueFactory<Kampftalent, String>("talentwert"));
//    }
//
//    public void setCellsForTalente(ObservableList<TableColumn<Talent, String>> columns) {
//        columns.get(0).setCellValueFactory(new PropertyValueFactory<Talent, String>("name"));
//        columns.get(1).setCellValueFactory(new PropertyValueFactory<Talent, String>("spalte"));
//        columns.get(2).setCellValueFactory(new PropertyValueFactory<Talent, String>("talentwert"));
//    }

}

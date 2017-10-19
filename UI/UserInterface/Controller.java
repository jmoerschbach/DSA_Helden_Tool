package UI.UserInterface;

import character.Character;
import character.CharacterManager;
import character.sonderfertigkeiten.ASonderfertigkeit;
import character.sonderfertigkeiten.EigenschaftsSF;
import character.sprachen.Schrift;
import character.sprachen.Sprache;
import character.talente.ATalent;
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
    private CharacterManager characterManager;
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
    public TableView<ATalent> kampftalente;
    public ChoiceBox<ATalent> kampftalenteSpezial;
    public Label steigerungskostenKampf;

    public TableView<ATalent> körpertalente;
    public ChoiceBox<ATalent> körpertalenteSpezial;
    public Label steigerungskostenKörper;

    public TableView<ATalent> interaktionstalente;
    public ChoiceBox<ATalent> interaktionstalenteSpezial;
    public Label steigerungskostenInteraktion;

    public TableView<ATalent> naturtalente;
    public ChoiceBox<ATalent> naturtalenteSpezial;
    public Label steigerungskostenNatur;

    public TableView<ATalent> stadttalente;
    public ChoiceBox<ATalent> stadttalenteSpezial;
    public Label steigerungskostenStadt;

    public TableView<ATalent> wissenstalente;
    public ChoiceBox<ATalent> wissenstalenteSpezial;
    public Label steigerungskostenWissen;

    public TableView<ATalent> fertigkeitstalente;
    public ChoiceBox<ATalent> fertigkeitstalenteSpezial;
    public Label steigerungskostenFertigkeit;

    public TableView<ATalent> handwerkstalente;
    public ChoiceBox<ATalent> handwerkstalenteSpezial;
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

    /***
     * Setzt den CharacterManager
     *
     * @param cm
     */
    public void setCharacterManager(CharacterManager cm) { this.characterManager = cm; }

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
        characterManager.levelUpEigenschaft(0);
    }

    public void levelUpKL() {
        characterManager.levelUpEigenschaft(1);
    }

    public void levelUpIN() {
        characterManager.levelUpEigenschaft(2);
    }

    public void levelUpCH() {
        characterManager.levelUpEigenschaft(3);
    }

    public void levelUpFF() {
        characterManager.levelUpEigenschaft(4);
    }

    public void levelUpGE() {
        characterManager.levelUpEigenschaft(5);
    }

    public void levelUpKO() {
        characterManager.levelUpEigenschaft(6);
    }

    public void levelUpKK() {
        characterManager.levelUpEigenschaft(7);
    }

    public void levelDownMU() {
        characterManager.levelDownEigenschaft(0);
    }

    public void levelDownKL() {
        characterManager.levelDownEigenschaft(1);
        //ToDo: Sprachen
//        if (isMaxNotSprachenInCap()) keepMaxSprachenCapped();
//        if (character.getKL() < 10 && character.canRead()) unselectLesenUndSchreiben();
    }

    public void levelDownIN() {
        characterManager.levelDownEigenschaft(2);
    }

    public void levelDownCH() {
        characterManager.levelDownEigenschaft(3);
    }

    public void levelDownFF() {
        characterManager.levelDownEigenschaft(4);
    }

    public void levelDownGE() {
        characterManager.levelDownEigenschaft(5);
    }

    public void levelDownKO() {
        characterManager.levelDownEigenschaft(6);
    }

    public void levelDownKK() {
        characterManager.levelDownEigenschaft(7);
    }

    public void levelUpLeP() {
        characterManager.levelUpLeP();
    }

    public void levelDownLeP() {
        characterManager.levelDownLeP();
    }

    public void levelUpMR() {
        characterManager.levelUpMR();
    }

    public void levelDownMR() {
        characterManager.levelDownMR();
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
            if (characterManager.addVorteil(v)) {
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
            if (characterManager.removeVorteil(v)) {
                ObservableList<Vorteil> ol = möglicheVorteile.getItems();
                ol.add(v);
                FXCollections.sort(ol);
            }
        }
    }

    /***
     * Erhält den ausgewählten Nachteil.
     */
    public void addNachteil() {
        if (!möglicheNachteile.getSelectionModel().isEmpty()) {
            Nachteil n = möglicheNachteile.getSelectionModel().getSelectedItem();
            if (characterManager.addNachteil(n)) {
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
            if (characterManager.removeNachteil(n)) {
                ObservableList<Nachteil> ol = möglicheNachteile.getItems();
                ol.add(n);
                FXCollections.sort(ol);
            }
        }
    }

    /***
     * Erhöht die ausgewählte schlechte Eigenschaft um 1.
     */
    public void increaseSchlechteEigenschaft() {
        if (!gekaufteNachteile.getItems().isEmpty()) {
            Nachteil n = gekaufteNachteile.getSelectionModel().getSelectedItem();
            if (characterManager.increaseSchlechteEigenschaft(n)) {
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
            if (characterManager.decreaseSchlechteEigenschaft(n)) {
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
            if (characterManager.addSprache(s)) {
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
            if (characterManager.removeSprache(s)) {
                ObservableList<Sprache> ol = möglicheSprachen.getItems();
                ol.add(s);
                FXCollections.sort(ol);
            }
        }
    }

    /***
     * Erhöht die Stufe der ausgewählten Sprache um 1.
     */
    public void increaseSprache() {
        if (!gekaufteSprachen.getItems().isEmpty()) {
            Sprache s = gekaufteSprachen.getSelectionModel().getSelectedItem();
            if (characterManager.increaseSprache(s)) {
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
            if (characterManager.decreaseSprache(s)) {
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
            if (characterManager.addSchrift(s)) {
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
            if (characterManager.removeSchrift(s)) {
                ObservableList<Schrift> ol = möglicheSchriften.getItems();
                ol.add(s);
                FXCollections.sort(ol);
            }
        }
    }

    /***
     * Erhält Lesen&Schreiben oder verliert es wieder.
     */
    public void lesenUndSchreiben() {
        if (!lesenUndSchreiben.isSelected()) {
            characterManager.forgetLesen(möglicheSchriften.getItems());
            lesenUndSchreiben.setSelected(false);
        } else {
            if(!characterManager.learnLesen()) {
                lesenUndSchreiben.setSelected(false);
            }
        }
    }

    /***
     * Sorgt dafür, dass die Sprachen im Cap bleiben, auch wenn KL verringert wird.
     */
//    private void keepMaxSprachenCapped() {
//        ObservableList<Sprache> sprachen = character.getSprachen();
//        while (isMaxNotSprachenInCap()) {
//            Sprache s = sprachen.get(0);
//            character.decreaseSprache(s);
//            gekaufteSprachen.refresh();
//        }
//    }

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

    public TableView<ATalent> getKampftalente() {
        return kampftalente;
    }

    public ChoiceBox<ATalent> getKampftalenteSpezial() {
        return kampftalenteSpezial;
    }

    public void increaseKampftalent() {
        int cost = increaseTalent(kampftalente);
        if (cost != 0) {
            steigerungskostenKampf.setText(Integer.toString(cost));
        }
    }

    public void decreaseKampftalent() {
        int cost = decreaseTalent(kampftalente);
        if (cost != 0) {
            steigerungskostenKampf.setText(Integer.toString(-cost));
        }
    }

    public void addKampftalent() {
        addTalent(kampftalenteSpezial, kampftalente);
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

    private int increaseTalent(TableView<ATalent> tv) {
        ATalent t = tv.getSelectionModel().getSelectedItem();
        int cost = characterManager.increaseTalent(t);
        if (cost != 0) {
            tv.refresh();
        }
        return cost;
    }

    private int decreaseTalent(TableView<ATalent> tv) {
        ATalent t = tv.getSelectionModel().getSelectedItem();
        int cost = characterManager.decreaseTalent(t);
        if (cost != 0) {
            tv.refresh();
        }
        return cost;
    }

    private void addTalent(ChoiceBox<ATalent> box, TableView<ATalent> tv) {
        ATalent t = box.getSelectionModel().getSelectedItem();
        if (t == null || !(box.getItems().contains(t))) return;
        box.getItems().remove(t);
        tv.getItems().add(t);
        FXCollections.sort(tv.getItems());
        tv.refresh();
    }

    public TableView<ATalent> getKörpertalente() {
        return körpertalente;
    }

    public ChoiceBox<ATalent> getKörpertalenteSpezial() {
        return körpertalenteSpezial;
    }

    public TableView<ATalent> getInteraktionstalente() {
        return interaktionstalente;
    }

    public ChoiceBox<ATalent> getInteraktionstalenteSpezial() {
        return interaktionstalenteSpezial;
    }

    public TableView<ATalent> getNaturtalente() {
        return naturtalente;
    }

    public ChoiceBox<ATalent> getNaturtalenteSpezial() {
        return naturtalenteSpezial;
    }

    public TableView<ATalent> getStadttalente() {
        return stadttalente;
    }

    public ChoiceBox<ATalent> getStadttalenteSpezial() {
        return stadttalenteSpezial;
    }

    public TableView<ATalent> getWissenstalente() {
        return wissenstalente;
    }

    public ChoiceBox<ATalent> getWissenstalenteSpezial() {
        return wissenstalenteSpezial;
    }

    public TableView<ATalent> getFertigkeitstalente() {
        return fertigkeitstalente;
    }

    public ChoiceBox<ATalent> getFertigkeitstalenteSpezial() {
        return fertigkeitstalenteSpezial;
    }

    public TableView<ATalent> getHandwerkstalente() {
        return handwerkstalente;
    }

    public ChoiceBox<ATalent> getHandwerkstalenteSpezial() {
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

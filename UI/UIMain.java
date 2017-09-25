package UI;

import UI.StartUIs.APAuswahlController;
import UI.StartUIs.StartController;
import UI.UserInterface.Controller;
import character.Character;
import character.Voraussetzungen.EigVoraussetzung;
import character.sonderfertigkeiten.ASonderfertigkeit;
import character.sonderfertigkeiten.EigenschaftsSF;
import character.sprachen.Schrift;
import character.sprachen.Sprache;
import character.talente.Kampftalent;
import character.talente.Talent;
import character.vorundnachteile.Nachteil;
import character.vorundnachteile.Vorteil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Hauke on 07.09.2017.
 */
public class UIMain {

    Controller controller;
    APAuswahlController apAuswahlController;

    public static void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(UIMain.class.getResource("StartUIs/StartInterface.fxml"));
        Parent root = loader.load();
        stage.setTitle("DSA Heldentool");
        stage.setScene(new Scene(root, 686, 119));
        stage.show();
        StartController startController = loader.getController();
        startController.setStage(stage);
    }

    public static void neu() throws Exception {
        FXMLLoader loader = new FXMLLoader(UIMain.class.getResource("StartUIs/APAuswahl.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("DSA Heldentool");
        stage.setScene(new Scene(root, 436, 176));
        stage.show();
        APAuswahlController apAuswahlController = loader.getController();
        apAuswahlController.setStage(stage);
        apAuswahlController.getApField().setText("16500");
    }

    public static void laden() throws Exception {
        System.out.println("test");
    }

    public static void startNewCharakter(String name, int ap) throws Exception {
        FXMLLoader loader = new FXMLLoader(UIMain.class.getResource("UserInterface/UserInterface.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();

        Stage stage = new Stage();
        stage.setTitle("DSA Heldentool");
        stage.setScene(new Scene(root, 1000, 800));
        stage.setMaximized(true);
        stage.show();
        initializeScene(controller, name, ap);

    }

    private static void initializeScene(Controller controller, String name, int ap) {
        controller.getSplitpane().setDividerPositions(0.8);
        controller.getAktuelleAP().setText(Integer.toString(ap));
        controller.getMaximaleAP().setText(Integer.toString(ap));

        Character c = new Character(name, ap);
        c.addObserver(controller);
        controller.setCharacter(c);

        //Vor- und Nachteile

        try {
            //Vorteile
            ObservableList<Vorteil> vorteile = FXCollections.observableArrayList();
            Scanner scanner = new Scanner(new FileReader("rsrc_Vorteile.csv"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                Scanner tokenscanner = new Scanner(s);
                tokenscanner.useDelimiter(";");
                while (tokenscanner.hasNext()) {
                    String n = tokenscanner.next();
                    int kosten = tokenscanner.nextInt();
                    String text = tokenscanner.next();

                    vorteile.add(new Vorteil(n, kosten, text));
                }
            }
            controller.setListViewVorteile(vorteile);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred reading the Vorteile!");
        }
        try {
            //Nachteile
            ObservableList<Nachteil> nachteile = FXCollections.observableArrayList();
            Scanner scanner = new Scanner(new FileReader("rsrc_Nachteile.csv"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                Scanner tokenscanner = new Scanner(s);
                tokenscanner.useDelimiter(";");
                while (tokenscanner.hasNext()) {
                    String n = tokenscanner.next();
                    int bonus = tokenscanner.nextInt();
                    String text = tokenscanner.next();
                    boolean SE = tokenscanner.nextBoolean();
                    int stufe = 0;
                    if (SE) stufe = 5;
                    nachteile.add(new Nachteil(n, bonus, text, SE, stufe));
                }
            }
            controller.setListViewNachteile(nachteile);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred reading the Nachteile!");
        }
        controller.setListViewsGainedVorundNachteile(c.getVorteile(), c.getNachteile());

        //Sprachen
        try {
            ObservableList<Sprache> sprachen = FXCollections.observableArrayList();
            Scanner scanner = new Scanner(new FileReader("rsrc_Sprachen.csv"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                Scanner tokenscanner = new Scanner(s);
                tokenscanner.useDelimiter(";");
                while (tokenscanner.hasNext()) {
                    String n = tokenscanner.next();
                    int cost = tokenscanner.nextInt();
                    int maxStufe = tokenscanner.nextInt();
                    String text = tokenscanner.next();
                    boolean sprachenkunde = tokenscanner.nextBoolean();
                    sprachen.add(new Sprache(n, cost, maxStufe, text, sprachenkunde));
                }
            }
            controller.setListViewSprachen(sprachen);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred reading the Sprachen!");
        }
        //Schriften
        try {
            ObservableList<Schrift> schriften = FXCollections.observableArrayList();
            Scanner scanner = new Scanner(new FileReader("rsrc_Schriften.csv"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                Scanner tokenscanner = new Scanner(s);
                tokenscanner.useDelimiter(";");
                while (tokenscanner.hasNext()) {
                    String n = tokenscanner.next();
                    int cost = tokenscanner.nextInt();
                    String text = tokenscanner.next();
                    schriften.add(new Schrift(n, cost, text));
                }
            }
            controller.setListViewSchriften(schriften);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred reading the Schriften!");
        }
        controller.setListViewsGainedSprachenUndSchriften(c.getSprachen(), c.getSchriften());

        //Talente

        //Kampftalente
        try{
            ObservableList<Kampftalent> kampftalente = c.getKampftalente();
            ObservableList<Kampftalent> kampftalenteSpezial = FXCollections.observableArrayList();
            Scanner scanner = new Scanner(new FileReader("rsrc_Kampftalente.csv"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                Scanner tokenscanner = new Scanner(s);
                tokenscanner.useDelimiter(";");
                while (tokenscanner.hasNext()) {
                    try {
                        String n = tokenscanner.next();
                        int talentwert = 0;
                        String spalte = tokenscanner.next();
                        String basis = tokenscanner.next();
                        String kategorie = tokenscanner.next();
                        if (basis.equals("Basis")) {
                            kampftalente.add(new Kampftalent(n, talentwert, spalte, basis, kategorie));
                        } else if (basis.equals("Spezial")) {
                            kampftalenteSpezial.add(new Kampftalent(n, talentwert, spalte, basis, kategorie));
                        } else {
                            throw new Exception("Wrong Tag in Kampftalente!");
                        }

                    } catch (Exception e) {
                        System.out.println(e.toString());
                        System.out.println(s);
                    }
                }
            }
            controller.setTalentViewTable(controller.getKampftalente(), kampftalente);
            controller.setTalentChoiceBox(controller.getKampftalenteSpezial(), kampftalenteSpezial);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred reading the Kampftalente!");
        }

        //Alle anderen Talente
        try {
            ObservableList<Talent> körpertalente = c.getKörperTalente();
            ObservableList<Talent> körpertalenteSpezial = FXCollections.observableArrayList();
            ObservableList<Talent> interaktionstalente = c.getInteraktionsTalente();
            ObservableList<Talent> interaktionstalenteSpezial = FXCollections.observableArrayList();
            ObservableList<Talent> naturtalente = c.getNaturTalente();
            ObservableList<Talent> naturtalenteSpezial = FXCollections.observableArrayList();
            ObservableList<Talent> stadttalente = c.getStadtTalente();
            ObservableList<Talent> stadttalenteSpezial = FXCollections.observableArrayList();
            ObservableList<Talent> wissenstalente = c.getWissensTalente();
            ObservableList<Talent> wissenstalenteSpezial = FXCollections.observableArrayList();
            ObservableList<Talent> fertigkeitstalente = c.getFertigkeitsTalente();
            ObservableList<Talent> fertigkeitstalenteSpezial = FXCollections.observableArrayList();
            ObservableList<Talent> handwerkstalente = c.getHandwerksTalente();
            ObservableList<Talent> handwerkstalenteSpezial = FXCollections.observableArrayList();
            Scanner scanner = new Scanner(new FileReader("rsrc_Talente.csv"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                Scanner tokenscanner = new Scanner (s);
                tokenscanner.useDelimiter(";");
                while (tokenscanner.hasNext()) {
                    String n = tokenscanner.next();
                    int talentwert = 0;
                    String spalte = tokenscanner.next();
                    String p = tokenscanner.next();
                    String[] proben = new String[3];
                    Scanner tokentokenscanner = new Scanner(p);
                    tokentokenscanner.useDelimiter("/");
                    for (int i = 0; tokentokenscanner.hasNext(); i++) {
                        proben[i] = tokentokenscanner.next();
                    }
                    tokentokenscanner.close();
                    String basis = tokenscanner.next();
                    String tag = tokenscanner.next();
                    n = n + " (" + p + ")";
                    Talent t = new Talent(n, talentwert, spalte, proben, basis, tag);
                    if (basis.equals("Basis")) {
                        if (tag.equals("Koerper")) körpertalente.add(t);
                        else if (tag.equals("Interaktion")) interaktionstalente.add(t);
                        else if (tag.equals("Natur")) naturtalente.add(t);
                        else if (tag.equals("Stadt")) stadttalente.add(t);
                        else if (tag.equals("Wissen")) wissenstalente.add(t);
                        else if (tag.equals("Fertigkeit")) fertigkeitstalente.add(t);
                        else if (tag.equals("Handwerk")) handwerkstalente.add(t);
                        else throw new Exception("Wrong tag in Basistalente!" );
                    } else if (basis.equals("Spezial")) {
                        if (tag.equals("Koerper")) körpertalenteSpezial.add(t);
                        else if (tag.equals("Interaktion")) interaktionstalenteSpezial.add(t);
                        else if (tag.equals("Natur")) naturtalenteSpezial.add(t);
                        else if (tag.equals("Stadt")) stadttalenteSpezial.add(t);
                        else if (tag.equals("Wissen")) wissenstalenteSpezial.add(t);
                        else if (tag.equals("Fertigkeit")) fertigkeitstalenteSpezial.add(t);
                        else if (tag.equals("Handwerk")) handwerkstalenteSpezial.add(t);
                        else throw new Exception("Wrong tag in Spezialtalente!");

                    } else {
                        throw new Exception("Wrong Basis-Tag in Talente at Talent " + n);
                    }
                }
            }
            controller.setTalentViewTable(controller.getKörpertalente(), körpertalente);
            controller.setTalentChoiceBox(controller.getKörpertalenteSpezial(), körpertalenteSpezial);
            controller.setTalentViewTable(controller.getInteraktionstalente(), interaktionstalente);
            controller.setTalentChoiceBox(controller.getInteraktionstalenteSpezial(), interaktionstalenteSpezial);
            controller.setTalentViewTable(controller.getNaturtalente(), naturtalente);
            controller.setTalentChoiceBox(controller.getNaturtalenteSpezial(), naturtalenteSpezial);
            controller.setTalentViewTable(controller.getStadttalente(), stadttalente);
            controller.setTalentChoiceBox(controller.getStadttalenteSpezial(), stadttalenteSpezial);
            controller.setTalentViewTable(controller.getWissenstalente(), wissenstalente);
            controller.setTalentChoiceBox(controller.getWissenstalenteSpezial(), wissenstalenteSpezial);
            controller.setTalentViewTable(controller.getFertigkeitstalente(), fertigkeitstalente);
            controller.setTalentChoiceBox(controller.getFertigkeitstalenteSpezial(), fertigkeitstalenteSpezial);
            controller.setTalentViewTable(controller.getHandwerkstalente(), handwerkstalente);
            controller.setTalentChoiceBox(controller.getHandwerkstalenteSpezial(), handwerkstalenteSpezial);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred reading the Talente!");
        }

        //Eigenschaftssonderfertigkeiten
        try{
            ObservableList<EigenschaftsSF> eigenschaftsSFs = FXCollections.observableArrayList();
            ObservableList<EigenschaftsSF> gekaufteEigenschaftsSFs = c.getEigenschaftsSFs();
            Scanner scanner = new Scanner(new FileReader("rsrc_Eigenschaftssonderfertigkeiten.csv"));
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                Scanner tokenscanner = new Scanner(s);
                tokenscanner.useDelimiter(";");
                while (tokenscanner.hasNext()) {
                    String n = tokenscanner.next();
                    int cost = tokenscanner.nextInt();
                    String text = tokenscanner.next();
                    //Eigenschaftsvoraussetzungen
                    String eigenschaft = tokenscanner.next();
                    int eigenschaftsWert = tokenscanner.nextInt();
                    String sonderfertigkeit = tokenscanner.next();
                    String oderSonderfertigkeit = tokenscanner.next();
                    String nichtSonderfertigkeit = tokenscanner.next();
                    EigVoraussetzung vor = new EigVoraussetzung(eigenschaft, eigenschaftsWert, sonderfertigkeit, oderSonderfertigkeit, nichtSonderfertigkeit);
                    //
                    eigenschaftsSFs.add(new EigenschaftsSF(n, cost, text, vor));
                }
            }
            controller.setListViewEigSFs(controller.getMöglicheEigSFs(), eigenschaftsSFs);
            controller.setListViewEigSFs(controller.getGekaufteEigSFs(), gekaufteEigenschaftsSFs);
        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred reading the Eigenschaftssonderfertigkeiten!");
        }


    }
}

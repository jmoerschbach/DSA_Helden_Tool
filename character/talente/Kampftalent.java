package character.talente;

import character.Character;

/**
 * Created by Hauke on 05.09.2017.
 */
public class Kampftalent extends ATalent {

    public String kategorie;

    public Kampftalent(String name, int talentwert, String spalte, String basis, String kategorie) {
        super(name, talentwert, spalte, basis);
        this.kategorie = kategorie;
    }

    public String getKategorie() {
        return kategorie;
    }

    public boolean fullfillVoraussetzung(Character c) {
        try {
            if (kategorie.equals("Nahkampf")) {
                return (talentwert.get()+1 <= (Math.max(c.getKK(), c.getGE()))+3);
            } else if (kategorie.equals("Fernkampf")) {
                return (talentwert.get()+1 <= (Math.max(Math.max(c.getFF(), c.getGE()), c.getKK()))+3);
            }
            throw new Exception("Wrong Kategorie!");

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("Error occurred in Kampftalent.fullfillVoraussetzung()!");
        }
        return false;
    }

    @Override
    public String toString() {
        return name.get();
    }
}

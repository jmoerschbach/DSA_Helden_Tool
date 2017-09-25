package character.Voraussetzungen;

import character.Character;

import java.util.Map;
import java.util.Set;

/**
 * Created by Hauke on 10.09.2017.
 */
public class Voraussetzung {
    public int[] eigenschaften;
    public String rasse;
    public String kultur;
    public Set<String> vorteil;
    public Set<String> nachteil;
    public Set<String> keinVorteil;
    public Set<String> keinNachteil;
    public Set<String> sonderfertigkeiten;
    public Set<String> keineSonderfertigkeiten;
    public Map<String, Integer> talente;

    //Voraussetzungen für Sonderfertigkeiten
    public Voraussetzung(int[] eigenschaften, Set<String> vorteil, Set<String> nachteil, Set<String> keinVorteil, Set<String> keinNachteil, Set<String> sonderfertigkeiten, Set<String> keineSonderfertigkeiten, Map<String, Integer> talente) {
        this.eigenschaften = eigenschaften;
        this.vorteil = vorteil;
        this.nachteil = nachteil;
        this.keinVorteil = keinVorteil;
        this.keinNachteil = keinNachteil;
        this.sonderfertigkeiten = sonderfertigkeiten;
        this.keineSonderfertigkeiten = keineSonderfertigkeiten;
        this.talente = talente;
    }

    //Voraussetzung für Vor- und Nachteile
    public Voraussetzung(String rasse, Set<String> vorteil, Set<String> nachteil, Set<String> keinVorteil, Set<String> keinNachteil) {
        this.rasse = rasse;
        this.vorteil = vorteil;
        this.nachteil = nachteil;
        this.keinVorteil = keinVorteil;
        this.keinNachteil = keinNachteil;
    }




    public boolean fullfillVoraussetzung(Character c) {
        if (eigenschaften != null) {
            for (int i = 0; i < 8; i++) {
                if (eigenschaften[i] > c.getEigenschaft(i)) return false;
            }
        }
        if (rasse != null) {
            if (!(c.isRasse(rasse))) return false;
        }
        if (vorteil != null) {
            for (String v : vorteil) {
                if (!(c.hasVorteil(v))) return false;
            }
        }
        if (nachteil != null) {
            for (String n : nachteil) {
                if (!(c.hasNachteil(n))) return false;
            }
        }
        if (keinVorteil != null) {
            for (String v : keinVorteil) {
                if (c.hasVorteil(v)) return false;
            }
        }
        if (keinNachteil != null) {
            for (String n : keinNachteil) {
                if (c.hasNachteil(n)) return false;
            }
        }
        if (sonderfertigkeiten != null) {
            for (String sf : sonderfertigkeiten) {
                if (!(c.hasSonderfertigkeit(sf))) return false;
            }
        }
        if (keineSonderfertigkeiten != null) {
            for (String sf: keineSonderfertigkeiten) {
                if (c.hasSonderfertigkeit(sf)) return false;
            }
        }
        if (talente != null) {
            for (String t : talente.keySet()) {
                if (c.hasTalent(t)) {
                    int v = talente.get(t);
                    if (v > c.getTalent(t).getTalentwert()) return false;
                } else {
                    return false;
                }

            }
        }
        return true;
    }
}

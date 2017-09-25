package character.rasseundkultur;

import character.sonderfertigkeiten.ASonderfertigkeit;
import character.sprachen.Sprache;
import character.talente.ATalent;
import character.vorundnachteile.Nachteil;
import character.vorundnachteile.Vorteil;

import java.util.Set;

/**
 * Created by Hauke on 11.09.2017.
 */
public class Kultur implements Comparable<Kultur> {

    public String name;
    public int LePMod;
    public Set<Vorteil> automatischeVorteile;
    public Set<Nachteil> automatischeNachteile;
    public Set<ASonderfertigkeit> sonderfertigkeiten;
    public Set<Sprache> sprachen;
    public Set<ATalent> automatischeTalente;

    public String text;


    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Kultur k) {
        if (k == null) return 1;
        return this.getName().compareTo(k.getName());
    }

    @Override
    public boolean equals(Object k) {
        if (k == null) return false;
        if (k.getClass() != getClass()) return false;
        return ((Kultur) k).getName().equals(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

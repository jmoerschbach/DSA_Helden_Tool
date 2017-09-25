package character.rasseundkultur;

import character.talente.ATalent;
import character.vorundnachteile.Nachteil;
import character.vorundnachteile.Vorteil;

import java.util.Set;

/**
 * Created by Hauke on 10.09.2017.
 */
public class Rasse implements Comparable<Rasse> {

    public String name;
    public int LePMod;
    public int MRMod;
    public Set<Vorteil> automatischeVorteile;
    public Set<Nachteil> automatischeNachteile;
    public Set<ATalent> automatischeTalente;
    public int[] eigenschaftsMod;
    public String text;

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Rasse r) {
        if (r == null) return 1;
        return this.getName().compareTo(r.getName());
    }

    @Override
    public boolean equals(Object r) {
        if (r == null) return false;
        if (r.getClass() != getClass()) return false;
        return ((Rasse) r).getName().equals(name);
    }

    @Override
    public String toString() {
        return name;
    }
}

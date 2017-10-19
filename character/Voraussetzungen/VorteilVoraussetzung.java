package character.Voraussetzungen;

import character.rasseundkultur.Kultur;
import character.rasseundkultur.Rasse;
import character.vorundnachteile.Vorteil;
import character.Character;

/**
 * Created by Hauke on 17.10.2017.
 */
public class VorteilVoraussetzung implements IVoraussetzung {

    private Rasse rasse;
    private Kultur kultur;
    private Vorteil vorteil;

    public VorteilVoraussetzung(Rasse rasse, Kultur kultur, Vorteil vorteil) {
        this.rasse = rasse;
        this.kultur = kultur;
        this.vorteil = vorteil;
    }

    public boolean fullfillVoraussetzung(Character c) {
        if (rasse != null) {

        }
        return c.getRasse().equals(rasse) && c.getKultur().equals(kultur) && c.has(vorteil);
    }
}

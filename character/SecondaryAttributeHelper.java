package character;

public class SecondaryAttributeHelper {

    private int basis;
    private int mod;

    public SecondaryAttributeHelper() {
        this.basis = 0;
        this.mod = 0;
    }

    public void setBasis(int basis) {
        this.basis = basis;
    }

    public void increaseMod(int mod) {
        this.mod += mod;
    }

    public void decreaseMod(int mod) {
        this.mod -= mod;
    }

    public int getActual() {
        return basis + mod;
    }
}

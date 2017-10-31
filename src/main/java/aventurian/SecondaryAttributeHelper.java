package aventurian;

public class SecondaryAttributeHelper {

    private int basis;
    private int mod;

    public SecondaryAttributeHelper() {
        this.basis = 0;
        this.mod = 0;
    }

    public void setBasis(int basis) {
        if (basis < 0) throw new IllegalArgumentException("Basis cannot be less than zero!");
        this.basis = basis;
    }

    public void increaseMod(int mod) {
        if (mod < 0) throw new IllegalArgumentException("Input must not be less than zero!");
        this.mod += mod;
    }

    public void decreaseMod(int mod) {
        if (mod < 0) throw new IllegalArgumentException("Input must not be less than zero!");
        this.mod -= mod;
    }

    public int getActual() {
        return basis + mod;
    }
}

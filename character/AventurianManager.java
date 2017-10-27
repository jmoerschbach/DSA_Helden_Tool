package character;

import skills.Property;

public class AventurianManager {

    private Aventurian aventurian;

    private final int MAX_POINTS_IN_ADVANTAGES = 2500;
    private final int MAX_POINTS_OUT_DISADVANTAGES = 2500;
    private final int MAX_ATTRIBUTES_SUM = 101;
    private int pointsInAdvantages;
    private int pointsOutDisadvantages;

    public AventurianManager(Aventurian aventurian) {
        this.aventurian = aventurian;
        this.pointsInAdvantages = 0;
        this.pointsOutDisadvantages = 0;
    }

    public void addProperty(Property p) {
        int cost = p.getCost();
        if (canPay(cost) && p.isAllowed(aventurian)) {
            pay(cost);
            aventurian.addProperty(p);
            p.gain(aventurian);
            if (p.isAdvantage() && pointsInAdvantages + cost <= MAX_POINTS_IN_ADVANTAGES) {
                pointsInAdvantages += cost;
            } else if (p.isDisadvantage() && pointsOutDisadvantages + (cost * -1) <= MAX_POINTS_OUT_DISADVANTAGES) {
                pointsOutDisadvantages += cost * -1;
            }
        }
    }

    public void removeProperty(Property p) {
        int refund = p.getCost();
        if (aventurian.hasProperty(p)) {
            refund(refund);
            aventurian.removeProperty(p);
            p.lose(aventurian);
            if (p.isAdvantage()) {
                pointsInAdvantages -= refund;
            } else {
                pointsOutDisadvantages -= refund * -1;
            }
        }
    }

    private boolean canPay(int cost) {
        return aventurian.canPay(cost);
    }

    private void pay(int cost) {
        aventurian.pay(cost);
    }

    private void refund(int refund) {
        aventurian.refund(refund);
    }
}

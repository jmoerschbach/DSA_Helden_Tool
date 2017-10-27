package character;

import skills.Property;

import static steigerrechner.Steigerrechner.getCost;

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

    public void increasePrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE a) {
        int cost = getCost(aventurian.getPrimaryAttribute(a), aventurian.getPrimaryAttribute(a)+1, 8);
        if (canPay(cost) && aventurian.getSumOfPrimaryAttributes() < MAX_ATTRIBUTES_SUM && aventurian.getPrimaryAttribute(a) < aventurian.getMaxOfPrimaryAttribute(a)) {
            pay(cost);
            aventurian.increasePrimaryAttribute(a);
        }
    }

    public void decreasePrimaryAttribut(PrimaryAttributes.PRIMARY_ATTRIBUTE a) {
        int cost = getCost(aventurian.getPrimaryAttribute(a)-1, aventurian.getPrimaryAttribute(a), 8);
        if (aventurian.getPrimaryAttribute(a) > 8) {
            refund(cost);
            aventurian.decrasePrimaryAttribute(a);
        }
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

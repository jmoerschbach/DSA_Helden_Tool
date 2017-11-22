package aventurian;

import static aventurian.LevelCostCalculator.COLUMN.H;

import skills.BadProperty;
import skills.Language;
import skills.Property;

public class AventurianManager {

	private final Aventurian aventurian;
	private final LevelCostCalculator calculator;

	static final int MAX_BAD_PROPERTIES_SUM = 25;
	static final int MAX_POINTS_IN_ADVANTAGES = 2500;
	static final int MAX_POINTS_OUT_DISADVANTAGES = 2500;
	static final int MAX_ATTRIBUTES_SUM = 101;
	private int pointsInAdvantages;
	private int pointsOutDisadvantages;

	public AventurianManager(Aventurian aventurian) {
		this.aventurian = aventurian;
		this.pointsInAdvantages = 0;
		this.pointsOutDisadvantages = 0;
		this.calculator = new LevelCostCalculator();
	}

	public void increasePrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE a) {
		final int cost = calculator.getCost(aventurian.getPrimaryAttribute(a), aventurian.getPrimaryAttribute(a) + 1,
				H);
		if (canPay(cost) && aventurian.getSumOfPrimaryAttributes() < MAX_ATTRIBUTES_SUM
				&& aventurian.getPrimaryAttribute(a) < aventurian.getMaxOfPrimaryAttribute(a)) {
			pay(cost);
			aventurian.increasePrimaryAttribute(a);
		}
	}

	public void decreasePrimaryAttribut(PrimaryAttributes.PRIMARY_ATTRIBUTE a) {
		final int cost = calculator.getRefund(aventurian.getPrimaryAttribute(a), aventurian.getPrimaryAttribute(a) - 1,
				H);
		if (aventurian.getPrimaryAttribute(a) > PrimaryAttributes.MIN) {
			refund(cost);
			aventurian.decrasePrimaryAttribute(a);
		}
	}

	public void addProperty(Property p) {
		if (aventurian.hasSkill(p))
			throw new IllegalStateException("has already skill " + p.getName());
		final int cost = p.getCost();
		if (p.isAllowed(aventurian)) {
			if (p.isAdvantage() && canPay(cost)
					&& aventurian.getPointsInAdvantages() + cost <= MAX_POINTS_IN_ADVANTAGES) {
				pay(cost);
				aventurian.add(p);
				p.gain(aventurian);
			} else if (p.isDisadvantage()
					&& aventurian.getPointsOutDisadvantages() + cost <= MAX_POINTS_OUT_DISADVANTAGES) {
				refund(cost);
				aventurian.add(p);
				p.gain(aventurian);
			}
		}
	}

	public void addBadProperty(BadProperty p) {
		if (aventurian.hasSkill(p))
			throw new IllegalStateException("has already skill " + p.getName());
		final int cost = p.getCost();
		if (aventurian.getBadPropertySum() + p.getLevel() <= MAX_BAD_PROPERTIES_SUM && p.isAllowed(aventurian)
				&& aventurian.getPointsOutDisadvantages() + (cost * p.getLevel()) <= MAX_POINTS_OUT_DISADVANTAGES) {
			refund(cost * p.getLevel());
			aventurian.add(p);
			p.gain(aventurian);
		}
	}

	public void removeBadProperty(BadProperty p) {
		if (!aventurian.hasSkill(p))
			throw new IllegalStateException("cannot remove skill " + p.getName());
		while (p.isDecreasable()) {
			decreaseBadProperty(p);
		}
		refund(p.getCost() * p.getLevel());
		aventurian.remove(p);
		p.lose(aventurian);
	}

	public void increaseBadProperty(BadProperty p) {
		if (p.isIncreasable() && aventurian.getBadPropertySum() + 1 <= MAX_BAD_PROPERTIES_SUM) {
			pay(p.getCost());
			p.increase();
		}
	}

	public void decreaseBadProperty(BadProperty p) {
		if (!p.isDecreasable())
			throw new IllegalStateException("cannot further decrease level of " + p.getName());
		if (!aventurian.hasSkill(p))
			throw new IllegalStateException("cannot decrease skill which is not owned: " + p.getName());
		refund(p.getCost());
		p.decrease();
	}

	public void removeProperty(Property p) {
		final int refund = p.getCost();
		if (aventurian.hasSkill(p)) {
			refund(refund);
			aventurian.remove(p);
			p.lose(aventurian);
			if (p.isAdvantage()) {
				pointsInAdvantages -= refund;
			} else {
				pointsOutDisadvantages -= refund * -1;
			}
		}
	}

	public void increaseLanguage(Language l) {
		if (!aventurian.hasSkill(l))
			throw new IllegalStateException("cannot increase skill " + l.getName());
		final int cost = l.getUpgradeCost();
		if (canPay(cost) && l.isAllowed(aventurian) && l.isIncreasable()) {
			pay(cost);
			l.increase();
		}
	}

	public void decreaseLanguage(Language l) {
		if (!l.isDecreasable())
			throw new IllegalStateException("cannot further decrease level of " + l.getName());
		if (!aventurian.hasSkill(l))
			throw new IllegalStateException("cannot decrease skill which is not owned: " + l.getName());
		final int refund = l.getDowngradeRefund();
		refund(refund);
		l.decrease();
	}

	public void addLanguage(Language l) {
		if(aventurian.hasSkill(l))
			throw new IllegalStateException("has already skill "+l.getName());
		final int cost = l.getLearningCost();
		if (canPay(cost) && l.isAllowed(aventurian)) {
			pay(cost);
			aventurian.add(l);
			l.gain(aventurian);
		}
	}

	public void removeLanguage(Language l) {
		if (!aventurian.hasSkill(l))
			throw new IllegalStateException("cannot remove skill " + l.getName());
		while (l.isDecreasable()) {
			decreaseLanguage(l);
		}
		refund(l.getLearningCost());
		aventurian.remove(l);
		l.lose(aventurian);
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

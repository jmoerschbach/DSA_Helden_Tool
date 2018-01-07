package aventurian;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.stream.Stream;

import skills.BadProperty;
import skills.Language;
import skills.Property;
import skills.Skill;

public class Aventurian extends Observable {

	private String name;
	private final PrimaryAttributes primaryAttributes;
	private final SecondaryAttributes secondaryAttributes;
	private int adventurePoints;

	private final List<Property> properties;
	private final List<BadProperty> badProperties;
	private final List<Language> languages;

	public Aventurian(String name, int ap) {
		this(name, ap, new PrimaryAttributes(), new SecondaryAttributes());
	}

	Aventurian(String name, int ap, PrimaryAttributes primary, SecondaryAttributes secondary) {
		this.name = name;
		this.primaryAttributes = primary;
		this.secondaryAttributes = secondary;
		this.adventurePoints = ap;
		this.properties = new ArrayList<>();
		this.badProperties = new ArrayList<>();
		this.languages = new ArrayList<>();
	}

	Aventurian(int ap) {
		this("", ap, new PrimaryAttributes(), new SecondaryAttributes());
	}

	public int getAdventurePoints() {
		return adventurePoints;
	}

	void pay(int cost) {
		if (canPay(cost) && cost >= 0) {
			adventurePoints -= cost;
			notifyObserversAndSetChanged();
		} else
			throw new IllegalArgumentException("Cannot pay: " + cost);
	}

	void refund(int refund) {
		if (refund >= 0) {
			adventurePoints += refund;
			notifyObserversAndSetChanged();
		} else
			throw new IllegalArgumentException("Cannot refund negative amound: " + refund);
	}

	boolean canPay(int cost) {
		return cost <= adventurePoints;
	}

	public void setName(String name) {
		this.name = name;
		notifyObserversAndSetChanged();
	}

	void add(Property p) {
		properties.add(p);
		notifyObserversAndSetChanged();
	}

	void remove(Property p) {
		properties.remove(p);
		notifyObserversAndSetChanged();
	}

	void add(BadProperty p) {
		badProperties.add(p);
		notifyObserversAndSetChanged();
	}

	void remove(BadProperty p) {
		badProperties.remove(p);
		notifyObserversAndSetChanged();
	}

	int getBadPropertySum() {
		return badProperties.stream().mapToInt(BadProperty::getLevel).sum();
	}

	int getPointsInAdvantages() {
		return properties.stream().filter((p) -> p.isAdvantage()).mapToInt(Property::getCost).sum();
	}

	int getPointsOutDisadvantages() {
		return properties.stream().filter((p) -> p.isDisadvantage()).mapToInt(Property::getCost).sum()
				+ badProperties.stream().mapToInt((p) -> p.getCost() * p.getLevel()).sum();
	}

	void add(Language l) {
		languages.add(l);
		notifyObserversAndSetChanged();
	}

	void remove(Language l) {
		languages.remove(l);
		notifyObserversAndSetChanged();
	}

	boolean hasSkill(Skill skill) {
		return Stream.of(badProperties, properties, languages).flatMap(Collection::stream)
				.anyMatch((s) -> s.equals(skill));
	}

	int getSumOfPrimaryAttributes() {
		return primaryAttributes.getSum();
	}

	public int getPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE a) {
		return primaryAttributes.getPrimaryAttribute(a);
	}

	public int getMaxOfPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE a) {
		return primaryAttributes.getMaximumOfPrimaryAttribute(a);
	}

	public void increasePrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE attribute) {
		primaryAttributes.increase(attribute);
		secondaryAttributes.updateValues(primaryAttributes);
		notifyObserversAndSetChanged();
	}

	private void notifyObserversAndSetChanged() {
		setChanged();
		notifyObservers();
	}

	public void decrasePrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE attribute) {
		primaryAttributes.decrease(attribute);
		secondaryAttributes.updateValues(primaryAttributes);
		notifyObserversAndSetChanged();
	}

	void increaseMaximumOfPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE attribute) {
		primaryAttributes.increaseMaximum(attribute);
		notifyObserversAndSetChanged();
	}

	void decreaseMaximumOfPrimaryAttribute(PrimaryAttributes.PRIMARY_ATTRIBUTE attribute) {
		primaryAttributes.decreaseMaximum(attribute);
		notifyObserversAndSetChanged();
	}

	public String getName() {
		return name;
	}

	public List<Language> getLanguages() {
		return new ArrayList<>(languages);
	}
}

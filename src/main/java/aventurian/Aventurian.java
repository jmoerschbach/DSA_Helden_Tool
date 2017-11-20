package aventurian;

import skills.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Aventurian {

	private String name;
	private PrimaryAttributes primaryAttributes;
	private SecondaryAttributes secondaryAttributes;
	private int adventurePoints;

	private final List<Property> properties;
	private final List<BadProperty> badProperties;
	private final List<Language> languages;

	public Aventurian() {
		this("", 16500, new PrimaryAttributes(), new SecondaryAttributes());
	}

	public Aventurian(String name, int ap) {
		this(name, ap, new PrimaryAttributes(), new SecondaryAttributes());
	}

	Aventurian(String name, int ap, PrimaryAttributes primary,
			SecondaryAttributes secondary) {
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
		if (canPay(cost) && cost >= 0)
			adventurePoints -= cost;
		else
			throw new IllegalArgumentException("Cannot pay: " + cost);
	}

	void refund(int refund) {
		if (refund >= 0)
			adventurePoints += refund;
		else
			throw new IllegalArgumentException(
					"Cannot refund negative amound: " + refund);
	}

	boolean canPay(int cost) {
		return cost <= adventurePoints;
	}

	public void setName(String name) {
		this.name = name;
	}

	void add(Property p) {
		properties.add(p);
	}

	void remove(Property p) {
		properties.remove(p);
	}

	void add(BadProperty p) {
		badProperties.add(p);
	}

	void remove(BadProperty p) {
		badProperties.remove(p);
	}

	int getBadPropertySum() {
		return badProperties.stream().mapToInt(BadProperty::getLevel).sum();
	}
	
	int getPointsInAdvantages() {
		return properties.stream().filter((p)->p.isAdvantage()).mapToInt(Property::getCost).sum();
	}
	
	int getPointsOutDisadvantages() {
		return properties.stream().filter((p)->p.isDisadvantage()).mapToInt(Property::getCost).sum() + badProperties.stream().mapToInt((p)->p.getCost()*p.getLevel()).sum();
	}

	void add(Language l) {
		languages.add(l);
	}

	void remove(Language l) {
		languages.remove(l);
	}

	boolean hasSkill(Skill skill) {
		return Stream.of(badProperties, properties, languages)
				.flatMap(Collection::stream).anyMatch((s) -> s.equals(skill));
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

	public void increasePrimaryAttribute(
			PrimaryAttributes.PRIMARY_ATTRIBUTE attribute) {
		primaryAttributes.increase(attribute);
		secondaryAttributes.updateValues(primaryAttributes);
	}

	public void decrasePrimaryAttribute(
			PrimaryAttributes.PRIMARY_ATTRIBUTE attribute) {
		primaryAttributes.decrease(attribute);
		secondaryAttributes.updateValues(primaryAttributes);
	}

	void increaseMaximumOfPrimaryAttribute(
			PrimaryAttributes.PRIMARY_ATTRIBUTE attribute) {
		primaryAttributes.increaseMaximum(attribute);
	}

	void decreaseMaximumOfPrimaryAttribute(
			PrimaryAttributes.PRIMARY_ATTRIBUTE attribute) {
		primaryAttributes.decreaseMaximum(attribute);
	}
}

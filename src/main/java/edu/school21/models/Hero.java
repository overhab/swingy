package edu.school21.models;

import edu.school21.app.StaticVariables;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.Frequency;

import java.util.ArrayList;
import java.util.List;

public abstract class Hero {
    protected List<String> artifacts = new ArrayList<>(3);
    @Size(min = 3, max = 16, message = "Hero name must be 3 to 16 characters long!")
    protected String name;

    @NotNull(message = StaticVariables.HERO_TYPE)
    protected HeroClass heroClass;

    protected int level;
    protected int experience;
    protected int attack;
    protected int defence;
    protected int hitPoints;

    public void setName(String name) {
        this.name = name;
    }

    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public String getName() {
        return name;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void gainExp(int exp) {
        this.experience += exp;
    }

    public int levelUp() {
        setLevel(getLevel() + 1);
        setHitPoints(getHitPoints() + 15);
        setAttack(getAttack() + 3);
        setDefence(getDefence() + 2);
        return getLevel();
    }
}

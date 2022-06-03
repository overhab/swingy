package edu.school21.models;

public class Warrior extends Hero {
    public Warrior(String name) {
        this.name = name;
        this.heroClass = HeroClass.WARRIOR;
        this.experience = 0;
        this.level = 1;
        this.hitPoints = 120;
        this.attack = 10;
        this.defence = 10;
    }

    public Warrior(String name, int exp, int level, int hp, int attack, int defence) {
        this.name = name;
        this.heroClass = HeroClass.WARRIOR;
        this.experience = exp;
        this.level = level;
        this.hitPoints = hp;
        this.attack = attack;
        this.defence = defence;
    }
}

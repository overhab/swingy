package edu.school21.models;

public class Archer extends Hero {
    public Archer(String name) {
        this.name = name;
        this.heroClass = HeroClass.ARCHER;
        this.experience = 0;
        this.level = 1;
        this.hitPoints = 100;
        this.attack = 12;
        this.defence = 8;
    }

    public Archer(String name, int exp, int level, int hp, int attack, int defence) {
        this.name = name;
        this.heroClass = HeroClass.ARCHER;
        this.experience = exp;
        this.level = level;
        this.hitPoints = hp;
        this.attack = attack;
        this.defence = defence;
    }
}

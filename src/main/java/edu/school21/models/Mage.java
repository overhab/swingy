package edu.school21.models;


import jakarta.persistence.*;

@Entity
@DiscriminatorValue("1")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Mage extends Hero {
    public Mage(String name) {
        this.name = name;
        this.heroClass = HeroClass.MAGE;
        this.experience = 0;
        this.level = 1;
        this.hitPoints = 80;
        this.attack = 15;
        this.defence = 5;
    }

    public Mage(String name, int exp, int level, int hp, int attack, int defence) {
        this.name = name;
        this.heroClass = HeroClass.MAGE;
        this.experience = exp;
        this.level = level;
        this.hitPoints = hp;
        this.attack = attack;
        this.defence = defence;
    }

    public Mage() {
    }
}

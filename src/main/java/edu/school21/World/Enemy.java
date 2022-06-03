package edu.school21.World;

public class Enemy {

    private int attack;
    private int defence;
    private int hitPoints;

    public Enemy(int attack, int defence, int hitPoints) {
        this.attack = attack;
        this.defence = defence;
        this.hitPoints = hitPoints;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }
}

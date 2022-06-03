package edu.school21.World;

import edu.school21.app.StaticVariables;
import edu.school21.models.Hero;

import java.util.Random;

public class Player {
    private final Hero hero;
    private final Random random = new Random();
    private int levelUp;
    private boolean nextLevel;

    public Player(Hero hero) {
        this.hero = hero;
        levelUp = (int) ((hero.getLevel() * 1000) + (Math.pow(hero.getLevel() - 1, 2.0) * 450));
        nextLevel = false;
    }

    private void gainExp(int exp) {
        System.out.println("You gained " + exp + " experience");
        hero.gainExp(exp);
        if (hero.getExperience() >= levelUp) {
            nextLevel = true;
            System.out.println("LEVEL UP! ! ! New level - " + (hero.getLevel() + 1));
            hero.levelUp();
            levelUp = (int) ((hero.getLevel() * 1000) + (Math.pow(hero.getLevel() - 1, 2.0) * 450));
        }
    }

    public int getLevelUp() {
        return levelUp;
    }

    public String getHeroName() {
        return hero.getName();
    }

    public int getHeroLevel() {
        return hero.getLevel();
    }

    public int getHeroExp() {
        return hero.getExperience();
    }

    public boolean isNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(boolean nextLevel) {
        this.nextLevel = nextLevel;
    }

    public boolean encounter(int type) {

        if (type == 0) {
            return true;
        }

        int eAttack = hero.getLevel() * Math.max(random.nextInt(5 * type), 2 * type);
        int eDefence = hero.getLevel() * Math.max(random.nextInt(4 * type), 2 * type);
        int eHitPoints = hero.getLevel() * Math.max(random.nextInt(20 * type), 12);

        Enemy enemy = new Enemy(eAttack, eDefence, eHitPoints);
        if (fightSimulation(hero, enemy)) {
            System.out.println(StaticVariables.BORDER + "\nYou won the fight!");
            gainExp(eHitPoints * (14 + type));
            System.out.println("Player hp: " + hero.getHitPoints() + " EXP: [" + hero.getExperience() + "/" + levelUp + "]");
            return true;
        }
        return false;
    }

    private boolean fightSimulation(Hero hero, Enemy enemy) {
        System.out.println("FIGHT VS HP: " + enemy.getHitPoints() + " A: " + enemy.getAttack() + " D: " + enemy.getDefence());
        if (random.nextInt(4) != 0) {
            while (enemy.getHitPoints() > 0) {
                enemy.setHitPoints(enemy.getHitPoints() - Math.max((hero.getAttack() - enemy.getDefence()), 1));
                if (enemy.getHitPoints() <= 0) {
                    break ;
                }
                hero.setHitPoints(hero.getHitPoints() - Math.max((enemy.getAttack() - hero.getDefence()), 1));
                if (hero.getHitPoints() <= 0) {
                    return false;
                }
            }
            return true;
        }
        while (hero.getHitPoints() > 0) {
            hero.setHitPoints(hero.getHitPoints() - Math.max((enemy.getAttack() - hero.getDefence()), 1));
            if (hero.getHitPoints() <= 0) {
                break ;
            }
            enemy.setHitPoints(enemy.getHitPoints() - Math.max((hero.getAttack() - enemy.getDefence()), 1));
            if (enemy.getHitPoints() <= 0) {
                return true;
            }
        }
        return false;
    }

}

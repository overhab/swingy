package edu.school21.gui;

import edu.school21.app.StaticVariables;
import edu.school21.models.Hero;

import java.util.List;

public class ConsoleView {
    private static boolean FLAG = true;

    public ConsoleView() {
    }

    public void startScreen() {
        System.out.println(StaticVariables.BORDER);
        if (FLAG) {
            System.out.println(StaticVariables.WELCOME_MESSAGE);
        }
        System.out.println(StaticVariables.MAIN_MENU);
        System.out.println(StaticVariables.BORDER);
        FLAG = false;
    }

    public void newHero() {
        System.out.println(StaticVariables.BORDER);
        System.out.println(StaticVariables.NEW_HERO);
    }

    public void gamePlay(String name) {
        System.out.println(StaticVariables.BORDER);
        System.out.println("WELCOME TO THE GAME " + name);
    }

    public void display(String text) {
        System.out.println(text);
    }

    public void chooseMove() {
        System.out.println(StaticVariables.BORDER + "\n" + StaticVariables.CHOOSE_MOVE);
    }

    public void wrongInput() {
        System.err.println(StaticVariables.WRONG_INPUT);
    }

    public void displayHeroes(List<Hero> heroes) {
        for (int i = 0; i < heroes.size(); i++) {
            Hero hero = heroes.get(i);
            System.out.println((i + 1) + hero.getName()
                    + " LvL: " + hero.getLevel()
                    + " Attack: " + hero.getAttack()
                    + " Defence: " + hero.getDefence()
                    + " HitPoints: " + hero.getHitPoints());
        }
    }
}

package edu.school21.gui;

import edu.school21.app.StaticVariables;

public class ViewManager {

    private static boolean MODE = true; // true - console, false - GUI
    private final ConsoleView consoleView;
    private final Swingy swingy;

    public ViewManager(ConsoleView consoleView, Swingy swingy) {
        this.consoleView = consoleView;
        this.swingy = swingy;
    }

    public void changeMode(boolean mode) {
        MODE = mode;
    }

    public void startScreen() {
        if (MODE) {
            consoleView.startScreen();
        } else {
            swingy.displayMain(StaticVariables.WELCOME_MESSAGE);
            swingy.displayOptions(StaticVariables.MAIN_MENU);
        }
    }

    public void newHero() {
        if (MODE) {
            consoleView.newHero();
            consoleView.display("Enter name:");
        } else {
            swingy.refresh();
            swingy.displayMain(StaticVariables.NEW_HERO);
            swingy.displayOptions("Enter name");
        }
    }

    public void display(String text) {
        if (MODE) {
            consoleView.display(text);
        } else {
            swingy.refresh();
            swingy.displayOptions(text);
        }
    }

    public void wrongInput() {
        if (MODE) {
            consoleView.wrongInput();
        } else {
            swingy.wrongInput();
        }
    }

    public void choseMove() {
        if (MODE) {
            consoleView.choseMove();
        } else {
            swingy.displayMain("CHOSE YOUR MOVE");
            swingy.displayOptions("North\nSouth\nWest\nEast");
        }
    }

    public void displayEnd(String text) {
        if (MODE) {
            consoleView.display(text);
        } else {
            /*swingy.changeMain();*/
            swingy.displayMain(text);
        }
    }

    public void gamePlay(String heroName, int level, int nextLevel, int exp) {
        if (MODE) {
            consoleView.gamePlay(heroName);
        } else {
            swingy.setHero(heroName, level, nextLevel, exp);
        }
    }

    public void afterEncounter(String heroName, int level, int nextLevel, int exp) {
        if (MODE) {

        } else {
            swingy.setHero(heroName, level, nextLevel, exp);
        }
    }

    public void levelUp() {
        swingy.levelUp();
    }

    public void displayMove(String text) {
        if (MODE) {
            consoleView.display("You moved " + text);
        } else {
            swingy.displayMove("You moved " + text);
        }
    }

    public void refresh() {
        if (!MODE) {
            swingy.refresh();
        }
    }
}

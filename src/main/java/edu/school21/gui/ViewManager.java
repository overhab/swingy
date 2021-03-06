package edu.school21.gui;

import edu.school21.World.Player;
import edu.school21.app.StaticVariables;
import edu.school21.models.Hero;

import java.util.List;

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

    public void displayLevel(String text, Player player) {
        swingy.refresh();
        swingy.displayOptions(text);
        swingy.showInventory(player.getArtifacts());
    }

    public void wrongInput() {
        if (MODE) {
            consoleView.wrongInput();
        } else {
            swingy.wrongInput();
        }
    }

    public void chooseHero(List<Hero> heroes) {
        if (MODE) {
            consoleView.display("CHOOSE YOUR HERO");
            consoleView.displayHeroes(heroes);
        } else {
            swingy.refresh();
            swingy.displayMain("CHOOSE YOUR HERO");
            swingy.displayHeroes(heroes);
        }
    }

    public void chooseMove(Player player) {
        if (MODE) {
            consoleView.chooseMove();
        } else {
            swingy.refresh();
            swingy.displayMain("CHOOSE YOUR MOVE");
            swingy.displayOptions("North\nSouth\nWest\nEast");
            swingy.setHero(player.getHeroName(),
                    player.getHeroLevel(),
                    player.getLevelUp(),
                    player.getHeroExp(),
                    player.getHitPoints(),
                    player.getAttack(),
                    player.getDefence());
            swingy.showInventory(player.getArtifacts());
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

    public void gamePlay(Player player) {
        if (MODE) {
            consoleView.gamePlay(player.getHeroName());
        } else {
            swingy.setHero(player.getHeroName(),
                    player.getHeroLevel(),
                    player.getLevelUp(),
                    player.getHeroExp(),
                    player.getHitPoints(),
                    player.getAttack(),
                    player.getDefence());
            swingy.showInventory(player.getArtifacts());
        }
    }

    public void afterEncounter(Player player, int drop, String aName) {
        if (MODE) {
            consoleView.display("You won the fight!");
            consoleView.display("Gained " + player.getExpGained() + " experience");
        } else {
            swingy.refresh();
            swingy.displayMain("You won the fight!");
            swingy.showInventory(player.getArtifacts());
            if (drop == 2) {
                swingy.message("Monster has loot: " + aName);
                swingy.displayOptions("Take it" +
                        "\nLeave it");
            }
            swingy.setHero(player.getHeroName(),
                    player.getHeroLevel(),
                    player.getLevelUp(),
                    player.getHeroExp(),
                    player.getHitPoints(),
                    player.getAttack(),
                    player.getDefence());
        }
    }

    public void encounter(String enemy, Player player) {
        if (MODE) {
            consoleView.display(StaticVariables.MAIN_ECNOUNTER + enemy);
            consoleView.display(StaticVariables.ENCOUNTER_OPTIONS);
        } else {
            swingy.refresh();
            swingy.displayMain(StaticVariables.MAIN_ECNOUNTER + enemy);
            swingy.displayOptions(StaticVariables.ENCOUNTER_OPTIONS);
            swingy.showInventory(player.getArtifacts());
        }
    }

    public void displayWarning(String text) {
        if (MODE) {
            consoleView.display(text);
        } else {
            swingy.warning(text);
        }
    }

    public void levelUp() {
        if (MODE) {
            consoleView.display("LEVEL UP!");
        } else {
            swingy.levelUp();
        }
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

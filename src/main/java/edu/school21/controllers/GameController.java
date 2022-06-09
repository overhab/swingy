package edu.school21.controllers;

import edu.school21.World.Player;
import edu.school21.World.WorldMap;
import edu.school21.app.InputValidation;
import edu.school21.app.StaticVariables;
import edu.school21.gui.ConsoleView;
import edu.school21.gui.Swingy;
import edu.school21.gui.ViewManager;
import edu.school21.models.*;
import edu.school21.services.HeroService;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GameController {

    private final InputValidation inputValidation;
    private final HeroService heroService;
    private final Random random = new Random();
    private ViewManager viewManager;
    private UserInput userInput;
    private String input = "";
    private ConsoleView consoleView;
    private Swingy swingy;
    private Player player;
    private WorldMap worldMap;

    public GameController() {
        inputValidation = new InputValidation();
        heroService = new HeroService();
    }

    public void start(boolean mode) {
        consoleView = new ConsoleView();
        swingy = new Swingy();
        userInput = new UserInput();

        userInput.setMODE(mode);
        if (!mode) {
            swingy.start();
            userInput.setSwing(swingy.getjPanel(), swingy.getLayout());
        }
        viewManager = new ViewManager(consoleView, swingy);
        viewManager.changeMode(mode);
        startGame();
        exit();
    }

    private void startGame() {

        while (true) {
            viewManager.startScreen();
            input = userInput.getInput();
            System.out.println(input);

            if (input.equals("Create new hero")) {
                viewManager.newHero();
                newGame();
                heroService.save(player.getHero());
                viewManager.gamePlay(player);
                startTheGame();
                return;
            } else if (input.equals("Select hero")) {

                List<Hero> heroes = heroService.findAll();

                if (heroes.isEmpty()) {
                    viewManager.newHero();
                    viewManager.displayWarning("No heroes");
                    newGame();
                    heroService.save(player.getHero());
                    viewManager.gamePlay(player);
                    startTheGame();
                    return;
                }

                viewManager.chooseHero(heroes);

                Hero hero;

                while (true) {
                    input = userInput.getInput();
                    hero = heroService.findByName(input);
                    if (hero == null) {
                        viewManager.displayWarning("Hero with this name doesn't exist");
                    } else {
                        break ;
                    }
                }

                player = new Player(hero);
                viewManager.gamePlay(player);
                startTheGame();

                return;
            } else if (input.equals("Quit")) {
                exit();
            }
            viewManager.wrongInput();
        }

    }

    private void startGUI() {
        String input;

        swingy.displayMain(StaticVariables.WELCOME_MESSAGE);
        swingy.displayOptions(StaticVariables.MAIN_MENU);
        input = userInput.getInput();
    }

    public void newGame() {
        String classType;
        Hero hero;

        while (true) {
            input = userInput.getInput();
            if (heroService.findByName(input) != null) {
                viewManager.displayWarning("Hero with this name already exists");
            } else {
                break ;
            }
        }

        viewManager.display(StaticVariables.HERO_TYPE);

        label:
        while (true) {
            classType = userInput.getInput().toUpperCase(Locale.ROOT);
            switch (classType) {
                case "MAGE":
                    hero = new Mage(input);
                    break label;
                case "WARRIOR":
                    hero = new Warrior(input);
                    break label;
                case "ARCHER":
                    hero = new Archer(input);
                    break label;
                default:
                    viewManager.wrongInput();
                    break;
            }
        }

        if (inputValidation.HeroValidation(hero)) {
            player = new Player(hero);
            System.out.println("Player created: " + player.getLevelUp());
        } else {
            newGame();
        }
    }

    private void startTheGame() {
        startLevel();
    }

    private void startLevel() {
        int encounter;
        makeMap();
        worldMap.printMap();

        viewManager.refresh();

        try {
            while (true) {
                viewManager.chooseMove();
                input = userInput.getInput().toUpperCase(Locale.ROOT);
                if (input.equals("NORTH") || input.equals("SOUTH") || input.equals("EAST") || input.equals("WEST")) {
                    viewManager.displayMove(input.toLowerCase(Locale.ROOT));
                    encounter = worldMap.movePlayer(input);

                    if (encounter > 0) {
                        viewManager.encounter(encounter == 1 ? "GOBLIN" : "LARGE OGRE");
                        input = userInput.getInput().toUpperCase(Locale.ROOT);
                        if (input.equals("FIGHT") || (input.equals("RUN") && random.nextInt(1000) > (200))) {

                            if (input.equals("RUN")) {
                                viewManager.display("You can't run!");
                            }

                            if (!player.encounter(encounter)) {
                                viewManager.displayEnd("YOU DIED! ! !");
                                return ;
                            }

                            heroService.update(player.getHero());
                            viewManager.afterEncounter(player);

                            if (player.isNextLevel()) {
                                player.setNextLevel(false);
                                viewManager.levelUp();
                            }

                        }
                    }

                    worldMap.printMap();
                } else if (input.equals("QUIT")) {
                    System.out.println("QUIT.");
                    break ;
                } else {
                    viewManager.wrongInput();
                }
            }
        } catch (IndexOutOfBoundsException exception) {
            heroService.update(player.getHero());
            viewManager.displayEnd("YOU WIN! ! !");
        }
    }

    public void makeMap() {
        int mapSize = (player.getHeroLevel() - 1) * 5 + 10 - 1;
        worldMap = new WorldMap(mapSize);
        worldMap.initMap(player.getHeroLevel());
    }

    public void exit() {
        System.out.println("exit!");
        swingy.exit();
        userInput.close();
    }
}
